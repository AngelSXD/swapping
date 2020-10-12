package com.sxd.swapping.guava;

import com.google.common.base.CharMatcher;
import com.google.common.base.Splitter;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

/**
 * @Author: SXD
 * @Description:  Google Guava的splitter,分割字符串的用法
 * @Date: create in 2019/11/12 9:52
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class GuavaSplitterTest {

    String str = "a,,b,    c,,,,2312312342,dasdaaa ,德玛西亚,    艾欧尼亚,寒冰之地   ,abCDefGHdasIJklmnoPpQqZz,ooAAdas";

    /**
     * 按 指定字符 拆分源字符串
     */
    @Test
    public void splitTest(){
        Iterable<String> split = Splitter.on(",").split(str);
        printResult(split);
    }

    /**
     * 按 指定字符  拆分源字符串
     * 并去除 空值
     */
    @Test
    public void omitEmptyStringsTest(){
        Iterable<String> split = Splitter.on(",").omitEmptyStrings().split(str);
        printResult(split);
    }


    /**
     * 按 指定字符  拆分源字符串
     * 并去除空值
     * 并去除额外空格
     */
    @Test
    public void trimResultsTest(){
        Iterable<String> split = Splitter.on(",").trimResults().omitEmptyStrings().split(str);
        printResult(split);
    }


    /**
     * 按 指定字符  拆分源字符串
     * 并去除空值
     * 并 按照指定类型 去除每一个分隔元素内的 指定类型
     */
    @Test
    public void trimResultsWithCharTest(){
        //去除 元素中包含的数字
        Iterable<String> split1 = Splitter.on(",").trimResults(CharMatcher.digit()).omitEmptyStrings().split(str);
        printResult(split1);

        //去除 元素中的 空格
        Iterable<String> split2 = Splitter.on(",").trimResults(CharMatcher.whitespace()).omitEmptyStrings().split(str);
        printResult(split2);

        //去除 元素中的 空格
        Iterable<String> split3 = Splitter.on(",").trimResults(CharMatcher.breakingWhitespace()).omitEmptyStrings().split(str);
        printResult(split3);

        //去除 元素中的 包含在ASCII中的所有元素 [留下的：例如中文]
        Iterable<String> split4 = Splitter.on(",").trimResults(CharMatcher.ascii()).omitEmptyStrings().split(str);
        printResult(split4);

        //去除 元素中的 任何元素
        Iterable<String> split5 = Splitter.on(",").trimResults(CharMatcher.any()).omitEmptyStrings().split(str);
        printResult(split5);

        //不去除 元素中的 任何元素
        Iterable<String> split6 = Splitter.on(",").trimResults(CharMatcher.none()).omitEmptyStrings().split(str);
        printResult(split6);

        //去除 元素左右两侧的在a-z范围内的元素
        Iterable<String> split9 = Splitter.on(",").trimResults(CharMatcher.inRange('a','z')).omitEmptyStrings().split(str);
        printResult(split9);

        //去除 元素左右两侧中不包含a  的元素
        Iterable<String> split10 = Splitter.on(",").trimResults(CharMatcher.isNot('a')).omitEmptyStrings().split(str);
        printResult(split10);

    }




    /**
     * 将 Iterable<String> 转化为 List<String>
     *
     * 最终打印结果集
     * @param split Iterable<String>
     */
    private void printResult(Iterable<String> split){
        ArrayList<String> result = Lists.newArrayList(split);
        System.out.println("结果集大小："+result.size());
        System.out.println(">>>>输入结果集：");
        for (String s : result) {
            System.out.println(s);
        }
        System.out.println();
    }
}
