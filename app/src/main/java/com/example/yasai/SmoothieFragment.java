package com.example.yasai;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class SmoothieFragment extends Fragment {
    static  MyAdapterSmoothie adapter8;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View V=inflater.inflate(R.layout.fragment_smoothie, container, false);

        RecyclerView r=V.findViewById(R.id.recycler_smoothie);
        adapter8=new MyAdapterSmoothie(getContext(),Menu.lista_Smoothie);
        r.setAdapter(adapter8);
        r.setLayoutManager(new LinearLayoutManager(getContext()));
        Postres.code_fragment=2;
        return V;
    }
}