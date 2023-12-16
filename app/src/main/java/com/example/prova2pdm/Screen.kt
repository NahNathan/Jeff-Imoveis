package com.example.prova2pdm

sealed class Screen (val route:String){
    object Inicial: Screen("tela_inicial")
    object Imovel: Screen("tela_imovel")
    object Lista: Screen("tela_lista")
    object Alugar: Screen("tela_alugar")
    object Inserir: Screen("tela_inserir")
    object Editar: Screen("tela_editar")
}