package com.maxlong.study.vo;

import lombok.extern.slf4j.Slf4j;

/**
 * @author 作者 maxlong:
 * @version 创建时间：2017/5/24 14:57
 *          类说明
 */
@Slf4j
public class VolatileTest {

    int a = 1;
    int b = 2;

    public void change(){
        a = 3;
        b = a;
    }

    public void print(){
       log.info("b=" + b +";a=" + a);
    }
}
