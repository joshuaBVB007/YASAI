package com.example.yasai;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Otra_FrutaFragment extends Fragment {
    static  MyAdapterOtraFruta adapter_3;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View V=inflater.inflate(R.layout.fragment_otra__fruta, container, false);
        RecyclerView r=V.findViewById(R.id.recycler_otra_fruta);
        adapter_3=new MyAdapterOtraFruta(getContext(),Menu.lista_otrafruta);
        r.setAdapter(adapter_3);
        r.setLayoutManager(new LinearLayoutManager(getContext()));

        Bebidas.code_fragment=2;

        return V;
    }
}