package com.maxlong.study.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * @author 作者 maxlong:
 * @version 创建时间：2017/4/19 10:25
 *          类说明
 */
@Component
@Setter
@Getter
@ToString
public class User {

    private String name;

    @NotNull(message = "must not be null")
    private String age;

//    private UserInfo userInfo;

    public User() {
    }

    public User(String name, String age) {
        this.name = name;
        this.age = age;
    }
}
