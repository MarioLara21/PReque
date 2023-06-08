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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class Login extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        EditText correo = findViewById(R.id.Correo_text2);
        EditText password = findViewById(R.id.Contrasena_text2);
        Button myButton = findViewById(R.id.button_registro2);

        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String correoText = correo.getText().toString();
                String passText = password.getText().toString();

                // Autenticar al usuario utilizando el método signInWithEmailAndPassword()
                mAuth.signInWithEmailAndPassword(correoText, passText)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    if (user != null) {
                                        // El usuario se autenticó correctamente
                                        Intent usuarioIntent = new Intent(Login.this, menu_usuarios.class);
                                        startActivity(usuarioIntent);
                                    } else {
                                        // El usuario no está autenticado o las credenciales son incorrectas
                                        Toast.makeText(Login.this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show();
                                        Log.d("Login", "Credenciales incorrectas. Correo: " + correoText + ", Contraseña: " + passText);
                                        buscarEnAdmins(correoText, passText);
                                    }
                                } else {
                                    // Error al autenticar al usuario
                                    Toast.makeText(Login.this, "Error al autenticar al usuario", Toast.LENGTH_SHORT).show();
                                    Log.d("Login", "Error al autenticar al usuario: " + task.getException().getMessage());
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
