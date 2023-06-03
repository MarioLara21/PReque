package com.example.proyecto1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        EditText correo=findViewById(R.id.Correo_text2);
        EditText password=findViewById(R.id.Contrasena_text2);
        Button myButton = (Button) findViewById(R.id.button_registro2);

        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String correoText=correo.getText().toString();
                String passText=password.getText().toString();
                mAuth.signInWithEmailAndPassword(correoText, passText).addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {

                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information

                            FirebaseUser user = mAuth.getCurrentUser();
                            String userUID= user.getUid();
                            Intent i;

                            if(userUID.equals("OTVTJvCApQfQptipOL5YCR8VKTk1")){
                                i = new Intent(Login.this, menu_administradores.class);
                            }

                            else {
                                i = new Intent(Login.this, menu_usuarios.class);
                            }
                            startActivity(i);


                        } else {
                            Intent i = new Intent(Login.this, UsuarioInexistente.class);
                            startActivity(i);
                        }
                    }
                });
            }
        });
    }
}