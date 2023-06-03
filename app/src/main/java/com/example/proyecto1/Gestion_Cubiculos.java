package com.example.proyecto1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Gestion_Cubiculos extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_cubiculos);

        Button buttonAC = (Button) findViewById(R.id.button_agregar_cubiculo);
        Button buttonEC = (Button) findViewById(R.id.button_eliminar_cubiculo);
        Button buttonMC = (Button) findViewById(R.id.button_modificar_cubiculo);
        Button buttonVGC = (Button) findViewById(R.id.button_volver_GC);
        buttonEC.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                Intent i = new Intent(Gestion_Cubiculos.this,MainActivity.class);
                startActivity(i);
            }
        });
        buttonMC.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                Intent i = new Intent(Gestion_Cubiculos.this,MainActivity.class);
                startActivity(i);
            }
        });
        buttonVGC.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                Intent i = new Intent(Gestion_Cubiculos.this,MainActivity.class);
                startActivity(i);
            }
        });
        buttonAC.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                Intent i = new Intent(Gestion_Cubiculos.this,MainActivity.class);
                startActivity(i);
            }
        });
    }

}