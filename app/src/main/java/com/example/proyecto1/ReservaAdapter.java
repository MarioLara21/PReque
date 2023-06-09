package com.example.proyecto1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyecto1.R;

import java.util.List;

public class ReservaAdapter extends RecyclerView.Adapter<ReservaAdapter.ReservaViewHolder> {
    private List<Reserva> reservas;

    public ReservaAdapter(List<Reserva> reservas) {
        this.reservas = reservas;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }

    @NonNull
    @Override
    public ReservaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_reserva, parent, false);
        return new ReservaViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ReservaViewHolder holder, int position) {
        Reserva reserva = reservas.get(position);
        holder.bind(reserva);
    }

    @Override
    public int getItemCount() {
        return reservas.size();
    }

    public static class ReservaViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewCubiculo;
        private TextView textViewUsuario;
        private TextView textViewHoraInicio;
        private TextView textViewHoraFin;

        public ReservaViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewCubiculo = itemView.findViewById(R.id.textView_cubiculo);
            textViewUsuario = itemView.findViewById(R.id.textView_usuario);
            textViewHoraInicio = itemView.findViewById(R.id.textView_hora_inicio);
            textViewHoraFin = itemView.findViewById(R.id.textView_hora_fin);
        }

        public void bind(Reserva reserva) {
            textViewCubiculo.setText(String.valueOf(reserva.getNumeroCubiculo()));
            textViewUsuario.setText(reserva.getUsuario());
            textViewHoraInicio.setText(reserva.getHoraInicio());
            textViewHoraFin.setText(reserva.getHoraFin());
        }
    }
}
