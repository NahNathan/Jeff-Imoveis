package com.example.prova2pdm

import android.content.ContentValues
import android.util.Log

class DAO_Imovel(banco : BancoSQLite)
{
    var banco : BancoSQLite
    init {
        this.banco = banco
    }

    fun inserirImovel(imovel: Imovel){
        val db_insercao = this.banco.writableDatabase
        var valores = ContentValues().apply{
            put("matricula", imovel.matricula)
            put("endereco", imovel.endereco)
            put("valor_aluguel", imovel.valor_aluguel)
            put("id_proprietario", imovel.proprietario.cpf)
            put("id_inquilino", imovel.inquilino.cpf)
        }
        val confirmaInsercao = db_insercao?.insert("Imovel",  null, valores)

        Log.i("Teste","Inserção: "+ confirmaInsercao)
    }

    fun mostrarImovel(): List<Imovel>{
        val listaImovel = ArrayList<Imovel>()
        val dao = DAO_Proprietario(banco)
        val dao2 = DAO_Inquilino(banco)
        val db_leitura = this.banco.readableDatabase
        var cursor = db_leitura.rawQuery("select * from Imovel",null)
        with(cursor) {
            while (moveToNext()) {
                val matricula = getString(getColumnIndexOrThrow("matricula"))
                val endereco = getString(getColumnIndexOrThrow("endereco"))
                val valor_aluguel = getFloat(getColumnIndexOrThrow("valor_aluguel"))
                val id_proprietario = getString(getColumnIndexOrThrow("id_proprietario"))
                val id_inquilino = getString(getColumnIndexOrThrow("id_inquilino"))
                val proprietario : Proprietario = dao.buscarPorCPF(id_proprietario)
                val inquilino : Inquilino = dao2.buscarPorCPF(id_inquilino)

                listaImovel.add(Imovel(matricula, endereco, valor_aluguel, proprietario, inquilino))
            }
        }
        cursor.close()

        Log.i("Teste", "Número de imóveis recuperados: ${listaImovel.size}")
        return(listaImovel)
    }

    fun buscarImovelPorMatricula(matricula: String): Imovel? {

        val db_leitura = this.banco.readableDatabase
        val cursor = db_leitura.rawQuery("SELECT * FROM Imovel WHERE matricula = ?", arrayOf(matricula))
        var imovel: Imovel? = null

        if (cursor.moveToFirst()) {
            val endereco = cursor.getString(cursor.getColumnIndexOrThrow("endereco"))
            val valorAluguel = cursor.getFloat(cursor.getColumnIndexOrThrow("valor_aluguel"))
            val idProprietario = cursor.getString(cursor.getColumnIndexOrThrow("id_proprietario"))
            val idInquilino = cursor.getString(cursor.getColumnIndexOrThrow("id_inquilino"))

            val daoProprietario = DAO_Proprietario(banco)
            val daoInquilino = DAO_Inquilino(banco)
            val proprietario = daoProprietario.buscarPorCPF(idProprietario)
            val inquilino = daoInquilino.buscarPorCPF(idInquilino)

            imovel = Imovel(matricula, endereco, valorAluguel, proprietario, inquilino)
        }
        cursor.close()
        return imovel
    }


    fun atualizarImovel(imovel: Imovel){
        val db_atualizacao = this.banco.writableDatabase
        var valores = ContentValues().apply {
            put("endereco", imovel.endereco)
            put("matricula", imovel.matricula)
            put("valor_aluguel", imovel.valor_aluguel)
            put("id_inquilino", imovel.inquilino.cpf)
        }
        val condicao = "matricula = ?"

        val confirmaAtualizacao = db_atualizacao.update("Imovel", valores, condicao, arrayOf(imovel.matricula))


        Log.i("Teste", "Atualização: $confirmaAtualizacao")
    }

    fun excluirImovel(imovel: Imovel){
        val db_exclusao = this.banco.readableDatabase
        val condicao = "matricula = " + imovel.matricula
        val confirmaExclusao = db_exclusao.delete("Imovel", condicao, null)

        Log.i("Teste","Após a exclusão: " + confirmaExclusao)
    }

}