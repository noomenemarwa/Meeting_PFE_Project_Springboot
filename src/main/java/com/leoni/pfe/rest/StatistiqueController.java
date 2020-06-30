package com.leoni.pfe.rest;

import com.leoni.pfe.accessingdatajpa.dto.StatsParReunion;
import com.leoni.pfe.services.MembreDeReunionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/stat")
/*@CrossOrigin(origins = {"http://localhost:4200"},
        allowedHeaders = "*",
        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})*/
public class StatistiqueController {


    private final MembreDeReunionService membreDeReunionService;

    @Autowired
    public StatistiqueController(MembreDeReunionService membreDeReunionService) {
        this.membreDeReunionService = membreDeReunionService;
    }


    @Secured({"ROLE_ADMIN"})
    @GetMapping(path = "/parReunion", produces = {MediaType.APPLICATION_JSON_VALUE})
    public StatsParReunion barGraphStatic(@RequestParam(value = "idReunion", defaultValue = "0") long idReunion) {
        if (idReunion < 1) {
            return null;
        }
       return membreDeReunionService.statsParReunion(idReunion);
    }

    @Secured({"ROLE_ADMIN"})
    @GetMapping(path = "/pieChartGraph")
    public String pieChartGraph(Model model) {
        model.addAttribute("Absence", 30);
        model.addAttribute("Presence", 80);
        return "pie_chart";
    }
}
