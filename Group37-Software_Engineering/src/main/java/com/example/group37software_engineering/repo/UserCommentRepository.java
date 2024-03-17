package com.example.group37software_engineering.repo;

import com.example.group37software_engineering.model.Comment;
import com.example.group37software_engineering.model.Course;
import com.example.group37software_engineering.model.MyUser;
import com.example.group37software_engineering.model.UserComment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserCommentRepository extends CrudRepository<UserComment, Integer> {

    public UserComment findByUserAndComment(MyUser user, Comment comments);

    List<UserComment> findByUser(MyUser user);

    List<UserComment> findUserCommentByCourse(Course course);

}
