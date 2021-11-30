package com.maxlong.study.vo;

import lombok.Data;

/**
 * Created on 2020/12/22.
 *
 * @author maxlong
 */
@Data
public class BaseVO {
    private Integer id;

    public BaseVO() {
    }

    public BaseVO(Integer id) {
        this.id = id;
    }
}
