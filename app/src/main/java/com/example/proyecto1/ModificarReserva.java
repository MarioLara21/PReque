package com.example.proyecto1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

public class ModificarReserva extends AppCompatActivity {

    private Button btnOpenDatePicker;
    private Button btnOpenTimePicker1;
    private Button btnOpenTimePicker2;
    private Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_reserva);

        btnOpenDatePicker = findViewById(R.id.seleccionFecha);
        btnOpenTimePicker1 = findViewById(R.id.seleccionHora);
        btnOpenTimePicker2 = findViewById(R.id.seleccionHora2);
        calendar = Calendar.getInstance();

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
                        String selectedDate = dateFormat.format(calendar.getTime());
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
                        String selectedTime = timeFormat.format(calendar.getTime());
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
                        String selectedTime = timeFormat.format(calendar.getTime());
                        Log.d("TimePicker2", "Hora seleccionada: " + selectedTime);
                    }
                },
                hour,
                minute,
                false
        );

        timePickerDialog.show();
    }
}



