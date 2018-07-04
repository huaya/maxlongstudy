package com.maxlong.study.test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author 作者: maxlong E-mail: hu5624548@163.com
 * @version 创建时间：2018/4/4 21:53
 * 类说明:
 */
public class HeapOom {

    static class OOMObject{};

    public static void main(String[] args) {

        List<OOMObject> list = new ArrayList<>();

        while(true){
            list.add(new OOMObject());
        }

    }

}
