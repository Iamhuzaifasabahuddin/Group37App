package com.example.group37software_engineering.repo;

import com.example.group37software_engineering.model.Quiz;
import org.springframework.data.repository.CrudRepository;

public interface QuizRepository extends CrudRepository<Quiz, Integer> {
    public Quiz findById(int id);

    public Quiz findByCategory(String category);
}
