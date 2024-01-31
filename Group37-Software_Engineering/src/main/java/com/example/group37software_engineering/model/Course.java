package com.example.group37software_engineering.model;


import jakarta.persistence.*;

import java.sql.Time;
import java.util.Date;
import java.util.List;

@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String title;

    private Date startDate;
    private Date endDate;

    private Time startTime;

    private Time endTime;

    private boolean completed;

    private double percentage;

    private double duration;

    private String category;

    private String description;

    private String imageUrl;

    private String link;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<MyUser> user;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String course_name) {
        this.title = course_name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date start_date) {
        this.startDate = start_date;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date end_date) {
        this.endDate = end_date;
    }


    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time start_time) {
        this.startTime = start_time;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time end_time) {
        this.endTime = end_time;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }


    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imgUrl) {
        this.imageUrl = imgUrl;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public List<MyUser> getUser() {
        return user;
    }

    public void setUser(List<MyUser> user) {
        this.user = user;
    }
}

