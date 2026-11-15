package com.example.aplicatie_seminar;

import java.util.Date;

public class Student {

    private String nume;
    private Date dataNasterii;
    private float medie;
    private String Facultate;
    private Boolean areBursa;

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public Date getDataNasterii() {
        return dataNasterii;
    }

    public void setDataNasterii(Date dataNasterii) {
        this.dataNasterii = dataNasterii;
    }

    public float getMedie() {
        return medie;
    }

    public void setMedie(float medie) {
        this.medie = medie;
    }

    public String getFacultate() {
        return Facultate;
    }

    public void setFacultate(String facultate) {
        Facultate = facultate;
    }

    public Boolean getAreBursa() {
        return areBursa;
    }

    public void setAreBursa(Boolean areBursa) {
        this.areBursa = areBursa;
    }

    public Student(String nume, Date dataNasterii, float medie, String facultate, Boolean areBursa) {
        this.nume = nume;
        this.dataNasterii = dataNasterii;
        this.medie = medie;
        Facultate = facultate;
        this.areBursa = areBursa;
    }

    @Override
    public String toString() {
        return "Student{" +
                "nume='" + nume + '\'' +
                ", dataNasterii=" + dataNasterii +
                ", medie=" + medie +
                ", Facultate='" + Facultate + '\'' +
                ", areBursa=" + areBursa +
                '}';
    }
}
