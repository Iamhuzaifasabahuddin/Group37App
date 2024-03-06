package com.example.group37software_engineering;

import com.example.group37software_engineering.model.League;
import com.example.group37software_engineering.repo.LeagueRepository;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


@Service
public class LeagueData {
    private final LeagueRepository leagueRepository;

    /**
     * Constructor for LeagueData.
     *
     * @param leagueRepository The repository for managing League entities.
     */
    public LeagueData(LeagueRepository leagueRepository) {
        this.leagueRepository = leagueRepository;
    }

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
                String[] data = line.split(","); // Assuming CSV has comma as delimiter
                League league = new League();
                league.setTitle(data[0].trim());
                league.setImageUrl(data[1].trim());
                leagueRepository.save(league);
            }
        } catch (IOException | ArrayIndexOutOfBoundsException | NumberFormatException e) {
            // Handle exceptions appropriately
            e.printStackTrace();
        }
    }
}