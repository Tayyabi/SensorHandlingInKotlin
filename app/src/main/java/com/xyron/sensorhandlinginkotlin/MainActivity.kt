package com.xyron.sensorhandlinginkotlin

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.xyron.sensorhandlinginkotlin.ui.theme.SensorHandlingInKotlinTheme
import kotlinx.coroutines.Delay

class MainActivity : ComponentActivity(), SensorEventListener {

    private lateinit var sensorManager: SensorManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        setContent {
            SensorHandlingInKotlinTheme {
                
                    DirectionScreen(0f)
                
            }
        }
    }

    override fun onResume() {
        super.onResume()

        val sensorList = sensorManager.getSensorList(Sensor.TYPE_ALL)

        for (sensor in sensorList){
            Log.d("Sensor: ", sensor.toString())
        }


        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION), SensorManager.SENSOR_DELAY_UI)
    }

    override fun onPause() {
        super.onPause()

        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent?) {

        var degree = Math.round(event?.values?.get(0) ?: 0f)

        Log.d("Degree: ", degree.toString())

    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        TODO("Not yet implemented")
    }
}

@Composable
fun DirectionScreen(degree: Float) {
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Degree: ",
            modifier = Modifier
                .padding(20.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )
        
        Image(
            painter = painterResource(id = R.drawable.ic_direction),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SensorHandlingInKotlinTheme {
        DirectionScreen(0f)
    }
}