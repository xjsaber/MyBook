package com.xjsaber.java.thread.code.ch1.daemon;

import java.util.Date;

/**
 * @author xjsaber
 */
public class Event {

    private Date date;
    private String event;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }
}
