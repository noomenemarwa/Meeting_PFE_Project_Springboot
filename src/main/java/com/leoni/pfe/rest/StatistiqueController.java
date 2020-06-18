package com.leoni.pfe.rest;

import org.springframework.security.access.annotation.Secured;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping(path = "/stat")
@CrossOrigin(origins = {"http://localhost:4200"})
public class StatistiqueController {
    @Secured({"ROLE_ADMIN"})
    @GetMapping(path = "/barGraphStatic")
    public String barGraphStatic(Model model) {
        Map<String, Integer> surveyMap = new LinkedHashMap<>();
        surveyMap.put("SSH&IT", 25);
        surveyMap.put("PPE", 18);
        surveyMap.put("VW", 17);
        surveyMap.put("QM", 13);
        surveyMap.put("BMW", 7);
        surveyMap.put("DF", 25);
        surveyMap.put("SYS APP", 5);
        return "barGraph";
    }

    @Secured({"ROLE_ADMIN"})
    @GetMapping(path = "/pieChartGraph")
    public String pieChartGraph(Model model) {
        model.addAttribute("Absence", 30);
        model.addAttribute("Presence", 80);
        return "pie_chart";
    }
}
