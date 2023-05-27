package com.example.proyecto1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Modificar_Tiempo extends AppCompatActivity {
    Button buttonMT = (Button) findViewById(R.id.button_modificar_tiempo);
    Button buttonVMT = (Button) findViewById(R.id.button_volver_MT);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_tiempo);
        buttonMT.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){

                Intent i = new Intent(Modificar_Tiempo.this,MainActivity.class);
                startActivity(i);
            }
        });
        buttonVMT.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(Modificar_Tiempo.this,MainActivity.class);
                startActivity(i);
            }
        });
    }
}