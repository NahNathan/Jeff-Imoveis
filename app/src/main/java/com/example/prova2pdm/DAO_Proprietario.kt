package com.example.prova2pdm

import android.content.ContentValues
import android.util.Log

class DAO_Proprietario(banco : BancoSQLite)
{
    var banco : BancoSQLite
    init {
        this.banco = banco
    }

    fun inserirProprietario(proprietario: Proprietario){
        val db_insercao = this.banco.writableDatabase
        var valores = ContentValues().apply{
            put("cpf", proprietario.cpf)
            put("nome", proprietario.nome)
            put("email", proprietario.email)
        }
        val confirmaInsercao = db_insercao?.insert("Proprietario",  null, valores)

        Log.i("Teste","Inserção: "+ confirmaInsercao)
    }

    fun mostrarProprietario(): List<Proprietario>{
        val listaProprietario = ArrayList<Proprietario>()
        val db_leitura = this.banco.readableDatabase
        var cursor = db_leitura.rawQuery("select * from Proprietario where cpf=cpf",null)

        with(cursor) {
            while (moveToNext()) {
                val cpf = getString(getColumnIndexOrThrow("cpf"))
                val nome = getString(getColumnIndexOrThrow("nome"))
                val email = getString(getColumnIndexOrThrow("email"))

                Log.i("Teste","cpf: " + cpf + " nome: " + nome + " email: " + email)

                listaProprietario.add(Proprietario(cpf, nome, email))
            }
        }
        cursor.close()

        return(listaProprietario)
    }

    fun buscarPorCPF(cpf : String): Proprietario{
        var proprietario : Proprietario = Proprietario("","","")
        val db_leitura = this.banco.readableDatabase
        var cursor = db_leitura.rawQuery("select * from Proprietario where cpf=$cpf",null)
        with(cursor) {
            while (moveToNext()) {
                val cpf = getString(getColumnIndexOrThrow("cpf"))
                val nome = getString(getColumnIndexOrThrow("nome"))
                val email = getString(getColumnIndexOrThrow("email"))

                Log.i("Teste","cpf: " + cpf + " nome: " + nome + " email: " + email)

                proprietario=Proprietario(cpf, nome, email)
            }
        }
        cursor.close()

        return proprietario
    }

    fun atualizarProprietario(proprietario: Proprietario){
        val db_atualizacao = this.banco.writableDatabase
        var valores = ContentValues().apply {
            put("nome", proprietario.nome)
            put("email", proprietario.email)
        }
        val condicao = "cpf = " + proprietario.cpf
        val confirmaAtualizacao = db_atualizacao.update("Proprietario", valores, condicao, null)

        Log.i("Teste", "Atualização: $confirmaAtualizacao")
    }

    fun excluirProprietario(proprietario: Proprietario){
        val db_exclusao = this.banco.readableDatabase
        val condicao = "cpf = " + proprietario.cpf
        val confirmaExclusao = db_exclusao.delete("Proprietario", condicao, null)

        Log.i("Teste","Após a exclusão: " + confirmaExclusao)
    }
}