package com.example.yasai;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Iterator;


public class MyAdapterCompra extends RecyclerView.Adapter<MyAdapterCompra.MyViewHolder> implements Filterable {

    Context con;
    ArrayList<Datos> lista;
    ArrayList<Datos> lista_full;
    static String filter9;
    public MyAdapterCompra(Context con,ArrayList<Datos> lista){
        this.con=con;
        this.lista=lista;
        lista_full=new ArrayList<>(lista);
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(con);
        View V=inflater.inflate(R.layout.my_row_compra,parent,false);
        return new MyAdapterCompra.MyViewHolder(V);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.nom.setText(lista.get(position).getNom());
        holder.precio.setText(lista.get(position).getPreci());
        holder.cantidad.setText(lista.get(position).getCantidad());
        holder.basurero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Iterator<Datos> it=lista.iterator();
                while(it.hasNext()){
                    Datos d=it.next();
                    if (d.getId()==(lista.get(position).getId())){
                        lista.remove(position);
                        Log.i("puff", ""+position+""+d.getId());
                        break;
                    }
                }
                ListaCompraFragment.adapter_lista_compra.notifyDataSetChanged();
            }
        });

    }



    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView nom;
        TextView precio;
        TextView cantidad;
        ImageView basurero;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nom=itemView.findViewById(R.id.nombre);
            precio=itemView.findViewById(R.id.precio_compra);
            cantidad=itemView.findViewById(R.id.cantidad_compra);
            basurero=itemView.findViewById(R.id.basurero);
        }
    }

    @Override
    public Filter getFilter() {
        return example_filter1;
    }
    private Filter example_filter1=new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<Datos> filteredList=new ArrayList<>();
            if (constraint == null || constraint.length() == 0){
                filteredList.addAll(lista_full);
            }else{
                filter9=constraint.toString().toLowerCase().trim();
                for (Datos item:lista_full){
                    if (item.getNom().toLowerCase().contains(filter9)){
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results=new FilterResults();
            results.values=filteredList;
            return results;
        }
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            lista.clear();
            lista.addAll((ArrayList)results.values);
            notifyDataSetChanged();
        }
    };


}
