package com.xyron.sensorhandlinginkotlin.qualifiers

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class LightSensorQualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class MagneticFieldSensorQualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AccelerometerSensorQualifier