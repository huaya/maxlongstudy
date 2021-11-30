package com.maxlong.study.vo;

import lombok.Data;

/**
 * Created on 2020/12/22.
 *
 * @author maxlong
 */
@Data
public class Obj2 extends BaseVO {

    private String key;

    public Obj2(String key) {
        this.key = key;
    }

    public Obj2(Integer id, String key) {
        super(id);
        this.key = key;
    }
}
