package com.example.group37software_engineering.repo;

import com.example.group37software_engineering.model.Course;
import com.example.group37software_engineering.model.MyUser;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CourseRepository extends CrudRepository<Course, Integer> {

    public Course findCoursesByTitle(String title);

    public Course findCoursesByCategory(String category);

    public List<Course> findCoursesByUser(MyUser user);


}
