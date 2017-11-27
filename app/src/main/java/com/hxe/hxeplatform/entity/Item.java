package com.hxe.hxeplatform.entity;

/**
 * Author:wangcaiwen
 * Time:2017/11/26.
 * Description:
 */

public class Item {
    private String title;
    private int image;

    public Item(String title, int image) {
        this.title = title;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
