package com.example.group37software_engineering.controller;

import com.example.group37software_engineering.model.*;
import com.example.group37software_engineering.repo.CourseRepository;
import com.example.group37software_engineering.repo.UserCommentRepository;
import com.example.group37software_engineering.repo.UserCourseRepository;
import com.example.group37software_engineering.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller class for managing operations related to courses and user enrollment.
 */
@Controller
public class CourseController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UserCourseRepository userCourseRepository;

    @Autowired
    private UserCommentRepository userCommentRepository;

    /**
     * Handles displaying available courses to the user.
     * Retrieves the user's information and the list of courses they are not enrolled in.
     *
     * @param model     The model to add attributes to.
     * @param principal The currently logged-in user.
     * @return The view name for displaying the list of available courses.
     */
    @GetMapping("/courses")
    public String getCourse(Model model, Principal principal) {
        MyUser user = userRepository.findByUsername(principal.getName());
        model.addAttribute("user", user);
        List<Course> allCourses = (List<Course>) courseRepository.findAll();
        List<Course> coursesNotEnrolled = new ArrayList<>();

        boolean anyCourseEnrolled = false;
        for (Course course : allCourses) {
            if (isUserEnrolledInCourse(user, course)) {
                List<UserComment> coursecomments = userCommentRepository.findByCourse(course);
                double average = coursecomments.stream().map(UserComment::getComment).mapToDouble(Comment::getReview).average().orElse(0.0);
                course.setAverageRating(average);
                courseRepository.save(course);
                anyCourseEnrolled = true;
                coursesNotEnrolled.add(course);
            }
        }

        if (!anyCourseEnrolled) {
            model.addAttribute("CourseError", "No courses left, check back another time!");
        } else {
            model.addAttribute("courseList", coursesNotEnrolled);
        }

        return "Course/courses";
    }

    /**
     * Handles enrolling a user in a specific course.
     * Checks if the user is not already enrolled and then performs the enrollment.
     *
     * @param course    The ID of the course to enroll the user in.
     * @param model     The model to add attributes to.
     * @param principal The currently logged-in user.
     * @return The view name for user dashboard or the courses view if enrollment fails.
     */
    @RequestMapping("/enroll")
    public String enroll(@RequestParam int course, Principal principal, Model model) {
        MyUser user = userRepository.findByUsername(principal.getName());
        Course c = courseRepository.findCourseById(course);
        if (c != null && isUserEnrolledInCourse(user, c)) {
            UserCourses userCourse = new UserCourses();
            userCourse.setUser(user);
            userCourse.setCourse(c);
            userCourseRepository.save(userCourse);
            return "redirect:/dashboard";
        }
        model.addAttribute("user", user);
        model.addAttribute("courseList", courseRepository.findAll());
        return "Course/courses";
    }


    /**
     * Handles setting the start time of a user's enrolled course.
     * Retrieves the user's information, the course, and updates the start time and date.
     *
     * @param courseId  The ID of the course for which to set the start time.
     * @param principal The currently logged-in user.
     * @return The view name of the user dashboard.
     */
    @RequestMapping("/startTime")
    public String getStartTime(@RequestParam int courseId, Principal principal) {
        MyUser user = userRepository.findByUsername(principal.getName());
        Course course = courseRepository.findCourseById(courseId);
        UserCourses userCourse = userCourseRepository.findByUserAndCourse(user, course);
        if (userCourse != null) {
            userCourse.setStartTime();
            userCourse.setStartDate();
            userCourseRepository.save(userCourse);
        }
        return "redirect:/dashboard";
    }

    @GetMapping("/top3")
    public String getTop3Courses(Model model) {
        List<Course> top3Courses = userCourseRepository.findTop3CoursesWithHighestUsers();
        model.addAttribute("top3Courses", top3Courses);
        return "Course/top3";
    }



    /**
     * Checks if a user is enrolled in a specific course.
     *
     * @param user   The user to check enrollment for.
     * @param course The course to check enrollment in.
     * @return True if the user is not enrolled in the course, false otherwise.
     */
    private boolean isUserEnrolledInCourse(MyUser user, Course course) {
        return user.getUserCourses().stream()
                .noneMatch(uc -> uc.getCourse().equals(course));
    }

}
