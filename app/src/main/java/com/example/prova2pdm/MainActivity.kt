package com.example.prova2pdm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.prova2pdm.ui.theme.Prova2PDMTheme

class MainActivity : ComponentActivity() {
    lateinit var navController: NavHostController
    lateinit var banco : BancoSQLite
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Prova2PDMTheme {
                banco = BancoSQLite(applicationContext)
                navController = rememberNavController()
                SetupNavGraph(navController = navController, banco)
            }
        }
    }
}



