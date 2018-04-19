package com.videdesk.mobile.cocassistant.models;

public class Chap {
    private String node, title;

    public Chap() {
    }

    public Chap(String node, String title) {
        this.node = node;
        this.title = title;
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
}
