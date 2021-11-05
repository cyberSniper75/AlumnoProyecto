package com.example.alumnoproyecto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

public class AlumnoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alumno);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.alumno_fragment_container);
        if (fragment == null){
            fragment = new AlumnoFragment();
            Bundle data = new Bundle();
            int matricula = getIntent().getIntExtra(Constantes.ID_ALUMNO, -1);
            data.putInt(Constantes.ID_ALUMNO,matricula);
            fragment.setArguments(data);
            fm.beginTransaction().add(R.id.alumno_fragment_container, fragment).commit();
        }
    }
}