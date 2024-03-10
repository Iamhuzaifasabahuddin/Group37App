package com.example.group37software_engineering;

import com.example.group37software_engineering.model.Achievement;
import com.example.group37software_engineering.repo.AchievementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Service class for reading data from a CSV file and saving it to the AchievementRepository.
 */
@Service
public class AchievementsData {

    @Autowired
    private AchievementRepository achievementRepository;

    /**
     * Read data from a CSV file and save it to the AchievementRepository.
     *
     * @param csvFilePath The path to the CSV file containing achievement data.
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
