package com.example.proyecto1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

public class RealizarReserva extends AppCompatActivity {

    private Button btnOpenDatePicker;
    private Button btnOpenTimePicker1;
    private Button btnOpenTimePicker2;
    private Calendar calendar;

    private Button volverASeleccionUsuarios;

    private TextView cubiculoTexto;

    private String selectedDate;
    private String selectedTime;
    private String selectedTime2;

    private Button btnRealizarReservacion;

    private int valorExtra;
    private  String valorExtraUsuario;


    FirebaseFirestore db = FirebaseFirestore.getInstance();

    CollectionReference reservasRef = db.collection("Reserva");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.realizar_reserva);
        cubiculoTexto= findViewById(R.id.numeroCubiculoSeleccionado);

        btnOpenDatePicker = findViewById(R.id.seleccionFecha);
        btnOpenTimePicker1 = findViewById(R.id.seleccionHora);
        btnOpenTimePicker2 = findViewById(R.id.seleccionHora2);
        calendar = Calendar.getInstance();
        volverASeleccionUsuarios= findViewById(R.id.btn_VolveraSeleccionUsuarios);

        btnRealizarReservacion= findViewById(R.id.btn_RealizarReservaCubiculo);


        // Obtener el Intent que inició esta actividad
        Intent intent = getIntent();

        // Obtener el valor pasado a través de putExtra()
        valorExtra= intent.getIntExtra("cubiculo",0);

        valorExtraUsuario=intent.getStringExtra("idUser");

        cubiculoTexto.setText(String.valueOf(valorExtra));
        volverASeleccionUsuarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){

                Intent i = new Intent(RealizarReserva.this,SeleccionCubiculosUsuarios.class);
                startActivity(i);
            }

        });

        btnOpenDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDatePickerDialog();
            }
        });

        btnOpenTimePicker1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTimePickerDialog1();
            }
        });

        btnOpenTimePicker2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTimePickerDialog2();
            }
        });



        btnRealizarReservacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReservacionCubiculo(valorExtra,valorExtraUsuario);
            }
        });

    }





    private void openDatePickerDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        calendar.set(Calendar.YEAR, year);
                        calendar.set(Calendar.MONTH, month);
                        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());
                        selectedDate = dateFormat.format(calendar.getTime());
                        Log.d("DatePicker", "Fecha seleccionada: " + selectedDate);
                    }
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );

        datePickerDialog.show();
    }

    private void openTimePickerDialog1() {
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(
                this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        calendar.set(Calendar.MINUTE, minute);

                        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss 'UTC-6'", Locale.getDefault());
                        timeFormat.setTimeZone(TimeZone.getTimeZone("GMT-6")); // Configura la zona horaria deseada
                        selectedTime = timeFormat.format(calendar.getTime());
                        Log.d("TimePicker1", "Hora seleccionada: " + selectedTime);
                    }
                },
                hour,
                minute,
                false
        );

        timePickerDialog.show();
    }

    private void openTimePickerDialog2() {
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(
                this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        calendar.set(Calendar.MINUTE, minute);

                        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss 'UTC-6'", Locale.getDefault());
                        timeFormat.setTimeZone(TimeZone.getTimeZone("GMT-6")); // Configura la zona horaria deseada
                        selectedTime2 = timeFormat.format(calendar.getTime());
                        Log.d("TimePicker2", "Hora seleccionada: " + selectedTime2);
                    }
                },
                hour,
                minute,
                false
        );

        timePickerDialog.show();
    }



    private void ReservacionCubiculo(int valorExtra, String valorExtraUsuario){


        Map<String, Object> nuevoDocumento = new HashMap<>();
        nuevoDocumento.put("HoraFin", selectedDate+","+selectedTime);
        nuevoDocumento.put("HoraInicio", selectedDate+","+selectedTime2);
        nuevoDocumento.put("NumeroCubiculo", valorExtra);
        nuevoDocumento.put("Usuario", valorExtraUsuario);
        String NombreReserva=valorExtraUsuario+"-Reserva";

        DocumentReference nuevoDocumentoRef = reservasRef.document(NombreReserva);

        nuevoDocumentoRef.set(nuevoDocumento)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getApplicationContext(), "Se agregó con exito", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "Ocurrió un error", Toast.LENGTH_SHORT).show();
                    }
                });

    }

}



