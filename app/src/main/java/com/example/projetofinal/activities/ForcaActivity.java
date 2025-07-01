package com.example.projetofinal.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.projetofinal.R;
import com.example.projetofinal.db.ForcaDao;
import com.example.projetofinal.menus.MenuForcaActivity;
import com.google.android.flexbox.FlexboxLayout;

import java.util.List;
import java.util.Random;

public class ForcaActivity extends MenuForcaActivity {

    private FlexboxLayout layoutLetras;
    private TextView textDica;
    private TextView tvTempo;
    private ImageView forcaImg;

    private int tempoRestante = 60;
    private Thread threadTempo;
    private boolean tempoAtivo = true;
    private int erros = 0;
    private int contagemPalavraSucesso = 0;
    private String palavra;
    private String dica;
    private int tempoTotalGasto;
    private String categoriaSelecionada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_forca);
        configurarToolbar();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        layoutLetras = findViewById(R.id.layoutLetras);
        textDica = findViewById(R.id.textDica);
        forcaImg = findViewById(R.id.forcaImg);
        tvTempo = findViewById(R.id.tvTempo);

        categoriaSelecionada = getIntent().getStringExtra("categoria");

        if (categoriaSelecionada == null || categoriaSelecionada.trim().isEmpty()) {
            categoriaSelecionada = "";
        }

        carregarPalavraAleatoria();
        criarEspacosDaPalavra(palavra);
        configurarTeclado(palavra);
        iniciarContagem();
    }

    private void carregarPalavraAleatoria() {
        ForcaDao dao = new ForcaDao(this);
        List<String[]> lista = dao.listarPalavrasComDicaPorGenero(categoriaSelecionada);

        if (lista.isEmpty()) {
            palavra = "MORANGO";
            dica = "Fruta vermelha";
        } else {
            Random rand = new Random();
            String[] selecionada = lista.get(rand.nextInt(lista.size()));
            palavra = selecionada[0];
            dica = selecionada[1];
        }

        textDica.setText("Dica: " + dica);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        tempoAtivo = false;
    }

    private void salvarPartida() {
        ForcaDao dao = new ForcaDao(this);
        String username = getSharedPreferences("prefs", MODE_PRIVATE).getString("username", null);
        if (username != null) {
            int userId = dao.buscarIdUsuarioPorUsername(username);
            tempoTotalGasto = 60 - tempoRestante;
            dao.registrarPartida(userId, tempoTotalGasto);
        }
    }

    public void iniciarContagem(){
        threadTempo = new Thread(() -> {
           while(tempoAtivo && tempoRestante > 0){
               runOnUiThread(() -> tvTempo.setText("Tempo: " + tempoRestante + "s"));
               try{
                   Thread.sleep(1000);
               } catch (InterruptedException error){
                   error.printStackTrace();
               }
               tempoRestante--;
           }

           if(tempoRestante == 0){
               runOnUiThread(() -> {
                   Toast.makeText(this, "Tempo esgotado!", Toast.LENGTH_LONG).show();
                   salvarPartida();
                   finish();
                   startActivity(new Intent(this, ResultadoActivity.class));
               });
           }
        });

        threadTempo.start();
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

            if(botao == null){
                Log.e("TECLADO FORCA", "Botão não existe");
                Toast.makeText(this, "Comando indisponível. Tente novamente", Toast.LENGTH_SHORT).show();
                return;
            }

            botao.setOnClickListener(v -> {
                String letraEscolhida = botao.getText().toString();
                boolean acertou = false;

                for (int i = 0; i < palavra.length(); i++) {
                    if (String.valueOf(palavra.charAt(i)).equalsIgnoreCase(letraEscolhida)) {
                        TextView letraView = (TextView) layoutLetras.getChildAt(i);
                        letraView.setText(letraEscolhida.toUpperCase());
                        acertou = true;
                        contagemPalavraSucesso++;
                    }
                }

                if(!acertou){
                    erros++;

                    Toast.makeText(this, "Faltam " + (6 - erros) +" tentativas", Toast.LENGTH_SHORT).show();
                    if(erros >= 6){
                        Toast.makeText(this, "Errou demais, tente novamente", Toast.LENGTH_SHORT).show();
                        finish();
                    }

                    int imagemId = getResources().getIdentifier(
                            "forca_" + erros, "drawable", getPackageName()
                    );

                    forcaImg.setImageResource(imagemId);
                }

                if(contagemPalavraSucesso == palavra.length()){
                    salvarPartida();
                    Intent intent = new Intent(this, ResultadoActivity.class);
                    intent.putExtra("tempoRestante", tempoRestante);

                    finish();
                    startActivity(intent);

                }

                // Desabilitar botão
                botao.setEnabled(false);
                botao.setBackgroundColor(Color.WHITE);
            });
        }
    }

    public void voltar(View v) {
        finish();
    }
}