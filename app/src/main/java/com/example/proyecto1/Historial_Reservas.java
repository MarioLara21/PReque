package com.example.proyecto1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

// Clase Reserva
class Reserva {

    private String HoraFin;
    private String HoraInicio;
    private int NumeroCubiculo; // Cambiado a int

    private String Usuario;
    // Agregar más campos según la estructura de tus documentos

    private String id;

    public Reserva(String HoraFin, String HoraInicio, int NumeroCubiculo, String Usuario, String id) {
        this.HoraFin = HoraFin;
        this.HoraInicio = HoraInicio;
        this.NumeroCubiculo = NumeroCubiculo;
        this.Usuario = Usuario;
        this.id = id;
    }

    public String getHoraFin() {
        return HoraFin;
    }

    public String getHoraInicio() {
        return HoraInicio;
    }

    public int getNumeroCubiculo() {
        return NumeroCubiculo;
    }

    public String getUsuario() {
        return Usuario;
    }

    public String getId() {
        return id;
    }

    // Agregar getters/setters para más campos según la estructura de tus documentos
}



public class Historial_Reservas extends AppCompatActivity {
    private FirebaseFirestore db;

    private CollectionReference reservasCollection;
    private RecyclerView recyclerView;
    private ReservaAdapter reservaAdapter;
    private Button buttonVolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial_reservas);

        // Inicializar Firebase Firestore
        db = FirebaseFirestore.getInstance();
        reservasCollection = db.collection("Reserva");

        // Obtener la referencia al RecyclerView
        recyclerView = findViewById(R.id.recyclerView);

        // Establecer el LayoutManager para el RecyclerView
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // Crear el adaptador de reservas
        reservaAdapter = new ReservaAdapter(new ArrayList<>());
        recyclerView.setAdapter(reservaAdapter);

        // Realizar la consulta a Firestore
        reservasCollection.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                List<Reserva> listaReservas = new ArrayList<>();

                for (QueryDocumentSnapshot document : task.getResult()) {
                    // Obtener los datos del documento y crear una instancia de Reserva
                    String horaFin = document.getString("HoraFin");
                    String horaInicio = document.getString("HoraInicio");
                    int numeroCubiculo = document.getLong("NumeroCubiculo").intValue(); // Convertir a int
                    String usuario = document.getString("Usuario");
                    String id = document.getId();

                    Reserva reserva = new Reserva(horaFin, horaInicio, numeroCubiculo, usuario, id);
                    listaReservas.add(reserva);
                }

                // Actualizar los datos del adaptador
                reservaAdapter.setReservas(listaReservas);
                reservaAdapter.notifyDataSetChanged();
            } else {
                Log.d("Historial_Reservas", "Error getting documents: ", task.getException());
            }
        });

        buttonVolver = findViewById(R.id.button_volverHR);
        buttonVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Historial_Reservas.this, Gestion_usuarios.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
