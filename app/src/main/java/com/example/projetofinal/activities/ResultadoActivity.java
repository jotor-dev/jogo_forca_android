package com.example.projetofinal.activities;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.projetofinal.R;
import com.example.projetofinal.db.ForcaDao;
import com.example.projetofinal.menus.MenuBaseActivity;

public class ResultadoActivity extends MenuBaseActivity {

    private int tempoRestante;
    TextView tvPontuacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_resultado);
        configurarToolbar();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        tvPontuacao = findViewById(R.id.tvPontuacao);

        tempoRestante = getIntent().getIntExtra("tempoRestante", 0);
        if (tempoRestante == 0) {
            Toast.makeText(this, "Tempo não recebido ou zerado.", Toast.LENGTH_SHORT).show();
        }

        int tempoTotalGasto = 60 - tempoRestante;
        tvPontuacao.setText(tempoRestante + " s");


        salvarPartida(tempoTotalGasto);
    }

    private void salvarPartida(int tempoTotalGasto) {
        ForcaDao dao = new ForcaDao(this);
        String username = getSharedPreferences("userPrefs", MODE_PRIVATE).getString("username", null);
        if (username != null) {
            int userId = dao.buscarIdUsuarioPorUsername(username);
            if (userId != -1) {
                dao.registrarPartida(userId, tempoTotalGasto);
                Toast.makeText(this, "Partida salva!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Usuário não encontrado.", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Usuário não logado.", Toast.LENGTH_SHORT).show();
        }
    }
}