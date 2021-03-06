package com.example.yasai;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.Date;

public class AnadirRecetas extends AppCompatActivity {
    Button cogerimg;
    Button verlista;
    ImageView imagen;
    EditText nombre_receta;
    EditText descripcion_receta;
    StorageReference storageReference;
    DatabaseReference mData=FirebaseDatabase.getInstance().getReference();
    public static final int GALLERY_INTENT=1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recetas);
        cogerimg=findViewById(R.id.button4);
        imagen=findViewById(R.id.imagen_receta);
        nombre_receta=findViewById(R.id.nombre_receta);
        descripcion_receta=findViewById(R.id.desc_receta);
        verlista=findViewById(R.id.button6);

        storageReference= FirebaseStorage.getInstance().getReference().child("fotos");



        verlista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AnadirRecetas.this,ListarReceta.class);
                startActivity(intent);
            }
        });

    }//FIN ONCREATE

    public void cogertImagen(View V){
        String nom=nombre_receta.getText().toString();
        String descripcion=descripcion_receta.getText().toString();
        if(nom.isEmpty() && descripcion.isEmpty()){
            nombre_receta.setError("Es necesario un nombre");
            descripcion_receta.setError("Es necesario una descripción");
        }else if(nom.isEmpty()){
            nombre_receta.setError("Es necesario un nombre");
        }else if(descripcion.isEmpty()){
            descripcion_receta.setError("Es necesario una descripción");
        }else if(!nom.isEmpty() && !descripcion.isEmpty()){
            Intent intent=new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            startActivityForResult(intent,GALLERY_INTENT);
        }
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==GALLERY_INTENT && resultCode==RESULT_OK){
            Uri path=data.getData();
            imagen.setImageURI(path);
            String nombre_de_la_receta=nombre_receta.getText().toString();
            String desc_de_la_receta=descripcion_receta.getText().toString();
            //linea de abajo escribe una nueva foto en el storage
            StorageReference filename=storageReference.child("fil"+path.getLastPathSegment());
            filename.putFile(path).addOnSuccessListener(taskSnapshot -> filename.getDownloadUrl()
                    .addOnSuccessListener(uri ->

                            //Recetas es el nodo raiz,T2 es el primer nodo hijo y nutrientes el contenido.
                            mData.child("Recetas_usuarios").child(mData.push().getKey()).setValue(new Receta(nombre_de_la_receta,desc_de_la_receta,String.valueOf(uri)))
                    )
            );
            Toast.makeText(getApplicationContext(),"Gracias por compartir tu receta",Toast.LENGTH_SHORT).show();


        }
    }




}