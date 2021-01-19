package com.jmu.onlinecourse.entity;

/*
封装
 */
public class TextInfo {
    private String textName;
    private int textId;
    private int imageView;
    private String textContent;

    public TextInfo(){

    }
    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

    public TextInfo(int textId, String textName, int imageView, String textContent){
        this.textId=textId;
        this.textName=textName;
        this.imageView=imageView;
        this.textContent=textContent;
    }
    public String getTextName() {
        return textName;
    }

    public void setTextName(String textName) {
        this.textName = textName;
    }

    public int getTextId() {
        return textId;
    }

    public void setTextId(int textId) {
        this.textId = textId;
    }

    public int getImageView() {
        return imageView;
    }

    public void setImageView(int imageView) {
        this.imageView = imageView;
    }
}
