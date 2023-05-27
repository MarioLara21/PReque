package com.example.proyecto1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Reserva_Cubiculos extends AppCompatActivity {
    Button buttonRC = (Button) findViewById(R.id.button_reserva_cubiculo);
    Button buttonVRC = (Button) findViewById(R.id.button_volver_RC);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserva_cubiculos);
        buttonRC.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(Reserva_Cubiculos.this,MainActivity.class);
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
}