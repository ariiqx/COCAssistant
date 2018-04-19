package com.videdesk.mobile.cocassistant.models;

public class Theme {
    private String node, title, image, color;

    public Theme() {
    }

    public Theme(String node, String title, String image, String color) {
        this.node = node;
        this.title = title;
        this.image = image;
        this.color = color;
    }

    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
