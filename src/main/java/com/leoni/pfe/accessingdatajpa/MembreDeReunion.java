package com.leoni.pfe.accessingdatajpa;

import javax.persistence.*;

@Entity
@Table(name = "membredereunion")
public class MembreDeReunion {

    // 1. list id reunion [id]
    // 2. list membre de reunion [idMembre, isPresent]
    // 3. list person [idDep]
    // 4. list departments [id, nom]

    // 2. select id_membre, is_present from membredereunion where id_reunion in (...) => list
    // 3. select id, id_dep from personne where id in (list id_membre) => list
    // 4.

    //  select mdp.id_membre, mdp.is_present,pers.id_dep, dep.id, dep.nom from membredereunion mdp, personne pers, departement dep where mdp.id_reunion in (1, 2, 3) and pers.id=mdp.id_reunion and pers.id_dep=dep.id group by mdp.id_membre;



    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "id_membre")
    private long idMembre;

    @Column(name = "id_reunion")
    private long idReunion;

    @Column(name = "is_present")
    private int isPresent;

    public MembreDeReunion() {
    }

    @Override
    public String toString() {
        return "MembreDeReunion{" +
                "id=" + id +
                ", idMembre=" + idMembre +
                ", idReunion=" + idReunion +
                ", isPresent=" + isPresent +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdMembre() {
        return idMembre;
    }

    public void setIdMembre(long idMembre) {
        this.idMembre = idMembre;
    }

    public long getIdReunion() {
        return idReunion;
    }

    public void setIdReunion(long idReunion) {
        this.idReunion = idReunion;
    }

    public int getIsPresent() {
        return isPresent;
    }

    public void setIsPresent(int isPresent) {
        this.isPresent = isPresent;
    }
}
