package com.example.myapplication

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.myapplication.data.ContactEvent
import com.example.myapplication.data.ContactViewModel


@Composable
fun ContactDetail(navController: NavHostController, viewModel: ContactViewModel){

    val state = viewModel.state.value
    val detail = state.detail


    var name by remember {
        mutableStateOf(detail?.name ?:"")
    }
    var email by remember {
        mutableStateOf(detail?.email ?:"")
    }
    var phone by remember {
        mutableStateOf(detail?.phone ?:"")
    }
    Column(
        modifier = Modifier.padding(15.dp)
    ) {
        if (detail?.image == 0){
            Image(
                painter = painterResource(
                    id = R.drawable.monkey
                ), contentDescription = "content",
                modifier = Modifier
                    .width(150.dp)
                    .clip(CircleShape)
            )
        }else{
            if (detail != null) {
                Image(
                    painter = painterResource(
                        id = detail.image
                    ), contentDescription = "content",
                    modifier = Modifier
                        .width(150.dp)
                        .clip(CircleShape)
                )
            }
        }

        Text(text = "Name: ")
        TextField(
            value = name,
            onValueChange = { name = it },
        )

        Text(text = "Email: ")
        TextField(
            value = email,
            onValueChange = { email = it },
        )

        Text(text = "Phone: ")
        TextField(
            value = phone,
            onValueChange = { phone = it },
        )
        Button(
            onClick = {
                if (detail != null) {
                    viewModel.updateVariables(name,email,phone,detail.id)
                    viewModel.onEvent(
                        ContactEvent.UpdateContact(
                            detail.id,
                            state.name.value,
                            state.email.value,
                            state.phone.value
                        ))
                }
            },
        ){
            Text(text = "Update")
        }
        Button(
            onClick = {
                if(detail != null){
                viewModel.onEvent(
                    ContactEvent.DeleteContact(
                        detail
                    ))
                }
                navController.navigate("contact_screen")
            },
        ){
            Text(text = "Delete")
        }

    }

}