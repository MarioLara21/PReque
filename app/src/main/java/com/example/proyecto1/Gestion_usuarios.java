package com.example.proyecto1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

class Usuario {
    private String CorreoElectronico;
    private String FechaDeNacimiento;
    private String Nombre;
    private String PrimerApellido;
    private String SegundoApellido;
    private String carne;
    private String cedula;

    public Usuario() {
        // Constructor vacío requerido para Firestore
    }

    public Usuario(String correoElectronico, String fechaDeNacimiento, String nombre, String primerApellido, String segundoApellido, String carne, String cedula) {
        this.CorreoElectronico = correoElectronico;
        this.FechaDeNacimiento = fechaDeNacimiento;
        this.Nombre = nombre;
        this.PrimerApellido = primerApellido;
        this.SegundoApellido = segundoApellido;
        this.carne = carne;
        this.cedula = cedula;
    }

    public String getCorreoElectronico() {
        return CorreoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.CorreoElectronico = correoElectronico;
    }

    public String getFechaDeNacimiento() {
        return FechaDeNacimiento;
    }

    public void setFechaDeNacimiento(String fechaDeNacimiento) {
        this.FechaDeNacimiento = fechaDeNacimiento;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        this.Nombre = nombre;
    }

    public String getPrimerApellido() {
        return PrimerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.PrimerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return SegundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.SegundoApellido = segundoApellido;
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
    private RecyclerView recyclerView;
    private UsuarioAdapter usuarioAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_usuarios);

        // Inicializar Firebase Firestore
        db = FirebaseFirestore.getInstance();
        usuariosCollection = db.collection("usuarios");

        // Obtener la referencia al RecyclerView
        recyclerView = findViewById(R.id.recyclerView);

        // Establecer el LayoutManager para el RecyclerView
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // Crear el adaptador de usuarios
        usuarioAdapter = new UsuarioAdapter(new ArrayList<>());
        recyclerView.setAdapter(usuarioAdapter);

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

                // Actualizar los datos del adaptador
                usuarioAdapter.setUsuarios(listaUsuarios);
                usuarioAdapter.notifyDataSetChanged();
            } else {
                Log.d("Gestion_usuarios", "Error getting documents: ", task.getException());
            }
        });
        Button buttonVolver = findViewById(R.id.button_volver_usuarios);
        buttonVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Gestion_usuarios.this, menu_administradores.class);
                startActivity(intent);
                finish();
            }
        });

    }
}

