package com.example.myapplication

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.navigation.NavHostController


class Person(
    val name:String,
    val email:String
){}
val contactPersons = listOf(
    Person("John","john@john.org"),
    Person("Elise","elise@elise.org"),
    Person("Michael","michael@michael.org"),
    Person("Kathy","kathy@kathy.org"),
    Person("Ann","ann@ann.org"),
)
@Composable
fun ContactsScreen(navController: NavHostController) {
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
            LazyColumn(
                content = {
                    items(20){i ->
                        val person = contactPersons[i % contactPersons.size]
                        Contact(
                            name = person.name, email = person.email )
                    }

                })
        }
    }

@Composable
fun Contact(name:String,email:String){
    var clicked by remember {
        mutableStateOf(false)
    }
    Row(
        modifier = Modifier.padding(vertical = 5.dp)
    ){
        Image(
            painter = painterResource(
                id = R.drawable.banana_dolphin
            ), contentDescription = "content",
            modifier = Modifier
                .width(150.dp)
                .clickable {
                    clicked = !clicked
                }
                .clip(CircleShape)
        )
        Column {
            Text(text = name)
            if(clicked){
                Text(text = "Email: $email", fontWeight = FontWeight.Bold)
            }
        }
    }
}