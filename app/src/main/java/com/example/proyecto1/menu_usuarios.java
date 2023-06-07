package com.example.proyecto1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class menu_usuarios extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_usuarios);

        Button buttonSC = (Button) findViewById(R.id.button_solicitar_cubiculo);
        Button buttonCS = (Button) findViewById(R.id.button_cerrar_sesion_menu);
        buttonSC.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(menu_usuarios.this, SeleccionCubiculosUsuarios.class);
                startActivity(i);
            }
        });

        buttonCS.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(menu_usuarios.this,MainActivity.class);
                startActivity(i);
            }
        });
    }
}