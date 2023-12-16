package com.example.prova2pdm.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.prova2pdm.BancoSQLite
import com.example.prova2pdm.DAO_Imovel
import com.example.prova2pdm.Imovel
import com.example.prova2pdm.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TelaImovel(navController: NavController, banco: BancoSQLite, matricula: String) {

    val imovel: Imovel? = DAO_Imovel(banco).buscarImovelPorMatricula(matricula)


    var endereco = imovel?.endereco
    var valor = imovel?.valor_aluguel



    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFF010C1D)
    ) {
        Box(contentAlignment = Alignment.Center) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {

                Log.i("teste", "Aqui está:"+imovel?.matricula)
                Text(text = "Matrícula: $matricula", color = Color.White,fontSize = 18.sp,)
                Text(text = "Endereço: $endereco", color = Color.White,fontSize = 18.sp,)
                Text(text = "Valor: R$ ${valor.toString()}0", color = Color.White,fontSize = 18.sp,)



                ElevatedButton(onClick = {
                    //fazer a lógica pra chamar o banco
                    navController.navigate(Screen.Alugar.route + "/${imovel?.matricula}")
                },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 40.dp)
                        .height(50.dp),
                    colors = ButtonDefaults.elevatedButtonColors(
                        containerColor = Color(0xFF52C09F)
                    )) {
                    Text("Alugar",
                        fontSize = 18.sp,
                        color = Color(0xFF010C1D)
                    )
                }
                ElevatedButton(onClick = {
                    //fazer a lógica pra chamar o banco
                    navController.navigate(Screen.Editar.route + "/${imovel?.matricula}")
                },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 40.dp)
                        .height(50.dp),
                    colors = ButtonDefaults.elevatedButtonColors(
                        containerColor = Color(0xFF52C09F)
                    )) {
                    Text("Atualizar", fontSize = 18.sp,
                        color = Color(0xFF010C1D))
                }
                ElevatedButton(
                    onClick = {
                        // Chama a função para excluir o imóvel
                        if (imovel != null) {
                            DAO_Imovel(banco).excluirImovel(imovel)
                            // Navega de volta para a tela inicial após a exclusão
                            navController.navigate(route = Screen.Inicial.route)
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 40.dp)
                        .height(50.dp),
                    colors = ButtonDefaults.elevatedButtonColors(
                        containerColor = Color(0xFF52C09F)
                    )
                ) {
                    Text("Deletar", fontSize = 18.sp, color = Color(0xFF010C1D))
                }
            }
        }
    }

}