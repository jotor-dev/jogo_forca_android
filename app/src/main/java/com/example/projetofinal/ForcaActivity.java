package com.example.projetofinal;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ForcaActivity extends MenuForcaActivity {

    private LinearLayout layoutLetras;
    private String palavra = "MORANGO";
    private TextView textDica;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_forca);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        layoutLetras = findViewById(R.id.layoutLetras);
        textDica = findViewById(R.id.textDica);

        textDica.setText("Dica: Fruta vermelha");

        criarEspacosDaPalavra(palavra);
        configurarTeclado(palavra);
    }

    private void criarEspacosDaPalavra(String palavra) {
        layoutLetras.removeAllViews();

        for (int i = 0; i < palavra.length(); i++) {
            TextView letraView = new TextView(this);
            letraView.setText("_");
            letraView.setTextSize(24f);
            letraView.setTextColor(Color.WHITE);
            letraView.setPadding(8, 8, 8, 8);

            layoutLetras.addView(letraView);
        }
    }

    private void configurarTeclado(String palavra) {
        for (char letra = 'A'; letra <= 'Z'; letra++) {
            int resId = getResources().getIdentifier("btLetra" + letra, "id", getPackageName());
            Button botao = findViewById(resId);

            if (botao != null) {
                botao.setOnClickListener(v -> {
                    botao.setEnabled(false);
                    botao.setBackgroundColor(Color.WHITE);
                    String letraEscolhida = botao.getText().toString();

                    for (int i = 0; i < palavra.length(); i++) {
                        if (String.valueOf(palavra.charAt(i)).equalsIgnoreCase(letraEscolhida)) {
                            TextView letraView = (TextView) layoutLetras.getChildAt(i);
                            letraView.setText(letraEscolhida.toUpperCase());
                        }
                    }
                });
            }
        }
    }

    public void voltar(View v) {
        finish();
    }
}