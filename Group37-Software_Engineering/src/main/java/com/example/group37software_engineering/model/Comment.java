package com.example.group37software_engineering.model;

import com.google.gson.annotations.Expose;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Expose
    private String description;

    @Expose
    private double review;

    @OneToMany(mappedBy = "comment")
    private List<UserComment> userComments;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getReview() {
        return review;
    }

    public void setReview(double review) {
        this.review = review;
    }


    public List<UserComment> getUserComments() {
        return userComments;
    }

    public void setUserComments(List<UserComment> userComments) {
        this.userComments = userComments;
    }
}
