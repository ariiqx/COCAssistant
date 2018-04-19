package com.videdesk.mobile.cocassistant.models;

public class Person {

    private String node, user_node,first_name, middle_name, last_name, gender, birth, career, education,
            nation_node, region_node, location, church_node;

    public Person(){}

    public Person(String node, String user_node, String first_name, String middle_name, String last_name, String gender,
                  String birth, String career, String education, String nation_node, String region_node, String location,
                  String church_node) {
        this.node = node;
        this.user_node = user_node;
        this.first_name = first_name;
        this.middle_name = middle_name;
        this.last_name = last_name;
        this.gender = gender;
        this.birth = birth;
        this.career = career;
        this.education = education;
        this.nation_node = nation_node;
        this.region_node = region_node;
        this.location = location;
        this.church_node = church_node;
    }

    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
    }

    public String getUser_node() {
        return user_node;
    }

    public void setUser_node(String user_node) {
        this.user_node = user_node;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getMiddle_name() {
        return middle_name;
    }

    public void setMiddle_name(String middle_name) {
        this.middle_name = middle_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getCareer() {
        return career;
    }

    public void setCareer(String career) {
        this.career = career;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getNation_node() {
        return nation_node;
    }

    public void setNation_node(String nation_node) {
        this.nation_node = nation_node;
    }

    public String getRegion_node() {
        return region_node;
    }

    public void setRegion_node(String region_node) {
        this.region_node = region_node;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getChurch_node() {
        return church_node;
    }

    public void setChurch_node(String church_node) {
        this.church_node = church_node;
    }
}
