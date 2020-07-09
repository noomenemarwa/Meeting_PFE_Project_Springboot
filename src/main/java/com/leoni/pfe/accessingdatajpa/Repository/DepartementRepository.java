package com.leoni.pfe.accessingdatajpa.Repository;

import com.leoni.pfe.accessingdatajpa.Departement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface DepartementRepository extends JpaRepository<Departement,Long> {


    //modifier un departement
    @Query(value = "update Departement d set d.nom=?1 where d.id=?2")
    @Modifying
    int updateDepartement(String name, long id);

    List<Departement> findByIdIn(List<Long> idDeps);


    //supprimer un departement
   /* @Query(value ="delete from Departement d where d.id=?1" )
    @Modifying
    int deleteDepartement(long id);*/
}
