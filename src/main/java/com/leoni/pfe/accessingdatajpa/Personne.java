package com.leoni.pfe.accessingdatajpa;

import javax.persistence.*;

@Entity
@Table(name = "Personne")
public class Personne {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @Column(name = "matricule", unique = true)
    private int matricule;

    @Column(name = "id_dep")
    private long idDep;

    private String prenom;

    private String nom;

    private String fonction;

    private String type;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "cin")
    private String cin;

    public Personne() {} //default constructor

    @Override
    public String toString() {
        return "Personne{" +
                "id=" + id +
                ", matricule=" + matricule +
                ", idDep=" + idDep +
                ", prenom='" + prenom + '\'' +
                ", nom='" + nom + '\'' +
                ", fonction='" + fonction + '\'' +
                ", type='" + type + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", cin=" + cin +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getMatricule() {
        return matricule;
    }

    public void setMatricule(int matricule) {
        this.matricule = matricule;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getFonction() {
        return fonction;
    }

    public void setFonction(String fonction) {
        this.fonction = fonction;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getIdDep() {
        return idDep;
    }

    public void setIdDep(long idDep) {
        this.idDep = idDep;
    }

    public String getCin(String cin) { return this.cin; }

    public void setCin(String cin) { this.cin = cin; }
}
