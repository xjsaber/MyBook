package com.xjsaber.java.permission.model;

/**
 * 因为mysql存储text和一般的字符串不太一样，需要另外存储
 * 1. 当我们只需要取这些数据的时候可以调用SysLog
 * 2. 当我们需要取text数据的时候就必须要调用SysLogWithBloBs
 * 3. 有选择性的时候取text的数据
 */
public class SysLogWithBLOBs extends SysLog {
    private String oldValue;

    private String newValue;

    public String getOldValue() {
        return oldValue;
    }

    public void setOldValue(String oldValue) {
        this.oldValue = oldValue == null ? null : oldValue.trim();
    }

    public String getNewValue() {
        return newValue;
    }

    public void setNewValue(String newValue) {
        this.newValue = newValue == null ? null : newValue.trim();
    }
}