package com.example.myapplication

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

val listOfNotes  = listOf<String>("something", "another", "some text here")
@Composable
fun NoteScreen(navController: NavHostController) {
    Column(
        modifier = Modifier.padding(15.dp)
    ) {
        Text(
            text="Notes",
            color = MaterialTheme.colorScheme.secondary,
            fontSize = 50.sp,
            fontWeight = FontWeight.ExtraBold
        )
        LazyColumn(
            content = {
                items(10){i ->
                    val note = listOfNotes[i % listOfNotes.size]
                    Row(
                        modifier = Modifier.padding(20.dp).background(Color.LightGray).fillMaxWidth()

                    ){
                        Text(
                            text = note
                        )
                    }
                }

            })

    }
}
