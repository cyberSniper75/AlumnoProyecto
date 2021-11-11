package com.example.alumnoproyecto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

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
            loadJSONFromAsset();
            //fragment = new AlumnoFragment();
            fragment = new AlumnoListaFragment();
            fm.beginTransaction().add(R.id.fragment_container, fragment).commit();
        }
    }

    private void loadJSONFromAsset() {
        try {
            InputStream is = this.getAssets().open("Alumnos.JSON");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            JSONString = new String(buffer, "UTF-8");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}