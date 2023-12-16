package com.example.prova2pdm.screens

import android.provider.CalendarContract.Colors
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.prova2pdm.BancoSQLite
import com.example.prova2pdm.DAO_Imovel
import com.example.prova2pdm.Imovel
import com.example.prova2pdm.Screen


@Composable
fun TelaLista(navController: NavController, banco : BancoSQLite) {


    val daoImovel  = DAO_Imovel(banco)
    val listaImoveis = remember { mutableStateOf(listOf<Imovel>()) }

    LaunchedEffect(Unit) {
        listaImoveis.value = daoImovel.mostrarImovel()
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFF010C1D)
    ) {
        LazyColumn(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(listaImoveis.value) { imovel ->
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFF52C09F),
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                        .clickable {
                            navController.navigate(Screen.Imovel.route + "/${imovel?.matricula}")
                        }

                ) {
                    Column(
                        modifier = Modifier.padding(20.dp)
                    ) {
                        Text(
                            text = imovel.toString(),
                            color=Color(0xFF010C1D)
                        )
                    }
                }
            }
        }
    }
}

