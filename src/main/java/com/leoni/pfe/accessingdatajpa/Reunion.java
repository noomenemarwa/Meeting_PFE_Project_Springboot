package com.leoni.pfe.accessingdatajpa;

import javax.persistence.*;
import java.util.Date;

@Entity    //pour le jpa sait que c'est une table
@Table(name = "Reunion")  //nom de la table dans la BD
public class Reunion {

    // JPA: specification
    // Hibernate: implementation de JPA
    // SpringData : utilise Hibernate

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "sujet")
    private String sujet ;

    @Column(name = "qrcode")
    private String qrcode;

    @Column(name = "date_deb")
    private String date_deb;

    @Column(name = "date_fin")
    private String  date_fin;



    public Reunion(){}

    @Override
    public String toString() {
        return "Reunion{" +
                "id=" + id +
                ", sujet='" + sujet + '\'' +
                ", qrcode='" + qrcode + '\'' +
                ", date_deb='" + date_deb + '\'' +
                ", date_fin='" + date_fin + '\'' +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public String getQrcode() {
        return qrcode;
    }

    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
    }

    public String getDate_deb() {
        return date_deb;
    }

    public void setDate_deb(String date_deb) {
        this.date_deb = date_deb;
    }

    public  String getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(String date_fin) {
        this.date_fin = date_fin;
    }

}
