package com.videdesk.mobile.cocassistant.models;

public class Verse {

    private String node, code, bible_node, book_node, chapter_node, title, details;

    public Verse() {
    }

    public Verse(String node, String code, String bible_node, String book_node,
                 String chapter_node, String title, String details) {
        this.node = node;
        this.code = code;
        this.bible_node = bible_node;
        this.book_node = book_node;
        this.chapter_node = chapter_node;
        this.title = title;
        this.details = details;
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

    public String getBible_node() {
        return bible_node;
    }

    public void setBible_node(String bible_node) {
        this.bible_node = bible_node;
    }

    public String getBook_node() {
        return book_node;
    }

    public void setBook_node(String book_node) {
        this.book_node = book_node;
    }

    public String getChapter_node() {
        return chapter_node;
    }

    public void setChapter_node(String chapter_node) {
        this.chapter_node = chapter_node;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
