package com.example.projetofinal.fragments;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.projetofinal.activities.ForcaActivity;
import com.example.projetofinal.R;

public class CategoriaDialogFragment extends DialogFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.dialog_categorias, container, false);

        LinearLayout llFrutas = root.findViewById(R.id.llFrutas);
        llFrutas.setOnClickListener(v -> iniciarJogoComCategoria("FRUTAS"));

        LinearLayout llAnimais = root.findViewById(R.id.llAnimais);
        llAnimais.setOnClickListener(v -> iniciarJogoComCategoria("ANIMAIS"));

        LinearLayout llEntretenimento = root.findViewById(R.id.llEntretenimento);
        llEntretenimento.setOnClickListener(v -> iniciarJogoComCategoria("ENTRETENIMENTO"));

        LinearLayout llSignos = root.findViewById(R.id.llSignos);
        llSignos.setOnClickListener(v -> iniciarJogoComCategoria("SIGNO"));

        LinearLayout llObjetos = root.findViewById(R.id.llObjetos);
        llObjetos.setOnClickListener(v -> iniciarJogoComCategoria("OBJETOS"));


        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
    }

    private void iniciarJogoComCategoria(String categoria) {
        Intent intent = new Intent(getActivity(), ForcaActivity.class);
        intent.putExtra("categoria", categoria);
        startActivity(intent);
        dismiss();
    }
}

