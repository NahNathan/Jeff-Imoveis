package com.example.prova2pdm

class Inquilino (cpf : String, nome : String, valor : Double)
{
    var cpf : String
    var nome : String
    var valor : Double

    init {
        this.cpf = cpf
        this.nome = nome
        this.valor = valor
    }

    override fun toString() : String {
        return "CPF: " + this.cpf + " Nome: " + this.nome + " Valor Caução: " + this.valor.toString()
    }
}