package com.xyron.sensorhandlinginkotlin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.xyron.sensorhandlinginkotlin.ui.theme.SensorHandlingInKotlinTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DirectionSensorActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SensorHandlingInKotlinTheme {
                DirectionScreen1()
            }
        }
    }
}

@Composable
fun DirectionScreen1() {

    val viewModel = viewModel<DirectionSensorViewModel>()
    val direction = viewModel.direction


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ){
        Text(
            text = "Orientation: $direction",
            color = Color.DarkGray
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DirectionScreen1Preview() {
    SensorHandlingInKotlinTheme {
        DirectionScreen1()
    }
}