package com.example.group37software_engineering.repo;

import com.example.group37software_engineering.model.Comment;
import com.example.group37software_engineering.model.Course;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment, Integer> {

    public Comment findCommentById(Integer Id);
}
