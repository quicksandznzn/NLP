package org.hanlp.demo;

import java.util.List;

import org.junit.Test;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.Segment;
import com.hankcs.hanlp.seg.CRF.CRFSegment;
import com.hankcs.hanlp.seg.NShort.NShortSegment;
import com.hankcs.hanlp.seg.Viterbi.ViterbiSegment;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.tokenizer.IndexTokenizer;
import com.hankcs.hanlp.tokenizer.NLPTokenizer;
import com.hankcs.hanlp.tokenizer.SpeedTokenizer;
import com.hankcs.hanlp.tokenizer.TraditionalChineseTokenizer;

public class SegmentTest {

    /**
     * 标准分词
     */
    @Test
    public void segment() {
        System.out.println("标准分词 ----------------------------------------------");
        List<Term> termList = HanLP.segment("商品和服务");
        System.out.println(termList);
    }

    /**
     * NLP分词
     */
    @Test
    public void nlpSegment() {
        System.out.println("NLP分词 ----------------------------------------------");
        List<Term> termList = NLPTokenizer.segment("中国科学院计算技术研究所的宗成庆教授正在教授自然语言处理课程");
        System.out.println(termList);
    }

    /**
     * 索引分词
     */
    @Test
    public void indexSegment() {
        System.out.println("索引分词 ----------------------------------------------");
        List<Term> termList = IndexTokenizer.segment("主副食品");
        for (Term term : termList) {
            System.out.println(
                    term + " [" + term.offset + ":" + (term.offset + term.word.length()) + "]");
        }
    }

    /**
     * 繁体分词
     */
    @Test
    public void traditionalChineseSegment() {
        System.out.println("繁体分词 ----------------------------------------------");
        List<Term> termList = TraditionalChineseTokenizer.segment(
                "大衛貝克漢不僅僅是名著名球員，球場以外，其妻為前辣妹合唱團成員維多利亞·碧咸，亦由於他擁有突出外表、百變髮型及正面的形象，以至自己品牌的男士香水等商品，及長期擔任運動品牌Adidas的代言人，因此對大眾傳播媒介和時尚界等方面都具很大的影響力，在足球圈外所獲得的認受程度可謂前所未見。");
        System.out.println(termList);
    }

    /**
     * 极速词典分词
     */
    @Test
    public void speedSegment() {
        System.out.println("极速词典分词 ----------------------------------------------");
        String text = "江西鄱阳湖干枯，中国最大淡水湖变成大草原";
        System.out.println(SpeedTokenizer.segment(text));
        long start = System.currentTimeMillis();
        int pressure = 1000000;
        for (int i = 0; i < pressure; ++i) {
            SpeedTokenizer.segment(text);
        }
        double costTime = (System.currentTimeMillis() - start) / (double) 1000;
        System.out.printf("分词速度：%.2f字每秒", text.length() * pressure / costTime);
        System.out.println("");
    }

    /**
     * N-最短路径分词
     */
    @Test
    public void nShortSegment() {
        System.out.println("N-最短路径分词 ----------------------------------------------");
        Segment nShortSegment = new NShortSegment().enableCustomDictionary(false)
                .enablePlaceRecognize(true).enableOrganizationRecognize(true);
        Segment shortestSegment = new ViterbiSegment().enableCustomDictionary(false)
                .enablePlaceRecognize(true).enableOrganizationRecognize(true);
        String[] testCase = new String[] {"刘喜杰石国祥会见吴亚琴先进事迹报告团成员",};
        for (String sentence : testCase) {
            System.out.println("N-最短分词：" + nShortSegment.seg(sentence) + "\n最短路分词："
                    + shortestSegment.seg(sentence));
        }
    }

    /**
     * CRF分词
     */
    @Test
    public void crfSegment() {
        System.out.println("CRF分词 ----------------------------------------------");
        Segment segment = new CRFSegment();
        segment.enablePartOfSpeechTagging(true);
        List<Term> termList = segment.seg("你看过穆赫兰道吗");
        System.out.println(termList);
        for (Term term : termList) {
            if (term.nature == null) {
                System.out.println("识别到新词：" + term.word);
            }
        }
    }
}
