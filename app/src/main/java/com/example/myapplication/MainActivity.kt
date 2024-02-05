package com.example.myapplication

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.myapplication.data.ContactViewModel
import com.example.myapplication.data.MyDatabase
import com.example.myapplication.ui.theme.MyApplicationTheme
import android.Manifest
import android.app.PendingIntent
import android.content.Intent
import android.util.Log
import androidx.annotation.RequiresApi

class MainActivity : ComponentActivity() {
    fun postNotification(){
        val intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent: PendingIntent =
            PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notification = NotificationCompat.Builder(applicationContext,"my_app_channel")
            .setContentText("It is getting hot in here!")
            .setContentTitle("Its getting hot")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentIntent(pendingIntent)
            .build()

        notificationManager.notify(1,notification)
    }
    private val database by lazy {
        Room.databaseBuilder(
            applicationContext,
            MyDatabase::class.java,
            "my_database"
        ).build()
    }
    private val viewModel by viewModels<ContactViewModel>(
        factoryProducer = {
            object : ViewModelProvider.Factory{
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return ContactViewModel(database.contactDao) as T
                }
            }
        }
    )
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                val context = LocalContext.current
                var hasNotificationPermission by remember {
                    mutableStateOf(
                        ContextCompat.checkSelfPermission(
                            context,
                            Manifest.permission.POST_NOTIFICATIONS,
                        ) == PackageManager.PERMISSION_GRANTED
                    )
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                    val channel = NotificationChannel(
                        "my_app_channel",
                        "my_app_channel_name",
                        NotificationManager.IMPORTANCE_HIGH
                    )
                    val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                    notificationManager.createNotificationChannel(channel)
                }
                Column {
                    val permissionLauncher= rememberLauncherForActivityResult(
                        contract = ActivityResultContracts.RequestPermission(),
                        onResult = {isGranted -> hasNotificationPermission = isGranted}
                    )
                    TemperatureSensor{postNotification()}
                    Navigation(viewModel,permissionLauncher)
                }
            }
        }
    }
}



@Composable
fun TemperatureSensor(postNotification:()->Unit) {
    val registered = remember {
        mutableStateOf(false)
    }
    val ctx = LocalContext.current
    val sensorManager: SensorManager = ctx.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    val ambientTemperatureSensor: Sensor? = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE)

    val sensorStatus = remember {
        mutableFloatStateOf(0.0F)
    }
    val ambientTemperatureSensorEventListener = object : SensorEventListener {
        override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
            // method to check accuracy changed in sensor.
        }

        override fun onSensorChanged(event: SensorEvent) {

            if (event.sensor.type == Sensor.TYPE_AMBIENT_TEMPERATURE) {

                sensorStatus.floatValue = event.values[0]
                if(sensorStatus.floatValue > 50.0f){
                    postNotification()
                }
            }
        }
    }
    if (!registered.value){
        sensorManager.registerListener(
            ambientTemperatureSensorEventListener,
            ambientTemperatureSensor,
            SensorManager.SENSOR_DELAY_NORMAL
        )
        registered.value = true
    }

    Row(
        modifier = Modifier.padding(20.dp)
    ) {
        Text(
            text = "Temperature ",
            color = Color.Black,
        )

        Text(
            text = sensorStatus.floatValue.toString(),
            color = Color.Black,
        )

        Text(
            text = " Celsius",
            color = Color.Black,
        )
    }
}





