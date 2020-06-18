package com.leoni.pfe.accessingdatajpa;

import javax.persistence.*;

@Entity
@Table(name = "membredereunion")
public class MembreDeReunion {

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
