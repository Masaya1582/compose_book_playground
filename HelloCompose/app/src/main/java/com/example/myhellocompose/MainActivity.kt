package com.example.myhellocompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
                    Greeting(
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
       Text(text = "I like Compose", fontSize = 10.sp)
       Text(text = "I like Compose", fontSize = 20.sp)
       Text(text = "I like Compose", fontSize = 30.sp)
       val story = "Today’s ceremony, however, has very special meaning. Because today we are not merely transferring power from one Administration to another, or from one party to another – but we are transferring power from Washington, D.C. and giving it back to you, the American People."
       Text(text = story)
       Text(
           text = story,
           maxLines = 1,
           fontWeight = FontWeight.Bold,
           modifier = Modifier
               .size(width = 200.dp, height = 100.dp)
               .background(
                   Brush.linearGradient(listOf(Color.White, Color.Gray)),
                   shape = RoundedCornerShape(20.dp)
               )
               .border(
                   width = 1.dp,
                   color = Color.Black,
                   shape = RoundedCornerShape(20.dp)
               )
               .clickable { println("Text Clicked!") }
               .padding(10.dp)
       )
       ImageSample()
   }
}

@Composable
private fun ImageSample() {
    Image(
        painter = painterResource(id = R.drawable.img_dog),
        contentDescription = "Sample Image",
        contentScale = ContentScale.Crop,
        modifier = Modifier.size(300.dp),
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyHelloComposeTheme {
        Greeting()
    }
}