package com.maxlong.study.vo;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2020/12/22.
 *
 * @author maxlong
 */
@Data
public class ReqVO {

    private List<BaseVO> objs;

    public static void main(String[] args) {
        ReqVO reqVO = new ReqVO();
        List<BaseVO> baseVOS = new ArrayList<>();
        baseVOS.add(new Obj1(1, "mxl"));
        baseVOS.add(new Obj2(2, "xxxx"));
        reqVO.setObjs(baseVOS);

        String json = JSONObject.toJSONString(reqVO);
        System.out.println(json);
        ReqVO reqVO2 = JSONObject.parseObject(json, ReqVO.class);
    }
}
