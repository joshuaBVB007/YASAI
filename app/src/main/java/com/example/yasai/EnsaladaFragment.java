package com.example.yasai;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class EnsaladaFragment extends Fragment {
    static  MyAdapterEnsalada adapter4;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View V=inflater.inflate(R.layout.fragment_ensalada, container, false);

        RecyclerView r=V.findViewById(R.id.recycler_ensaladas);
        adapter4=new MyAdapterEnsalada(getContext(),Menu.lista_ensalada);
        r.setAdapter(adapter4);
        r.setLayoutManager(new LinearLayoutManager(getContext()));

        Comidas.code_fragment=2;

        return V;
    }
}