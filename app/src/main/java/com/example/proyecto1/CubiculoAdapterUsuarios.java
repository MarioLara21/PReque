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

class CubiculoUsuarios{
    private int capacidad;
    private boolean disponibilidadAcceso;
    private boolean disponibilidadBraile;
    private boolean estado;
    private int numero;

    public CubiculoUsuarios() {
        // Constructor vacío requerido para Firestore
    }

    public CubiculoUsuarios(int capacidad, boolean disponibilidadAcceso, boolean disponibilidadBraile, boolean estado, int numero) {
        this.capacidad = capacidad;
        this.disponibilidadAcceso = disponibilidadAcceso;
        this.disponibilidadBraile = disponibilidadBraile;
        this.estado = estado;
        this.numero = numero;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public boolean isDisponibilidadAcceso() {
        return disponibilidadAcceso;
    }

    public void setDisponibilidadAcceso(boolean disponibilidadAcceso) {
        this.disponibilidadAcceso = disponibilidadAcceso;
    }

    public boolean isDisponibilidadBraile() {
        return disponibilidadBraile;
    }

    public void setDisponibilidadBraile(boolean disponibilidadBraile) {
        this.disponibilidadBraile = disponibilidadBraile;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }


}


class CubiculoAdapterUsuarios extends RecyclerView.Adapter<CubiculoAdapterUsuarios.CubiculoViewHolder> {


    List<CubiculoUsuarios> listaCubiculos;


    // Constructor y métodos necesarios

    public CubiculoAdapterUsuarios( List<CubiculoUsuarios> listaCubiculos) {
        this.listaCubiculos = listaCubiculos;
    }

    @NonNull
    @Override
    public CubiculoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_cubiculos, parent, false);
        return new CubiculoViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull CubiculoViewHolder holder, int position) {

        CubiculoUsuarios cubiculo = listaCubiculos.get(position);
        holder.numCubiculo.setText(String.valueOf(cubiculo.getNumero()));
        if(cubiculo.isEstado()){
            holder.estadoCubiculo.setText("Disponible");
        }
        else {
            holder.estadoCubiculo.setText("Inhabilitado");
        }


    }

    @Override
    public int getItemCount() {
        return listaCubiculos.size();
    }

    public class CubiculoViewHolder extends RecyclerView.ViewHolder {

        TextView numCubiculo, estadoCubiculo;
        public CubiculoViewHolder(@NonNull View itemView) {
            super(itemView);
            numCubiculo= itemView.findViewById(R.id.IdNumCubiculo);
            estadoCubiculo= itemView.findViewById(R.id.IdEstado);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        CubiculoUsuarios cubiculo = listaCubiculos.get(position);
                        // Abrir la actividad de detalles del cubículo y pasar los datos
                        int idCubiculo = cubiculo.getNumero();
                        boolean estadocubiculo=cubiculo.isEstado();
                        if (estadocubiculo){
                            Intent intent = new Intent(itemView.getContext(), RealizarReserva.class);
                            intent.putExtra("cubiculo", idCubiculo);
                            itemView.getContext().startActivity(intent);
                        }
                        else{
                            Toast.makeText(itemView.getContext(), "No se puede reservar este cubiculo", Toast.LENGTH_SHORT).show();
                        }

                    }
                }
            });
        }

    }

    // Agrega aquí los métodos para actualizar la lista de cubiculos en el adaptador
    // Por ejemplo: setListaCubiculos(), addCubiculo(), removeCubiculo(), etc.

    public void setListaCubiculos(List<CubiculoUsuarios> listaCubiculos) {
        this.listaCubiculos = listaCubiculos;
    }
}






