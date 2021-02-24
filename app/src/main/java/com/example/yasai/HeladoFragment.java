package com.example.yasai;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class HeladoFragment extends Fragment {
    static  MyAdapterHelado adapter7;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View V=inflater.inflate(R.layout.fragment_helado, container, false);

        RecyclerView r=V.findViewById(R.id.recycler_helado);
        adapter7=new MyAdapterHelado(getContext(),Menu.lista_Helado);
        r.setAdapter(adapter7);
        r.setLayoutManager(new LinearLayoutManager(getContext()));
        Postres.code_fragment=1;
        return V;
    }
}