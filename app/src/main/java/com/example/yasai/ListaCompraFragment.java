package com.example.yasai;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



public class ListaCompraFragment extends Fragment {
    static  MyAdapterCompra adapter_lista_compra;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View V= inflater.inflate(R.layout.fragment_lista_compra, container, false);

        RecyclerView r=V.findViewById(R.id.recyclerComprados);

            adapter_lista_compra=new MyAdapterCompra(getContext(),Menu.lista_De_La_Compra);
            r.setAdapter(adapter_lista_compra);
            r.setLayoutManager(new LinearLayoutManager(getContext()));


        Bebidas.code_fragment=3;
        Comidas.code_fragment=3;
        Postres.code_fragment=3;
        return V;
    }
}