package com.videdesk.mobile.cocassistant.models;

public class Church {

    private String uid, node, code, nation_node, region_node, title, location, postal, phone, email, direction,
            latitude, longitude, image, members, created, updated, status;

    public Church() {
    }

    public Church(String uid, String node, String code, String nation_node, String region_node, String title, String location,
                  String postal, String phone, String email, String direction, String latitude, String longitude, String image,
                  String members, String created, String updated, String status) {
        this.uid = uid;
        this.node = node;
        this.code = code;
        this.nation_node = nation_node;
        this.region_node = region_node;
        this.title = title;
        this.location = location;
        this.postal = postal;
        this.phone = phone;
        this.email = email;
        this.direction = direction;
        this.latitude = latitude;
        this.longitude = longitude;
        this.image = image;
        this.members = members;
        this.created = created;
        this.updated = updated;
        this.status = status;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPostal() {
        return postal;
    }

    public void setPostal(String postal) {
        this.postal = postal;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getMembers() {
        return members;
    }

    public void setMembers(String members) {
        this.members = members;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
