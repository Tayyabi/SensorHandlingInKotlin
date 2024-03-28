package com.xyron.sensorhandlinginkotlin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
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
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.xyron.sensorhandlinginkotlin.ui.theme.SensorHandlingInKotlinTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SensorActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SensorHandlingInKotlinTheme {

                val viewModel = viewModel<MainViewModel>()
                val isDark = viewModel.isDark

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            if (isDark) Color.DarkGray else Color.White
                        ),
                    contentAlignment = Alignment.Center
                ){
                    Text(
                        text = if (isDark) {
                            "It's dark outside"
                        }
                        else {
                            "It's bright outside"
                        },
                        color =   if (isDark) Color.White else Color.DarkGray
                    )
                }

            }
        }
    }
}

@Composable
fun Greeting() {

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    SensorHandlingInKotlinTheme {
        Greeting()
    }
}