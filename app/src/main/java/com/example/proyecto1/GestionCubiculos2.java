package com.example.proyecto1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class GestionCubiculos2 extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    // Obtener referencia a la colección en Firestore
    CollectionReference cubiculosCollection = db.collection("Cubiculos");
    private ListenerRegistration listenerRegistration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gestion_cubiculos2);


        Button buttonAC = (Button) findViewById(R.id.button_agregar_cubiculo);
        Button buttonEC = (Button) findViewById(R.id.button_eliminar_cubiculo);
        Button buttonVGC = (Button) findViewById(R.id.button_volver_GC);
        buttonEC.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                Intent i = new Intent(GestionCubiculos2.this,MainActivity.class);
                startActivity(i);
            }
        });

        buttonVGC.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                Intent i = new Intent(GestionCubiculos2.this,menu_administradores.class);
                startActivity(i);
            }
        });
        buttonAC.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                Intent i = new Intent(GestionCubiculos2.this,agregarCubibulo.class);
                startActivity(i);
            }
        });

        cubiculosCollection.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    List<Cubiculo> listaCubiculos = new ArrayList<>();

                    for (QueryDocumentSnapshot document : task.getResult()) {
                        // Obtener los datos del documento y crear una instancia de Cubiculo
                        int capacidad = document.getLong("Capacidad").intValue();
                        boolean disponibilidadAcceso = document.getBoolean("DisponibilidadAcceso");
                        boolean disponibilidadBraile = document.getBoolean("DisponibilidadBraile");
                        boolean estado = document.getBoolean("Estado");
                        int numero = document.getLong("Numero").intValue();

                        Cubiculo cubiculo = new Cubiculo(capacidad, disponibilidadAcceso, disponibilidadBraile, estado, numero);

                        // Agregar el Cubiculo a la lista
                        listaCubiculos.add(cubiculo);
                    }
                    // Obtener referencia al RecyclerView desde el layout
                    RecyclerView recyclerView = findViewById(R.id.ListaCubiculos);

                    // Configurar el adaptador
                    CubiculoAdapterAdmin adapter = new CubiculoAdapterAdmin();

                    adapter.setListaCubiculos(listaCubiculos);
                    adapter.setTipo(1);
                    adapter.notifyDataSetChanged();



                    // Configurar el administrador de diseño (puedes usar LinearLayoutManager u otros)
                    LinearLayoutManager layoutManager = new LinearLayoutManager(GestionCubiculos2.this);
                    recyclerView.setLayoutManager(layoutManager);

                    // Asignar el adaptador al RecyclerView
                    recyclerView.setAdapter(adapter);

                } else {
                    Log.e("RealizarReserva", "Error al obtener los datos de Firestore", task.getException());
                }
            }
        });

    }
}