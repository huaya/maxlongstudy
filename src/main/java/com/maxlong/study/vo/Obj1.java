package com.maxlong.study.vo;

import lombok.Data;

/**
 * Created on 2020/12/22.
 *
 * @author maxlong
 */
@Data
public class Obj1 extends BaseVO {

    private String name;

    public Obj1(String name) {
        this.name = name;
    }

    public Obj1(Integer id, String name) {
        super(id);
        this.name = name;
    }
}
