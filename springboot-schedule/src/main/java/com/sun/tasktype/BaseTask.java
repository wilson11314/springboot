package com.sun.tasktype;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.scheduling.config.ScheduledTask;

import com.sun.taskconfig.BaseTaskConfig;
import com.sun.taskconfig.TaskType;

/**
 *  任务基础配置类
 * @Date  2019/03/10
 * @author qsl
 *
 */
public abstract class BaseTask implements Runnable,InitializingBean {
    protected final Logger log = LoggerFactory.getLogger(this.getClass());
    public final TaskType taskType;
    private ScheduledTask scheduledTask;
    private final String id;
    public BaseTask(TaskType taskType, String id) {
        this.taskType = taskType;
        this.id = id;
    }

    /**
     *  获取任务表达式如：0 0 0/1 * * *? (每个整点执行)
     * @return
     */
    public abstract String getExpression();

    /**
     * 固定频率执行的时间间隔
     * @return
     */
    public abstract long interval();

    /**
     * 固定频率执行的延迟时间
     * @return
     */
    public abstract long delay();

    /**
     * 设置任务表达式
     * @param expression
     */
    public abstract void setExpression(String expression);

    /**
     * 获取任务唯一标识
     * @return
     */
    public String getId() {
        return id;
    }

    public final ScheduledTask getScheduledTask() {
        return scheduledTask;
    }

    public final void setScheduledTask(ScheduledTask scheduledTask) {
        this.scheduledTask = scheduledTask;
    }

    @Override
    public void afterPropertiesSet() {
        BaseTaskConfig.addTask(this);
    }
    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "(id:" + id + ",expression:" + getExpression()
                + ",type:" + taskType + ",interval:" + interval()
                + ", delay:" + delay() + ")";
    }
}
