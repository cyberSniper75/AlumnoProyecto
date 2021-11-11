package com.example.alumnoproyecto;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class GrupoAlumnos {
    public static ArrayList<Alumno> listaAlumnos;
    private static GrupoAlumnos grupo = new GrupoAlumnos();

    private GrupoAlumnos() {
        listaAlumnos = new ArrayList<Alumno>();
        try{
            JSONArray jsonArray = new JSONArray(MainActivity.JSONString);
            for(int i = 0; i<jsonArray.length(); i++){
                JSONObject obj = jsonArray.getJSONObject(i);
                Alumno alumno = new Alumno();
                alumno.setMatricula(obj.getInt("matricula"));
                alumno.setNombre(obj.getString("nombre"));
                alumno.setActivo(obj.getBoolean("activo"));
                listaAlumnos.add(alumno);
            }
        }catch (JSONException je){
            je.printStackTrace();
        }catch(NullPointerException ex){
            listaAlumnos = new ArrayList<Alumno>();
            for (int  i = 0 ; i<20; i++){
                Alumno alumno = new Alumno();
                alumno.setMatricula(i);
                alumno.setNombre("Alumno #" + i);
                alumno.setActivo(i%2==0);
                listaAlumnos.add(alumno);
            }
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