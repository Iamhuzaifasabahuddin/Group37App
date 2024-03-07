package com.example.group37software_engineering.repo;

import com.example.group37software_engineering.model.League;
import org.springframework.data.repository.CrudRepository;

public interface LeagueRepository extends CrudRepository<League, Integer> {

    public League findLeagueByTitle(String Title);

    public League findLeagueById(Integer Id);

    League findByTitle(String rank);
}
