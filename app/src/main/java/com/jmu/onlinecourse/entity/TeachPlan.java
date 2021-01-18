package com.jmu.onlinecourse.entity;

/**
 * @author ywww
 * @date 2021-01-17 15:56
 */
public class TeachPlan {
    /**
     * 学年
     */
    private int begin_year;
    /**
     * 学期
     */
    private int term;

    private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getBegin_year() {
        return begin_year;
    }

    public TeachPlan() {
    }

    public TeachPlan(int begin_year, int term, String date, String teaching_content, double class_hour) {
        this.begin_year = begin_year;
        this.term = term;
        this.date = date;
        this.teaching_content = teaching_content;
        this.class_hour = class_hour;
    }

    /**
     * 教学内容
     */
    private String teaching_content;
    /**
     * 学时
     */
    private double class_hour;

    public void setBegin_year(int begin_year) {
        this.begin_year = begin_year;
    }

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    public String getTeaching_content() {
        return teaching_content;
    }

    public void setTeaching_content(String teaching_content) {
        this.teaching_content = teaching_content;
    }

    public double getClass_hour() {
        return class_hour;
    }

    public void setClass_hour(double class_hour) {
        this.class_hour = class_hour;
    }
}
