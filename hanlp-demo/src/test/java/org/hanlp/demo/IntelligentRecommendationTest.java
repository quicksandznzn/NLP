package org.hanlp.demo;


import org.junit.Test;

import com.hankcs.hanlp.dictionary.CoreSynonymDictionary;
import com.hankcs.hanlp.suggest.Suggester;

public class IntelligentRecommendationTest {

    /**
     * 语义距离
     */
    @Test
    public void coreSynonymDictionaryDistance() {
        System.out.println("语义距离 ----------------------------------------------");
        String[] wordArray = new String[] {"香蕉", "苹果", "白菜", "水果", "蔬菜", "自行车", "公交车", "飞机", "买",
                "卖", "购入", "新年", "春节", "丢失", "补办", "办理", "送给", "寻找", "孩子", "教室", "教师", "会计",};
        for (String a : wordArray) {
            for (String b : wordArray) {
                System.out.println(
                        a + "\t" + b + "\t之间的距离是\t" + CoreSynonymDictionary.distance(a, b));
            }
        }

    }

    /**
     * 文本推荐
     */
    @Test
    public void textRec() {
        System.out.println("文本推荐 ----------------------------------------------");
        Suggester suggester = new Suggester();
        String[] titleArray =
                ("威廉王子发表演说 呼吁保护野生动物\n" + "《时代》年度人物最终入围名单出炉 普京马云入选\n" + "“黑格比”横扫菲：菲吸取“海燕”经验及早疏散\n"
                        + "日本保密法将正式生效 日媒指其损害国民知情权\n" + "英报告说空气污染带来“公共健康危机”").split("\\n");
        for (String title : titleArray) {
            suggester.addSentence(title);
        }

        System.out.println(suggester.suggest("发言", 1)); // 语义
        System.out.println(suggester.suggest("危机公共", 1)); // 字符
        System.out.println(suggester.suggest("mayun", 1)); // 拼音
    }

}
