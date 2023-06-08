package com.example.proyecto1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class signup extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    private DocumentReference contadorRef;

    public static boolean customRegex(EditText editText, String
            regexPattern){
        Pattern pattern = Pattern.compile(regexPattern,Pattern.CASE_INSENSITIVE);
        Matcher matcher =
                pattern.matcher(editText.getText().toString());
        if(!matcher.find()){
            //editText.setError(errorMessage); Si se necesita se debe agregar esto en los parametros String errorMessage
            return false;
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        contadorRef = db.collection("Contadores").document("ContadorUsuarios");

        EditText correoText = findViewById(R.id.Correo_text);
        EditText passwordText = findViewById(R.id.Contrasena_text);
        EditText fechaNacimientoText = findViewById(R.id.FechaNacimiento_text);
        EditText nombreText = findViewById(R.id.Nombre_text);
        EditText primerApellidoText = findViewById(R.id.PrimerApellido_text);
        EditText segundoApellidoText = findViewById(R.id.SegundoApellido_text);
        EditText carneText = findViewById(R.id.Carne_text);
        EditText cedulaText = findViewById(R.id.Cedula_text);
        Button myButton = findViewById(R.id.button_registro);
        Button boton_volv = findViewById(R.id.boton_volver);

        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String correo = correoText.getText().toString();
                String password = passwordText.getText().toString();
                String fechaNacimientoString = fechaNacimientoText.getText().toString();
                String nombre = nombreText.getText().toString();
                String primerApellido = primerApellidoText.getText().toString();
                String segundoApellido = segundoApellidoText.getText().toString();
                String carne = carneText.getText().toString();
                String cedula = cedulaText.getText().toString();
                String regexContrasena = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8}$".toString();
                // Verifica si el correo termina en "@estudiantec.cr"
                if (correo.endsWith("@estudiantec.cr")) {
                    // Autentica al usuario utilizando el método createUserWithEmailAndPassword()
                    if(customRegex(passwordText,regexContrasena)){ //Verifica el formato de la contrasenna
                        mAuth.createUserWithEmailAndPassword(correo, password)
                                .addOnCompleteListener(task -> {
                                    if (task.isSuccessful()) {
                                        // El usuario se autenticó correctamente, procede a agregar sus datos a la base de datos
                                        FirebaseUser currentUser = mAuth.getCurrentUser();
                                        if (currentUser != null) {
                                            // Convierte la cadena de fecha en un objeto Date
                                            Date fechaNacimientoDate = parseDate(fechaNacimientoString);
                                            // Obtiene el valor de tiempo en milisegundos como un long
                                            long fechaNacimientoMillis = fechaNacimientoDate.getTime();
                                            // Crea el objeto Timestamp utilizando el valor de tiempo
                                            Timestamp fechaNacimiento = new Timestamp(fechaNacimientoMillis);
                                            // Obtiene el contador actual
                                            contadorRef.get().addOnCompleteListener(task1 -> {
                                                if (task1.isSuccessful()) {
                                                    long contador = task1.getResult().getLong("contador");
                                                    // Crea el nuevo documento de usuario con el ID basado en el contador
                                                    DocumentReference nuevoUsuarioRef = db.collection("usuarios").document(String.valueOf(contador));
                                                    Map<String, Object> nuevoUsuario = new HashMap<>();
                                                    nuevoUsuario.put("CorreoElectronico", correo);
                                                    nuevoUsuario.put("Contrasena", password);
                                                    nuevoUsuario.put("FechaDeNacimiento", fechaNacimiento);
                                                    nuevoUsuario.put("Nombre", nombre);
                                                    nuevoUsuario.put("PrimerApellido", primerApellido);
                                                    nuevoUsuario.put("SegundoApellido", segundoApellido);
                                                    nuevoUsuario.put("carne", carne);
                                                    nuevoUsuario.put("cedula", cedula);

                                                    nuevoUsuarioRef.set(nuevoUsuario)
                                                            .addOnCompleteListener(task2 -> {
                                                                if (task2.isSuccessful()) {
                                                                    // Incrementa el contador en la base de datos
                                                                    contadorRef.update("contador", contador + 1);
                                                                    Toast.makeText(getApplicationContext(), "Se agregó el usuario con éxito", Toast.LENGTH_SHORT).show();
                                                                } else {
                                                                    // No se pudo agregar el usuario a la base de datos
                                                                    String errorMessage = task2.getException().getMessage();
                                                                    Toast.makeText(getApplicationContext(), "Error al agregar el usuario: " + errorMessage, Toast.LENGTH_SHORT).show();
                                                                }
                                                            });
                                                } else {
                                                    // No se pudo obtener el contador
                                                    String errorMessage = task1.getException().getMessage();
                                                    Toast.makeText(getApplicationContext(), "Error al obtener el contador: " + errorMessage, Toast.LENGTH_SHORT).show();
                                                }
                                            });
                                        }
                                    } else {
                                        // No se pudo autenticar al usuario
                                        Toast.makeText(getApplicationContext(), "No se pudo autenticar al usuario", Toast.LENGTH_SHORT).show();
                                    }
                                });
                    }else{
                        Toast.makeText(getApplicationContext(), "Por favor utilice una contraseña con el siguiente formato:\n " +"8 caracteres mínimo\n" + "minimo una mayúscula\n" + "mínimo una minúscula\n" + "mínimo un caracter especial", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // El correo no termina en "@estudiantec.cr", no se agrega a la base de datos
                    Toast.makeText(getApplicationContext(), "El correo no es válido", Toast.LENGTH_SHORT).show();
                }
            }
        });
        boton_volv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(signup.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
    // Método para convertir una cadena de fecha en un objeto Date
    private Date parseDate(String dateString) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            return format.parse(dateString);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
