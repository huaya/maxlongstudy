package com.maxlong.study.lintCode;

import java.util.Random;

/**
 * Created by IntelliJ IDEA.
 *
 * @author 作者: maxlong E-mail: hu5624548@163.com
 * @version 创建时间：2018/7/14 18:51
 * 类说明: lintCode 最长AB字符串
 */
public class LonestABStr {

    private static final int SLENGTH = 100;

    public static void main(String[] args) {
        String s = createSString();

        System.out.println(s);
        String longestAb = getAns(s);

    }

    /**
     * 生成固定长度的AB字符串
     *
     * @return
     */
    private static String createSString() {

        StringBuilder builder = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < SLENGTH; i++) {
            builder.append(random.nextBoolean() ? "A" : "B");
        }
        return builder.toString();
    }

    public static String getAns(String S) {
        int countA = 0;
        int countB = 0;
        for (int i = 0; i < S.length(); i++) {
            if (S.charAt(i) == 'A') {
                countA++;
            } else {
                countB++;
            }
        }

        int diff = Math.abs(countB - countA);
        char shortChar = countB > countA ? 'B' : 'A';

        while (diff>0){




        }


        System.out.println("A : " + countA);
        System.out.println("B : " + countB);

        return null;
    }
}
