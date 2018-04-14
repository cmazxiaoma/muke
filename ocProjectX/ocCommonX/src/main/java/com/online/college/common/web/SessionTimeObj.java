package com.online.college.common.web;

/**
 *
 * @Description: Session中setAttribute的对象，加入超时属性 时间从创建对象开始
 * @author majinlan
 * @date 2018年2月5日
 * @version V1.0
 */
public class SessionTimeObj {

    /**
     * 值对象
     */
    private Object value;

    /**
     * 超时时间，毫秒
     */
    private Long overTime;

    /**
     * 加入value时的当前时间
     */
    private Long currentTime;

    public SessionTimeObj(Object value, Long overTime, Long currentTime) {
        super();
        this.value = value;
        this.overTime = overTime;
        this.currentTime = currentTime;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Long getOverTime() {
        return overTime;
    }

    public void setOverTime(Long overTime) {
        this.overTime = overTime;
    }

    public boolean isOverTime() {
        return System.currentTimeMillis() - currentTime > overTime;
    }
}
