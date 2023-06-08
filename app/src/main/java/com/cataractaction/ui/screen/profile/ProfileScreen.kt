package com.cataractaction.ui.screen.profile

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.cataractaction.core.domain.model.UserData
import com.cataractaction.ui.components.profile.ButtonLogout
import com.cataractaction.ui.components.profile.ButtonProfile
import com.cataractaction.ui.components.profile.NameProfile
import com.cataractaction.ui.components.profile.PhotoProfile
import com.cataractaction.ui.navigation.Screen
import kotlinx.coroutines.launch

@Composable
fun ProfileScreen(
    userData: UserData?,
    navHostController: NavHostController,
    viewModel: ProfileViewModel = hiltViewModel()
) {

    val context = LocalContext.current
    val navigateToComing = { navHostController.navigate(Screen.Coming.route) }

    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(Modifier.size(33.dp))
        Text(
            text = "Profile",
            style = MaterialTheme.typography.h5,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(Modifier.size(33.dp))
        PhotoProfile(navigateToComing, userData?.profilePictureUrl)
        Spacer(Modifier.size(10.dp))
        NameProfile(
            userData?.username ?: userData?.email.toString().split("@")[0],
            userData?.email ?: "Not found"
        )
        Spacer(Modifier.size(40.dp))
        ButtonProfile("password", navigateToComing)
        Spacer(Modifier.size(15.dp))
        ButtonProfile("language", navigateToComing)
        Spacer(Modifier.size(15.dp))
        ButtonProfile("contact", navigateToComing)
        Spacer(Modifier.size(15.dp))
        ButtonProfile("term", navigateToComing)
        Spacer(Modifier.size(40.dp))
        ButtonLogout(buttonLogout = {
            viewModel.viewModelScope.launch {
                viewModel.signOut()
                Toast.makeText(context, "Signed out", Toast.LENGTH_LONG).show()

                navHostController.navigate(Screen.Register.route) {
                    popUpTo(Screen.Home.route) {
                        inclusive = true
                    }
                }
            }
        })
    }
}