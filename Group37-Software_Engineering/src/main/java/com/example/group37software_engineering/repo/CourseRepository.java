package com.example.group37software_engineering.repo;

import com.example.group37software_engineering.model.Course;
import com.example.group37software_engineering.model.MyUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CourseRepository extends CrudRepository<Course, Integer> {

    public Course findCoursesByTitle(String title);
    List<Course> findCoursesByTitleContaining(String title);

    List<Course> findAllByCategory(String category);

    public Course findCourseById(Integer id);

    public Course findCoursesByCategory(String category);

    List<Course> findAllByDurationGreaterThanEqual(double duration);

    @Query("SELECT u FROM MyUser u JOIN u.course c WHERE c.category = :category")
    List<MyUser> findAllUsersByCourseCategory(@Param("category") String category);
}

