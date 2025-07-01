package com.example.projetofinal.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class ForcaDao {
    private final SQLiteDatabase banco;

    public ForcaDao(Context ctx) {
        BancoHelper helper = new BancoHelper(ctx);
        banco = helper.getWritableDatabase();
    }

    // ---------- PALAVRA ----------
    public long inserirPalavra(String texto, String genero, String dica) {
        ContentValues valores = new ContentValues();
        valores.put("texto", texto);
        valores.put("genero", genero);
        valores.put("dica", dica);
        return banco.insert("Palavra", null, valores);
    }

    public List<String> listarTodasPalavras() {
        List<String> lista = new ArrayList<>();
        Cursor cursor = banco.rawQuery("SELECT texto FROM Palavra", null);
        while (cursor.moveToNext()) {
            lista.add(cursor.getString(0));
        }
        cursor.close();
        return lista;
    }

    public List<String[]> listarPalavrasComDica() {
        List<String[]> lista = new ArrayList<>();
        Cursor cursor = banco.rawQuery("SELECT texto, dica FROM Palavra", null);
        while (cursor.moveToNext()) {
            String texto = cursor.getString(0);
            String dica = cursor.getString(1);
            lista.add(new String[]{texto, dica});
        }
        cursor.close();
        return lista;
    }

    public List<String[]> listarPalavrasComDicaPorGenero(String genero) {
        List<String[]> lista = new ArrayList<>();

        if (genero.isEmpty()) {
            return listarPalavrasComDica();
        } else {
            Cursor cursor = banco.rawQuery(
                    "SELECT texto, dica FROM Palavra WHERE genero = ?", new String[]{genero}
            );
            while (cursor.moveToNext()) {
                String texto = cursor.getString(0);
                String dica = cursor.getString(1);
                lista.add(new String[]{texto, dica});
            }
            cursor.close();
            return lista;
        }

    }


    public String buscarDicaPorPalavra(String texto) {
        Cursor cursor = banco.rawQuery("SELECT dica FROM Palavra WHERE texto = ?", new String[]{texto});
        String dica = null;
        if (cursor.moveToFirst()) {
            dica = cursor.getString(0);
        }
        cursor.close();
        return dica;
    }

    // ---------- USUARIO ----------
    public long inserirUsuario(String username, String avatar) {
        ContentValues valores = new ContentValues();
        valores.put("username", username);
        valores.put("avatar", avatar);
        return banco.insert("Usuario", null, valores);
    }

    public int buscarIdUsuarioPorUsername(String username) {
        Cursor cursor = banco.rawQuery("SELECT id FROM Usuario WHERE username = ?", new String[]{username});
        int id = -1;
        if (cursor.moveToFirst()) {
            id = cursor.getInt(0);
        }
        cursor.close();
        return id;
    }

    public String buscarAvatarPorUsername(String username) {
        Cursor cursor = banco.rawQuery("SELECT avatar FROM Usuario WHERE username = ?", new String[]{username});
        String avatar = null;
        if (cursor.moveToFirst()) {
            avatar = cursor.getString(0);
        }
        cursor.close();
        return avatar;
    }

    // ---------- PARTIDA ----------
    public long registrarPartida(int usuarioId, int tempoSegundos) {
        ContentValues valores = new ContentValues();
        valores.put("usuario_id", usuarioId);
        valores.put("tempo_segundos", tempoSegundos);
        return banco.insert("Partida", null, valores);
    }

    public List<String> listarHistoricoPartidas(int usuarioId) {
        List<String> lista = new ArrayList<>();
        Cursor cursor = banco.rawQuery("SELECT tempo_segundos, data FROM Partida WHERE usuario_id = ? ORDER BY data DESC", new String[]{String.valueOf(usuarioId)});
        while (cursor.moveToNext()) {
            int tempo = cursor.getInt(0);
            String data = cursor.getString(1);
            lista.add("Tempo: " + tempo + "s | Data: " + data);
        }
        cursor.close();
        return lista;
    }

    public List<String> listarRankingComTempoEData() {
        List<String> lista = new ArrayList<>();
        Cursor cursor = banco.rawQuery(
                "SELECT u.username, p.tempo_segundos, p.data " +
                        "FROM Usuario u " +
                        "JOIN Partida p ON u.id = p.usuario_id " +
                        "ORDER BY p.data DESC", null);

        while (cursor.moveToNext()) {
            String username = cursor.getString(0);
            int tempo = cursor.getInt(1);
            String data = cursor.getString(2);
            String tempoFormatado = formatarTempo(tempo);
            lista.add(username + " - " + tempoFormatado + " - " + data);
        }

        cursor.close();
        return lista;
    }

    private String formatarTempo(int segundos) {
        int minutos = segundos / 60;
        int seg = segundos % 60;
        return String.format("%02d:%02d", minutos, seg);
    }

}
