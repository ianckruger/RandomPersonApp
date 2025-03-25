package com.example.userprofiles

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun UserProfileScreen(viewModel: UserViewModel = viewModel()) {
    //Do this in the middle of the others
    val user by viewModel.user.collectAsState()
    val loading by viewModel.loading.collectAsState()
    val error by viewModel.error.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // set up a when statement for 3 differnet things
        // if were loading; if we have an error; if we get user data
        when {
            loading -> {
                CircularProgressIndicator()
            }

            // two !! means it can be null so leave it alone, its fine
            error != null -> {
                Text(text = error!!, fontSize = 16.sp, color = Color.Red)
                BUtton(onClick = { viewModel.fetchRandomUser() }) {
                    Text(text = "Load New User")
                }
            }

            user != null -> {
                Text(
                    text = "${user!!.name.first} ${user!!.name.last}",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                AsyncImage(
                    model = user!!.picture.large,
                    contentDescription = "User Image"
                )
                // create a button to load the user onto the interface
                Button(onClick = { viewModel.fetchRandomUser() }) {
                    Text(text = "Get Random User")
                }
            }
        }
    }
}
