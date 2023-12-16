package com.example.prova2pdm

import TelaInicial
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.prova2pdm.screens.TelaAlugar
import com.example.prova2pdm.screens.TelaEditarImovel
import com.example.prova2pdm.screens.TelaImovel
import com.example.prova2pdm.screens.TelaInserirImovel
import com.example.prova2pdm.screens.TelaLista

    @Composable
    fun SetupNavGraph(
        navController: NavHostController,
        banco : BancoSQLite
    ) {
        NavHost(
            navController = navController,
            startDestination = Screen.Inicial.route
        ) {
            composable(
                route = Screen.Inicial.route
            ) {
                TelaInicial(navController = navController)
            }
            composable(
                route = Screen.Imovel.route + "/{matricula}",
                arguments = listOf(navArgument("matricula") { type = NavType.StringType })
            ) {
                val matricula = it.arguments?.getString("matricula") ?: ""
                TelaImovel(navController = navController, banco, matricula = matricula)
            }

            composable(
                route = Screen.Alugar.route + "/{matricula}",
                arguments = listOf(navArgument("matricula") { type = NavType.StringType })
            ) {
                val matricula = it.arguments?.getString("matricula") ?: ""
                TelaAlugar(navController, banco, matricula)
            }

            composable(
                route = Screen.Inserir.route
            ) {
                TelaInserirImovel(navController = navController, banco)
            }
            composable(
                route = Screen.Lista.route
            ) {
                TelaLista(navController = navController, banco)
            }
            composable(
                route = Screen.Editar.route + "/{matricula}",
                arguments = listOf(navArgument("matricula") { type = NavType.StringType })
            ) {
                val matricula = it.arguments?.getString("matricula") ?: ""
                TelaEditarImovel(navController = navController, matricula,  banco)
            }
        }
    }

