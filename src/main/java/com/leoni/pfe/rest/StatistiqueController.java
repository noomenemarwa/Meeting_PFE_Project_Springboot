package com.leoni.pfe.rest;

import com.leoni.pfe.accessingdatajpa.dto.StatsParDepartment;
import com.leoni.pfe.accessingdatajpa.dto.StatsParReunion;
import com.leoni.pfe.services.MembreDeReunionService;
import com.leoni.pfe.services.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
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
    private final StatsService statsService;

    @Autowired
    public StatistiqueController(MembreDeReunionService membreDeReunionService, StatsService statsService) {
        this.membreDeReunionService = membreDeReunionService;
        this.statsService = statsService;
    }


    @Secured({"ROLE_ADMIN"})
    @GetMapping(path = "/parReunion", produces = {MediaType.APPLICATION_JSON_VALUE})
    public StatsParReunion statsParReunion(@RequestParam(value = "idReunion", defaultValue = "0") long idReunion) {
        if (idReunion < 1) {
            return null;
        }
       return membreDeReunionService.statsParReunion(idReunion);
    }

    @Secured({"ROLE_ADMIN"})
    @GetMapping(path = "/parDepartment")
    public StatsParDepartment statsParDepartment(@RequestParam("date_deb") String dateDeb,
                                                 @RequestParam("date_fin") String dateFin) {
        return statsService.statsParDepartements(dateDeb, dateFin); // "2020-11-15 10:00:00", "2020-11-17 12:00:00"
    }
}
