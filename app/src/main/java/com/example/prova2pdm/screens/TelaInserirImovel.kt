package com.example.prova2pdm.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.prova2pdm.BancoSQLite
import com.example.prova2pdm.DAO_Imovel
import com.example.prova2pdm.DAO_Proprietario
import com.example.prova2pdm.Imovel
import com.example.prova2pdm.Inquilino
import com.example.prova2pdm.Proprietario
import com.example.prova2pdm.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TelaInserirImovel(navController: NavController, banco : BancoSQLite){
    var matricula = remember { mutableStateOf("") }
    var endereco = remember { mutableStateOf("") }
    var valor = remember { mutableStateOf("") }
    var cpf = remember { mutableStateOf("") }
    var nome = remember { mutableStateOf("") }
    var email = remember { mutableStateOf("") }

    var daoImovel: DAO_Imovel = DAO_Imovel(banco)
    var daoProprietario: DAO_Proprietario = DAO_Proprietario(banco)


    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFF010C1D)
    ) {
        Box(contentAlignment = Alignment.Center) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
                ) {
                Text(text = "Dados do imóvel", color = Color.White)
                TextField(value = matricula.value , onValueChange = {matricula.value=it},
                    placeholder = {Text("Matrícula")},
                    keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.None, autoCorrect = true),
                    textStyle = androidx.compose.ui.text.TextStyle(
                        color = Color.Black,
                        fontSize = TextUnit.Unspecified
                    ), )
                TextField(value = endereco.value , onValueChange = {endereco.value=it},
                    placeholder = {Text("Endereço")},
                    keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.None, autoCorrect = true),
                    textStyle = androidx.compose.ui.text.TextStyle(
                        color = Color.Black,
                        fontSize = TextUnit.Unspecified
                    ), )
                TextField(value = valor.value , onValueChange = {valor.value=it},
                    placeholder = {Text("Valor")},
                    keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.None, autoCorrect = true),
                    textStyle = androidx.compose.ui.text.TextStyle(
                        color = Color.Black,
                        fontSize = TextUnit.Unspecified
                    ), )


                Text(text = "Dados do Proprietário", color = Color.White)
                TextField(value = cpf.value , onValueChange = {cpf.value=it},
                    placeholder = {Text("CPF")},
                    keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.None, autoCorrect = true),
                    textStyle = androidx.compose.ui.text.TextStyle(
                        color = Color.Black,
                        fontSize = TextUnit.Unspecified
                    ), )
                TextField(value = nome.value , onValueChange = {nome.value=it},
                    placeholder = {Text("Nome")},
                    keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.None, autoCorrect = true),
                    textStyle = androidx.compose.ui.text.TextStyle(
                        color = Color.Black,
                        fontSize = TextUnit.Unspecified
                    ), )
                TextField(value = email.value , onValueChange = {email.value=it},
                    placeholder = {Text("Email")},
                    keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.None, autoCorrect = true),
                    textStyle = androidx.compose.ui.text.TextStyle(
                        color = Color.Black,
                        fontSize = TextUnit.Unspecified
                    ), )
                
                Spacer(modifier = Modifier.size(75.dp))
                ElevatedButton(onClick = {
                    if (matricula.value != "" && endereco.value != "" && valor.value != "" && matricula.value != "" && endereco.value != "" && valor.value != "") {
                        var proprietario: Proprietario =
                            Proprietario(cpf.value, nome.value, email.value)

                        var inquilino:Inquilino = Inquilino("","",0.0)


                        var imovel: Imovel = Imovel(
                            matricula.value,
                            endereco.value,
                            valor.value.toFloat(),
                            proprietario,
                            inquilino
                        )

                        //Inserindo aqui
                        Log.i("Teste", imovel.toString() + "Aqui atualização")
                        daoProprietario.inserirProprietario(proprietario)
                        daoImovel.inserirImovel(imovel)


                    } else{
                        Log.i("Teste", "preencha tudo")
                    }
                    navController.navigate(route = Screen.Inicial.route)
                },modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 40.dp)
                    .height(50.dp),
                    colors = ButtonDefaults.elevatedButtonColors(
                        containerColor = Color(0xFF52C09F)
                    )
                    ) {
                    Text("Inserir",
                        fontSize = 18.sp,
                        color=Color(0xFF010C1D))
                }

                Spacer(modifier = Modifier.height(20.dp))
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