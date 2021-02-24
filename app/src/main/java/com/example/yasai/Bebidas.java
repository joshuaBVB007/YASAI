package com.example.yasai;

import androidx.annotation.NonNull;
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
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;



public class Bebidas extends AppCompatActivity {
    static int code_fragment=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bebidas);

        BottomNavigationView navView = findViewById(R.id.bebidas_navView);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.citricosFragment, R.id.exoticasFragment, R.id.otra_FrutaFragment,R.id.listaCompraFragment)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);


        ImageButton ir_a_menu=findViewById(R.id.irAmenu);
        ImageButton pagar=findViewById(R.id.pagar);
        ImageButton ir_a_comida=findViewById(R.id.irAcomidas);
        ImageButton ir_a_postres=findViewById(R.id.irApostres);

        ir_a_postres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Postres.class);
                startActivity(intent);
            }
        });
        ir_a_comida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Comidas.class);
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
                if(code_fragment==0){
                    CitricosFragment.adapter1.getFilter().filter(newText);
                }else if(code_fragment==1){
                    ExoticasFragment.adapter2.getFilter().filter(newText);
                }else if(code_fragment==2){
                    Otra_FrutaFragment.adapter_3.getFilter().filter(newText);
                }else if(code_fragment==3){
                    ListaCompraFragment.adapter_lista_compra.getFilter().filter(newText);
                }
                return false;
            }
        });
        return true;
    }


}