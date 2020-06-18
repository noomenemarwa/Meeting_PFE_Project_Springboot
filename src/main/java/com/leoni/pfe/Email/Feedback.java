package com.leoni.pfe.Email;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Feedback {

    @NotNull
    private String nom;

    @NotNull
    @Email
    private String email;

    @NotNull
    // @Min(10L)
    @Size(min = 10) //taille minimal de feedback 10
    private String feedback;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

}
