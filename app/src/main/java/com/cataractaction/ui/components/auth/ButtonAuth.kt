package com.cataractaction.ui.components.auth

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.cataractaction.R

@Composable
fun ButtonAuth(register: Boolean, navigateToHome: () -> Unit) {
    Button(
        onClick = { navigateToHome() }, modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 15.dp), shape = RoundedCornerShape(60.dp)
    ) {
        Text(
            text = if (register) "Sign Up" else "Sign In",
            style = MaterialTheme.typography.body2,
            modifier = Modifier.padding(5.dp)
        )
    }
}

@Composable
fun ButtonAuthGoogle(register: Boolean, onSignInClick: () -> Unit) {
    OutlinedButton(
        onClick = { onSignInClick() }, modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 15.dp),
        shape = RoundedCornerShape(60.dp),
        border = BorderStroke(1.dp, MaterialTheme.colors.primary),
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.Black,
            backgroundColor = Color.White
        )
    ) {
        Image(
            painter = painterResource(R.drawable.google),
            contentDescription = "google",
            modifier = Modifier
                .width(20.dp)
                .height(20.dp)
        )
        Spacer(Modifier.size(9.dp))
        Text(
            text = if (register) "Sign up with google" else "Sign in with google",
            style = MaterialTheme.typography.body2,
            modifier = Modifier.padding(5.dp)
        )
    }
}