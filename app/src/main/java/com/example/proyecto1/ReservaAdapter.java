package com.example.proyecto1;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

class Reservas {
    private String horaInicio;
    private String horaFin;
    private int numeroCubiculo;
    private String usuario;

    public Reservas(String horaInicio, String horaFin, int numeroCubiculo, String usuario) {
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.numeroCubiculo = numeroCubiculo;
        this.usuario = usuario;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    public int getNumeroCubiculo() {
        return numeroCubiculo;
    }

    public void setNumeroCubiculo(int numeroCubiculo) {
        this.numeroCubiculo = numeroCubiculo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}


class ReservasAdapter extends RecyclerView.Adapter<ReservasAdapter.ReservasViewHolder> {


    List<Reservas> listaReservas;



    // Constructor y métodos necesarios

    public ReservasAdapter() {

    }

    @NonNull
    @Override
    public ReservasViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_reservas, parent, false);
        return new ReservasViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ReservasViewHolder holder, int position) {

        Reservas reserva = listaReservas.get(position);
        holder.numCubiculo.setText(String.valueOf(reserva.getNumeroCubiculo()));
        holder.reservaID.setText(String.valueOf(reserva.getUsuario()));
        holder.horaInicio.setText(String.valueOf(reserva.getHoraInicio()));
        holder.horaFin.setText(String.valueOf(reserva.getHoraFin()));

    }

    @Override
    public int getItemCount() {
        return listaReservas.size();
    }

    public class ReservasViewHolder extends RecyclerView.ViewHolder {

        TextView numCubiculo, reservaID, horaInicio,horaFin;

        public ReservasViewHolder(@NonNull View itemView) {
            super(itemView);
            numCubiculo= itemView.findViewById(R.id.IdCubiculoReserva);
            reservaID= itemView.findViewById(R.id.IdReserva);
            horaInicio = itemView.findViewById(R.id.HoraInicio);
            horaFin = itemView.findViewById(R.id.HoraFin);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        Reservas reserva = listaReservas.get(position);
                        String  id=reserva.getUsuario();
                        // Abrir la actividad de detalles del cubículo y pasar los datos
                        Intent intent = new Intent(itemView.getContext(), EditarReserva.class);
                        intent.putExtra("carne",id);
                        itemView.getContext().startActivity(intent);

                    }
                }
            });
        }

    }

    // Agrega aquí los métodos para actualizar la lista de cubiculos en el adaptador
    // Por ejemplo: setListaCubiculos(), addCubiculo(), removeCubiculo(), etc.

    public void setListaReservas(List<Reservas> listaReservas) {
        this.listaReservas = listaReservas;
    }



}


