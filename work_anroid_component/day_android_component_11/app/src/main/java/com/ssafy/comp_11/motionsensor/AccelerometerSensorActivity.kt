package com.ssafy.comp_11.motionsensor

import android.annotation.SuppressLint
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

private const val TAG = "AccelerometerSensor_싸피"
class AccelerometerSensorActivity : AppCompatActivity() {

    private lateinit var accelerometerSensor: Sensor
    private lateinit var sensorManager: SensorManager
    private lateinit var sensorEventListener: SensorEventListener

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_accelerometer_sensor)

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        sensorEventListener = AccelerometerListener()

        val btnStart = findViewById<Button>(R.id.btnStart)
        btnStart.setOnTouchListener { _, event ->
            when (event?.action) {
                MotionEvent.ACTION_DOWN -> {
                    sensorManager.registerListener(
                        sensorEventListener,
                        accelerometerSensor,
                        SensorManager.SENSOR_DELAY_UI
                    )
                }
                MotionEvent.ACTION_UP -> {
                    sensorManager.unregisterListener(sensorEventListener)
                }
            }
            false
        }
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(sensorEventListener)
    }

    override fun onDestroy() {
        super.onDestroy()
        sensorManager.unregisterListener(sensorEventListener)
    }

    private class AccelerometerListener : SensorEventListener {
        override fun onSensorChanged(event: SensorEvent) {

            Log.e(
                TAG,
                "           (x):" + String.format(
                    "%.4f",
                    event.values[0]
                ) + "           (y):" + String.format(
                    "%.4f",
                    event.values[1]
                ) + "           (z):" + String.format(
                    "%.4f",
                    event.values[2]
                )
            )
        }

        override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {}
    }
}

