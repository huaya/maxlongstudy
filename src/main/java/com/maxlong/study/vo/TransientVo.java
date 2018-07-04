package com.maxlong.study.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author 作者 maxlong:
 * @version 创建时间：2017/5/24 13:25
 *          类说明
 */
@Getter
@Setter
@ToString
public class TransientVo implements Serializable {

    private String name;

    private String School;

    private transient String room;
}
