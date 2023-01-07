package ru.denisss.footballplayerscatalog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.denisss.footballplayerscatalog.entity.Footballer;
import ru.denisss.footballplayerscatalog.entity.Team;
import ru.denisss.footballplayerscatalog.repository.FootballerRepository;
import ru.denisss.footballplayerscatalog.repository.TeamRepository;

import java.text.ParseException;

@Controller
public class FootballerController {

    @Autowired
    private FootballerRepository footballerRepository;
    @Autowired
    private TeamRepository teamRepository;


    @GetMapping("/edit/footballer/{id}")
    public String edit(@PathVariable Long id, Model model) {
        var teams = teamRepository.findAll();
        model.addAttribute("teams", teams.subList(0, Math.min(teams.size(), 20)));
        model.addAttribute("footballer", footballerRepository.findById(id).get());
        return "edit-footballer";
    }

    @PostMapping("/edit/footballer")
    public String edit(@RequestParam("id") Long id,
                       @RequestParam("name") String name,
                       @RequestParam("surname") String surname,
                       @RequestParam("gender") String gender,
                       @RequestParam("birthday") String birthday,
                       @RequestParam("team") String teamName,
                       @RequestParam("country") String country) throws ParseException {


        var footballer = footballerRepository.findById(id).get();

        footballer.setName(name);
        footballer.setSurname(surname);
        footballer.setGender(gender);
        footballer.setBirthday(birthday);
        footballer.setCountry(country);


        var team = teamRepository.findByName(teamName);

        if (team == null) {
            team = new Team(teamName);
            teamRepository.save(team);
            footballer.setTeam(team);
        } else {
            footballer.setTeam(team);
        }

        footballerRepository.save(footballer);

        return "redirect:/";
    }

    @GetMapping("/add/footballer")
    public String add(Model model) {
        var teams = teamRepository.findAll();
        model.addAttribute("teams", teams.subList(0, Math.min(teams.size(), 20)));
        return "add-footballer";
    }

    @MessageMapping("/get-footballer")
    @SendTo("/topic/send-footballer")
    public Footballer add(Footballer footballer) {

        var team = teamRepository.findByName(footballer.getTeam().getName());

        if (team == null)
            teamRepository.save(footballer.getTeam());
        else
            footballer.setTeam(team);


        footballerRepository.save(footballer);
        return footballer;
    }

}
