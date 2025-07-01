package com.example.projetofinal.fragments;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.projetofinal.R;
import com.example.projetofinal.db.ForcaDao;

public class AdicionarPalavraDialogFragment extends DialogFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.dialog_adicionar_palavra, container, false);


        EditText etPalavra = root.findViewById(R.id.etCampoPalavra);
        Spinner spCategoria = root.findViewById(R.id.spCategoria);
        EditText etCampoDica = root.findViewById(R.id.etCampoDica);
        Button btEnviar = root.findViewById(R.id.btEnviar);

        btEnviar.setOnClickListener(v -> {
            String palavra = etPalavra.getText().toString().trim().toUpperCase();
            String categoria = spCategoria.getSelectedItem().toString();
            String dica = etCampoDica.getText().toString().trim();

            if (palavra.isEmpty() || categoria.isEmpty() || dica.isEmpty()) {
                Toast.makeText(getContext(), "Preencha todos os campos.", Toast.LENGTH_SHORT).show();
                return;
            }

            ForcaDao dao = new ForcaDao(getContext());
            long id = dao.inserirPalavra(palavra, categoria, dica);

            if (id != -1) {
                Toast.makeText(getContext(), "Palavra adicionada!", Toast.LENGTH_SHORT).show();
                dismiss();
            } else {
                Toast.makeText(getContext(), "Erro ao adicionar palavra.", Toast.LENGTH_SHORT).show();
            }
        });

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

}

