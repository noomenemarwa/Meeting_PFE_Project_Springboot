package com.leoni.pfe.accessingdatajpa.dto;

import java.io.Serializable;

public class StatsParReunion implements Serializable {

    private long idReunion;
    private int nbInvite;
    private int nbAbscent;
    private int nbPresent;

    public StatsParReunion() {}

    public StatsParReunion(long idReunion, int nbInvite, int nbAbscent, int nbPresent) {
        this.idReunion = idReunion;
        this.nbInvite = nbInvite;
        this.nbAbscent = nbAbscent;
        this.nbPresent = nbPresent;
    }

    @Override
    public String toString() {
        return "StatsParReunion{" +
                "idReunion=" + idReunion +
                ", nbInvite=" + nbInvite +
                ", nbAbscent=" + nbAbscent +
                ", nbPresent=" + nbPresent +
                '}';
    }

    public long getIdReunion() {
        return idReunion;
    }

    public void setIdReunion(long idReunion) {
        this.idReunion = idReunion;
    }

    public int getNbInvite() {
        return nbInvite;
    }

    public void setNbInvite(int nbInvite) {
        this.nbInvite = nbInvite;
    }

    public int getNbAbscent() {
        return nbAbscent;
    }

    public void setNbAbscent(int nbAbscent) {
        this.nbAbscent = nbAbscent;
    }

    public int getNbPresent() {
        return nbPresent;
    }

    public void setNbPresent(int nbPresent) {
        this.nbPresent = nbPresent;
    }
}
