package com.example.group37software_engineering.repo;

import com.example.group37software_engineering.model.Course;
import com.example.group37software_engineering.model.MyUser;
import com.example.group37software_engineering.model.UserCourses;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserCourseRepository extends CrudRepository<UserCourses, Integer> {
    public UserCourses findByUserAndCourse(MyUser user, Course course);

    List<UserCourses> findByUser(MyUser user);

    @Query("SELECT uc.course " +
            "FROM UserCourses uc " +
            "GROUP BY uc.course " +
            "ORDER BY COUNT(uc.user) DESC")
    List<Course> findTop3CoursesWithHighestUsers();

}
