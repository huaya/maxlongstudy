package com.maxlong.study.lintCode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by IntelliJ IDEA.
 *
 * @author 作者: maxlong E-mail: hu5624548@163.com
 * @version 创建时间：2018/7/18 21:11
 * 类说明:
 */
public class TempTest {

    public static void main(String[] args) {

        String sas = "1121";
        sas.length();
        LinkedList list = new LinkedList();

        list.add("aaa");
        list.add(1,"bbbb");
        list.remove();

        list.get(1);

        Stack stack = new Stack<String>();
        Queue queue = new ArrayBlockingQueue(10);
    }
}
