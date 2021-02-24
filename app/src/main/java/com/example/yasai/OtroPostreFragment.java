package com.example.yasai;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class OtroPostreFragment extends Fragment {
    static  MyAdapterOtroPostre adapter9;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View V=inflater.inflate(R.layout.fragment_otro_postre, container, false);

        RecyclerView r=V.findViewById(R.id.recycler_otro_postre);
        adapter9=new MyAdapterOtroPostre(getContext(),Menu.lista_OtroPostre);
        r.setAdapter(adapter9);
        r.setLayoutManager(new LinearLayoutManager(getContext()));

        Postres.code_fragment=0;


        return V;
    }
}