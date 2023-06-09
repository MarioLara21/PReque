package com.example.proyecto1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
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
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

public class EditarReserva extends AppCompatActivity {

    private Button btnOpenDatePicker;
    private Button btnOpenTimePicker1;
    private Button btnOpenTimePicker2;
    private Calendar calendar;

    private Button volverAGestionReservas;

    private TextView cubiculoTexto;

    private String selectedDate_edicion;
    private String selectedTime_edicion;
    private String selectedTime2_edicion;

    private Button btnEditarReservacion;
    private Button btnEliminarReserva;

    private String UsuarioID;

    private String valorExtra;
    private CountDownTimer timer;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    CollectionReference reservasRef = db.collection("Reserva");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_reserva);

        timer = new CountDownTimer(2000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {

            }
        };
        btnOpenDatePicker = findViewById(R.id.seleccionFecha_edicion);
        btnOpenTimePicker1 = findViewById(R.id.seleccionHora1_Edicion);
        btnOpenTimePicker2 = findViewById(R.id.seleccionHora2_edicion);
        calendar = Calendar.getInstance();
        volverAGestionReservas= findViewById(R.id.btn_VolverGestionReservas);

        btnEditarReservacion= findViewById(R.id.modificar_Reserva);
        btnEliminarReserva= findViewById(R.id.eliminar_reserva);


        // Obtener el Intent que inició esta actividad
        Intent intent = getIntent();

        // Obtener el valor pasado a través de putExtra()
        valorExtra= intent.getStringExtra("carne");


        volverAGestionReservas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){

                Intent i = new Intent(EditarReserva.this,Gestion_Reservas.class);
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



        btnEditarReservacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EdicionReserva(valorExtra);
            }
        });

        btnEliminarReserva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BorrarReserva(valorExtra);
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
                        selectedDate_edicion = dateFormat.format(calendar.getTime());
                        Log.d("DatePicker", "Fecha seleccionada: " + selectedDate_edicion);
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
                        selectedTime_edicion = timeFormat.format(calendar.getTime());
                        Log.d("TimePicker1", "Hora seleccionada: " + selectedTime_edicion);
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
                        selectedTime2_edicion = timeFormat.format(calendar.getTime());
                        Log.d("TimePicker2", "Hora seleccionada: " + selectedTime2_edicion);
                    }
                },
                hour,
                minute,
                false
        );

        timePickerDialog.show();
    }



    private void EdicionReserva(String valorExtra1){




        String horaIni= selectedDate_edicion+","+selectedTime_edicion;
        String horaFin =selectedDate_edicion+","+selectedTime2_edicion;


        db.collection("Reserva")
                .whereEqualTo("Usuario", valorExtra1)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            // Accede al documento y actualiza el valor del campo
                            document.getReference().update("HoraInicio", horaIni);
                            document.getReference().update("HoraFin", horaFin);

                            Toast.makeText(EditarReserva.this, "Reserva Actualizada", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(EditarReserva.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                });

    }

    public void BorrarReserva(String valorExtra2){
        CollectionReference collectionRef;
        collectionRef= db.collection("Reserva");
        collectionRef.whereEqualTo("Usuario", valorExtra2).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                // Accede al documento y elimínalo
                                document.getReference().delete();
                            }
                            Toast.makeText(EditarReserva.this, "Documento(s) eliminado(s)", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(EditarReserva.this, "Error al eliminar el documento", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}