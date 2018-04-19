package com.videdesk.mobile.cocassistant.models;

public class Nation {

    private String node, code, dial, title;

    public Nation() {
    }

    public Nation(String node, String code, String dial, String title) {
        this.node = node;
        this.code = code;
        this.dial = dial;
        this.title = title;
    }

    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDial() {
        return dial;
    }

    public void setDial(String dial) {
        this.dial = dial;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
