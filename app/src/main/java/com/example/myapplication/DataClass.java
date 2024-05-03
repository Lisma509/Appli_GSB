package com.example.myapplication;

public class DataClass {
    String date,praticien,motif, bilan, medicament, avis_visiteur, id_form, id_user;




    public DataClass(String date, String praticien, String motif, String bilan, String medicament, String avis_visiteur, String id_form, String id_user ) {
        this.date = date;
        this.praticien = praticien;
        this.motif = motif;
        this.bilan = bilan;
        this.medicament = medicament;
        this.avis_visiteur = avis_visiteur;
        this.id_form = id_form;
        this.id_user = id_user;


    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public String getId_form() {
        return id_form;
    }

    public void setId_form(String id_form) {
        this.id_form = id_form;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public String getBilan() {
        return bilan;
    }

    public void setBilan(String bilan) {
        this.bilan = bilan;
    }

    public String getMedicament() {
        return medicament;
    }

    public void setMedicament(String medicament) {
        this.medicament = medicament;
    }

    public String getAvis_visiteur() {
        return avis_visiteur;
    }

    public void setAvis_visiteur(String avis_visiteur) {
        this.avis_visiteur = avis_visiteur;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPraticien() {
        return praticien;
    }

    public void setPraticien(String praticien) {
        this.praticien = praticien;
    }
}
