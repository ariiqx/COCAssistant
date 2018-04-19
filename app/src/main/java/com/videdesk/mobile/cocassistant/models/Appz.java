package com.videdesk.mobile.cocassistant.models;

public class Appz {
    private String id, title, version;

    public Appz() {
    }

    public Appz(String id, String title, String version) {
        this.id = id;
        this.title = title;
        this.version = version;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
