package com.qcloud.test;

import java.util.TreeMap;

import org.junit.Test;

import com.qcloud.WenzhiUtil;
import com.qcloud.Utilities.Json.JSONObject;

public class WenzhiUtilTest {


    /**
     * 分词&命名实体识别 提供智能分词、词性标注、命名实体识别功能
     */
    @Test
    public void testLexicalAnalysis() {
        TreeMap<String, Object> params = new TreeMap<String, Object>();
        params.put("offset", 0);
        params.put("limit", 3);
        /*
         * 待词法分析的文本
         */
        params.put("text", "我爱洗澡");
        /*
         * text的编码(0x00200000=utf-8) 目前文智统一输入为utf-8
         */
        params.put("code", 123);
        /*
         * 取值0或1，默认为0。
         * 
         * 0为基础粒度版分词，倾向于将句子切分的更细， 在搜索场景使用为佳。
         * 
         * 1为混合粒度版分词，倾向于保留更多基本短语不被切分开。
         */
        // params.put("type", "123");

        JSONObject jsonObject = WenzhiUtil.wenzhiInit(params, "LexicalAnalysis");
        System.out.println("分词\t" + jsonObject);
    }

    /**
     * 情感分析 分析信息中情感的正向，负向及中性
     * 
     * positive 正面情感概率
     * 
     * negative 负面情感概率
     */
    @Test
    public void testTextSentiment() {
        TreeMap<String, Object> params = new TreeMap<String, Object>();
        params.put("offset", 0);
        params.put("limit", 3);
        /*
         * 待分析的文本（只能为utf8编码）
         */
        params.put("content", "iphone16g不错，就是内存小了点");
        /*
         * （可选参数，默认为4） 1：电商；2：APP；3：美食；4：酒店和其他。
         */
        params.put("type", 1);
        JSONObject jsonObject = WenzhiUtil.wenzhiInit(params, "TextSentiment");
        System.out.println("情感分析\t" + jsonObject);
    }

    /**
     * 敏感信息识别 识别信息的色情、政治敏感程度
     * 
     * sensitive 敏感的概率
     * 
     * nonsensitive 不敏感的概率
     */
    @Test
    public void testTextSensitivity() {
        TreeMap<String, Object> params = new TreeMap<String, Object>();
        params.put("offset", 0);
        params.put("limit", 3);
        /*
         * 待分析的文本（只能为utf8编码）
         */
        params.put("content", "六四事件是一个历史问题");
        /*
         * 区分敏感词类型:1表示色情，2表示政治
         */
        params.put("type", 2);
        JSONObject jsonObject = WenzhiUtil.wenzhiInit(params, "TextSensitivity");
        System.out.println("敏感信息识别\t" + jsonObject);
    }

    /**
     * 关键词提取 为用户实现诸如新闻内容，评论等的关键词提取
     */
    @Test
    public void testTextKeywords() {
        TreeMap<String, Object> params = new TreeMap<String, Object>();
        params.put("offset", 0);
        params.put("limit", 3);
        /*
         * 新闻标题
         */
        params.put("title", "Dior新款，秋冬新款娃娃款甜美圆领配毛领毛呢大衣外套、码数：SM、P330");
        /*
         * channel
         */
        // params.put("channel", "123");
        /*
         * 新闻正文
         */
        params.put("content", "Dior新款，秋冬新款娃娃款甜美圆领配毛领毛呢大衣外套、码数：SM、P330");

        JSONObject jsonObject = WenzhiUtil.wenzhiInit(params, "TextKeywords");
        System.out.println("关键词提取\t" + jsonObject);
    }

    /**
     * 文本分类 提供自动文本分类服务，目前能识别90多个类别
     */
    @Test
    public void testTextClassify() {
        TreeMap<String, Object> params = new TreeMap<String, Object>();
        params.put("offset", 0);
        params.put("limit", 3);
        /*
         * 文章标题，编码格式utf8
         */
        // params.put("title", "");
        /*
         * 正文内容，编码格式utf8
         */
        params.put("content", "腾讯入股京东");
        /*
         * 二级导航，编码格式utf8
         */
        // params.put("secd_nav", "");
        /*
         * 文章对应的url
         */
        // params.put("url", "");
        JSONObject jsonObject = WenzhiUtil.wenzhiInit(params, "TextClassify");
        System.out.println("文本分类\t" + jsonObject);
    }

    /**
     * 句法分析 分析出句子中词与词之间的关系
     */
    @Test
    public void testTextDependency() {
        TreeMap<String, Object> params = new TreeMap<String, Object>();
        params.put("offset", 0);
        params.put("limit", 3);
        /*
         * 待分析的文本（只能为utf8编码）
         */
        params.put("content", "腾讯入股京东");
        JSONObject jsonObject = WenzhiUtil.wenzhiInit(params, "TextDependency");
        System.out.println("句法分析\t" + jsonObject);
    }

    /**
     * 同义词 提供同义词查询服务
     */
    @Test
    public void testLexicalSynonym() {
        TreeMap<String, Object> params = new TreeMap<String, Object>();
        params.put("offset", 0);
        params.put("limit", 3);
        /*
         * 待分析的文本（目前文智统一输入为utf-8）
         */
        params.put("text", "你好");
        JSONObject jsonObject = WenzhiUtil.wenzhiInit(params, "LexicalSynonym");
        System.out.println("同义词\t" + jsonObject);
    }

    /**
     * 纠错 提供针对短文本的纠错功能
     */
    @Test
    public void testLexicalCheck() {
        TreeMap<String, Object> params = new TreeMap<String, Object>();
        params.put("offset", 0);
        params.put("limit", 3);
        /*
         * 待纠错的文本（utf-8)
         */
        params.put("text", "你s好");
        JSONObject jsonObject = WenzhiUtil.wenzhiInit(params, "LexicalCheck");
        System.out.println("纠错\t" + jsonObject);
    }

    /**
     * 转码 将PC端页面转换为适合在手机等移动端展示的页面
     */
    @Test
    public void testContentTranscode() {
        TreeMap<String, Object> params = new TreeMap<String, Object>();
        params.put("offset", 0);
        params.put("limit", 3);
        /*
         * 网页地址
         */
        params.put("url", "www.163.com");
        /*
         * 1 : html 0 : xml
         */
        params.put("to_html", 1);
        JSONObject jsonObject = WenzhiUtil.wenzhiInit(params, "ContentTranscode");
        System.out.println("转码\t" + jsonObject);
    }
}
