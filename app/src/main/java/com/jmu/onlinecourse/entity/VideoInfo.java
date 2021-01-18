package com.jmu.onlinecourse.entity;

import java.io.Serializable;

/**
 * @author zjw
 * @date 2021/1/12 14:36
 * @ClassName VideoInfo
 */
public class VideoInfo implements Serializable {
    /**
     * 视频ID号
     */
    private long ID;
    /**
     * 标题
     */
    private String videoName;
    /**
     * 摘要
     */
    private String summary;
    /**
     * 视频封面图片
     */
    private String imageUrl;
    /**
     * 点赞数
     */
    private int likes;
    /**
     * 收藏量
     */
    private int collection;
    /**
     * 播放量
     */
    private int playVolume;
    /**
     * 视频流地址
     */
    private String detailUrl;

    public VideoInfo() {}

    public VideoInfo(long ID, String videoName, String summary, String imageUrl, int likes, int collection, int playVolume, String detailUrl) {
        this.ID = ID;
        this.videoName = videoName;
        this.summary = summary;
        this.imageUrl = imageUrl;
        this.likes = likes;
        this.collection = collection;
        this.playVolume = playVolume;
        this.detailUrl = detailUrl;
    }

    @Override
    public String toString() {
        return "VideoInfo{" +
                "ID=" + ID +
                ", videoName='" + videoName + '\'' +
                ", summary='" + summary + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", likes=" + likes +
                ", collection=" + collection +
                ", playVolume=" + playVolume +
                ", detailUrl='" + detailUrl + '\'' +
                '}';
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getCollection() {
        return collection;
    }

    public void setCollection(int collection) {
        this.collection = collection;
    }

    public int getPlayVolume() {
        return playVolume;
    }

    public void setPlayVolume(int playVolume) {
        this.playVolume = playVolume;
    }

    public String getDetailUrl() {
        return detailUrl;
    }

    public void setDetailUrl(String detailUrl) {
        this.detailUrl = detailUrl;
    }
}
