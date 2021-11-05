package com.example.alumnoproyecto;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AlumnoListaFragment extends Fragment {
    private RecyclerView alumnoRecyclerView;
    private AlumnoAdapter adapter;

    private class AlumnoHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView txtNombreAlumno;
        private TextView txtMatricula;
        private ImageView imgStatus;
        private Alumno alumnoHolder;

        public AlumnoHolder(LayoutInflater inflater, ViewGroup parent) {
           super(inflater.inflate(R.layout.lista_elemento_alumno, parent, false));
           txtNombreAlumno = itemView.findViewById(R.id.txtNombreAlumno);
           txtMatricula = itemView.findViewById(R.id.txtMatricula);
           imgStatus = itemView.findViewById(R.id.imgStatus);
           itemView.setOnClickListener(this);
        }

        public void bind(Alumno alumno){
            alumnoHolder = alumno;
            txtNombreAlumno.setText(alumno.getNombre());
            // TENIAMOS UN ERROR, EL ENTERO SE TIENE QUE TRANSFORMAR A CADENA
            txtMatricula.setText(String.valueOf(alumno.getMatricula()));
            imgStatus.setImageResource(alumno.isActivo()? R.drawable.activo: R.drawable.inactivo);
        }

        @Override
        public void onClick(View view) {
            Log.d("Evento", "Entro a evento:" + alumnoHolder.getMatricula() );
            Toast.makeText(getActivity(), alumnoHolder.getNombre()+ "he sido seleccionado!", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(getActivity(), AlumnoActivity.class);
            intent.putExtra(Constantes.ID_ALUMNO, alumnoHolder.getMatricula());
            //startActivity(intent);
            startActivityForResult(intent,Constantes.REQUEST_ALUMNO);
        }
    }

    private class AlumnoAdapter extends RecyclerView.Adapter<AlumnoHolder>{
        private List<Alumno> listaAlumnos;

        public AlumnoAdapter(List<Alumno> lista){
            listaAlumnos = lista;
        }

        @NonNull
        @Override
        public AlumnoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new AlumnoHolder(layoutInflater, parent);
        }

        public void onBindViewHolder(AlumnoHolder holder, int posicion){
            Alumno alumno = listaAlumnos.get(posicion);
            holder.bind(alumno);
        }

        public int getItemCount(){
            return listaAlumnos.size();
        }
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == Constantes.REQUEST_ALUMNO){
            // MANEJAR EL RESULTATO
        }
    }

    public void onResume(){
        super.onResume();
        if(adapter == null){
            adapter = new AlumnoAdapter(GrupoAlumnos.getGrupo().getListaAlumnos());
            alumnoRecyclerView.setAdapter(adapter);
        }
        else{
            adapter.notifyDataSetChanged();
        }
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.lista_alumnos_fragment, container, false);

        alumnoRecyclerView = view.findViewById(R.id.alumno_recycler_view);
        alumnoRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        ArrayList<Alumno> alumnos = GrupoAlumnos.getGrupo().getListaAlumnos();
        adapter = new AlumnoAdapter(alumnos);
        alumnoRecyclerView.setAdapter(adapter);

        return view;
    }
}
