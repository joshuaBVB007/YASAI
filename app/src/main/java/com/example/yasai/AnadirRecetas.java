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

public class AnadirRecetas extends AppCompatActivity {
    Button cogerimg;
    Button subir;
    Button verlista;
    ImageView imagen;
    EditText nombre_receta;
    Uri path;
    public StorageReference storageReference;
    public DatabaseReference databaseReference;
    public static final int GALLERY_INTENT=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recetas);
        cogerimg=findViewById(R.id.button4);
        imagen=findViewById(R.id.imagen_receta);
        subir=findViewById(R.id.button5);
        nombre_receta=findViewById(R.id.nombre_receta);
        verlista=findViewById(R.id.button6);

        storageReference= FirebaseStorage.getInstance().getReference();
        databaseReference= FirebaseDatabase.getInstance().getReference("recetas_usuarios");

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
            path=data.getData();
            imagen.setImageURI(path);
            //hihi es la carpeta adonde va.
            StorageReference filepath=storageReference.child("hihi").child(path.getLastPathSegment());
            filepath.putFile(path).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(AnadirRecetas.this,"Operacion exitosa",Toast.LENGTH_SHORT).show();
                }
            });

        }
    }





    /*public void subirImg(){
        if(path!=null){
            StorageReference reference=storageReference.child(""+path);
            reference.putFile(path).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Receta mireceta=new Receta(nombre_receta.getText().toString()
                                ,taskSnapshot.getUploadSessionUri().toString());
                        String nombre_receta=databaseReference.push().getKey();
                        databaseReference.child(nombre_receta).setValue(mireceta);
                        Toast.makeText(AnadirRecetas.this,"Operacion exitosa",Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(AnadirRecetas.this,"error al subir imagen",Toast.LENGTH_SHORT).show();
                }
            });
        }else{
            Toast.makeText(AnadirRecetas.this,"no seleccionaste imagen",Toast.LENGTH_SHORT).show();
        }
    }*/



}