package com.example.yasai;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

public class CitricosFragment extends Fragment {
    static  MyAdapterCitricos adapter1;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View V=inflater.inflate(R.layout.fragment_citricos, container, false);

        RecyclerView r=V.findViewById(R.id.recycler_citricos);
        adapter1=new MyAdapterCitricos(getContext(),Menu.lista_citricas);
        r.setAdapter(adapter1);
        r.setLayoutManager(new LinearLayoutManager(getContext()));
        Bebidas.code_fragment=0;
        return V;
    }
}