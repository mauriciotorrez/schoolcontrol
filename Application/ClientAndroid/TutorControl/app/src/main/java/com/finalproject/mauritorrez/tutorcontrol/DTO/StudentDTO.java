package com.finalproject.mauritorrez.tutorcontrol.DTO;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by mauri on 4/8/2016.
 */
public class StudentDTO implements Serializable {

    public UUID getGuidEstudiante() {
        return guidEstudiante;
    }
    public void setGuidEstudiante(UUID guidEstudiante) {
        this.guidEstudiante = guidEstudiante;
    }
    private UUID guidEstudiante;


    public String getNombre1() {
        return nombre1;
    }
    public void setNombre1(String nombre1) {
        this.nombre1 = nombre1;
    }
    private String nombre1;


    public String getNombre2() {
        return nombre2;
    }
    public void setNombre2(String nombre2) {
        this.nombre2 = nombre2;
    }
    private String nombre2;

    public String getApellido_paterno() {
        return apellido_paterno;
    }
    public void setApellido_paterno(String apellido_paterno) {
        this.apellido_paterno = apellido_paterno;
    }
    private String apellido_paterno;

    public String getApellido_materno() {
        return apellido_materno;
    }
    public void setApellido_materno(String apellido_materno) {
        this.apellido_materno = apellido_materno;
    }
    private String apellido_materno;

    public String getMail1() {
        return mail1;
    }
    public void setMail1(String mail1) {
        this.mail1 = mail1;
    }
    private String mail1;

    public String getMail2() {
        return mail2;
    }
    public void setMail2(String mail2) {
        this.mail2 = mail2;
    }
    private String mail2;

    public String getFono() {
        return fono;
    }
    public void setFono(String fono) {
        this.fono = fono;
    }
    private String fono;

    public String getCel() {
        return cel;
    }
    public void setCel(String cel) {
        this.cel = cel;
    }
    private String cel;

    public String getDir1() {
        return dir1;
    }
    public void setDir1(String dir1) {
        this.dir1 = dir1;
    }
    private String dir1;

    public String getDir2() {
        return dir2;
    }
    public void setDir2(String dir2) {
        this.dir2 = dir2;
    }
    private String dir2;


}
