package com.example.group37software_engineering.repo;

import com.example.group37software_engineering.model.MyUser;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<MyUser, Integer> {
    public MyUser findByUsername(String username);
    public MyUser findByEmail(String email);

}
