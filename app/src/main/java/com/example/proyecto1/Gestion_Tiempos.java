package com.example.proyecto1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Gestion_Tiempos extends AppCompatActivity {
    Button buttonBC = (Button) findViewById(R.id.button_bloquear_cubiculo);
    Button buttonCE = (Button) findViewById(R.id.button_login);
    Button buttonMR = (Button) findViewById(R.id.button_login);
    Button buttonECGT = (Button) findViewById(R.id.button_login);
    Button buttonVGT = (Button) findViewById(R.id.button_login);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_tiempos);
        buttonCE.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(Gestion_Tiempos.this,MainActivity.class);
                startActivity(i);
            }
        });
        buttonMR.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(Gestion_Tiempos.this,MainActivity.class);
                startActivity(i);
            }
        });
        buttonECGT.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(Gestion_Tiempos.this,MainActivity.class);
                startActivity(i);
            }
        });
        buttonVGT.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(Gestion_Tiempos.this,MainActivity.class);
                startActivity(i);
            }
        });
        buttonBC.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(Gestion_Tiempos.this,MainActivity.class);
                startActivity(i);
            }
        });
    }
}