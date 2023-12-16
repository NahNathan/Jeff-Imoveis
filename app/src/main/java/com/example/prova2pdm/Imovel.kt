package com.example.prova2pdm

class Imovel (matricula : String, endereco : String, valor_aluguel : Float, proprietario : Proprietario, inquilino: Inquilino)
{
    var matricula : String
    var endereco : String
    var valor_aluguel : Float
    var proprietario : Proprietario
    var inquilino : Inquilino

    init {
        this.matricula = matricula
        this.endereco = endereco
        this.valor_aluguel = valor_aluguel
        this.proprietario = proprietario
        this.inquilino = inquilino
    }

    override fun toString(): String {
        return buildString {
            append("Matrícula - ${this@Imovel.matricula}\n")
            append("Endereço: ${this@Imovel.endereco}\n")
            append("Valor do Aluguel: ${this@Imovel.valor_aluguel}0\n\n")
            append("Proprietário:\n")
            append("CPF: ${this@Imovel.proprietario.cpf}\n")
            append("Nome: ${this@Imovel.proprietario.nome}\n")
            append("Email: ${this@Imovel.proprietario.email}\n\n")
            append("Inquilino:\n")
            append("CPF: ${this@Imovel.inquilino.cpf}\n")
            append("Nome: ${this@Imovel.inquilino.nome}\n")
            append("Valor: ${this@Imovel.inquilino.valor}0\n")
        }
    }

}