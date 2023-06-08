package com.example.proyecto1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyecto1.R;
import com.example.proyecto1.Usuario;

import java.util.List;

public class UsuarioAdapter extends RecyclerView.Adapter<UsuarioAdapter.UsuarioViewHolder> {
    private List<Usuario> listaUsuarios;

    public UsuarioAdapter(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public void setUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }
    @NonNull
    @Override
    public UsuarioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_usuario, parent, false);
        return new UsuarioViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull UsuarioViewHolder holder, int position) {
        Usuario usuario = listaUsuarios.get(position);
        holder.correoTextView.setText(usuario.getCorreoElectronico());
        holder.nombreTextView.setText(usuario.getNombre());
        holder.apellidoTextView.setText(usuario.getPrimerApellido() + " " + usuario.getSegundoApellido());
        holder.carneTextView.setText(usuario.getCarne());
        holder.cedulaTextView.setText(usuario.getCedula());
    }

    @Override
    public int getItemCount() {
        return listaUsuarios.size();
    }

    public static class UsuarioViewHolder extends RecyclerView.ViewHolder {
        public TextView correoTextView;
        public TextView nombreTextView;
        public TextView apellidoTextView;
        public TextView carneTextView;
        public TextView cedulaTextView;

        public UsuarioViewHolder(@NonNull View itemView) {
            super(itemView);
            correoTextView = itemView.findViewById(R.id.textViewCorreo);
            nombreTextView = itemView.findViewById(R.id.textViewNombre);
            apellidoTextView = itemView.findViewById(R.id.textViewApellido);
            carneTextView = itemView.findViewById(R.id.textViewCarne);
            cedulaTextView = itemView.findViewById(R.id.textViewCedula);
        }
    }


}

