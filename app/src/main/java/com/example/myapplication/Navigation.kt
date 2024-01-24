package com.example.myapplication

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


@Composable
fun Navigation(){
    val navController = rememberNavController()
    Column {
        Row{
            Button(
                modifier = Modifier.padding(5.dp),
                onClick = {
                    navController.navigate(route = "home_screen"){
                        popUpTo("home_screen")
                    }
                }) {
                Text(text = "Home")
            }
            Button(
                modifier = Modifier.padding(5.dp),
                onClick = {
                    navController.navigate(route = "contact_screen"){
                        popUpTo("contact_screen")
                    }
                }) {
                Text(text = "Contacts")
            }
            Button(
                modifier = Modifier.padding(5.dp),
                onClick = {
                    navController.navigate(route = "note_screen"){
                        popUpTo("note_screen")
                    }
                }) {
                Text(text = "Notes")
            }

        }
        NavHost(navController = navController, startDestination = "home_screen"){
            composable(route="home_screen"){
                HomeScreen(navController)
            }
            composable(route="contact_screen"){
                ContactsScreen(navController)
            }
            composable(route="note_screen"){
                NoteScreen(navController)
            }
        }

    }

}