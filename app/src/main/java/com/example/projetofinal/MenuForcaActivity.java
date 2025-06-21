package com.example.projetofinal;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

public class MenuForcaActivity extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.miReiniciar) {
            startActivity(new Intent(this, ForcaActivity.class));
            return true;
        } else if (id == R.id.miNovaPartida) {
            startActivity(new Intent(this, CategoriaActivity.class));
            return true;
        } else if (id == R.id.miSairJogo) {
            startActivity(new Intent(this, MainActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

