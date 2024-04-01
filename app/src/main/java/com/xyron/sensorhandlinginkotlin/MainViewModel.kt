package com.xyron.sensorhandlinginkotlin

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.xyron.sensorhandlinginkotlin.qualifiers.LightSensorQualifier
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    @LightSensorQualifier private val lightSensor: MeasurableSensor
): ViewModel() {

    var isDark by mutableStateOf(false)
    init {
        lightSensor.startListening()
        lightSensor.setOnSensorValuesChangedListener { values ->
            val lux = values[0]

            isDark = lux < 60f

        }
    }
}
