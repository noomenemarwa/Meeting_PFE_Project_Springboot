package com.leoni.pfe.rest;

import com.leoni.pfe.accessingdatajpa.Reunion;
import com.leoni.pfe.services.ReunionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/Reunion")
@CrossOrigin(origins = {"http://localhost:4200"})
public class ReunionController {

    private final ReunionService reunionService;

    @Autowired //injection de dépondance
    public ReunionController(ReunionService reunionService) {
        this.reunionService = reunionService;
    }

    //afficher la liste des reunion
    @Secured({"ROLE_ADMIN"})
    @GetMapping(path = "/findAll")
    public List<Reunion> findAllReunion() {
        return reunionService.findAllReunion();
    }

    @Secured({"ROLE_USER"})
    @GetMapping(path = "/findMyReunion/{idMembre}")
    public List<Reunion> findMyReunion(@PathVariable("idMembre") int idMembre) {
        return reunionService.findMyReunion(idMembre);
    }


    //modifier une reunion
    @Secured({"ROLE_ADMIN"})
    @PostMapping(path = "/updateReunion")
    public boolean updateReunion(@RequestParam("id") long id,
                                 @RequestParam("sujet") String sujet,
                                 @RequestParam("date_deb") String date_deb,
                                 @RequestParam("date_fin") String date_fin) {
        return reunionService.updateReunion(id, sujet, date_deb, date_fin);
    }

    //supprimer une reunion
    @Secured({"ROLE_ADMIN"})
    @DeleteMapping(path = "/deleteReunion/{id}")
    public boolean deleteReunion(@PathVariable(name = "id") long idReunion) {
        try {
            reunionService.deleteReunion(idReunion);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // ajouter une reunion
    @Secured({"ROLE_ADMIN"})
    @PostMapping(path = "/saveReunion", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public Reunion saveReunion(@RequestParam("sujet") String sujet,
                               @RequestParam("date_deb") String date_deb,
                               @RequestParam("date_fin") String date_fin) {
        return reunionService.saveReunion(sujet, date_deb, date_fin);
    }
}
