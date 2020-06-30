package com.leoni.pfe.rest;

import com.leoni.pfe.accessingdatajpa.MembreDeReunion;
import com.leoni.pfe.services.MembreDeReunionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/Membre")
/*
@CrossOrigin(origins = {"http://localhost:4200"},
        allowedHeaders = "*",
        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})
*/
public class MembreDeReunionController {


    private final MembreDeReunionService membreDeReunionService;

    @Autowired
    public MembreDeReunionController(MembreDeReunionService membreDeReunionService) {
        this.membreDeReunionService = membreDeReunionService;
    }

    //afficher la liste des membres
    @Secured({"ROLE_ADMIN"})
    @GetMapping(path = "/findAll")
    public List<MembreDeReunion> findAllMembreDeReunion() {
        return membreDeReunionService.findAllMembreDeReunion();
    }

    // find by IdMembre
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @PostMapping(path = "/findByIdMembre", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public boolean findByIdMembre(@RequestParam(name = "idMembre") int idMembre) {

        boolean existe = membreDeReunionService.findByIdMembre(idMembre);
        return existe;
    }

    //  si mdr assiste la reunion: modifier is_present = true

    /**
     * Cette fonction permet de marquer un utilisteur comment present dans un reunion
     * @param idReunion id de reunion que l'utilsateur va assister
     * @param idMembre  id de membre qui va fr fr fr
     * @return true si l'operation effectue avec succes, sinon false
     */
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @PostMapping(path = "/membreIsPresent", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public boolean membreIsPresent(@RequestParam(value = "idReunion", defaultValue = "0") long idReunion,
                                   @RequestParam(value = "idMembre", defaultValue = "0") long idMembre) {
        if (idReunion < 1 || idMembre < 1) {
            return false;
        }
        int resultat = membreDeReunionService.membreIsPresent(idReunion, idMembre);
        return (resultat > 0); //la modification est efectuée
    }

    //ajouter une membre
    @Secured({"ROLE_ADMIN"})
    @PostMapping(path = "/saveMembre", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public MembreDeReunion saveMembre(@RequestParam("idMembre") int idMembre,
                                      @RequestParam("idReunion") int idReunion) {
        return membreDeReunionService.saveMembre(idMembre, idReunion);
    }
}
