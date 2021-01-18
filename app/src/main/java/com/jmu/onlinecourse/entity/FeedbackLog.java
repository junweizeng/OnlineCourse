package com.jmu.onlinecourse.entity;

/**
 * @author ywww
 * @date 2021-01-18 22:19
 */
public class FeedbackLog {
    String date;

    public String getDate() {
        return date;
    }

    public FeedbackLog() {
    }

    public FeedbackLog(String date, String content) {
        this.date = date;
        this.content = content;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    String content;
}
