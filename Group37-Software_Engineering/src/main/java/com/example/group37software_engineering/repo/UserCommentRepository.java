package com.example.group37software_engineering.repo;

import com.example.group37software_engineering.model.Comment;
import com.example.group37software_engineering.model.MyUser;
import com.example.group37software_engineering.model.UserComment;
import org.springframework.data.repository.CrudRepository;

public interface UserCommentRepository extends CrudRepository<UserComment, Integer> {

    public UserComment findUserCommentByUserAndComment(MyUser user, Comment comments);

    public UserComment findUserCommentById(Integer id);
}
