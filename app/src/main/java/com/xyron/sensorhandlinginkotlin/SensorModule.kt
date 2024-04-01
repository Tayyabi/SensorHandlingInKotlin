package com.xyron.sensorhandlinginkotlin

import android.app.Application
import com.xyron.sensorhandlinginkotlin.qualifiers.AccelerometerSensorQualifier
import com.xyron.sensorhandlinginkotlin.qualifiers.LightSensorQualifier
import com.xyron.sensorhandlinginkotlin.qualifiers.MagneticFieldSensorQualifier
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SensorModule {


    @Provides
    @Singleton
    @LightSensorQualifier
    fun provideLightSensor(app: Application): MeasurableSensor {

        return LightSensor(app)
    }


    @Provides
    @Singleton
    @MagneticFieldSensorQualifier
    fun provideMagneticFieldSensor(app: Application): MeasurableSensor {

        return MagneticFieldSensor(app)
    }

    @Provides
    @Singleton
    @AccelerometerSensorQualifier
    fun provideAccelerometerSensor(app: Application): MeasurableSensor {

        return AccelerometerSensor(app)
    }

}