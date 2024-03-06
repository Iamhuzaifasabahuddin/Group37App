package com.example.group37software_engineering.repo;

import com.example.group37software_engineering.model.Achievement;
import org.springframework.data.repository.CrudRepository;

public interface AchievementRepository extends CrudRepository<Achievement, Integer> {

    public Achievement findAchievementById(Integer Id);

    public Achievement findAchievementByTitle(String title);
}
