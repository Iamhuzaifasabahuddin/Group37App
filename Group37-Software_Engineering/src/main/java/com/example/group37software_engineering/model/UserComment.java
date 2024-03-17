package com.example.group37software_engineering.model;

import com.google.gson.annotations.Expose;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Optional;

@Entity
public class UserComment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Expose
    @ManyToOne
    @JoinColumn(name = "user_id")
    private MyUser user;

    @Expose
    private String dateCommented;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @Expose
    @ManyToOne
    @JoinColumn(name = "comment_id")
    private Comment comment;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public MyUser getUser() {
        return user;
    }

    public void setUser(MyUser user) {
        this.user = user;
    }

    public String getDateCommented() {
        return dateCommented;
    }

    public void setDateCommented() {
        this.dateCommented = String.valueOf(LocalDate.now());
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "UserComment{" +
                "user:" + user.getUsername() +
                ", dateCommented:" + dateCommented +
                ", course:" + course.getTitle() +
                ", Description:" + comment.getDescription() +
                ", Rating:" + comment.getReview() +
                '}';
    }
}
