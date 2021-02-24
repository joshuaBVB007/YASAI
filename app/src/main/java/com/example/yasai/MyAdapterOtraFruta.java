package com.example.yasai;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyAdapterOtraFruta  extends RecyclerView.Adapter<MyAdapterOtraFruta.MyViewHolder> implements Filterable {

    Context con;
    ArrayList<Datos> lista;
    ArrayList<Datos> lista_full;
    static String filter9;
    public MyAdapterOtraFruta(Context con, ArrayList<Datos> lista){
        this.con=con;
        this.lista=lista;
        lista_full=new ArrayList<>(lista);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(con);
        View V=inflater.inflate(R.layout.my_row,parent,false);
        return new MyAdapterOtraFruta.MyViewHolder(V);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Datos dato=lista.get(position);
        holder.nom.setText(dato.getNom());
        holder.desc.setText(dato.getDesc());
        holder.precio.setText((dato.getPreci()));
        //agregar es el boton de cada item del recyclerView
        holder.agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre=holder.nom.getText().toString();
                String precio=holder.precio.getText().toString();
                int validador=Integer.parseInt(holder.cantidad.getText().toString());
                if(holder.cantidad.getText().toString().equals("")){
                    //hay cadena vacia
                }else if(validador>0){
                    Datos objeto_comprado=new Datos(nombre,precio,holder.cantidad.getText().toString(),0);
                    Menu.lista_De_La_Compra.add(objeto_comprado);
                    Log.i("compra", "Haz comprado una: "+nombre);
                    holder.cantidad.setText("0");
                }else{
                    //no agregamos nada al carro porque el usuario ha puesto cero
                    Log.i("No_compra", "No se ha comprado una: "+nombre);
                }
            }
        });


        Picasso.with(con).load(dato.getImg()).into(holder.image_brand, new Callback() {
            @Override
            public void onSuccess() {
                holder.image_brand.setVisibility(View.VISIBLE);
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
        TextView nom;
        TextView desc;
        ImageView image_brand;
        TextView precio;
        Button agregar;
        EditText cantidad;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nom=itemView.findViewById(R.id.nom);
            desc=itemView.findViewById(R.id.desc);
            image_brand=itemView.findViewById(R.id.imagen_);
            precio=itemView.findViewById(R.id.precio);
            agregar=itemView.findViewById(R.id.button3);
            cantidad=itemView.findViewById(R.id.cantidad);
        }
    }

    //METODOS QUE CREAN EL FILTRO
    @Override
    public Filter getFilter() {
        return example_filter3;
    }

    private Filter example_filter3=new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<Datos> filteredList=new ArrayList<Datos>();
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
