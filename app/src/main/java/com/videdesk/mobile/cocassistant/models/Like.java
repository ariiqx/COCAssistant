package com.videdesk.mobile.cocassistant.models;

public class Like {

    private String node, theme_node, item_node;

    public Like() {
    }

    public Like(String node, String theme_node, String item_node) {
        this.node = node;
        this.theme_node = theme_node;
        this.item_node = item_node;
    }

    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
    }

    public String getTheme_node() {
        return theme_node;
    }

    public void setTheme_node(String theme_node) {
        this.theme_node = theme_node;
    }

    public String getItem_node() {
        return item_node;
    }

    public void setItem_node(String item_node) {
        this.item_node = item_node;
    }
}
