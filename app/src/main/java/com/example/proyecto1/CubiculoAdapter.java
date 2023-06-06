package com.example.proyecto1;

import android.content.Intent;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

class Cubiculo{
    private int capacidad;
    private boolean disponibilidadAcceso;
    private boolean disponibilidadBraile;
    private boolean estado;
    private int numero;

    public Cubiculo() {
        // Constructor vacío requerido para Firestore
    }

    public Cubiculo(int capacidad, boolean disponibilidadAcceso, boolean disponibilidadBraile, boolean estado, int numero) {
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


public class CubiculoAdapter extends RecyclerView.Adapter<CubiculoAdapter.CubiculoViewHolder> {

    private List<Cubiculo> listaCubiculos;

    // Constructor y métodos necesarios

    @NonNull
    @Override
    public CubiculoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // No se infla ninguna vista, ya que no se utilizará un diseño específico para cada elemento del RecyclerView
        View itemView = new View(parent.getContext()); // Crear una vista vacía
        return new CubiculoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CubiculoViewHolder holder, int position) {

        Cubiculo cubiculo = listaCubiculos.get(position);
        // No se configuran elementos de diseño, ya que no se utiliza un archivo de diseño específico para cada elemento
    }

    @Override
    public int getItemCount() {
        return listaCubiculos.size();
    }

    public class CubiculoViewHolder extends RecyclerView.ViewHolder {

        public CubiculoViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        Cubiculo cubiculo = listaCubiculos.get(position);
                        // Abrir la actividad de detalles del cubículo y pasar los datos
                        int idCubiculo = cubiculo.getNumero();
                        Intent intent = new Intent(itemView.getContext(), DetallesCubiculoEdicion.class);
                        intent.putExtra("cubiculo", idCubiculo);
                        itemView.getContext().startActivity(intent);
                    }
                }
            });
        }

    }

    // Agrega aquí los métodos para actualizar la lista de cubiculos en el adaptador
    // Por ejemplo: setListaCubiculos(), addCubiculo(), removeCubiculo(), etc.

    public void setListaCubiculos(List<Cubiculo> listaCubiculos) {
        this.listaCubiculos = listaCubiculos;
    }
}






