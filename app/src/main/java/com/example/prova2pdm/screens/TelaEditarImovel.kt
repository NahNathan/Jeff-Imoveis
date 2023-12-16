package com.example.prova2pdm.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.prova2pdm.BancoSQLite
import com.example.prova2pdm.DAO_Imovel
import com.example.prova2pdm.Imovel
import com.example.prova2pdm.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TelaEditarImovel(navController: NavController, matricula: String, banco: BancoSQLite) {

    val daoImovel = DAO_Imovel(banco)
    val imovel: Imovel? = DAO_Imovel(banco).buscarImovelPorMatricula(matricula)


    var valorAluguel by remember { mutableStateOf(imovel?.valor_aluguel.toString()) }
    var endereco by remember { mutableStateOf(imovel?.endereco ?: "") }


    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFF010C1D)
    ) {
        Box(contentAlignment = Alignment.Center) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {

                OutlinedTextField(
                    value = endereco,
                    onValueChange = { endereco = it },
                    label = { Text("Endereço") },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        textColor = Color.White,
                        focusedLabelColor = Color.White,
                        unfocusedLabelColor = Color.White
                    )
                )


                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = valorAluguel,
                    onValueChange = { valorAluguel = it },
                    label = { Text("Valor do Aluguel") },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        textColor = Color.White,
                        focusedLabelColor = Color.White,
                        unfocusedLabelColor = Color.White
                    )
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

                    val imovelAtualizado = Imovel(
                        matricula,
                        endereco,
                        valorAluguel.toFloat(),
                        imovel!!.proprietario,
                        imovel.inquilino
                    )
                    Log.i("Teste", "Atualizaçãão"+imovelAtualizado.toString())
                    DAO_Imovel(banco).atualizarImovel(imovelAtualizado)
                    navController.navigate(Screen.Lista.route)
                }) {
                    Text("Salvar",
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