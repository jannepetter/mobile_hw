package com.example.myapplication

import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import android.Manifest
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun HomeScreen(
    navController: NavHostController,
    permissionLauncher: ManagedActivityResultLauncher<String, Boolean>
) {
        Column(
            modifier = Modifier.padding(15.dp)
        ) {
            Text(
                text="Home",
                color = MaterialTheme.colorScheme.secondary,
                fontSize = 50.sp,
                fontWeight = FontWeight.ExtraBold
                )
            Button(onClick = {
                permissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }) {
                Text(text = "Permissions")
            }

        }
    }
