package ru.job4j.accidents.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.service.AccidentService;
import ru.job4j.accidents.service.AccidentTypeService;
import ru.job4j.accidents.service.RuleService;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/accidents")
public class AccidentController {
    private final AccidentService accidentService;
    private final AccidentTypeService accidentTypeService;
    private final RuleService ruleService;

    @GetMapping("/createForm")
    public String viewCreateAccident(Model model) {
        model.addAttribute("types", accidentTypeService.findAll());
        model.addAttribute("rules", ruleService.findAll());
        return "createAccident";
    }

    @GetMapping("/updateForm")
    public String viewUpdateAccident(@RequestParam int id, Model model) {
        model.addAttribute("accident", accidentService.findById(id).get());
        model.addAttribute("types", accidentTypeService.findAll());
        model.addAttribute("rules", ruleService.findAll());
        return "editAccident";
    }

    @PostMapping("/create")
    public String save(@ModelAttribute Accident accident, @RequestParam(value = "rIds") List<Integer> rIds) {
        accidentService.save(accident, rIds);
        return "redirect:/";
    }

    @PostMapping("/update")
    public String viewUpdateAccident(@ModelAttribute Accident accident, @RequestParam(value = "rIds") List<Integer> rIds) {
        accidentService.update(accident, rIds);
        return "redirect:/";
    }
}
