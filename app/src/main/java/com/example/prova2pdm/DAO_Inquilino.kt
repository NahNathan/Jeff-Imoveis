package com.example.prova2pdm

import android.content.ContentValues
import android.util.Log

class DAO_Inquilino(banco : BancoSQLite)
{
    var banco : BancoSQLite
    init {
        this.banco = banco
    }

    fun inserirInquilino(inquilino: Inquilino){
        val db_insercao = this.banco.writableDatabase
        var valores = ContentValues().apply{
            put("cpf", inquilino.cpf)
            put("nome", inquilino.nome)
            put("valor", inquilino.valor)
        }
        val confirmaInsercao = db_insercao?.insert("Inquilino",  null, valores)

        Log.i("Teste","Inserção: "+ confirmaInsercao)
    }

    fun mostrarInquilino(): List<Inquilino>{
        val listaInquilino = ArrayList<Inquilino>()
        val db_leitura = this.banco.readableDatabase
        var cursor = db_leitura.rawQuery("select * from Inquilino",null)
        with(cursor) {
            while (moveToNext()) {
                val cpf = getString(getColumnIndexOrThrow("cpf"))
                val nome = getString(getColumnIndexOrThrow("nome"))
                val valor = getDouble(getColumnIndexOrThrow("valor"))

                Log.i("Teste","cpf: " + cpf + " nome: " + nome + " valor: " + valor)

                listaInquilino.add(Inquilino(cpf, nome, valor))
            }
        }
        cursor.close()
        return(listaInquilino)
    }
    fun buscarPorCPF(cpf : String): Inquilino{
        var inquilino : Inquilino = Inquilino("","",0.0)
        val db_leitura = this.banco.readableDatabase
        var cursor = db_leitura.rawQuery("select * from Inquilino where cpf='$cpf'",null)


        with(cursor) {
            while (moveToNext()) {
                val cpf = getString(getColumnIndexOrThrow("cpf"))
                val nome = getString(getColumnIndexOrThrow("nome"))
                val valor = getDouble(getColumnIndexOrThrow("valor"))
                inquilino=Inquilino(cpf, nome, valor)
            }
        }
        cursor.close()
        return inquilino
    }


    fun atualizarInquilino(inquilino: Inquilino){
        val db_atualizacao = this.banco.writableDatabase
        var valores = ContentValues().apply {
            put("nome", inquilino.nome)
            put("cpf", inquilino.cpf)
            put("email", inquilino.valor)
        }
        val condicao = "cpf = " + inquilino.cpf
        val confirmaAtualizacao = db_atualizacao.update("Inquilino", valores, condicao, null)

        Log.i("Teste", "Atualização: $confirmaAtualizacao")
    }

    fun excluirInquilino(inquilino: Inquilino){
        val db_exclusao = this.banco.readableDatabase
        val condicao = "cpf = " + inquilino.cpf
        val confirmaExclusao = db_exclusao.delete("Inquilino", condicao, null)

        Log.i("Teste","Após a exclusão: " + confirmaExclusao)
    }
}