package com.example.yasai;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ImageView Imagen;
    TextView nombre_yasai;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Animation anim= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.anim);
        nombre_yasai=findViewById(R.id.NombreYasai);


        nombre_yasai.setAnimation(anim);


        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(MainActivity.this, Registro.class);
                startActivity(intent);
                finish();
            }
        }, 1000);

    }
}