package com.example.yasai;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class AnadirRecetas extends AppCompatActivity {
    Button cogerimg;
    Button verlista;
    ImageView imagen;
    EditText nombre_receta;
    StorageReference storageReference;
    DatabaseReference mData=FirebaseDatabase.getInstance().getReference();


    public static final int GALLERY_INTENT=1;
    public static int JSON_posicion=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recetas);
        cogerimg=findViewById(R.id.button4);
        imagen=findViewById(R.id.imagen_receta);
        nombre_receta=findViewById(R.id.nombre_receta);
        verlista=findViewById(R.id.button6);

        storageReference= FirebaseStorage.getInstance().getReference().child("fotos");
        cogerimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,GALLERY_INTENT);
            }
        });

        verlista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AnadirRecetas.this,ListarReceta.class);
                startActivity(intent);
            }
        });

    }//FIN ONCREATE





    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==GALLERY_INTENT && resultCode==RESULT_OK){
            Uri path=data.getData();
            imagen.setImageURI(path);
            String nombre_de_la_receta=nombre_receta.getText().toString();
            //linea de abajo escribe una nueva foto en el storage
            JSON_posicion++;
            StorageReference filename=storageReference.child("fil"+path.getLastPathSegment());
            filename.putFile(path).addOnSuccessListener(taskSnapshot -> filename.getDownloadUrl()
                    .addOnSuccessListener(uri ->

                            //Recetas es el nodo raiz,T2 es el primer nodo hijo y nutrientes el contenido.
                            mData.child("Recetas_usuarios").child(String.valueOf(JSON_posicion)).setValue(new Receta(nombre_de_la_receta,String.valueOf(uri)))
                    )
            );

        }
    }

}