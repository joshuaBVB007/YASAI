package com.example.yasai;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Registro extends AppCompatActivity {
    String user;
    String contra;
    EditText username;
    EditText contraseña;
    public static FirebaseAuth firebaseM;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        firebaseM=FirebaseAuth.getInstance();

        username=findViewById(R.id.username);
        contraseña=findViewById(R.id.password);


        SharedPreferences mispreferencias=getSharedPreferences("datos", Context.MODE_PRIVATE);
        username.setText(mispreferencias.getString("user","cf19jonathan.ascencio@iesjoandaustria.org"));
        SharedPreferences mispreferencias2=getSharedPreferences("datos2", Context.MODE_PRIVATE);
        contraseña.setText(mispreferencias2.getString("contra","jaKsVaFa"));

        FirebaseUser usuario=firebaseM.getCurrentUser();
        if(usuario!=null){
            //HAY UNA SESION EN ACTIVO
            Intent intent=new Intent(getApplicationContext(),Menu.class);
            startActivity(intent);
        }else{
            //PIDE REGISTRO
        }

    }

    public void registrar_usuario_en_firebase(View view){
        user=username.getText().toString();
        contra=contraseña.getText().toString();
        SharedPreferences mis=getSharedPreferences("datos", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=mis.edit();
        editor.putString("user",username.getText().toString());
        editor.commit();
        SharedPreferences mis2=getSharedPreferences("datos2", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor2=mis2.edit();
        editor2.putString("contra",contraseña.getText().toString());
        editor2.commit();
        if (!user.isEmpty() && !contra.isEmpty()){
            firebaseM.getInstance()
                    .createUserWithEmailAndPassword(user,contra)
                    .addOnCompleteListener(Registro.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(Registro.this, "Dado de alta",
                                        Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(Registro.this, "Alta falló.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }else{
            if (user.isEmpty()){
                username.setError("Campo obligatorio");
            }else if(contra.isEmpty()){
                contraseña.setError("Campo obligatorio");
            }
        }
    }



    public void Sign_in(View view){//Metodo que agrega usuarios a mi base de datos de firebase
        SharedPreferences mis=getSharedPreferences("datos", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=mis.edit();
        editor.putString("user",username.getText().toString());
        editor.commit();
        SharedPreferences mis2=getSharedPreferences("datos2", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor2=mis2.edit();
        editor2.putString("contra",contraseña.getText().toString());
        editor2.commit();
        user=username.getText().toString();
        contra=contraseña.getText().toString();
        if (!user.isEmpty() && !contra.isEmpty()){
           FirebaseAuth.getInstance()
                    .signInWithEmailAndPassword(user,contra)
                    .addOnCompleteListener(Registro.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(Registro.this, "Authentication Success.",
                                        Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(Registro.this, Menu.class));
                            }else{
                                Toast.makeText(Registro.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

        }else{
            if (user.isEmpty()){
                username.setError("Campo obligatorio");
            }else if(contra.isEmpty()){
                contraseña.setError("Campo obligatorio");
            }
        }
    }


}