package com.finalproject.mauritorrez.schoolcontrol.DTO;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by mauri on 3/30/2016.
 */
public class CourseDTO implements Serializable {

    public UUID getGuidCurso() {
        return guidCurso;
    }
    public void setGuidCurso(UUID guidCurso) {
        this.guidCurso = guidCurso;
    }
    private UUID guidCurso;


    public String getNomCurso() {
        return nomCurso;
    }
    public void setNomCurso(String nomCurso) {
        this.nomCurso = nomCurso;
    }
    private String nomCurso;


    public String getGradoCurso() {
        return gradoCurso;
    }
    public void setGradoCurso(String gradoCurso) {
        this.gradoCurso = gradoCurso;
    }
    private String gradoCurso;


    public int getNivel() {
        return nivel;
    }
    public void setNivel(int nivel) {
        this.nivel = nivel;
    }
    private int nivel;

}
