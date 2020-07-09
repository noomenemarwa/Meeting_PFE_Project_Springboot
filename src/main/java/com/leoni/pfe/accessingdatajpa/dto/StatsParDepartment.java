
package com.leoni.pfe.accessingdatajpa.dto;

import java.util.List;


@SuppressWarnings("unused")
public class StatsParDepartment {

    private String dateDeb;
    private String dateFin;
    private List<Department> departments;

    public StatsParDepartment() {
    }

    public StatsParDepartment(String dateDeb, String dateFin, List<Department> departments) {
        this.dateDeb = dateDeb;
        this.dateFin = dateFin;
        this.departments = departments;
    }

    public String getDateDeb() {
        return dateDeb;
    }

    public void setDateDeb(String dateDeb) {
        this.dateDeb = dateDeb;
    }

    public String getDateFin() {
        return dateFin;
    }

    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }
}
