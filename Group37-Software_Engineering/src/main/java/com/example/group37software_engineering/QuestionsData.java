package com.example.group37software_engineering;
import com.example.group37software_engineering.model.Course;
import com.example.group37software_engineering.model.Question;
import com.example.group37software_engineering.repo.CourseRepository;
import com.example.group37software_engineering.repo.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class QuestionsData {

    private QuestionRepository questionRepository;
    private CourseRepository courseRepository;

    @Autowired
    public QuestionsData(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public void importQuestionsFromCSV(String csvFilePath) {
        List<Question> questions = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 4) { // Assuming CSV format: prompt, answer, list of options, list of ids

                    Question question = new Question();
                    question.setPrompt(data[0]);
                    question.setAnswer(data[1]);

                    // Parse the list of options
                    List<String> options = parseOptions(Arrays.copyOfRange(data, 2, data.length - 1));
                    question.setOptions(options);

                    // Parse the list of IDs
                    List<Integer> ids = parseIds(Arrays.copyOfRange(data, data.length - 1, data.length));

                    List<Course> courses = new ArrayList<>();

                    // Loop through the list of IDs and perform actions
                    for (Integer id : ids) {

                        courses.add(courseRepository.findCourseById(id));

                    }

                    question.setCourses(courses);

                    questions.add(question);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            // Handle IOException (e.g., log, throw a custom exception)
        }

        // Save the questions to the repository
        questionRepository.saveAll(questions);
    }

    private List<String> parseOptions(String[] options) {
        return Arrays.asList(options);
    }

    private List<Integer> parseIds(String[] ids) {
        List<Integer> idList = new ArrayList<>();
        for (String id : ids) {
            idList.add(Integer.parseInt(id.trim()));
        }
        return idList;
    }
}
