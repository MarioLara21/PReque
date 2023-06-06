package com.example.proyecto1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class Gestion_usuarios extends AppCompatActivity {
    private FirebaseFirestore db;
    private CollectionReference usuariosCollection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_usuarios);

        // Inicializar Firebase Firestore
        db = FirebaseFirestore.getInstance();
        usuariosCollection = db.collection("usuarios");

        // Obtener la referencia al TableLayout del diseño XML
        TableLayout tableLayout = findViewById(R.id.tableLayout);

        // Realizar la consulta a Firestore
        usuariosCollection.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                QuerySnapshot querySnapshot = task.getResult();
                if (querySnapshot != null) {
                    // Recorrer los documentos obtenidos
                    for (DocumentSnapshot document : querySnapshot.getDocuments()) {
                        // Crear una nueva fila para cada documento
                        TableRow row = new TableRow(Gestion_usuarios.this);

                        // Obtener los datos de cada documento
                        String numeroCubiculo = document.getString("numero_cubiculo");
                        String numeroCarne = document.getString("numero_carne");
                        String nombre = document.getString("nombre");
                        String primerApellido = document.getString("primer_apellido");
                        String segundoApellido = document.getString("segundo_apellido");
                        String edad = document.getString("edad");
                        String fechaNacimiento = document.getString("fecha_nacimiento");
                        String estado = document.getString("estado");
                        String correoElectronico = document.getString("correo_electronico");
                        String contrasenaInstitucional = document.getString("contrasena_institucional");

                        // Crear celdas y establecer los valores de texto
                        addCellToRow(row, numeroCubiculo);
                        addCellToRow(row, numeroCarne);
                        addCellToRow(row, nombre);
                        addCellToRow(row, primerApellido);
                        addCellToRow(row, segundoApellido);
                        addCellToRow(row, edad);
                        addCellToRow(row, fechaNacimiento);
                        addCellToRow(row, estado);
                        addCellToRow(row, correoElectronico);
                        addCellToRow(row, contrasenaInstitucional);

                        // Agregar la fila al TableLayout
                        tableLayout.addView(row);
                    }
                }
            } else {
                // Error al obtener los datos de Firestore
                Toast.makeText(Gestion_usuarios.this, "Error al obtener los datos", Toast.LENGTH_SHORT).show();
            }
        });

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

    private void addCellToRow(TableRow row, String text) {
        TextView cell = new TextView(Gestion_usuarios.this);
        cell.setText(text);
        row.addView(cell);
    }
}
