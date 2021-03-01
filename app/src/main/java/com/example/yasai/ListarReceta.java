package com.example.yasai;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class ListarReceta extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_receta);

        RecyclerView r=findViewById(R.id.recycler_recetas);
        MyAdapterRecetasUsuario adapterRecetasUsuario=new MyAdapterRecetasUsuario(ListarReceta.this,Menu.lista_propuestas_recetas);
        r.setAdapter(adapterRecetasUsuario);
        r.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

    }
}