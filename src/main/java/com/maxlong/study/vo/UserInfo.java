package com.maxlong.study.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author 作者 maxlong:
 * @version 创建时间：2017/5/23 18:18
 *          类说明
 */
@Setter
@Getter
@ToString
public class UserInfo {

    private String address;

    private String id;

    public UserInfo(String address, String id) {
        this.address = address;
        this.id = id;
    }
}
