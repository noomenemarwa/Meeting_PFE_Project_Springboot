package com.leoni.pfe.rest;

import com.leoni.pfe.accessingdatajpa.Departement;
import com.leoni.pfe.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/dep")
@CrossOrigin(origins = {"http://localhost:4200"},
        allowedHeaders = "*",
        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})
public class DepartementController {


    private final DepartmentService departmentService;


    @Autowired
    public DepartementController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @Secured({"ROLE_ADMIN"})
    @GetMapping(path = "/findAll")
    // @RequestMapping(method = {RequestMethod.GET}, path = "/findAll")
    public List<Departement> findAllDepartments() {
        return departmentService.findAllDepartments();
    }


    //supprimer un departement
    @Secured({"ROLE_ADMIN"})
    @DeleteMapping(path = "/deleteDepartement/{id}")
    public boolean deleteDepartement(@PathVariable(name = "id") long idDepartement) {
        try {
            departmentService.deleteDepartement(idDepartement);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //modifier un departement
    @Secured({"ROLE_ADMIN"})
    @PostMapping(path = "/updateDepartement/{id}", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public boolean updateDepartment(@PathVariable("id") long id,
                                    @RequestParam("nom") String nom) {
        return departmentService.updateDepartement(id, nom);
    }


    //ajouter un departement
    @Secured({"ROLE_ADMIN"})
    @PostMapping(path = "/saveDepartement", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public Departement saveDepartement(@RequestParam("nom") String nom) {
        return departmentService.saveDepartement(nom);
    }

}