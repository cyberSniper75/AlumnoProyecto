package com.example.alumnoproyecto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import java.util.List;

public class AlumnoPagerActivity extends AppCompatActivity {

    private ViewPager2 alumnoViewPager;
    private List<Alumno> grupoAlumnos;

    class AlumnoPagerAdapter extends FragmentStateAdapter{
        public AlumnoPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            Alumno alumno = grupoAlumnos.get(position);
            return AlumnoFragment.newInstanceFragment(alumno.getMatricula());
        }

        @Override
        public int getItemCount() {
            return grupoAlumnos.size();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alumno_pager);

        alumnoViewPager = findViewById(R.id.alumno_pager_view);
        grupoAlumnos = GrupoAlumnos.getGrupo().getListaAlumnos();

        alumnoViewPager.setAdapter(new AlumnoPagerAdapter(this));

        int matricula_actual = getIntent().getIntExtra(Constantes.ID_ALUMNO, 0);

        for(int i = 0; i<grupoAlumnos.size(); i++){
            if(grupoAlumnos.get(i).getMatricula() == matricula_actual){
                alumnoViewPager.setCurrentItem(i);
                break;
            }
        }
    }
}