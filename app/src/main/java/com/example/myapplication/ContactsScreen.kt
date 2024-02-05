package com.example.myapplication

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.res.ResourcesCompat
import androidx.navigation.NavHostController
import com.example.myapplication.data.Contact
import com.example.myapplication.data.ContactState
import com.example.myapplication.data.ContactViewModel
import kotlinx.coroutines.flow.update


@Composable
fun ContactsScreen(navController: NavHostController, viewModel: ContactViewModel) {

        val contacts = viewModel.state.collectAsState().value.contacts

        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp)
        ) {
            Text(
                text = "Contacts",
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold
            )
            Button(onClick = {
                navController.navigate("new_contact_screen")
            }) {
                Text(text = "Add Contact")
            }
            LazyColumn(
                content = {
                    items(contacts.size){i ->
                        ContactItem(contacts[i],navController,viewModel)
                    }

                })
        }
    }

@Composable
fun ContactItem(contact: Contact,navController: NavHostController,viewModel: ContactViewModel){
    var clicked by remember {
        mutableStateOf(false)
    }
    Row(
        modifier = Modifier.padding(vertical = 5.dp)
    ){
        if (contact.image == 0){
            Image(
                painter = painterResource(
                    id = R.drawable.monkey
                ), contentDescription = "content",
                modifier = Modifier
                    .width(150.dp)
                    .clickable {
                        clicked = !clicked
                    }
                    .clip(CircleShape)
            )
        }else{
            Image(
                painter = painterResource(
                    id = contact.image
                ), contentDescription = "content",
                modifier = Modifier
                    .width(150.dp)
                    .clickable {
                        clicked = !clicked
                    }
                    .clip(CircleShape)
            )
        }
        Column {
            Text(text = contact.name)
            if(clicked){
                Text(text = "Email: ${contact.email}", fontWeight = FontWeight.Bold)
                Button(onClick = {
                    viewModel.updateContactDetail(contact)
                    val idStr = contact.id.toString()
                    navController.navigate("contact_detail/$idStr")
                }) {
                    Text(text = "Inspect")
                }
            }

        }
    }
}