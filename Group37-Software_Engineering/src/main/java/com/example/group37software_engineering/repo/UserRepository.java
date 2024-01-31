package com.example.group37software_engineering.repo;

import com.example.group37software_engineering.model.Courses;
import com.example.group37software_engineering.model.MyUser;
import jdk.dynalink.linker.LinkerServices;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<MyUser, Integer> {
    public MyUser findByUsername(String username);
    public MyUser findByEmail(String email);
    public List<MyUser> findByCourse(List<Courses> courses);


}
