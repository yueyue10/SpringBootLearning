package com.forezp.entity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by fangzhipeng on 2017/4/19.
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class User {

    private String name;
    private String avatar_url;
    private String blog;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public String getBlog() {
        return blog;
    }

    public void setBlog(String blog) {
        this.blog = blog;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", avatar_url='" + avatar_url + '\'' +
                ", blog='" + blog + '\'' +
                '}';
    }
}