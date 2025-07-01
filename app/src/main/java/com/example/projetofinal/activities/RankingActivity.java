package com.example.projetofinal.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.projetofinal.R;
import com.example.projetofinal.db.ForcaDao;
import com.example.projetofinal.menus.MenuBaseActivity;

import java.util.List;

public class RankingActivity extends MenuBaseActivity {

    private LinearLayout rankingContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ranking);
        configurarToolbar();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        rankingContainer = findViewById(R.id.llRankingContainer);

        exibirRanking();
    }

    private void exibirRanking() {
        ForcaDao dao = new ForcaDao(this);
        List<String> ranking = dao.listarRankingComTempoEData();

        for (int i = 0; i < ranking.size(); i++) {
            String texto = (i + 1) + ". " + ranking.get(i);

            TextView textView = new TextView(this);
            textView.setText(texto);
            textView.setTextSize(18f);
            textView.setTextColor(Color.WHITE);
            textView.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            ));
            textView.setPadding(0, 8, 0, 8);

            rankingContainer.addView(textView);
        }
    }
}