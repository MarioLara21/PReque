package com.example.proyecto1;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class SeleccionCubiculosUsuarios extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    // Obtener referencia a la colección en Firestore
    CollectionReference cubiculosCollection = db.collection("Cubiculos");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seleccion_cubiculos_usuarios);

        // Obtener el Intent que inició esta actividad
        Intent intent = getIntent();

        // Obtener el valor pasado a través de putExtra()
        String userID= intent.getStringExtra("userID");

        System.out.println("\n\n"+userID+"\n\n");

        cubiculosCollection.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    List<CubiculoUsuarios> listaCubiculos = new ArrayList<>();

                    for (QueryDocumentSnapshot document : task.getResult()) {
                        // Obtener los datos del documento y crear una instancia de Cubiculo
                        int capacidad = document.getLong("Capacidad").intValue();
                        boolean disponibilidadAcceso = document.getBoolean("DisponibilidadAcceso");
                        boolean disponibilidadBraile = document.getBoolean("DisponibilidadBraile");
                        boolean estado = document.getBoolean("Estado");
                        int numero = document.getLong("Numero").intValue();

                        CubiculoUsuarios cubiculo = new CubiculoUsuarios(capacidad, disponibilidadAcceso, disponibilidadBraile, estado, numero);

                        // Agregar el Cubiculo a la lista
                        listaCubiculos.add(cubiculo);
                    }
                    // Obtener referencia al RecyclerView desde el layout
                    RecyclerView recyclerView = findViewById(R.id.ListaReservas);

                    // Configurar el adaptador
                    CubiculoAdapterUsuarios adapter = new CubiculoAdapterUsuarios();

                    adapter.setListaCubiculos(listaCubiculos);
                    adapter.setUsuarioReserva(String.valueOf(userID));

                    adapter.notifyDataSetChanged();



                    // Configurar el administrador de diseño (puedes usar LinearLayoutManager u otros)
                    LinearLayoutManager layoutManager = new LinearLayoutManager(SeleccionCubiculosUsuarios.this);
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