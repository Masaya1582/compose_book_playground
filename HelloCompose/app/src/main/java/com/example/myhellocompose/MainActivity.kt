package com.example.myhellocompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
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
                    LoopImageList(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

data class Animal(
    @DrawableRes val resourceId: Int,
    val name: String
)

@Composable
private fun LoopImageList(modifier: Modifier = Modifier) {
    val animals = listOf(
        Animal(
            resourceId = R.drawable.img_dog,
            name = "Dog"
        ),
        Animal(
            resourceId = R.drawable.img_cat,
            name = "Cat"
        ),
        Animal(
            resourceId = R.drawable.img_hamster,
            name = "Hamster"
        )
    )

    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxWidth()
    ) {
        for (animal in animals) {
            AnimalCard(animal = animal)
        }
    }
}

@Composable
private fun AnimalCard(animal: Animal) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(2.dp)
    ) {
        Image(
            painter = painterResource(id = animal.resourceId),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(width = 80.dp, height = 80.dp)
        )
        Text(
            text = animal.name,
            fontSize = 16.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyHelloComposeTheme {
        LoopImageList()
    }
}
