package com.example.yasai;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class Recetas extends AppCompatActivity {
    Button cogerimg;
    Button subir;
    ImageView imagen;
    EditText nombre_receta;
    Uri path;
    public StorageReference storageReference;
    public DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recetas);
        cogerimg=findViewById(R.id.button4);
        imagen=findViewById(R.id.imagen_receta);
        subir=findViewById(R.id.button5);
        nombre_receta=findViewById(R.id.nombre_receta);

        storageReference= FirebaseStorage.getInstance().getReference("/img_recetas");
        databaseReference= FirebaseDatabase.getInstance().getReference("recetas_usuarios");

        cogerimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Recetas.this,"cogiendo imagen",Toast.LENGTH_SHORT).show();
                cogerimg();
            }
        });

        subir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subirImg();
            }
        });

    }

    public void cogerimg(){
        Intent intent =new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/");
        startActivityForResult(intent.createChooser(intent,"selecciona una imagen"),10);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode==RESULT_OK){
            path=data.getData();
            imagen.setImageURI(path);
        }
    }

    public void subirImg(){
        if(path!=null){
            StorageReference reference=storageReference.child(""+path);
            reference.putFile(path).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Receta mireceta=new Receta(nombre_receta.getText().toString()
                                ,taskSnapshot.getUploadSessionUri().toString());
                        String nombre_receta=databaseReference.push().getKey();
                        databaseReference.child(nombre_receta).setValue(mireceta);
                        Toast.makeText(Recetas.this,"upload correct",Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(Recetas.this,"error al subir imagen",Toast.LENGTH_SHORT).show();
                }
            });
        }else{
            Toast.makeText(Recetas.this,"no seleccionaste archivo",Toast.LENGTH_SHORT).show();
        }
    }



}