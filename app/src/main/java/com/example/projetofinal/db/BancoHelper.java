package com.example.projetofinal.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BancoHelper extends SQLiteOpenHelper {

    private static final String NOME_BANCO = "forca.db";
    private static final int VERSAO = 1;

    public BancoHelper(Context context) {
        super(context, NOME_BANCO, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Usuario (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "username TEXT NOT NULL, " +
                "avatar TEXT)");

        db.execSQL("CREATE TABLE Palavra (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "texto TEXT NOT NULL, " +
                "genero TEXT NOT NULL," +
                "dica TEXT NOT NULL)");

        db.execSQL("CREATE TABLE Partida (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "usuario_id INTEGER, " +
                "tempo_segundos INTEGER, " +
                "data DATETIME DEFAULT CURRENT_TIMESTAMP, " +
                "FOREIGN KEY(usuario_id) REFERENCES Usuario(id)) ");


        // INSERTS
        db.execSQL("INSERT INTO Palavra (texto, genero, dica) VALUES ('MORANGO', 'FRUTAS', 'Fruta vermelha')");
        db.execSQL("INSERT INTO Palavra (texto, genero, dica) VALUES ('BANANA', 'FRUTAS', 'Fruta amarela')");
        db.execSQL("INSERT INTO Palavra (texto, genero, dica) VALUES ('UVA', 'FRUTAS', 'Fruta roxa ou verde')");
        db.execSQL("INSERT INTO Palavra (texto, genero, dica) VALUES ('MANGA', 'FRUTAS', 'Fruta tropical suculenta')");
        db.execSQL("INSERT INTO Palavra (texto, genero, dica) VALUES ('ABACAXI', 'FRUTAS', 'Fruta tropical com casca espinhosa')");

        db.execSQL("INSERT INTO Palavra (texto, genero, dica) VALUES ('CÃO', 'ANIMAIS', 'Animal doméstico leal')");
        db.execSQL("INSERT INTO Palavra (texto, genero, dica) VALUES ('GATO', 'ANIMAIS', 'Felino doméstico ágil')");
        db.execSQL("INSERT INTO Palavra (texto, genero, dica) VALUES ('TIGRE', 'ANIMAIS', 'Grande felino selvagem')");
        db.execSQL("INSERT INTO Palavra (texto, genero, dica) VALUES ('PATO', 'ANIMAIS', 'Ave que nada e grasna')");
        db.execSQL("INSERT INTO Palavra (texto, genero, dica) VALUES ('CAVALO', 'ANIMAIS', 'Animal usado para montaria')");

        db.execSQL("INSERT INTO Palavra (texto, genero, dica) VALUES ('CINEMA', 'ENTRETENIMENTO', 'Lugar onde se assistem filmes')");
        db.execSQL("INSERT INTO Palavra (texto, genero, dica) VALUES ('VIOLÃO', 'ENTRETENIMENTO', 'Instrumento de cordas')");
        db.execSQL("INSERT INTO Palavra (texto, genero, dica) VALUES ('JOGOS', 'ENTRETENIMENTO', 'Atividades lúdicas e recreativas')");
        db.execSQL("INSERT INTO Palavra (texto, genero, dica) VALUES ('TEATRO', 'ENTRETENIMENTO', 'Espetáculo com atores ao vivo')");
        db.execSQL("INSERT INTO Palavra (texto, genero, dica) VALUES ('DANCA', 'ENTRETENIMENTO', 'Expressão artística com movimentos')");

        db.execSQL("INSERT INTO Palavra (texto, genero, dica) VALUES ('AQUARIO', 'SIGNO', 'Signo do elemento ar')");
        db.execSQL("INSERT INTO Palavra (texto, genero, dica) VALUES ('LEAO', 'SIGNO', 'Signo do elemento fogo')");
        db.execSQL("INSERT INTO Palavra (texto, genero, dica) VALUES ('ESCORPIAO', 'SIGNO', 'Signo do elemento água')");
        db.execSQL("INSERT INTO Palavra (texto, genero, dica) VALUES ('GEMEOS', 'SIGNO', 'Signo dos gêmeos e da comunicação')");
        db.execSQL("INSERT INTO Palavra (texto, genero, dica) VALUES ('TOURO', 'SIGNO', 'Signo da terra e da persistência')");

        db.execSQL("INSERT INTO Palavra (texto, genero, dica) VALUES ('CADEIRA', 'OBJETOS', 'Objeto usado para sentar')");
        db.execSQL("INSERT INTO Palavra (texto, genero, dica) VALUES ('LIVRO', 'OBJETOS', 'Contém histórias e conhecimento')");
        db.execSQL("INSERT INTO Palavra (texto, genero, dica) VALUES ('MOCHILA', 'OBJETOS', 'Usada para carregar itens')");
        db.execSQL("INSERT INTO Palavra (texto, genero, dica) VALUES ('LAPIS', 'OBJETOS', 'Usado para escrever ou desenhar')");
        db.execSQL("INSERT INTO Palavra (texto, genero, dica) VALUES ('GARRAFA', 'OBJETOS', 'Contém líquidos para beber')");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Partida");
        db.execSQL("DROP TABLE IF EXISTS Palavra");
        db.execSQL("DROP TABLE IF EXISTS Usuario");
        onCreate(db);
    }
}
