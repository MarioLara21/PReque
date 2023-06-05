package com.example.proyecto1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Gestion_usuarios extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_usuarios);

        Button buttonRH = findViewById(R.id.button_revisar_historial);
        Button buttonEU = findViewById(R.id.button_eliminar_usuario);
        Button buttonVU = findViewById(R.id.button_volver_usuarios);

        buttonRH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Acción al hacer clic en "Revisar Historial"
                Intent intent = new Intent(Gestion_usuarios.this, Historial_Reservas.class);
                startActivity(intent);
            }
        });

        buttonEU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Acción al hacer clic en "Eliminar Usuario"
                // Agrega tu código aquí para eliminar un usuario
            }
        });

        buttonVU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Acción al hacer clic en "volver"
                Intent intent = new Intent(Gestion_usuarios.this, menu_administradores.class);
                startActivity(intent);
            }
        });
    }
}
