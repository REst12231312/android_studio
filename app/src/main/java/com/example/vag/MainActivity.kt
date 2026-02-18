package com.example.vag

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                RadioBookApp()
            }
        }
    }
}

data class RadioStation(
    val name: String,
    val frequency: String,
    val description: String
)

val radioStations = listOf(
    RadioStation("Europa Plus", "106.2 FM", "Популярная зарубежная музыка"),
    RadioStation("Retro FM", "104.0 FM", "Хиты 80-х, 90-х и 2000-х"),
    RadioStation("Energy", "101.7 FM", "Современные танцевальные хиты"),
    RadioStation("Rock FM", "95.2 FM", "Рок музыка"),
    RadioStation("Jazz FM", "102.1 FM", "Джаз и блюз"),
    RadioStation("Love Radio", "103.7 FM", "Поп-музыка и романтические хиты"),
    RadioStation("Hit FM", "107.0 FM", "Современные популярные хиты"),
    RadioStation("Русское Радио", "106.6 FM", "Русская популярная музыка"),
    RadioStation("Авторадио", "105.9 FM", "Музыка и новости для водителей"),
    RadioStation("DFM", "104.6 FM", "Танцевальная электронная музыка"),
    RadioStation("Radio Monte Carlo", "102.8 FM", "Лёгкая музыка и лаунж"),
    RadioStation("Maximum", "103.7 FM", "Зарубежный рок и альтернативная музыка"),
    RadioStation("Comedy Radio", "102.5 FM", "Юмор и популярная музыка"),
    RadioStation("Relax FM", "90.8 FM", "Спокойная релакс-музыка"),
    RadioStation("Classic FM", "99.5 FM", "Классическая музыка"),
    RadioStation("Nashe Radio", "101.8 FM", "Русский рок"),
    RadioStation("Radio Record", "106.3 FM", "Клубная и танцевальная музыка"),
    RadioStation("Chillout Radio", "98.4 FM", "Chillout и ambient"),
    RadioStation("Deep FM", "100.1 FM", "Deep house музыка"),
    RadioStation("Lounge FM", "97.2 FM", "Лаунж и спокойная музыка")
)

@Composable
fun RadioBookApp() {
    var searchText by rememberSaveable { mutableStateOf("") }

    val filteredStations = radioStations.filter {
        it.name.contains(searchText, ignoreCase = true) ||
                it.description.contains(searchText, ignoreCase = true)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(60.dp))

        Text(
            text = "Электронная книга-справочник\n«Радиостанции»",
            fontSize = 20.sp,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = searchText,
            onValueChange = { searchText = it },
            label = { Text("Поиск радиостанции") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(filteredStations) { station ->
                RadioItem(station)
            }
        }
    }
}

@Composable
fun RadioItem(station: RadioStation) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = station.name, fontSize = 18.sp, textAlign = TextAlign.Center)
            Text(text = "Частота: ${station.frequency}", textAlign = TextAlign.Center)
            Text(text = station.description, textAlign = TextAlign.Center)
        }
    }
}
