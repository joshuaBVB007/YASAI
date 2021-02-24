package com.example.yasai;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ExoticasFragment extends Fragment {
    static  MyAdapterExoticos adapter2;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View V=inflater.inflate(R.layout.fragment_exoticas, container, false);

        RecyclerView r=V.findViewById(R.id.recycler_exoticos);
        adapter2=new MyAdapterExoticos(getContext(),Menu.lista_exoticas);
        r.setAdapter(adapter2);
        r.setLayoutManager(new LinearLayoutManager(getContext()));

        Bebidas.code_fragment=1;
        return V;
    }
}