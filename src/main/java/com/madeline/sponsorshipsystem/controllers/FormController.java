package com.madeline.sponsorshipsystem.controllers;

import com.madeline.sponsorshipsystem.models.League;
import com.madeline.sponsorshipsystem.repositories.LeagueRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Controller
public class FormController {
    private final LeagueRepository leagueRepo;

    public FormController(LeagueRepository leagueRepo) {
        this.leagueRepo = leagueRepo;
    }

    @GetMapping("/")
    public String viewForms(Model model) {
        model.addAttribute("league", new League());
        return "forms";
    }

    @PostMapping("/add-league")
    public String addLeague(@ModelAttribute League league) {
        leagueRepo.save(league);
        return "redirect:/";
    }

    @PostMapping("/find-leagues")
    public String sponsorLeagues(@RequestParam(name = "coords") String coords,
                                 @RequestParam(name = "radius") int radius,
                                 @RequestParam(name = "budget") int budget) {
        List<League> acceptedLeagues = findLeagues(coords, radius, budget);

        int totalSpend = 0;

        if (acceptedLeagues.size() <= 0) {
            System.out.println("Your budget of $" + budget + " cannot be used to sponsor a league.");
            System.out.println("--------------------------------------");
        } else {
            System.out.println("--------------------------------------");
            for (League acceptedLeague : acceptedLeagues) {
                System.out.println("Name:" + acceptedLeague.getName());
                System.out.println("Price:" + acceptedLeague.getPrice());
                totalSpend = totalSpend + acceptedLeague.getPrice();
            }
            System.out.println("Total Budget Used: " + totalSpend);
            System.out.println("Total Budget Remaining: " + (budget - totalSpend));
            System.out.println("--------------------------------------");
        }
        return "redirect:/";
    }

    public List<League> findLeagues(String coords, int radius, int budget) {

        List<League> leagues = leagueRepo.findAll(Sort.by(Sort.Direction.ASC, "price"));
        List<League> acceptedLeagues = new ArrayList<>();

        for (League league : leagues) {
            if (budget - league.getPrice() <= 0)
                return acceptedLeagues;
            else if (league.getPrice() <= budget) {
                budget = budget - league.getPrice();
                acceptedLeagues.add(league);
            }
        }
        return acceptedLeagues;
    }
}
