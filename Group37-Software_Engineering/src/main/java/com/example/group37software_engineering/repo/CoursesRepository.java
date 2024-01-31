package com.example.group37software_engineering.repo;

import com.example.group37software_engineering.model.Courses;
import com.example.group37software_engineering.model.MyUser;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CoursesRepository extends CrudRepository<Courses, Integer> {

    public Courses findCoursesByCourse_name(String name);

    public Courses findCoursesByCategory(String category);

    public List<Courses> findCoursesByUser(List<MyUser> user);


}
