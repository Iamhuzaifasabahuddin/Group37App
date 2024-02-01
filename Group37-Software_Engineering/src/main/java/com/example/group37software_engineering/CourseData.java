package com.example.group37software_engineering;


import com.example.group37software_engineering.model.Course;
import com.example.group37software_engineering.repo.CourseRepository;
import com.example.group37software_engineering.repo.UserRepository;
import com.example.group37software_engineering.model.MyUser;
import org.springframework.stereotype.Service;
import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

@Service
public class CourseData {
    private final CourseRepository courseRepository;
    private UserRepository userRepository;

    public CourseData(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public void readDataAndSaveToRepo(String csvFilePath, MyUser user, UserRepository u1) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                new ClassPathResource(csvFilePath).getInputStream()))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(","); // Assuming CSV has comma as delimiter
                Course course = new Course();
                course.setTitle(data[0].trim());
                course.setImageUrl(data[1].trim());
                course.setLink(data[2].trim());
                course.setCategory(data[3].trim());
                course.setDuration(Double.parseDouble(data[4].trim()));
                courseRepository.save(course);
                user.getCourse().add(course);
                u1.save(user);
            }
        } catch (IOException | ArrayIndexOutOfBoundsException | NumberFormatException e) {
            // Handle exceptions appropriately
            e.printStackTrace();
        }
    }
}
