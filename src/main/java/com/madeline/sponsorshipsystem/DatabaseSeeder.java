package com.madeline.sponsorshipsystem;

import com.madeline.sponsorshipsystem.models.League;
import com.madeline.sponsorshipsystem.repositories.LeagueRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
class DatabaseSeeder implements CommandLineRunner {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final LeagueRepository leagueDao;

    @Value("${app.env}")
    private String environment;

    public DatabaseSeeder(LeagueRepository leagueDao) {
        this.leagueDao = leagueDao;
    }
    private void seedLeagues() {
        List<League> leagues = Arrays.asList(
                new League(1, "The Wyld Stallions", "1,1", 4500),
                new League(2, "Team Zoidberg", "1,1", 6000),
                new League(3, "The Zoomers", "1,1", 1500),
                new League(4, "North Horseburg Little League", "1,1", 3500),
                new League(5, "The Duloc Ogres", "1,1", 2500)
        );
        leagueDao.saveAll(leagues);
    }

    @Override
    public void run(String... strings) throws Exception {
        if (! environment.equals("development")) {
            log.info("app.env is not development, doing nothing.");
            return;
        }
        log.info("Deleting posts...");
        leagueDao.deleteAll();
        log.info("Seeding posts...");
        seedLeagues();
        log.info("Finished running seeders!");
    }
}