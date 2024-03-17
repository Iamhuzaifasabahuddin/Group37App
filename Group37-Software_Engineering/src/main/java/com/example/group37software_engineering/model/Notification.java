package com.example.group37software_engineering.model;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private boolean seen;

    private String pageLink;

    private String iconLink;

    private String description;

    private String timeReceived;

    public Notification() {
        this.seen = false;
        this.setReceived();
    }

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isSeen() {
        return seen;
    }

    public void setSeen(boolean read) {
        this.seen = read;
    }

    public String getPageLink() {
        return pageLink;
    }

    public void setPageLink(String pageLink) {
        this.pageLink = pageLink;
    }

    public String getIconLink() {
        return iconLink;
    }

    public void setIconLink(String iconLink) {
        this.iconLink = iconLink;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String text) {
        this.description = text;
    }

    public String getTimeReceived() {
        return timeReceived;
    }

    public void setReceived() {
        this.timeReceived = Instant.now().toString();
    }
}
