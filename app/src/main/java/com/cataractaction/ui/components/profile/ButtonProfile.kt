package com.cataractaction.ui.components.profile

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Contacts
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

data class IconName(val icon: ImageVector, val text: String)

@Composable
fun ButtonProfile(category: String, button: () -> Unit) {
    val icon: IconName = when (category) {
        "password" -> IconName(Icons.Filled.Lock, "Change Password")
        "language" -> IconName(Icons.Filled.Language, "Change Language")
        "contact" -> IconName(Icons.Filled.Contacts, "Contact Us")
        "term" -> IconName(Icons.Filled.ArrowForward, "Terms and Conditions")
        else -> IconName(Icons.Filled.Lock, "Change Password")
    }

    OutlinedButton(
        onClick = { button() },
        modifier = Modifier.padding(horizontal = 17.dp),
        shape = RoundedCornerShape(25),
        border = BorderStroke(1.dp, MaterialTheme.colors.primary)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = icon.text)
            Icon(imageVector = icon.icon, contentDescription = category)
        }
    }
}

@Composable
fun ButtonLogout(buttonLogout: () -> Unit) {
    Button(
        onClick = { buttonLogout() }, modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 40.dp), shape = RoundedCornerShape(60.dp)
    ) {
        Text(
            text = "Logout",
            style = MaterialTheme.typography.body2,
            modifier = Modifier.padding(5.dp)
        )
    }
}