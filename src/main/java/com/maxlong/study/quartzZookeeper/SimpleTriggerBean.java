package com.maxlong.study.quartzZookeeper;

/**
 * @author 作者 maxlong:
 * @version 创建时间：2017/5/25 17:24
 *          类说明
 */
public class SimpleTriggerBean extends TriggerBean {

    /**
     * 时间间隔(秒)
     */
    private Integer interval;
    /**
     * 重复次数(默认：-1为无限循环)
     */
    private Integer repeatCount;

    public Integer getInterval() {
        return interval;
    }

    public void setInterval(Integer interval) {
        this.interval = interval;
    }

    public Integer getRepeatCount() {
        return repeatCount;
    }

    public void setRepeatCount(Integer repeatCount) {
        this.repeatCount = repeatCount;
    }
}