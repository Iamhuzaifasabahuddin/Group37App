package com.example.group37software_engineering.repo;

import com.example.group37software_engineering.model.Achievement;
import com.example.group37software_engineering.model.MyUser;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AchievementRepository extends CrudRepository<Achievement, Integer> {

    public Achievement findAchievementById(Integer Id);

    public Achievement findAchievementByTitle(String title);

}
