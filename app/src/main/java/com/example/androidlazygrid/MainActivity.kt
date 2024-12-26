package com.example.androidlazygrid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import multiplatform.network.cmptoast.ToastDuration
import multiplatform.network.cmptoast.showToast

val images = listOf(
    R.drawable.banana,
    R.drawable.cashews,
    R.drawable.figs,
    R.drawable.garnet,
    R.drawable.kumquat,
    R.drawable.papaya,
    R.drawable.pineapple,
    R.drawable.quince
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GreetingPreview()
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    val arrayArrays = arrayListOf<Int>()
    val array = arrayListOf<Int>()
    var buf = 0
    var flag = true
    var count = 0
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray),
        horizontalArrangement = Arrangement.Center
    ) {
        items(100) {
            buf = images.random()
            array.add(buf)
            Column(
                modifier = Modifier
                    .size(120.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painterResource(id = buf),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .border(2.dp, Color.Green)
                        .fillMaxSize()
                )
            }
            if (array.size == 3) {
                array.forEach {
                    if (it != buf) {
                        flag = false
                    }
                }
                if (flag) {
                    showToast(
                        message = "Поздравляем, найдено совпадение",
                        backgroundColor = Color.White,
                        textColor = Color.Black,
                        duration = ToastDuration.Short
                    )
                }
                array.forEach {
                    arrayArrays.add(it)
                }
                array.clear()
                flag = true
            }
        }
    }
}