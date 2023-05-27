package com.example.proyecto1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class menu_administradores extends AppCompatActivity {
    Button buttonGC = (Button) findViewById(R.id.button_gestion_cubiculos);
    Button buttonGU = (Button) findViewById(R.id.button_gestion_usuarios);
    Button buttonGTU = (Button) findViewById(R.id.button_gestion_tiempo_uso);
    Button buttonGR = (Button) findViewById(R.id.button_gestion_reserva);
    Button buttonCS = (Button) findViewById(R.id.button_cerrar_sesion);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_administradores);
        buttonGC.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){

                Intent i = new Intent(menu_administradores.this,Gestion_Tiempos.class);
                startActivity(i);
            }
        });
        buttonGU.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){

                Intent i = new Intent(menu_administradores.this,MainActivity.class);
                startActivity(i);
            }
        });
        buttonGTU.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){

                Intent i = new Intent(menu_administradores.this,MainActivity.class);
                startActivity(i);
            }
        });
        buttonGR.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){

                Intent i = new Intent(menu_administradores.this,MainActivity.class);
                startActivity(i);
            }
        });
        buttonCS.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){

                Intent i = new Intent(menu_administradores.this,MainActivity.class);
                startActivity(i);
            }
        });
    }
}