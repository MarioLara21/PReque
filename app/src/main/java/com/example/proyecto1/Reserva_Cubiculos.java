package com.example.proyecto1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class Reserva_Cubiculos extends AppCompatActivity {
    private FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserva_cubiculos);

        db = FirebaseFirestore.getInstance();
        Button buttonRC = (Button) findViewById(R.id.button_reserva_cubiculo);
        Button buttonVRC = (Button) findViewById(R.id.button_volver_RC);
        buttonRC.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(Reserva_Cubiculos.this, agregarCubibulo.class);
                startActivity(i);
            }
        });
        buttonVRC.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(Reserva_Cubiculos.this,MainActivity.class);
                startActivity(i);
            }
        });

    }
    private void llenarTabla(){
        db.collection("Cubiculos").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

            }
        });
    }
}