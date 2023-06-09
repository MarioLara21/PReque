package com.example.proyecto1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Gestion_Tiempos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_tiempos);

        Button buttonBC = (Button) findViewById(R.id.button_bloquear_cubiculo);
        Button buttonCE = (Button) findViewById(R.id.button_cambiar_estado);
        Button buttonMR = (Button) findViewById(R.id.button_modificar_rango);
        Button buttonECGT = (Button) findViewById(R.id.button_enviar_correo_tiempo);
        Button buttonVGT = (Button) findViewById(R.id.button_volver_TC);
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