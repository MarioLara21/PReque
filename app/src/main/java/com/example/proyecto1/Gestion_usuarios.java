package com.example.proyecto1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.NonNull;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

// Clase Usuario
 class Usuario {
    private String id;
    private String correoElectronico;
    private Timestamp fechaDeNacimiento;
    private String nombre;
    private String primerApellido;
    private String segundoApellido;
    private String carne;
    private String cedula;

    public Usuario() {
        // Constructor vacío requerido para Firestore
    }

    public Usuario(String id, String correoElectronico, Timestamp fechaDeNacimiento, String nombre, String primerApellido, String segundoApellido, String carne, String cedula) {
        this.id = id;
        this.correoElectronico = correoElectronico;
        this.fechaDeNacimiento = fechaDeNacimiento;
        this.nombre = nombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.carne = carne;
        this.cedula = cedula;
    }

    public String getId() {
        return id;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public Timestamp getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public String getCarne() {
        return carne;
    }

    public String getCedula() {
        return cedula;
    }
}

// Clase Gestion_usuarios
public class Gestion_usuarios extends AppCompatActivity {
    private FirebaseFirestore db;
    private CollectionReference usuariosCollection;
    private RecyclerView recyclerView;
    private UsuarioAdapter usuarioAdapter;
    private Button buttonEliminarUsuario;
    private Button buttonRevisarHistorial;

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
                    // Obtener el ID del documento
                    String id = document.getId();

                    // Obtener los demás datos del documento y crear una instancia de Usuario
                    String correoElectronico = document.getString("CorreoElectronico");
                    Timestamp fechaDeNacimiento = document.getTimestamp("FechaDeNacimiento");
                    String nombre = document.getString("Nombre");
                    String primerApellido = document.getString("PrimerApellido");
                    String segundoApellido = document.getString("SegundoApellido");
                    String carne = document.getString("carne");
                    String cedula = document.getString("cedula");

                    Usuario usuario = new Usuario(id, correoElectronico, fechaDeNacimiento, nombre, primerApellido, segundoApellido, carne, cedula);
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

        buttonEliminarUsuario = findViewById(R.id.button_eliminar_usuario);
        buttonEliminarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!usuarioAdapter.getSelectedUserId().isEmpty()) {
                    // Obtener el ID del usuario seleccionado
                    String selectedUserId = usuarioAdapter.getSelectedUserId();

                    // Eliminar el usuario de Firestore
                    usuariosCollection.document(selectedUserId).delete()
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(Gestion_usuarios.this, "Usuario eliminado correctamente", Toast.LENGTH_SHORT).show();

                                    // Actualizar la lista de usuarios
                                    usuarioAdapter.setSelectedUserId("");
                                    usuarioAdapter.notifyDataSetChanged();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(Gestion_usuarios.this, "Error al eliminar usuario", Toast.LENGTH_SHORT).show();
                                }
                            });
                } else {
                    Toast.makeText(Gestion_usuarios.this, "Seleccione un usuario para eliminar", Toast.LENGTH_SHORT).show();
                }
            }
        });
        buttonRevisarHistorial = findViewById(R.id.button_revisar_historial);
        buttonRevisarHistorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!usuarioAdapter.getSelectedUserId().isEmpty()) {
                    // Obtener el ID del usuario seleccionado
                    String selectedUserId = usuarioAdapter.getSelectedUserId();

                    // Pasar el ID del usuario a la actividad de revisar historial
                    Intent intent = new Intent(Gestion_usuarios.this, Historial_Reservas.class);
                    intent.putExtra("userId", selectedUserId);
                    startActivity(intent);
                } else {
                    Toast.makeText(Gestion_usuarios.this, "Seleccione un usuario para revisar el historial", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
