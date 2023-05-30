package com.cataractaction.ui.screen.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cataractaction.ui.components.profile.ButtonLogout
import com.cataractaction.ui.components.profile.ButtonProfile
import com.cataractaction.ui.components.profile.NameProfile
import com.cataractaction.ui.components.profile.PhotoProfile

@Composable
fun ProfileScreen(navigateToComing: () -> Unit, buttonLogout: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(Modifier.size(23.dp))
        Text(
            text = "Profile",
            style = MaterialTheme.typography.h5,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(Modifier.size(33.dp))
        PhotoProfile(navigateToComing)
        Spacer(Modifier.size(10.dp))
        NameProfile()
        Spacer(Modifier.size(40.dp))
        ButtonProfile("password", navigateToComing)
        Spacer(Modifier.size(15.dp))
        ButtonProfile("language", navigateToComing)
        Spacer(Modifier.size(15.dp))
        ButtonProfile("contact", navigateToComing)
        Spacer(Modifier.size(15.dp))
        ButtonProfile("term", navigateToComing)
        Spacer(Modifier.size(40.dp))
        ButtonLogout(buttonLogout)
    }
}