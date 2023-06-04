package com.example.proyecto1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.google.common.collect.Table;

public class Gestion_Asignacion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_asignacion);

        Button buttonER = (Button) findViewById(R.id.button_eliminar_reserva);
        Button buttonMR = (Button) findViewById(R.id.button_modificar_reserva);
        Button buttonECRG = (Button) findViewById(R.id.button_enviar_correo_general);
        Button buttonECRR = (Button) findViewById(R.id.button_enviar_correo_reserva);
        Button buttonV = (Button) findViewById(R.id.button_volver_GAC);
        TableLayout tabla = (TableLayout) findViewById(R.id.table_GAC);
        TableRow fila = (TableRow) findViewById(R.id.fila_1_tabla_GAC);
        buttonECRG.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){

                Intent i = new Intent(Gestion_Asignacion.this,MainActivity.class);
                startActivity(i);
            }
        });
        buttonECRR.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){

                Intent i = new Intent(Gestion_Asignacion.this,MainActivity.class);
                startActivity(i);
            }
        });
        buttonV.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){

                Intent i = new Intent(Gestion_Asignacion.this,MainActivity.class);
                startActivity(i);
            }
        });
        buttonMR.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){

                Intent i = new Intent(Gestion_Asignacion.this,MainActivity.class);
                startActivity(i);
            }
        });
        buttonER.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(Gestion_Asignacion.this,MainActivity.class);
                startActivity(i);
            }
        });
    }

}