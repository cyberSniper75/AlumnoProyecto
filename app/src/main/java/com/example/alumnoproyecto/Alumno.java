package com.example.alumnoproyecto;

import java.util.Date;

public class Alumno {
    private int matricula;
    private String nombre;
    private Date fechaCumpleanos;
    private boolean activo;

    public Alumno() {
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaCumpleanos() {
        return fechaCumpleanos;
    }

    public void setFechaCumpleanos(Date fechaCumpleanos) {
        this.fechaCumpleanos = fechaCumpleanos;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}
