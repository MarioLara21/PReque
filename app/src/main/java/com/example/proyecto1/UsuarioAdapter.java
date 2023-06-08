package com.example.proyecto1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyecto1.R;
import com.example.proyecto1.Usuario;
import com.google.firebase.firestore.DocumentSnapshot;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class UsuarioAdapter extends RecyclerView.Adapter<UsuarioAdapter.UsuarioViewHolder> {
    private List<Usuario> listaUsuarios;
    private int selectedPosition = -1;

    private String selectedUserId = "";

    private String deletedUserId = "";



    public UsuarioAdapter(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public void setUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public String getSelectedUserId() {
        return selectedUserId;
    }

    public void setSelectedUserId(String userId) {
        selectedUserId = userId;
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
        holder.primerApellidoTextView.setText(usuario.getPrimerApellido());
        holder.segundoApellidoTextView.setText(usuario.getSegundoApellido());
        holder.carneTextView.setText(String.valueOf(usuario.getCarne()));
        holder.cedulaTextView.setText(String.valueOf(usuario.getCedula()));
        holder.documentoTextView.setText(usuario.getId()); // Cambio solicitado

        // Formatear y mostrar la fecha de nacimiento
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String fechaNacimiento = dateFormat.format(usuario.getFechaDeNacimiento().toDate());
        holder.fechaNacimientoTextView.setText(fechaNacimiento);

        // Cambiar el color de fondo y agregar borde al elemento seleccionado
        if (selectedPosition == position) {
            holder.itemView.setBackgroundColor(holder.itemView.getContext().getResources().getColor(R.color.purple_200));
            holder.itemView.setBackgroundResource(R.drawable.selected_item_background);
        } else {
            holder.itemView.setBackgroundColor(holder.itemView.getContext().getResources().getColor(android.R.color.transparent));
            holder.itemView.setBackgroundResource(R.drawable.default_item_background);
        }

        // Manejar el evento de clic en el elemento
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int previousSelectedPosition = selectedPosition;
                selectedPosition = holder.getAdapterPosition();

                // Guardar el ID del usuario seleccionado
                selectedUserId = listaUsuarios.get(selectedPosition).getId();

                // Actualizar solo los elementos afectados
                if (previousSelectedPosition != RecyclerView.NO_POSITION) {
                    notifyItemChanged(previousSelectedPosition);
                }
                notifyItemChanged(selectedPosition);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaUsuarios.size();
    }

    public static class UsuarioViewHolder extends RecyclerView.ViewHolder {
        public TextView correoTextView;
        public TextView nombreTextView;
        public TextView primerApellidoTextView;
        public TextView segundoApellidoTextView;
        public TextView carneTextView;
        public TextView cedulaTextView;
        public TextView documentoTextView;
        public TextView fechaNacimientoTextView;

        public UsuarioViewHolder(@NonNull View itemView) {
            super(itemView);
            correoTextView = itemView.findViewById(R.id.textViewCorreo);
            nombreTextView = itemView.findViewById(R.id.textViewNombre);
            primerApellidoTextView = itemView.findViewById(R.id.textViewPrimerApellido);
            segundoApellidoTextView = itemView.findViewById(R.id.textViewSegundoApellido);
            carneTextView = itemView.findViewById(R.id.textViewCarne);
            cedulaTextView = itemView.findViewById(R.id.textViewCedula);
            documentoTextView = itemView.findViewById(R.id.textViewDocumento);
            fechaNacimientoTextView = itemView.findViewById(R.id.textViewFechaNacimiento);
        }
    }
}
