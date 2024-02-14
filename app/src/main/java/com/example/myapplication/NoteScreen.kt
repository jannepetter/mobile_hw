package com.example.myapplication

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.myapplication.data.ContactEvent
import com.example.myapplication.data.NoteEvent
import com.example.myapplication.data.NoteViewModel

//val listOfNotes  = listOf<String>("something", "another", "some text here")
@Composable
fun NoteScreen(navController: NavHostController, noteViewModel: NoteViewModel) {
    val state = noteViewModel.state.value
    val notes = noteViewModel.state.collectAsState().value.notes

    Column(
        modifier = Modifier.padding(15.dp)
    ) {
        Text(
            text="Notes",
            color = MaterialTheme.colorScheme.secondary,
            fontSize = 50.sp,
            fontWeight = FontWeight.ExtraBold
        )
        TextField(
            value = state.data.value,
            onValueChange = { state.data.value = it },
            label = { Text("Add note?") }
        )
        Button(
            onClick = {
                noteViewModel.onEvent(
                    NoteEvent.SaveNote(
                        state.data.value,
                    ))
                state.data.value = ""
            },
        ){
            Text(text = "Create")
        }

        LazyColumn(
            content = {
                items(notes.size){i ->
                    val note = notes[i]
                    Row(
                        modifier = Modifier
                            .padding(20.dp)
                            .background(Color.LightGray)
                            .fillMaxWidth()

                    ){
                        Text(
                            text = note.data,
                            modifier = Modifier.weight(1f)
                        )
                        Icon(
                            Icons.Rounded.Delete,
                            contentDescription = "Delete",
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .clickable {
                                    noteViewModel.onEvent(
                                        NoteEvent.DeleteNote(
                                            notes[i]
                                        )
                                    )
                                }
                        )
                    }
                }

            })

    }
}
