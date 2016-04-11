package com.finalproject.mauritorrez.tutorcontrol.DTO;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * Created by mauri on 4/8/2016.
 */
public class FaultDTO implements Serializable {

    public UUID getGuidFalta() {
        return guidFalta;
    }
    public void setGuidFalta(UUID guidFalta) {
        this.guidFalta = guidFalta;
    }
    private UUID guidFalta;

    public UUID getGuidEstudiante() {
        return guidEstudiante;
    }
    public void setGuidEstudiante(UUID guidEstudiante) {
        this.guidEstudiante = guidEstudiante;
    }
    private UUID guidEstudiante;

    public UUID getGuidProfesor() {
        return guidProfesor;
    }
    public void setGuidProfesor(UUID guidProfesor) {
        this.guidProfesor = guidProfesor;
    }
    private UUID guidProfesor;

    public UUID getGuidAdministrador() {
        return guidAdministrador;
    }
    public void setGuidAdministrador(UUID guidAdministrador) {
        this.guidAdministrador = guidAdministrador;
    }
    private UUID guidAdministrador;

    public UUID getGuidTipoFalta() {
        return guidTipoFalta;
    }
    public void setGuidTipoFalta(UUID guidTipoFalta) {
        this.guidTipoFalta = guidTipoFalta;
    }
    private UUID guidTipoFalta;


    public String getMensaje() {
        return mensaje;
    }
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    private String mensaje;


    public String getRespuesta() {
        return respuesta;
    }
    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }
    private String respuesta;


    public Date getFechaEnviado() {
        return fechaEnviado;
    }
    public void setFechaEnviado(Date fechaEnviado) {
        this.fechaEnviado = fechaEnviado;
    }
    private Date fechaEnviado;

    public Date getFechaLeido() {
        return fechaLeido;
    }
    public void setFechaLeido(Date fechaLeido) {
        this.fechaLeido = fechaLeido;
    }
    private Date fechaLeido;

    public String getTipoFaltaText() {
        return tipoFaltaText;
    }
    public void setTipoFaltaText(String tipoFaltaText) {
        this.tipoFaltaText = tipoFaltaText;
    }
    private String tipoFaltaText;

}
