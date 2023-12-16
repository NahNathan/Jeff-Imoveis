package com.example.prova2pdm.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.prova2pdm.BancoSQLite
import com.example.prova2pdm.DAO_Imovel
import com.example.prova2pdm.DAO_Inquilino
import com.example.prova2pdm.Inquilino
import com.example.prova2pdm.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TelaAlugar(navController: NavController, banco : BancoSQLite, matricula: String){
    var camp1 = remember { mutableStateOf("") }
    var camp2 = remember { mutableStateOf("") }
    var camp3 = remember { mutableStateOf("") }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFF010C1D)
    ) {
        Box(contentAlignment = Alignment.Center) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                Text(text = "Dados do Inquilíno", color = Color.White)
                TextField(
                    value = camp1.value, onValueChange = { camp1.value = it },
                    placeholder = { Text("CPF") },
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.None,
                        autoCorrect = true
                    ),
                    textStyle = TextStyle(
                        color = Color.Black,
                        fontSize = TextUnit.Unspecified
                    ),
                )
                TextField(
                    value = camp2.value, onValueChange = { camp2.value = it },
                    placeholder = { Text("Nome") },
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.None,
                        autoCorrect = true
                    ),
                    textStyle = TextStyle(
                        color = Color.Black,
                        fontSize = TextUnit.Unspecified
                    ),
                )
                TextField(
                    value = camp3.value, onValueChange = { camp3.value = it },
                    placeholder = { Text("Valor de Caução") },
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.None,
                        autoCorrect = true
                    ),
                    textStyle = TextStyle(
                        color = Color.Black,
                        fontSize = TextUnit.Unspecified
                    ),
                )
                ElevatedButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 40.dp)
                        .height(50.dp),
                    colors = ButtonDefaults.elevatedButtonColors(
                        containerColor = Color(0xFF52C09F)
                    ),
                    onClick = {
                    val novoInquilino = Inquilino(camp1.value, camp2.value, camp3.value.toDouble())
                    val daoInquilino = DAO_Inquilino(banco)
                    daoInquilino.inserirInquilino(novoInquilino)

                    val daoImovel = DAO_Imovel(banco)
                    val imovelAtualizado = daoImovel.buscarImovelPorMatricula(matricula)?.apply {
                        this.inquilino = novoInquilino
                    }

                    if (imovelAtualizado != null) {
                        daoImovel.atualizarImovel(imovelAtualizado)
                    }
                    navController.navigate(Screen.Lista.route)
                }) {
                    Text("Alugar",
                        fontSize = 18.sp,
                        color = Color(0xFF010C1D))

                }

                ElevatedButton(
                    onClick = { navController.navigate(route = Screen.Lista.route) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 40.dp)
                        .height(50.dp),
                    colors = ButtonDefaults.elevatedButtonColors(
                        containerColor = Color(0xFF52C09F)
                    )

                ) {
                    Text(
                        "Cancelar",
                        fontSize = 18.sp,
                        color = Color(0xFF010C1D)
                    )
                }


            }
        }
    }

}