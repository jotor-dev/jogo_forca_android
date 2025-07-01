package com.example.projetofinal.menus;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projetofinal.activities.CategoriaActivity;
import com.example.projetofinal.activities.MenuActivity;
import com.example.projetofinal.R;
import com.example.projetofinal.activities.RankingActivity;
import com.google.android.material.appbar.MaterialToolbar;

public class MenuBaseActivity extends AppCompatActivity {

    protected void configurarToolbar() {
        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_geral, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.miMenuActivity) {
            startActivity(new Intent(this, MenuActivity.class));
            return true;
        } else if (id == R.id.miRankingActivity) {
            startActivity(new Intent(this, RankingActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

