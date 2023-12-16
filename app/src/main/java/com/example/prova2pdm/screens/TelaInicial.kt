import android.provider.CalendarContract.Colors
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.prova2pdm.R
import com.example.prova2pdm.Screen


@Composable
fun TelaInicial(navController: NavController) {



    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFF010C1D)
    ) {
        Box(contentAlignment = Alignment.Center) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo), // Substitua 'logo' pelo nome correto do arquivo, se necessário
                    contentDescription = "Logo JeffImóveis",
                    modifier = Modifier.size(500.dp) // Ajuste o tamanho conforme necessário
                )

                Botao("Inserir", navController, Screen.Inserir.route)
                Botao("Lista de Imóveis", navController, Screen.Lista.route)
            }
        }
    }
}

@Composable
fun Botao(texto: String, navController: NavController, route: String) {
    ElevatedButton(
        onClick = { navController.navigate(route) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 40.dp)
            .height(50.dp),
        colors = ButtonDefaults.elevatedButtonColors(
            containerColor = Color(0xFF52C09F)
        )

    ) {
        Text(
            texto,
            fontSize = 18.sp,
            color = Color(0xFF010C1D)
        )
    }
}
