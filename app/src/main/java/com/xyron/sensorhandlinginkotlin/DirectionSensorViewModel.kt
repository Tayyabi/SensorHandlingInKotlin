package com.xyron.sensorhandlinginkotlin

import android.hardware.SensorManager
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.xyron.sensorhandlinginkotlin.qualifiers.AccelerometerSensorQualifier
import com.xyron.sensorhandlinginkotlin.qualifiers.MagneticFieldSensorQualifier
import dagger.hilt.android.lifecycle.HiltViewModel
import java.lang.Math.round
import javax.inject.Inject

@HiltViewModel
class DirectionSensorViewModel @Inject constructor(
    @MagneticFieldSensorQualifier private val directionSensor: MeasurableSensor,
    @AccelerometerSensorQualifier private val accelerometerSensor: MeasurableSensor
): ViewModel() {

    var direction by mutableStateOf("N")

    private val accelerometerReading = FloatArray(3)
    private val magnetometerReading = FloatArray(3)

    private val rotationMatrix = FloatArray(9)
    private val orientationAngles = FloatArray(3)

    init {
        directionSensor.startListening()
        directionSensor.setOnSensorValuesChangedListener { values ->

            System.arraycopy(values.toFloatArray(), 0, magnetometerReading, 0, magnetometerReading.size)
            updateOrientationAngles()

        }

        accelerometerSensor.startListening()
        accelerometerSensor.setOnSensorValuesChangedListener { values ->
            System.arraycopy(values.toFloatArray(), 0, accelerometerReading, 0, accelerometerReading.size)
            updateOrientationAngles()
        }
    }

    private fun updateOrientationAngles() {
        // 1
        SensorManager.getRotationMatrix(rotationMatrix, null, accelerometerReading, magnetometerReading)
        // 2
        val orientation = SensorManager.getOrientation(rotationMatrix, orientationAngles)
        // 3
        val degrees = (Math.toDegrees(orientation.get(0).toDouble()) + 360.0) % 360.0
        // 4
        val angle = round(degrees * 100) / 100

        val dr = getDirection(degrees)
        direction = dr
    }

    private fun getDirection(angle: Double): String {
        var direction = ""

        if (angle >= 350 || angle <= 10)
            direction = "N"
        if (angle < 350 && angle > 280)
            direction = "NW"
        if (angle <= 280 && angle > 260)
            direction = "W"
        if (angle <= 260 && angle > 190)
            direction = "SW"
        if (angle <= 190 && angle > 170)
            direction = "S"
        if (angle <= 170 && angle > 100)
            direction = "SE"
        if (angle <= 100 && angle > 80)
            direction = "E"
        if (angle <= 80 && angle > 10)
            direction = "NE"

        return direction
    }

}