package com.example.myhellocompose

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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myhellocompose.ui.theme.MyHelloComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyHelloComposeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MultipleSwitches(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
private fun MultipleSwitches(modifier: Modifier = Modifier) {
    val isWifiSwitchOn = remember { mutableStateOf(false) }
    val isBluetoothSwitchOn = remember { mutableStateOf(false) }
    val isAirplaneModeSwitchOn = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 12.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            elevation = CardDefaults.cardElevation(
                defaultElevation = 4.dp
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Wifiのステータス: ${if (isWifiSwitchOn.value) "ON" else "OFF"}"
                )
                Text(
                    text = "Bluetoothのステータス: ${if (isBluetoothSwitchOn.value) "ON" else "OFF"}"
                )
                Text(
                    text = "Airplane Modeのステータス: ${if (isAirplaneModeSwitchOn.value) "ON" else "OFF"}"
                )
            }
        }
        Spacer(modifier = Modifier.width(24.dp))
        SettingSwitches(
            title = "Wifi",
            isSwitchOn = isWifiSwitchOn
        )
        SettingSwitches(
            title = "Bluetooth",
            isSwitchOn = isBluetoothSwitchOn
        )
        SettingSwitches(
            title = "Airplane Mode",
            isSwitchOn = isAirplaneModeSwitchOn
        )
    }
}

@Composable
private fun SettingSwitches(
    title: String,
    isSwitchOn: MutableState<Boolean>,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = title)
        Switch(
            checked = isSwitchOn.value,
            onCheckedChange = {
                isSwitchOn.value = !isSwitchOn.value
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyHelloComposeTheme {
        MultipleSwitches()
    }
}
