package com.example.yasai;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SearchView;

import com.google.android.material.bottomnavigation.BottomNavigationView;



public class Comidas extends AppCompatActivity {
    static int code_fragment=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comidas);

        BottomNavigationView navView = findViewById(R.id.menu_comidas);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.ensaladaFragment, R.id.sopaFragment, R.id.platoFragment,R.id.listaCompraFragment2)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.fragment_comidas);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        ImageButton ir_a_menu=findViewById(R.id.irAmenu);
        ImageButton pagar=findViewById(R.id.pagar);
        ImageButton ir_a_bebidas=findViewById(R.id.irAbebidas);
        ImageButton ir_a_postres=findViewById(R.id.irApostres);

        ir_a_postres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Postres.class);
                startActivity(intent);
            }
        });
        ir_a_bebidas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Bebidas.class);
                startActivity(intent);
            }
        });
        pagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Pagar.class);
                startActivity(intent);
            }
        });
        ir_a_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Menu.class);
                startActivity(intent);
            }
        });



    }




    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.example,menu);

        MenuItem searchitem=menu.findItem(R.id.action_search);
        SearchView searchView=(SearchView)searchitem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(Comidas.code_fragment==0){
                    PlatoFragment.adapter6.getFilter().filter(newText);
                }else if(Comidas.code_fragment==1){
                    SopaFragment.adapter5.getFilter().filter(newText);
                }else if(Comidas.code_fragment==2){
                    EnsaladaFragment.adapter4.getFilter().filter(newText);
                }else if(code_fragment==3){
                    ListaCompraFragment.adapter_lista_compra.getFilter().filter(newText);
                }
                return false;
            }
        });

        return true;
    }

}