package ru.denisss.footballplayerscatalog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.denisss.footballplayerscatalog.repository.FootballerRepository;

@Controller
public class MainController {

    @Autowired
    private FootballerRepository footballerRepository;

    @GetMapping("/")
    public String index(Model model,
                        @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC, size = 9) Pageable pageable
    ) {

        var footballers = footballerRepository.findAll(pageable);
        model.addAttribute("footballers", footballers);
        model.addAttribute("pages", footballers.getTotalPages());
        return "catalog";
    }
}
