package com.example.proyecto1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class Login extends AppCompatActivity {
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db = FirebaseFirestore.getInstance();

        EditText correo = findViewById(R.id.Correo_text2);
        EditText password = findViewById(R.id.Contrasena_text2);
        Button myButton = findViewById(R.id.button_registro2);


        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String correoText = correo.getText().toString();
                String passText = password.getText().toString();

                // Buscar en la colección "usuarios"
                db.collection("usuarios").whereEqualTo("CorreoElectronico", correoText)
                        .whereEqualTo("Contrasena", passText)
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    if (!task.getResult().isEmpty()) {
                                        // El usuario existe y las credenciales son correctas
                                        Intent usuarioIntent = new Intent(Login.this, menu_usuarios.class);
                                        startActivity(usuarioIntent);
                                    } else {
                                        // Credenciales incorrectas
                                        Toast.makeText(Login.this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show();
                                        Log.d("Login", "Credenciales incorrectas. Correo: " + correoText + ", Contraseña: " + passText);

                                        // Intenta buscar en la colección "Admins"
                                        buscarEnAdmins(correoText, passText);
                                    }
                                } else {
                                    // Error al consultar la colección "usuarios"
                                    Toast.makeText(Login.this, "Error al buscar el usuario", Toast.LENGTH_SHORT).show();
                                    Log.d("Login", "Error al buscar el usuario: " + task.getException().getMessage());
                                }
                            }
                        });
            }
        });
    }

    // Método para buscar el usuario en la colección "Admins"
    private void buscarEnAdmins(String correoText, String passText) {
        db.collection("Admins").whereEqualTo("CorreoElectronico", correoText)
                .whereEqualTo("Contrasena", passText)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            if (!task.getResult().isEmpty()) {
                                // El administrador existe y las credenciales son correctas
                                Intent adminIntent = new Intent(Login.this, menu_administradores.class);
                                startActivity(adminIntent);
                            } else {
                                // Credenciales incorrectas
                                Toast.makeText(Login.this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            // Error al consultar la colección "Admins"
                            Toast.makeText(Login.this, "Error al buscar el usuario", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
