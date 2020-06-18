package com.leoni.pfe.accessingdatajpa.Repository;

import com.leoni.pfe.accessingdatajpa.Personne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
public interface PersonneRepository extends JpaRepository<Personne,Long> {

    //afficher les personnes par password et email
    List<Personne> findByEmailAndPassword(String email, String password);

    //modifier une personne
    @Query(value = "update Personne P set P.fonction=?1, P.email=?2 , P.password=?3, P.prenom=?4, P.nom=?5, P.type=?6, P.matricule=?7,P.cin=?8, id_dep=?9 where P.id=?10")
    @Modifying
    int updatePersonne(String fonction,String email,String password, String prenom, String nom, String type, int matricule, String cin,long idDep, long id);


    //supprimer une personne
    /*@Query(value ="delete from Personne P where P.id=?1" )
    @Modifying
    int deletePersonne(long id);*/


}
