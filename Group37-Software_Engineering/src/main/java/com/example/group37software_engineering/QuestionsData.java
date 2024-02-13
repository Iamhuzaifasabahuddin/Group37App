//package com.example.group37software_engineering;
//import com.example.group37software_engineering.model.Course;
//import com.example.group37software_engineering.model.Question;
//import com.example.group37software_engineering.repo.CourseRepository;
//import com.example.group37software_engineering.repo.QuestionRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.stereotype.Service;
//
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//public class QuestionsData {
//
//    private QuestionRepository questionRepository;
//
//    private CourseRepository courseRepository;
//
//
//    @Autowired
//    public QuestionsData(QuestionRepository questionRepository, CourseRepository courseRepository) {
//
//        this.questionRepository = questionRepository;
//        this.courseRepository = courseRepository;
//
//    }
//
//    public void importQuestionsFromCSV(String csvFilePath) {
//
//        List<Question> questions = new ArrayList<>();
//
//        try (BufferedReader br = new BufferedReader(new InputStreamReader(
//                new ClassPathResource(csvFilePath).getInputStream()))) {
//            String line;
//            while ((line = br.readLine()) != null) {
//
//                Course c = new Course();
//                String[] data = line.split(",");
//                if (data.length >= 4) { // Assuming CSV format: prompt, answer, list of options, list of ids
//
//                    Question question = new Question();
//                    question.setPrompt(data[0]);
//                    question.setAnswer(data[1]);
//
//                    // Parse the list of options
//                    List<String> options = parseOptions(data[data.length - 2]);
//                    question.setOptions(options);
//
//                    // Parse the list of IDs
//                    List<Integer> ids = parseIds(data[data.length - 1]);
//
//                    System.out.println(ids);
//
//                    List<Course> courses = new ArrayList<>();
//
//                    // Loop through the list of IDs and perform actions
//                    for (Integer id : ids) {
//
//                        courses.add(courseRepository.findCourseById(id));
//
//                    }
//
//                    question.setCourses(courses);
//
//                    questionRepository.save(question);
//
//                    for (Integer id : ids) {
//
//                        c = courseRepository.findCourseById(id);
//                        c.getQuestions().addAll(Arrays.asList(question));
//                        courseRepository.save(c);
//
//                    }
//
//                    questions.add(question);
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//            // Handle IOException (e.g., log, throw a custom exception)
//        }
//
////        // Save the questions to the repository
//
//    }
//
//    private List<String> parseOptions(String options) {
//        return Arrays.asList(options.split(";"));
//    }
//
//    private List<Integer> parseIds(String ids) {
//        return Arrays.stream(ids.split(";"))
//                .map(Integer::parseInt)
//                .collect(Collectors.toList());
//    }
//}
