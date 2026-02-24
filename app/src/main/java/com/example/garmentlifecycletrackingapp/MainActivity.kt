package com.example.garmentlifecycletrackingapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.material3.Button
import androidx.compose.foundation.clickable
import androidx.compose.ui.Alignment

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UsageSummaryApp()
        }
    }
}

enum class Screen { Welcome, Summary }

@Composable
fun UsageSummaryApp() {
    val currentScreen = remember { mutableStateOf(Screen.Welcome) }

    MaterialTheme {
        when (currentScreen.value) {
            Screen.Welcome -> WelcomeScreen(
                onStartClick = { currentScreen.value = Screen.Summary }
            )
            Screen.Summary -> UsageSummaryScreen(
                onBackClick = { currentScreen.value = Screen.Welcome }
            )
        }
    }
}

@Composable
fun WelcomeScreen(onStartClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant
            )
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    "Welcome to Garment Tracker",
                    style = MaterialTheme.typography.headlineSmall
                )
                Text(
                    "Check wears & washes for rental garments.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Button(onClick = onStartClick) {
                    Text("See Usage Summary")
                }
            }
        }
    }
}



@Composable
fun UsageSummaryScreen(onBackClick: () -> Unit) {
    val repository = FakeGarmentRepository()   // uses the interface type

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .padding(top = 32.dp)
    ) {
        Text(
            "< Back",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .padding(bottom = 8.dp)
                .clickable { onBackClick() }
        )

        Text(
            "Garment Usage Summary",
            style = MaterialTheme.typography.headlineSmall
        )
        Text(
            "Rental wears & washes",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(Modifier.height(16.dp))

        UsageSummaryList(repository)
    }
}


@Composable
fun UsageSummaryList(repository: GarmentRepository) {
    val garments = repository.getGarments()

    LazyColumn {
        items(garments) { garment ->
            GarmentRow(garment)
        }
    }
}



@Composable
fun GarmentRow(garment: Garment) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(garment.name, style = MaterialTheme.typography.bodyLarge)
                Text(
                    garment.state,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            Column(horizontalAlignment = Alignment.End) {
                Text("Wears: ${garment.wears}", style = MaterialTheme.typography.bodyMedium)
                Text("Washes: ${garment.washes}", style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}




