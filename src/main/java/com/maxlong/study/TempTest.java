package com.maxlong.study;

/**
 * Created by IntelliJ IDEA.
 *
 * @author 作者: maxlong E-mail: hu5624548@163.com
 * @version 创建时间：2018/7/3 18:59
 * 类说明:
 */
public class TempTest {

    public static void main(String[] args) {
        {
            byte[] b = new byte[6 * 1024 * 1024];
            b = null;
        }
        System.gc();

    }
}
