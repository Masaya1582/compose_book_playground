package com.example.myhellocompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myhellocompose.ui.theme.MyHelloComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyHelloComposeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ScrollSample(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(modifier: Modifier = Modifier) {
   Column(
       modifier = Modifier
           .fillMaxSize()
   ) {
       Text(text = "Good Morning")
       Text(text = "Good Afternoon")
       Text(text = "Good Evening")
       Text(text = "Good Night")
       RowImageList()
       BoxImageSample()
       ArrangementSample()
   }
}

@Composable
private fun ScrollSample(modifier: Modifier = Modifier) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier.verticalScroll(state = scrollState)
    ) {
        for (i in 1..50) {
            Image(
                painterResource(id = R.drawable.img_dog),
                contentDescription = "DOG",
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    }
}

@Composable
private fun MutableTextField(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var inputText by remember { mutableStateOf("") }

        Text(
            text = inputText,
            fontSize = 24.sp
        )
        TextField(
            value = inputText,
            onValueChange = { inputText = it }
        )
    }
}

@Composable
private fun IncrementCounter(modifier: Modifier = Modifier) {
    var count by remember { mutableIntStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "$count",
            fontSize = 24.sp,
            modifier = Modifier
                .clickable { count++ }
        )
    }
}

@Composable
private fun RowImageList() {
    Row {
        Image(
            painterResource(id = R.drawable.img_dog),
            contentDescription = "DOG",
            modifier = Modifier.size(100.dp)
        )
        Spacer(modifier = Modifier.width(20.dp))
        Image(
            painterResource(id = R.drawable.img_dog),
            contentDescription = "DOG",
            modifier = Modifier.size(100.dp)
        )
        Spacer(modifier = Modifier.width(20.dp))
        Image(
            painterResource(id = R.drawable.img_dog),
            contentDescription = "DOG",
            modifier = Modifier.size(100.dp)
        )
    }
}

// @Preview(showBackground = true)
@Composable
private fun ArrangementSample() {
    val imageIdLIst = listOf(
        R.drawable.img_dog,
        R.drawable.img_dog,
        R.drawable.img_dog
    )
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        imageIdLIst.forEach { imageId ->
            Image(
                painterResource(id = imageId),
                contentDescription = "DOG",
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(200.dp)
            )
        }
    }
}

// @Preview(showBackground = true)
@Composable
private fun AlignmentSample() {
    val imageIdLIst = listOf(
        R.drawable.img_dog,
        R.drawable.img_dog,
        R.drawable.img_dog
    )
    Row(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        imageIdLIst.forEach { imageId ->
            Image(
                painterResource(id = imageId),
                contentDescription = "DOG",
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(100.dp)
            )
        }
    }
}

@Composable
private fun BoxImageSample() {
    Box {
        Image(
            painterResource(id = R.drawable.img_dog),
            contentDescription = "DOG",
            modifier = Modifier.size(100.dp)
        )
        Text(
            text = "Hello Dog",
            color = Color.Black
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ColumnRowMixSample() {
    val imageIdLIst = listOf(
        R.drawable.img_dog,
        R.drawable.img_cat,
        R.drawable.img_hamster
    )
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            imageIdLIst.forEach { imageId ->
                Image(
                    painterResource(id = imageId),
                    contentDescription = "Multiple Animals",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(width = 120.dp, height = 120.dp),
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Cute Animals",
            fontSize = 24.sp
        )
    }
}

// @Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyHelloComposeTheme {
        Greeting()
    }
}