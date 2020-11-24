package com.madeline.sponsorshipsystem.controllers;

import com.madeline.sponsorshipsystem.models.League;
import com.madeline.sponsorshipsystem.repositories.LeagueRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String showLeagues( @RequestParam(name = "coords") String coords,
                               @RequestParam(name = "radius") int radius,
                               @RequestParam(name = "budget") int budget){
    List<League> acceptedLeagues = findLeagues(coords, radius, budget);

    for (League league: acceptedLeagues){
        System.out.println("Name:" + league.getName());
        System.out.println("Price:" + league.getPrice());
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
