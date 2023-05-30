package com.cataractaction.ui.components.auth

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cataractaction.R

@Composable
fun TextFieldName() {
    var name by rememberSaveable { mutableStateOf("") }

    Text(
        text = stringResource(R.string.login_name),
        style = MaterialTheme.typography.h5.copy(fontSize = 14.sp),
        modifier = Modifier.padding(bottom = 1.dp)
    )
    OutlinedTextField(
        value = name,
        onValueChange = { name = it },
        label = {
            Text(
                "Enter your name",
                style = MaterialTheme.typography.body1.copy(fontSize = 12.sp)
            )
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            unfocusedBorderColor = Color.Black
        ),
        shape = RoundedCornerShape(60.dp),
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 10.dp)
    )
}

@Composable
fun TextFieldEmail() {
    var email by rememberSaveable { mutableStateOf("") }

    Text(
        text = stringResource(R.string.auth_email),
        style = MaterialTheme.typography.h5.copy(fontSize = 14.sp),
        modifier = Modifier.padding(bottom = 1.dp)
    )
    OutlinedTextField(
        value = email,
        onValueChange = { email = it },
        label = {
            Text(
                "Enter your email address",
                style = MaterialTheme.typography.body1.copy(fontSize = 12.sp)
            )
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            unfocusedBorderColor = Color.Black
        ),
        shape = RoundedCornerShape(60.dp),
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 10.dp)
    )
}

@Composable
fun TextFieldPassword() {
    var password by rememberSaveable { mutableStateOf("") }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    Text(
        text = stringResource(R.string.auth_password),
        style = MaterialTheme.typography.h5.copy(fontSize = 14.sp),
        modifier = Modifier.padding(bottom = 1.dp)
    )
    OutlinedTextField(
        value = password,
        onValueChange = { password = it },
        label = {
            Text(
                "Enter your password min 8 characters",
                style = MaterialTheme.typography.body1.copy(fontSize = 11.sp)
            )
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            unfocusedBorderColor = Color.Black

        ),
        shape = RoundedCornerShape(60.dp),
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        trailingIcon = {
            val image = if (passwordVisible)
                Icons.Filled.Visibility
            else Icons.Filled.VisibilityOff
            val description = if (passwordVisible) "Hide password" else "Show password"
            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                Icon(imageVector = image, description, tint = MaterialTheme.colors.primary)
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 10.dp)
    )
}