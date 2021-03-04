package com.example.yasai;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;

public class Pagar extends AppCompatActivity {
    String nombre;
    String direccion_;
    String cuenta_bancaria;
    String DNI;

    EditText nombre_comprador;
    EditText direccion;
    EditText numero_cuenta;
    EditText dni;
    LottieAnimationView paga;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagar);

        nombre_comprador =findViewById(R.id.nombre_comprador);
        direccion=findViewById(R.id.direccion);
        numero_cuenta=findViewById(R.id.Cuenta_bancaria);
        dni=findViewById(R.id.id_comprador);
        paga=findViewById(R.id.compra);

        paga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pagar();
            }
        });


    }

    public void pagar(){
        nombre= nombre_comprador.getText().toString();
        direccion_= direccion.getText().toString();
        cuenta_bancaria=numero_cuenta.getText().toString();
        DNI=dni.getText().toString();
        if(nombre.isEmpty() && direccion_.isEmpty() && cuenta_bancaria.isEmpty() && DNI.isEmpty()){
            nombre_comprador.setError("Campo obligatorio");
            direccion.setError("Campo obligatorio");
            numero_cuenta.setError("Campo obligatorio");
            dni.setError("Campo obligatorio");
        }else if(nombre.isEmpty()){
            nombre_comprador.setError("Campo obligatorio");
        }else if(direccion_.isEmpty()){
            direccion.setError("Campo obligatorio");
        }else if(cuenta_bancaria.isEmpty()){
            numero_cuenta.setError("Campo obligatorio");
        }else if(DNI.isEmpty()){
            dni.setError("Campo obligatorio");
        }else if(!nombre.isEmpty() && !direccion_.isEmpty() && !cuenta_bancaria.isEmpty() && !DNI.isEmpty()){
            Toast.makeText(getApplicationContext(),"Compra Exitosa",Toast.LENGTH_SHORT).show();
        }
    }

}