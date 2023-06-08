package com.example.proyecto1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

class Usuario {
    private String correoElectronico;
    private String fechaDeNacimiento;
    private String nombre;
    private String primerApellido;
    private String segundoApellido;
    private String carne;
    private String cedula;

    public Usuario() {
        // Constructor vacío requerido para Firestore
    }

    public Usuario(String correoElectronico, String fechaDeNacimiento, String nombre, String primerApellido, String segundoApellido, String carne, String cedula) {
        this.correoElectronico = correoElectronico;
        this.fechaDeNacimiento = fechaDeNacimiento;
        this.nombre = nombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.carne = carne;
        this.cedula = cedula;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(String fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public String getCarne() {
        return carne;
    }

    public void setCarne(String carne) {
        this.carne = carne;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }
}
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
                List<Usuario> listaUsuarios = new ArrayList<>();

                for (QueryDocumentSnapshot document : task.getResult()) {
                    // Obtener los datos del documento y crear una instancia de Usuario
                    String correoElectronico = document.getString("correoElectronico");
                    String fechaDeNacimiento = document.getString("fechaDeNacimiento");
                    String nombre = document.getString("nombre");
                    String primerApellido = document.getString("primerApellido");
                    String segundoApellido = document.getString("segundoApellido");
                    String carne = document.getString("carne");
                    String cedula = document.getString("cedula");

                    Usuario usuario = new Usuario(correoElectronico, fechaDeNacimiento, nombre, primerApellido, segundoApellido, carne, cedula);
                    listaUsuarios.add(usuario);
                }

                // Mostrar los datos de los usuarios en la tabla
                for (Usuario usuario : listaUsuarios) {
                    TableRow row = new TableRow(this);
                    TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
                    row.setLayoutParams(layoutParams);

                    TextView correoTextView = new TextView(this);
                    correoTextView.setText(usuario.getCorreoElectronico());
                    row.addView(correoTextView);

                    TextView nombreTextView = new TextView(this);
                    nombreTextView.setText(usuario.getNombre());
                    row.addView(nombreTextView);

                    TextView apellidoTextView = new TextView(this);
                    apellidoTextView.setText(usuario.getPrimerApellido() + " " + usuario.getSegundoApellido());
                    row.addView(apellidoTextView);

                    TextView carneTextView = new TextView(this);
                    carneTextView.setText(usuario.getCarne());
                    row.addView(carneTextView);

                    TextView cedulaTextView = new TextView(this);
                    cedulaTextView.setText(usuario.getCedula());
                    row.addView(cedulaTextView);

                    tableLayout.addView(row);
                }
            } else {
                Log.d("Gestion_usuarios", "Error getting documents: ", task.getException());
            }
        });
    }
}

