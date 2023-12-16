package com.example.prova2pdm

class Proprietario (cpf : String, nome : String, email : String)
{
    var cpf : String
    var nome : String
    var email : String

    init {
        this.cpf = cpf
        this.nome = nome
        this.email = email
    }

    override fun toString() : String {
        return "CPF: " + this.cpf + " Nome: " + this.nome + " E-mail: " + this.email
    }
}