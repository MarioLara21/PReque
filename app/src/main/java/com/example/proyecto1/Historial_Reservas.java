package com.example.proyecto1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Historial_Reservas extends AppCompatActivity {
    Button buttonVHR = (Button) findViewById(R.id.button_volverHR);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial_reservas);
        buttonVHR.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){

                Intent i = new Intent(Historial_Reservas.this,MainActivity.class);
                startActivity(i);
            }
        });
    }
}