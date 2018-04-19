package com.videdesk.mobile.cocassistant.models;

public class Book {

    private String node, title, half_node, genre_node, chapters;

    public Book() {
    }

    public Book(String node, String title, String half_node, String genre_node, String chapters) {
        this.node = node;
        this.title = title;
        this.half_node = half_node;
        this.genre_node = genre_node;
        this.chapters = chapters;
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

    public String getHalf_node() {
        return half_node;
    }

    public void setHalf_node(String half_node) {
        this.half_node = half_node;
    }

    public String getGenre_node() {
        return genre_node;
    }

    public void setGenre_node(String genre_node) {
        this.genre_node = genre_node;
    }

    public String getChapters() {
        return chapters;
    }

    public void setChapters(String chapters) {
        this.chapters = chapters;
    }
}
