package com.example.yasai;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SopaFragment extends Fragment {
    static  MyAdapterSopas adapter5;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View V=inflater.inflate(R.layout.fragment_sopa, container, false);

        RecyclerView r=V.findViewById(R.id.recycler_sopas);
        adapter5=new MyAdapterSopas(getContext(),Menu.lista_sopas);
        r.setAdapter(adapter5);
        r.setLayoutManager(new LinearLayoutManager(getContext()));

        Comidas.code_fragment=1;

        return V;
    }
}