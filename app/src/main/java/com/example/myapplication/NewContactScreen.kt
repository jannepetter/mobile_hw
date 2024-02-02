package com.example.myapplication
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.myapplication.data.ContactEvent
import com.example.myapplication.data.ContactViewModel
import androidx.compose.ui.platform.LocalContext

class CustomImage(val name:String,val id:Int){
}
val imageList = listOf(
    CustomImage("banana",R.drawable.banana_dolphin),
    CustomImage("monkey",R.drawable.monkey),
    CustomImage("man",R.drawable.man)
)
@Composable
fun NewContactScreen(navController: NavHostController, viewModel: ContactViewModel) {
    val state = viewModel.state.value
    var selectedImage by remember { mutableStateOf(0) }
    Column(
        modifier = Modifier.padding(15.dp)
    ) {

        Text("Select image for contact:")
        Spacer(modifier = Modifier.height(8.dp))

        RadioButton(
            selected = selectedImage == 0,
            onClick = { selectedImage = 0 }
        )
        Text(imageList[0].name)

        RadioButton(
            selected = selectedImage == 1,
            onClick = { selectedImage = 1 }
        )
        Text(imageList[1].name)

        RadioButton(
            selected = selectedImage == 2,
            onClick = { selectedImage = 2 }
        )
        Text(imageList[2].name)

        Text(text = "Name: ")
        TextField(
            value = state.name.value,
            onValueChange = { state.name.value = it },
            label = { Text("Add name?") }
        )

        Text(text = "Email: ")
        TextField(
            value = state.email.value,
            onValueChange = { state.email.value = it },
            label = { Text("Add email") }
        )

        Text(text = "Phone: ")
        TextField(
            value = state.phone.value,
            onValueChange = { state.phone.value = it },
            label = { Text("Add email") }
        )
        Button(
            onClick = {
                state.image.value = imageList[selectedImage].id
                viewModel.onEvent(
                    ContactEvent.SaveContact(
                    state.name.value,
                    state.email.value,
                    state.phone.value,
                    state.image.value

                ))
                state.name.value = ""
                state.email.value = ""
                state.phone.value = ""
            },
        ){
            Text(text = "Submit")

        }
    }

}