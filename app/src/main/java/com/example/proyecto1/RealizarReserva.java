package com.example.proyecto1;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class RealizarReserva extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    // Obtener referencia a la colección en Firestore
    CollectionReference cubiculosCollection = db.collection("Cubiculos");
    private ListenerRegistration listenerRegistration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realizar_reserva);

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
                    CubiculoAdapter adapter = new CubiculoAdapter();

                    adapter.setListaCubiculos(listaCubiculos);

                    recyclerView.setAdapter(adapter);

                    // Configurar el administrador de diseño (puedes usar LinearLayoutManager u otros)
                    LinearLayoutManager layoutManager = new LinearLayoutManager(RealizarReserva.this);
                    recyclerView.setLayoutManager(layoutManager);

                    // Asignar el adaptador al RecyclerView
                    recyclerView.setAdapter(adapter);
                } else {
                    Log.e("RealizarReserva", "Error al obtener los datos de Firestore", task.getException());
                }
            }
        });

        // Escuchar cambios en la colección de Firestore
        /*
        ListenerRegistration listenerRegistration = cubiculosCollection.addSnapshotListener((snapshot, error) -> {
            if (error != null) {
                // Manejar el error
                return;
            }

            if (snapshot != null && !snapshot.isEmpty()) {
                // Obtener los documentos de la colección y actualizar el adaptador
                List<Cubiculo> listaCubiculos = new ArrayList<>();
                for (DocumentSnapshot document : snapshot.getDocuments()) {
                    Cubiculo cubiculo = document.toObject(Cubiculo.class);
                    listaCubiculos.add(cubiculo);
                }
                adapter.setListaCubiculos(listaCubiculos);
                adapter.notifyDataSetChanged();
            }
        });

        // Guardar la referencia a la suscripción en una variable de instancia
        this.listenerRegistration = listenerRegistration;
        */
    }
}