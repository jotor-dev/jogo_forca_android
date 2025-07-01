package com.example.projetofinal.activities;

import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.projetofinal.BatteryReceiver;
import com.example.projetofinal.R;
import com.example.projetofinal.db.ForcaDao;
import com.example.projetofinal.menus.MenuBaseActivity;

public class MainActivity extends MenuBaseActivity implements SensorEventListener {
    Sensor sensorAcelerometro;
    Sensor sensorTemperatura;
    SensorManager sensorManager;
    private EditText editTextUsername;
    private String avatarSelecionado = "avatar_default";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextUsername = findViewById(R.id.etUsername);

        registerReceiver(new BatteryReceiver(), new IntentFilter(Intent.ACTION_BATTERY_LOW));

        configurarSensores();
        configurarAvatares();
    }

    private void configurarAvatares() {
        int[] avatarIds = {
                R.id.avatar1,
                R.id.avatar2,
                R.id.avatar3,
                R.id.avatar4
        };

        for (int id : avatarIds) {
            ImageView avatarView = findViewById(id);
            avatarView.setOnClickListener(v -> {
                marcarAvatarSelecionado(id);

                avatarSelecionado = getResources().getResourceEntryName(id);
                Toast.makeText(this, "Avatar selecionado: " + avatarSelecionado, Toast.LENGTH_SHORT).show();
            });
        }
    }

    private void marcarAvatarSelecionado(int idSelecionado) {
        // Aqui podes implementar para destacar visualmente o avatar escolhido,
        // por exemplo, com borda ou mudança de cor.
        // Por ora, apenas exemplo simples:
        int[] avatarIds = { R.id.avatar1, R.id.avatar2, R.id.avatar3, R.id.avatar4 };
        for (int id : avatarIds) {
            ImageView iv = findViewById(id);
            iv.setAlpha(id == idSelecionado ? 1f : 0.5f);
        }
    }

    public void loginIniciar(View v){
        String username = editTextUsername.getText().toString().trim();

        if (username.isEmpty()) {
            Toast.makeText(this, "Digite seu nome de usuário!", Toast.LENGTH_SHORT).show();
            return;
        }

        ForcaDao dao = new ForcaDao(this);

        // Verifica se já existe usuário
        int usuarioId = dao.buscarIdUsuarioPorUsername(username);
        if (usuarioId == -1) {
            // Insere novo usuário
            long id = dao.inserirUsuario(username, avatarSelecionado);
            if (id == -1) {
                Toast.makeText(this, "Erro ao salvar usuário.", Toast.LENGTH_SHORT).show();
                return;
            }
            usuarioId = (int) id;
        }

        // Salva em SharedPreferences para usar durante o jogo
        SharedPreferences prefs = getSharedPreferences("userPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("username", username);
        editor.putString("avatar", avatarSelecionado);
        editor.apply();

        // Avança para a próxima tela
        startActivity(new Intent(this, MenuActivity.class));
        finish();
    }
    public void configurarSensores(){
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        sensorAcelerometro = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorTemperatura = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        if (sensorAcelerometro != null)
            sensorManager.registerListener(this, sensorAcelerometro, SensorManager.SENSOR_DELAY_NORMAL);

        if (sensorTemperatura != null)
            sensorManager.registerListener(this, sensorTemperatura, SensorManager.SENSOR_DELAY_NORMAL);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        sensorManager.unregisterListener(this, sensorAcelerometro);
        sensorManager.unregisterListener(this, sensorTemperatura);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            if (Math.abs(x) > 10 || Math.abs(y) > 10) {
                Toast.makeText(this, "Sacudiu o celular! Você quer pular a rodada?", Toast.LENGTH_SHORT).show();
            }
        }

        if (event.sensor.getType() == Sensor.TYPE_PROXIMITY) {
            float temperatura = event.values[0];
            Toast.makeText(this, "Temperatura ambiente: " + temperatura + "°C", Toast.LENGTH_SHORT).show();

            if (temperatura > 30.0) {
                Toast.makeText(this, "Está quente demais para jogar! 😓", Toast.LENGTH_LONG).show();
            } else if (temperatura < 15.0) {
                Toast.makeText(this, "Clima gélido... digno de uma forca fria! ❄️", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}