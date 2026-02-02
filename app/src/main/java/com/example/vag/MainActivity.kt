package com.example.vag

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vag.ui.theme.VAGTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            VAGTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    var result by remember { mutableStateOf<Double?>(null) }

    val x = 5.0
    val a = 2.0
    val b = 1.0
    val c = 4.0

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Image(
            painter = painterResource(id = R.drawable.formula),
            contentDescription = "Формула",
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = {
            result = calculateY(x, a, b, c)
        }) {
            Text(text = "Вычислить", fontSize = 18.sp)
        }

        result?.let {
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Результат: y = $it",
                fontSize = 20.sp
            )
        }
    }
}

fun calculateY(x: Double, a: Double, b: Double, c: Double): Double {
    return if (x < 4) {
        ((x * x + a * a) * c) / (2 * b)
    } else {
        x * x * x * (a - b)
    }
}
