package com.maxlong.study.quartzZookeeper;

/**
 * @author 作者 maxlong:
 * @version 创建时间：2017/5/25 17:23
 *          类说明
 */
public class CronTriggerBean extends TriggerBean {

    /**
     * CRON表达式
     */
    private String cronExpression;

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }
}
