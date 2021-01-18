package com.jmu.onlinecourse.entity;

/**
 * 题目实体类
 * @author czc
 */
public class Problem {
    private int id;
    private String title;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private int examination;
    private String answer;

    public Problem(int id, String title, String optionA, String optionB, String optionC, String optionD, int examination, String answer) {
        this.id = id;
        this.title = title;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.examination = examination;
        this.answer = answer;
    }
    public Problem(String title, String optionA, String optionB, String optionC, String optionD, int examination, String answer) {
        this.title = title;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.examination = examination;
        this.answer = answer;
    }
    public int getId(){
        return id;
    }
    public String getTitle() {
        return title;
    }

    public String getOptionA() {
        return optionA;
    }

    public String getOptionB() {
        return optionB;
    }

    public String getOptionC() {
        return optionC;
    }

    public String getOptionD() {
        return optionD;
    }

    public int getExamination() {
        return examination;
    }

    public String getAnswer() {
        return answer;
    }

    @Override
    public String toString() {
        return "Problem{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", optionA='" + optionA + '\'' +
                ", optionB='" + optionB + '\'' +
                ", optionC='" + optionC + '\'' +
                ", optionD='" + optionD + '\'' +
                ", examination=" + examination +
                ", answer='" + answer + '\'' +
                '}';
    }
}
