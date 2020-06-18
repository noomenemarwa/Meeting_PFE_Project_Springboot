package com.leoni.pfe.rest;


import com.leoni.pfe.accessingdatajpa.Personne;
import com.leoni.pfe.services.PersonneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import java.util.List;

@RestController
@RequestMapping(path = "/personne")
@CrossOrigin(origins = {"http://localhost:4200"})
public class PersonneController {

    private final PersonneService personneService;

    @Autowired // Injection de dependence
    public PersonneController(PersonneService personneService) {
        this.personneService = personneService;
    }


    //afficher tous la liste des personnes
    @Secured({"ROLE_ADMIN"})
    @GetMapping(path = "/findAll")
    public List<Personne> findAllDepartments() {
        return personneService.findAllPersonne();
    }


    //définir l'URI qui sera utilisé pour lancer la demande à effectuer
   // @Secured({"ROLE_ADMIN","ROLE_USER"})
    @PermitAll
    @PostMapping(path = "/login", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public Personne login(@RequestParam(name = "email") String email,
                          @RequestParam(name = "password") String password) {

        Personne personne = personneService.login(email, password);
        return (personne);
    }


    // supprimer une personne
    @Secured({"ROLE_ADMIN"})
    @DeleteMapping(path = "/deletePersonne/{id}")
    public boolean deletePersonne(@PathVariable(name = "id") long idPersonne) {
        try {
            personneService.deletePersonne(idPersonne);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    // modifier une personne
    @Secured({"ROLE_ADMIN"})
    @PostMapping(path = "/updatePersonne", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public boolean updatePersonne(@RequestParam(value = "id") Long id,
                                  @RequestParam("id_dep") long idDep,
                                  @RequestParam("matricule") int matricule,
                                  @RequestParam("prenom") String prenom,
                                  @RequestParam("nom") String nom,
                                  @RequestParam("fonction") String fonction,
                                  @RequestParam("type") String type,
                                  @RequestParam("email") String email,
                                  @RequestParam("cin") String cin,
                                  @RequestParam(value = "password", required = false) String password) {
        /*System.out.println(String.format("update personne request: id: %s, idDep: %s, password: %s",
                String.valueOf(id), String.valueOf(idDep), String.valueOf(password)));*/
        return personneService.updatePersonne(id, idDep, matricule, prenom, nom, fonction, type, email, cin, password);
    }

    /*@Secured({"ROLE_ADMIN"})
    @PutMapping(path = {"/updatePersonne"}, consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public boolean anotherUpdatePersonne(@RequestParam("idd") String id) {
        System.out.println(String.format("received update personne:  id: %s", id));
        return true;
    }*/

    //ajouter une personne
    @Secured({"ROLE_ADMIN"})
    @PostMapping(path = "/savePersonne", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public Personne savePersonne(@RequestParam("matricule") int matricule,
                                 @RequestParam("id_dep") long idDep,
                                 @RequestParam("prenom") String prenom,
                                 @RequestParam("nom") String nom,
                                 @RequestParam("fonction") String fonction,
                                 @RequestParam("type") String type,
                                 @RequestParam("email") String email,
                                 @RequestParam("cin") String cin,
                                 @RequestParam("password") String password) {

        System.out.println("cin received in controller: " + cin);

        return personneService.savePersonne(matricule, idDep, prenom, nom, fonction, type, email, cin, password);
    }

}
