package com.example.group37software_engineering.repo;

import com.example.group37software_engineering.model.Course;
import com.example.group37software_engineering.model.MyUser;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<MyUser, Integer> {
    public MyUser findByUsername(String username);
    public MyUser findByEmail(String email);
    MyUser findByUsernameOrEmail(String username, String email);

    List<MyUser> findAllByLeagueId(int leagueId);

    MyUser findByPasswordResetToken(String token);

    MyUser findByEmailVerificationToken(String token);

    List<MyUser> findAllByLeagueId(Integer id);
}
