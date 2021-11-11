package com.example.alumnoproyecto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    public static String JSONString;

    public MainActivity() {
        JSONString = null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);
        if (fragment == null){
            loadJSON();
            //fragment = new AlumnoFragment();
            fragment = new AlumnoListaFragment();
            fm.beginTransaction().add(R.id.fragment_container, fragment).commit();
        }
    }

    @Override
    protected void onStop(){
        super.onStop();
        try {
            saveJSON();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Toast.makeText(getApplicationContext(), "onStop called", Toast.LENGTH_LONG).show();
    }

    private void loadJSON(){
        try{
            File file = new File(this.getFilesDir(), Constantes.JSONFileName);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            StringBuilder stringBuilder = new StringBuilder();
            String line = bufferedReader.readLine();
            while (line != null){
                stringBuilder.append(line).append("\n");
                line = bufferedReader.readLine();
            }
            bufferedReader.close();

            JSONString = stringBuilder.toString();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    private void saveJSON() throws JSONException, IOException {
        JSONArray jsonArray = new JSONArray();
        for(Alumno alumno: GrupoAlumnos.listaAlumnos){
            JSONObject JsonObject = new JSONObject();
            JsonObject.put("matricula", alumno.getMatricula());
            JsonObject.put("nombre", alumno.getNombre());
            JsonObject.put("activo", alumno.isActivo());
            jsonArray.put(JsonObject);
        }

        String userString = jsonArray.toString();
        File file = new File(this.getFilesDir(), Constantes.JSONFileName);
        FileWriter fileWriter = new FileWriter(file);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(userString);
        bufferedWriter.close();
    }
}