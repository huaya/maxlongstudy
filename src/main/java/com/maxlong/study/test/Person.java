package com.maxlong.study.test;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author 作者 maxlong:
 * @version 创建时间：2017/6/26 15:37
 *          类说明
 */
public abstract class Person {

    @Autowired
    public EatService eatService;

    public String eat(){
        eatService.eat();
        return "yes";
    }
}
