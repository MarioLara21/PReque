package com.example.proyecto1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonSU = (Button) findViewById(R.id.button_signup);
        Button buttonLgn = (Button) findViewById(R.id.button_login);

        buttonSU.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){

                Intent i = new Intent(MainActivity.this,signup.class);
                startActivity(i);
            }
        });
        buttonLgn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){

                Intent i = new Intent(MainActivity.this,Login.class);
                startActivity(i);
            }
        });

        }
}