package com.example.projetofinal.menus;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projetofinal.activities.CategoriaActivity;
import com.example.projetofinal.activities.ForcaActivity;
import com.example.projetofinal.R;
import com.example.projetofinal.activities.MenuActivity;
import com.example.projetofinal.fragments.CategoriaDialogFragment;
import com.google.android.material.appbar.MaterialToolbar;

public class MenuForcaActivity extends AppCompatActivity {

    protected void configurarToolbar() {
        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_jogo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.miReiniciar) {
            startActivity(new Intent(this, ForcaActivity.class));
            finish();
            return true;
        } else if (id == R.id.miNovaPartida) {
            new CategoriaDialogFragment().show(getSupportFragmentManager(), "Categoria Modal");
            return true;
        } else if (id == R.id.miSairJogo) {
            startActivity(new Intent(this, MenuActivity.class));
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

