package es.viu.moviles.actividad1.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.coroutines.delay
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import es.viu.moviles.actividad1.ui.theme.OrangeDark
import androidx.compose.foundation.Image
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import es.viu.moviles.actividad1.R

/*
Representa una Pantalla de Splash
Se mantiene durante 1,5 segundos antes de dar paso a la HomeScreen
 */
@Composable
fun AppSplashScreen(onFinish: () -> Unit) {
    val scope = rememberCoroutineScope()

    // Usamos SideEffect para iniciar la cuenta atrás una sola vez
    LaunchedEffect(true) {
        delay(1500L)
        onFinish()
    }

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = R.drawable.splash_logo),
                contentDescription = "Logo",
                modifier = Modifier.size(200.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            CircularProgressIndicator(
                color = OrangeDark,
                strokeWidth = 4.dp
            )
        }
    }
}