package com.example.myapplication

import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.data.ContactViewModel
import com.example.myapplication.data.NoteViewModel


@Composable
fun Navigation(
    viewModel: ContactViewModel,
    noteViewModel: NoteViewModel,
    permissionLauncher: ManagedActivityResultLauncher<String, Boolean>
) {
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
                HomeScreen(navController,permissionLauncher)
            }
            composable(route="contact_screen"){
                ContactsScreen(navController,viewModel)
            }
            composable(route="note_screen"){
                NoteScreen(navController,noteViewModel)
            }
            composable(route="new_contact_screen"){
                NewContactScreen(navController,viewModel)
            }
            composable(route="contact_detail/{contactId}"){it->
                ContactDetail(navController, viewModel)
            }
        }

    }

}