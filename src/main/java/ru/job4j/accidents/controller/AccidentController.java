package ru.job4j.accidents.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.service.AccidentService;

@Controller
@AllArgsConstructor
@RequestMapping("/accidents")
public class AccidentController {
    private final AccidentService accidentService;

    @GetMapping("/createForm")
    public String viewCreateAccident() {
        return "createAccident";
    }

    @GetMapping("/updateForm")
    public String viewUpdateAccident(@RequestParam int id) {
        return "editAccident";
    }

    @PostMapping("/create")
    public String save(@ModelAttribute Accident accident) {
        accidentService.save(accident);
        return "redirect:/";
    }

    @PostMapping("/update")
    public String viewUpdateAccident(@ModelAttribute Accident accident) {
        return "redirect:/";
    }
}
