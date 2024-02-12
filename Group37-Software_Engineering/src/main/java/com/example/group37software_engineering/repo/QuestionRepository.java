package com.example.group37software_engineering.repo;

import com.example.group37software_engineering.model.Question;
import org.springframework.data.repository.CrudRepository;

public interface QuestionRepository extends CrudRepository<Question, Integer> {
}
