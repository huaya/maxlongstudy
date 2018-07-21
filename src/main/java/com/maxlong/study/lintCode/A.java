package com.maxlong.study.lintCode;

/**
 * Created by IntelliJ IDEA.
 *
 * @author 作者: maxlong E-mail: hu5624548@163.com
 * @version 创建时间：2018/7/17 18:20
 * 类说明:
 */
public class A {

    public A() {
        i = (j++ != 0) ? ++j : --j;
    }

    public int i;
    public static int j = 0;

}
