package com.example.yasai;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;


public class Menu extends AppCompatActivity {
    static ArrayList<Datos> lista_De_La_Compra=new ArrayList<>();
    //FRUTAS
    static ArrayList<Datos> lista_citricas =new ArrayList<>();
    static ArrayList<Datos> lista_exoticas =new ArrayList<>();
    static ArrayList<Datos> lista_otrafruta =new ArrayList<>();
    //COMIDAS
    static ArrayList<Datos> lista_ensalada =new ArrayList<>();
    static ArrayList<Datos> lista_sopas =new ArrayList<>();
    static ArrayList<Datos> lista_platos =new ArrayList<>();
    //POSTRES
    static ArrayList<Datos> lista_OtroPostre =new ArrayList<>();
    static ArrayList<Datos> lista_Helado =new ArrayList<>();
    static ArrayList<Datos> lista_Smoothie =new ArrayList<>();

    //CONTROLA QUE SI VOLVEMOS AL MENU VARIAS VECES NO CARGUEMOS LAS LISTAS DE COMIDAS VARIAS VECES
    public static int ingresa=1;


    Button boton_bebidas;
    ImageView instagram;
    ImageView location;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        boton_bebidas=findViewById(R.id.button_bebidas);
        instagram=findViewById(R.id.instagram);
        location=findViewById(R.id.location);

        if(ingresa==1){
               ingresa++;
               agregarProductos();
            Log.i("ingresa", "onCreate: "+ingresa);
        }



        //Boton que nos abre en la web la cuenta de la tienda
        instagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Menu.this,"INSTAGRAM",Toast.LENGTH_SHORT).show();
                Uri url=Uri.parse("https://www.instagram.com/volcom/");
                Intent insta=new Intent(Intent.ACTION_VIEW,url);
                insta.setPackage("com.instagram.android");
                try{
                    startActivity(insta);
                }catch (ActivityNotFoundException e){
                    startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.instagram.com/joshuabvb007/")));
                    Log.i("exception", "Ha fallado en instagram");
                }
            }
        });

        //Boton que nos abre la ubicacion de google maps
        location.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
                Toast.makeText(Menu.this,"Google Maps",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getApplicationContext(),MapsActivity.class);
                startActivity(intent);
             }
        });


    }


    public void ir_a_bebidas(View V){
        Intent intent=new Intent(Menu.this,Bebidas.class);
        startActivity(intent);
    }
    public void ir_a_comidas(View V){
        Intent intent=new Intent(Menu.this,Comidas.class);
        startActivity(intent);
    }
    public void ir_a_postres(View V){
            Intent intent=new Intent(Menu.this,Postres.class);
            startActivity(intent);
    }

    public void log_out(View V){
        Registro.firebaseM.getInstance().signOut();
        Toast.makeText(Menu.this,"Hasta pronto",Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(Menu.this,Registro.class);
        startActivity(intent);
        finish();
    }

    public void agregarProductos(){
        ingresarCitricos();
        ingresarExoticas();
        ingresarOtraFruta();
        ingresarEnsalada();
        ingresarSopa();
        ingresarPlatos();
        ingresarOtroPostre();
        ingresarSmoothie();
        ingresarHelado();
    }

    public void ingresarCitricos(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Citricos");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                        Datos dato=snapshot.getValue(Datos.class);
                        lista_citricas.add(dato);
                    }
                }
                Log.i("T", "onDataChange: ");
            }
            @Override
            public void onCancelled(DatabaseError error) {
                Log.w("TAG_2", "Failed to read value.", error.toException());
            }
        });
    }

    public void ingresarExoticas(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Exoticos");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                        Datos dato=snapshot.getValue(Datos.class);
                        lista_exoticas.add(dato);

                    }
                }
                Log.i("T", "onDataChange: "+ lista_exoticas.size());
            }
            @Override
            public void onCancelled(DatabaseError error) {
                Log.w("TAG_2", "Failed to read value.", error.toException());
            }
        });
    }

    public void ingresarOtraFruta(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("OtraFruta");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                        Datos dato=snapshot.getValue(Datos.class);
                        lista_otrafruta.add(dato);

                    }
                }
                Log.i("T", "onDataChange: "+ lista_otrafruta.size());
            }
            @Override
            public void onCancelled(DatabaseError error) {
                Log.w("TAG_2", "Failed to read value.", error.toException());
            }
        });
    }

    public void ingresarEnsalada(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Ensalada");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                        Datos dato=snapshot.getValue(Datos.class);
                        lista_ensalada.add(dato);
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
                Log.w("TAG_2", "Failed to read value.", error.toException());
            }
        });
    }

    public void ingresarSopa(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Sopas");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                        Datos dato=snapshot.getValue(Datos.class);
                        lista_sopas.add(dato);
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
                Log.w("TAG_2", "Failed to read value.", error.toException());
            }
        });
    }

    public void ingresarPlatos(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Plato");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                        Datos dato=snapshot.getValue(Datos.class);
                        lista_platos.add(dato);
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
                Log.w("TAG_2", "Failed to read value.", error.toException());
            }
        });
    }

    public void ingresarSmoothie(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Smoothie");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                        Datos dato=snapshot.getValue(Datos.class);
                        lista_Smoothie.add(dato);
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
                Log.w("TAG_2", "Failed to read value.", error.toException());
            }
        });
    }

    public void ingresarOtroPostre(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("OtroPostre");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                        Datos dato=snapshot.getValue(Datos.class);
                        lista_OtroPostre.add(dato);
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
                Log.w("TAG_2", "Failed to read value.", error.toException());
            }
        });
    }

    public void ingresarHelado(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Helado");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                        Datos dato=snapshot.getValue(Datos.class);
                        lista_Helado.add(dato);
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
                Log.w("TAG_2", "Failed to read value.", error.toException());
            }
        });
    }




}