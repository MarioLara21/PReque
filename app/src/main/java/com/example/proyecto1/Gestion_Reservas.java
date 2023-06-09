package com.example.proyecto1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Gestion_Reservas extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    // Obtener referencia a la colección en Firestore
    CollectionReference reservasCollection = db.collection("Reserva");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_reservas);

        Button buttonECR = (Button) findViewById(R.id.button_enviarCorreoReserva);
        Button buttonECG = (Button) findViewById(R.id.button_eliminar_cubiculo);
        Button buttonVR = (Button) findViewById(R.id.button_volver_Reservas);


        buttonECR.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                System.out.println("correoEnviado");
            }
        });
        buttonECG.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                System.out.println("correoEnviado");
            }
        });


        buttonVR.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                Intent i = new Intent(Gestion_Reservas.this,menu_administradores.class);
                startActivity(i);
            }
        });


        reservasCollection.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    List<Reservas> listaReservas = new ArrayList<>();

                    for (QueryDocumentSnapshot document : task.getResult()) {
                        // Obtener los datos del documento y crear una instancia de Cubiculo
                        String horaFin = document.getString("HoraFin");
                        String HoraInicio = document.getString("HoraInicio");
                        String Usuario = document.getString("Usuario");
                        int NumeroCubiculo = document.getLong("NumeroCubiculo").intValue();

                        Reservas reserva = new Reservas(HoraInicio, horaFin, NumeroCubiculo, Usuario);

                        // Agregar el Cubiculo a la lista
                        listaReservas.add(reserva);
                    }
                    // Obtener referencia al RecyclerView desde el layout
                    RecyclerView recyclerView = findViewById(R.id.ListaReservas);

                    // Configurar el adaptador
                    ReservasAdapter adapter = new ReservasAdapter();

                    adapter.setListaReservas(listaReservas);

                    adapter.notifyDataSetChanged();

                    // Configurar el administrador de diseño (puedes usar LinearLayoutManager u otros)
                    LinearLayoutManager layoutManager = new LinearLayoutManager(Gestion_Reservas.this);
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