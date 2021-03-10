package com.example.yasai;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyAdapterRecetasUsuario extends RecyclerView.Adapter<MyAdapterRecetasUsuario.MyViewHolder> {
    Context con;
    ArrayList<Receta> lista;
    public MyAdapterRecetasUsuario(Context con, ArrayList<Receta> lista){
        this.con=con;
        this.lista=lista;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(con);
        View V=inflater.inflate(R.layout.my_row_receta,parent,false);
        return new MyAdapterRecetasUsuario.MyViewHolder(V);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Receta dato=lista.get(position);
        holder.nombre_receta.setText(dato.getNom());
        holder.descripcion.setText(dato.getDescripcion());
        Picasso.with(con).load(dato.getUrl()).resize(100,100).into(holder.imagen_receta, new Callback() {
            @Override
            public void onSuccess() {
                holder.imagen_receta.setMaxHeight(200);
                holder.imagen_receta.setMaxWidth(200);
                holder.imagen_receta.setVisibility(View.VISIBLE);
            }
            @Override
            public void onError() {
                Toast.makeText(con,"Ha fallado",Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView nombre_receta;
        ImageView imagen_receta;
        TextView descripcion;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre_receta=itemView.findViewById(R.id.nombre_rece);
            descripcion=itemView.findViewById(R.id.descripcion_receta);
            imagen_receta=itemView.findViewById(R.id.img);
        }
    }


}
