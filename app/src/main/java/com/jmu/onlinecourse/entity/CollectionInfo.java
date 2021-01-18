package com.jmu.onlinecourse.entity;

/**
 * @author zjw
 * @date 2021/1/17 22:25
 * @ClassName CollectionInfo
 */
public class CollectionInfo {
    private long ID;
    private String name;
    private String type;

    public CollectionInfo() {}

    public CollectionInfo(long ID, String name, String type) {
        this.ID = ID;
        this.name = name;
        this.type = type;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "CollectionInfo{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
