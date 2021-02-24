package com.example.yasai;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class PlatoFragment extends Fragment {
    static  MyAdapterPlato adapter6;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View V= inflater.inflate(R.layout.fragment_plato, container, false);

        RecyclerView r=V.findViewById(R.id.recycler_platos);
        adapter6=new MyAdapterPlato(getContext(),Menu.lista_platos);
        r.setAdapter(adapter6);
        r.setLayoutManager(new LinearLayoutManager(getContext()));

        Comidas.code_fragment=0;

        return V;
    }
}