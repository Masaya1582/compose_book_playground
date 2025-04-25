package com.example.myhellocompose

import android.app.TimePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myhellocompose.ui.theme.MyHelloComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyHelloComposeTheme {
               SampleDateAndTimePickerView()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = false)
@Composable
private fun SampleDateAndTimePickerView() {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Date & Time Picker Example") })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            DateTimePickerScreen()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DateTimePickerScreen() {
    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)
    var selectedDate by remember { mutableStateOf("$day/${month + 1}/$year") }

    val hour = calendar.get(Calendar.HOUR_OF_DAY)
    val minute = calendar.get(Calendar.MINUTE)
    var selectedTime by remember { mutableStateOf(String.format("%02d:%02d", hour, minute)) }

    var isShowDatePickerDialog by remember { mutableStateOf(false) }
    var isShowTimePickerDialog by remember { mutableStateOf(false) }

    Text(
        text = "Selected Date: $selectedDate",
        style = MaterialTheme.typography.headlineMedium
    )
    Button(
        modifier = Modifier.fillMaxWidth(),
        onClick = {
            isShowDatePickerDialog = true
        }
    ) {
        Text("Pick Date")
    }
    Spacer(modifier = Modifier.height(24.dp))
    Text(
        text = "Selected Time: $selectedTime",
        style = MaterialTheme.typography.headlineMedium,
        modifier = Modifier.padding(bottom = 8.dp)
    )

    // Button to show TimePicker
    Button(
        modifier = Modifier.fillMaxWidth(),
        onClick = {
            isShowTimePickerDialog = true
        }
    ) {
        Text("Pick Time")
    }

    if (isShowDatePickerDialog) {
        val context = LocalContext.current
        android.app.DatePickerDialog(
            context,
            { _, selectedYear, selectedMonth, selectedDay ->
                selectedDate =
                    "$selectedDay/${selectedMonth + 1}/$selectedYear"
                isShowDatePickerDialog = false
            },
            year,
            month,
            day
        ).show()
    }

    if (isShowTimePickerDialog) {
        val context = LocalContext.current
        TimePickerDialog(
            context,
            { _, selectedHour, selectedMinute ->
                // Update the selected time state when a time is picked
                selectedTime = String.format("%02d:%02d", selectedHour, selectedMinute)
                isShowTimePickerDialog = false // Hide the dialog
            },
            hour,
            minute,
            true // is24HourView
        ).show() // Show the dialog immediately when state is true
    }
}
