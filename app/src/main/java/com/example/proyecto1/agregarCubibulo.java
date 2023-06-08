package com.example.proyecto1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.ktx.Firebase;

import java.util.HashMap;
import java.util.Map;

public class agregarCubibulo extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_cubibulo);


        Button buttonBack = (Button) findViewById(R.id.btn_Volver);

        Button buttonAddCubiculo = (Button) findViewById(R.id.btn_addCubiculo);
        //Borrar desde aqui
        CollectionReference cubiculosRef = db.collection("Cubiculos");
        //Hasta aqui
        buttonBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(agregarCubibulo.this,GestionCubiculos2.class);
                startActivity(i);
            }
        });
        buttonAddCubiculo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                boolean acceso;
                boolean braile;
                boolean disponible;
                EditText capacidadText = findViewById(R.id.TextCapacidad);
                int capacidad = Integer.parseInt(capacidadText.getText().toString());
                EditText numeroCubiculoText = findViewById(R.id.TextNumeroCubiculo);
                int idCubiculo = Integer.parseInt(numeroCubiculoText.getText().toString());
                Switch switchAcceso = findViewById(R.id.switchAccesoEspecializado);
                acceso = switchAcceso.isChecked();
                Switch switchBraile = findViewById(R.id.switchBraile);
                braile = switchBraile.isChecked();
                Switch switchDisponibilidad = findViewById(R.id.switchDisponibilidad);
                disponible = switchDisponibilidad.isChecked();
                Map<String, Object> nuevoDocumento = new HashMap<>();
                nuevoDocumento.put("Capacidad", capacidad);
                nuevoDocumento.put("DisponibilidadAcceso", acceso);
                nuevoDocumento.put("DisponibilidadBraile", braile);
                nuevoDocumento.put("Estado", disponible);
                nuevoDocumento.put("Numero", idCubiculo);
                String NombreCubiculo= "Cubiculo"+numeroCubiculoText.getText().toString();
                DocumentReference nuevoDocumentoRef = cubiculosRef.document(NombreCubiculo);
                nuevoDocumentoRef.set(nuevoDocumento)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(getApplicationContext(), "Se agregó con exito", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getApplicationContext(), "Ocurrió un error", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });


    }
}