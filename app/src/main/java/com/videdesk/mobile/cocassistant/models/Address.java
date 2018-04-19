package com.videdesk.mobile.cocassistant.models;

public class Address {
    private String node, contact_node, type, title;

    public Address() {
    }

    public Address(String node, String contact_node, String type, String title) {
        this.node = node;
        this.contact_node = contact_node;
        this.type = type;
        this.title = title;
    }

    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
    }

    public String getContact_node() {
        return contact_node;
    }

    public void setContact_node(String contact_node) {
        this.contact_node = contact_node;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
