
package com.leoni.pfe.accessingdatajpa.dto;

@SuppressWarnings("unused")
public class Department {

    private long idDep;
    private String nomDep;
    private long total;
    private long abscent;
    private long pourcentageAbscence;

    public Department() {

    }

    public Department(long idDep, String nomDep, long total, long abscent, long pourcentageAbscence) {
        this.idDep = idDep;
        this.nomDep = nomDep;
        this.total = total;
        this.abscent = abscent;
        this.pourcentageAbscence = pourcentageAbscence;
    }

    public long getIdDep() {
        return idDep;
    }

    public void setIdDep(long idDep) {
        this.idDep = idDep;
    }

    public String getNomDep() {
        return nomDep;
    }

    public void setNomDep(String nomDep) {
        this.nomDep = nomDep;
    }

    public long getPourcentageAbscence() {
        return pourcentageAbscence;
    }

    public void setPourcentageAbscence(long pourcentageAbscence) {
        this.pourcentageAbscence = pourcentageAbscence;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public long getAbscent() {
        return abscent;
    }

    public void setAbscent(long abscent) {
        this.abscent = abscent;
    }
}
