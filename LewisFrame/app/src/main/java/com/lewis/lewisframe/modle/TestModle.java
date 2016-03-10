package com.lewis.lewisframe.modle;

/**
 * Created by lewis on 16/2/23.
 */
public class TestModle {
    public static final int TYPE_1=1;
    public static final int TYPE_2=2;
    private String content;
    private String title;
    private int type;

    public TestModle(String content, String title, int type) {
        this.content = content;
        this.title = title;
        this.type = type;
    }

    public TestModle(String title,int type) {
        this.type = type;
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
