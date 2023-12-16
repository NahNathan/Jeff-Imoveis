package com.example.prova2pdm

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class BancoSQLite(context: Context) : SQLiteOpenHelper(context, "prova2PDM", null, 9)
{
    override fun onCreate(db: SQLiteDatabase)
    {
        val tabelaProprietario = "Proprietario"
        val cpf_proprietario = "cpf"
        val nome_proprietario = "nome"
        val email_proprietario = "email"

        val tabelaInquilino = "Inquilino"
        val cpf_inquilino = "cpf"
        val nome_inquilino = "nome"
        val valor_inquilino = "valor"

        val tabelaImovel = "Imovel"
        val matricula = "matricula"
        val endereco = "endereco"
        val valor_aluguel = "valor_aluguel"
        val id_proprietario = "id_proprietario"
        val id_inquilino = "id_inquilino"

        val SQL_criacao_proprietario =
            "CREATE TABLE ${tabelaProprietario} (" +
                    "${cpf_proprietario} TEXT NOT NULL PRIMARY KEY," +
                    "${nome_proprietario} TEXT NOT NULL," +
                    "${email_proprietario} TEXT NOT NULL);"

        val SQL_criacao_inquilino =
            "CREATE TABLE ${tabelaInquilino} (" +
                    "${cpf_inquilino} TEXT NOT NULL PRIMARY KEY," +
                    "${nome_inquilino} TEXT NOT NULL," +
                    "${valor_inquilino} TEXT NOT NULL);"

        val SQL_criacao_imovel =
            "CREATE TABLE Imovel (" +
                    "matricula INTEGER NOT NULL PRIMARY KEY," +
                    "endereco TEXT NOT NULL," +
                    "valor_aluguel REAL NOT NULL," +
                    "id_proprietario TEXT NOT NULL," +
                    "id_inquilino TEXT NOT NULL," +
                    "FOREIGN KEY (id_proprietario) REFERENCES Proprietario(cpf_proprietario)," +
                    "FOREIGN KEY (id_inquilino) REFERENCES Inquilino(cpf_inquilino)" +
                    ");"

        db.execSQL(SQL_criacao_proprietario)
        db.execSQL(SQL_criacao_inquilino)
        db.execSQL(SQL_criacao_imovel)
        Log.i("teste","Criou o novo")
    }
    override fun onUpgrade(db: SQLiteDatabase, versaoAntiga: Int, novaVersao: Int) {
        val SQL_exclusao_proprietario = "DROP TABLE IF EXISTS Proprietario"
        val SQL_exclusao_inquilino = "DROP TABLE IF EXISTS Inquilino"
        val SQL_exclusao_imovel = "DROP TABLE IF EXISTS Imovel"
        db.execSQL(SQL_exclusao_proprietario)
        db.execSQL(SQL_exclusao_inquilino)
        db.execSQL(SQL_exclusao_imovel)
        onCreate(db)
    }
}