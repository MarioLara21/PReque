package com.example.proyecto1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.HashMap;
import java.util.Map;

public class DetallesCubiculoEdicion extends AppCompatActivity {


    FirebaseFirestore db = FirebaseFirestore.getInstance();



    // Obtener el Intent que inició esta actividad

    int cubiculoId;
    boolean acceso;
    boolean braile;
    boolean disponible;

    EditText capacidad;

    int capacidadInt;

    Switch switchAcceso;
    Switch switchBraile;
    Switch switchDisponibilidad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_cubiculo_edicion);

        Intent intent = getIntent();

        // Obtener el valor pasado a través de putExtra()
         cubiculoId= intent.getIntExtra("cubiculo",0);

        System.out.printf("\n");
        System.out.printf("\n");
        System.out.printf(String.valueOf(cubiculoId));
        System.out.printf("\n");
        System.out.printf("\n");

        Button buttonBackEdicion = (Button) findViewById(R.id.btn_VolverEdicion);

        Button buttonEditar= (Button) findViewById(R.id.btn_modificarCubiculoEdicion);

        capacidad = findViewById(R.id.textCapacidadEdicion);


         switchAcceso = findViewById(R.id.switchAccesoEspecializadoEdicion);

         switchBraile = findViewById(R.id.switchBraileEdicion);

         switchDisponibilidad = findViewById(R.id.switchDisponibilidadEdicion);



        buttonBackEdicion.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(DetallesCubiculoEdicion.this,GestionCubiculos2.class);
                startActivity(i);
            }
        });


        buttonEditar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                EditarCubiculo(cubiculoId);
            }
        });



}


    public void EditarCubiculo(int CubiculoId){

        capacidadInt= Integer.parseInt(capacidad.getText().toString());
        acceso = switchAcceso.isChecked();
        braile = switchBraile.isChecked();
        disponible = switchDisponibilidad.isChecked();


        db.collection("Cubiculos")
                .whereEqualTo("Numero", cubiculoId)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            // Accede al documento y actualiza el valor del campo
                            document.getReference().update("Capacidad", capacidadInt);
                            document.getReference().update("DisponibilidadAcceso", acceso);
                            document.getReference().update("DisponibilidadBraile", braile);
                            document.getReference().update("Estado", disponible);

                            Toast.makeText(DetallesCubiculoEdicion.this, "Cubiculo Actualizado", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(DetallesCubiculoEdicion.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                });






    }


}