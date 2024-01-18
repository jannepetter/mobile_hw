package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme

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
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Column(
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(15.dp)
                ) {
                  Text(
                      text = "Main app",
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
        }
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
        )
        Column {
            Text(text = name)
            if(clicked){
                Text(text = "Email: $email", fontWeight = FontWeight.Bold)
            }
        }
    }
}





