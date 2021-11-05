package com.example.alumnoproyecto;

import java.util.ArrayList;

public class GrupoAlumnos {
    private ArrayList<Alumno> listaAlumnos;
    private static GrupoAlumnos grupo = new GrupoAlumnos();

    private GrupoAlumnos(){
        listaAlumnos = new ArrayList<Alumno>();
        for (int  i = 0 ; i<20; i++){
            Alumno alumno = new Alumno();
            alumno.setMatricula(i);
            alumno.setNombre("Alumno #" + i);
            alumno.setActivo(i%2==0);
            listaAlumnos.add(alumno);
        }
    }

    public static GrupoAlumnos getGrupo(){
        return grupo;
    }

    public ArrayList<Alumno> getListaAlumnos(){
        return listaAlumnos;
    }

    public Alumno getAlumno(int matricula){
        for(Alumno alumno: listaAlumnos){
            if(alumno.getMatricula() == matricula){
                return alumno;
            }
        }
        return null;
    }
}