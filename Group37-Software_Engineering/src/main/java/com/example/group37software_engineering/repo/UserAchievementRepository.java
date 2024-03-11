package com.example.group37software_engineering.repo;

import com.example.group37software_engineering.model.Achievement;
import com.example.group37software_engineering.model.MyUser;
import com.example.group37software_engineering.model.UserAchievement;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface UserAchievementRepository extends CrudRepository<UserAchievement, Integer>{
        public UserAchievement findUserAchievementByUserAndAchievement(MyUser user, Achievement achievement);
        public UserAchievement findUserAchievementById(Integer Id);

}
