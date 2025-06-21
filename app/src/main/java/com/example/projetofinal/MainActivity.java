package com.example.projetofinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends MenuBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void loginIniciar(View v){
        startActivity(new Intent(this, CategoriaActivity.class));
    }
}