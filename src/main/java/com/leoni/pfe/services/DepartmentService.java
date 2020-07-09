package com.leoni.pfe.services;

import com.leoni.pfe.accessingdatajpa.Departement;
import com.leoni.pfe.accessingdatajpa.Repository.DepartementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DepartmentService {


    private final DepartementRepository departementRepository;

    @Autowired
    public DepartmentService(DepartementRepository departementRepository) {
        this.departementRepository = departementRepository;
    }

    //afficher la liste des departements
    public List<Departement> findAllDepartments() {
        return departementRepository.findAll();
    }

    List<Departement> findAllDepByIds(List<Long> idDeps) {
        return departementRepository.findByIdIn(idDeps);
    }

    //supprimer un departement
    @Transactional //s'execute dans une transaction
    public void deleteDepartement(long id) {
        departementRepository.deleteById(id);
    }


    //modifier un departement
    @Transactional //s'execute dans une transaction
    public boolean updateDepartement(long id, String nom) {
        int updateCount = departementRepository.updateDepartement(nom, id); //int car retourner les nombre des modifocations
        return updateCount > 0;
    }

    //ajouter un departement
    public Departement saveDepartement(String nom) {
        Departement departement = new Departement();
        departement.setNom(nom);
        return departementRepository.save(departement);
    }


}
