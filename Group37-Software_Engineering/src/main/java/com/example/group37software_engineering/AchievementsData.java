package com.example.group37software_engineering;

import com.example.group37software_engineering.model.Achievement;
import com.example.group37software_engineering.model.League;
import com.example.group37software_engineering.repo.AchievementRepository;
import com.example.group37software_engineering.repo.LeagueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


@Service
public class AchievementsData {

    @Autowired
    private AchievementRepository achievementRepository;

    /**
     * Constructor for LeagueData.
     *
     * @param leagueRepository The repository for managing League entities.
     */
    /**
     * Read data from a CSV file and save it to the LeagueRepository.
     *
     * @param csvFilePath The path to the CSV file containing league data.
     */
    public void readCSVAndSaveToRepo(String csvFilePath) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                new ClassPathResource(csvFilePath).getInputStream()))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                Achievement achievement = new Achievement();
                achievement.setTitle(data[0].trim());
                achievement.setDescription(data[1].trim());
                achievement.setImageUrl(data[2].trim());
                achievement.setPoints(Integer.parseInt(data[3].trim()));
                achievementRepository.save(achievement);
            }
        } catch (IOException | ArrayIndexOutOfBoundsException | NumberFormatException e) {
            e.printStackTrace();
        }
    }
}