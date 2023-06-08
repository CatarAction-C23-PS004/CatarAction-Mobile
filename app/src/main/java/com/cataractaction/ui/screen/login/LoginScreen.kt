package com.cataractaction.ui.screen.login

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.cataractaction.ui.components.auth.ButtonAuth
import com.cataractaction.ui.components.auth.ButtonAuthGoogle
import com.cataractaction.ui.components.auth.DividerAuth
import com.cataractaction.ui.components.auth.LogoTitle
import com.cataractaction.ui.components.auth.TextAuth
import com.cataractaction.ui.components.auth.TextFieldEmail
import com.cataractaction.ui.components.auth.TextFieldPassword
import com.cataractaction.ui.components.auth.TextTitle
import com.cataractaction.ui.navigation.Screen

@Composable
fun LoginScreen(
    navHostController: NavHostController,
    onSignInClick: () -> Unit,
    onSignUpClick: (email: String, password: String) -> Unit,
    loading: Boolean?
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {

            val email = rememberSaveable { mutableStateOf("") }
            val password = rememberSaveable { mutableStateOf("") }

            LogoTitle()
            Column(modifier = Modifier.padding(horizontal = 40.dp)) {
                TextTitle(false)
                TextFieldEmail(email)
                Spacer(Modifier.size(14.dp))
                TextFieldPassword(password, false)
                Spacer(Modifier.size(30.dp))
                ButtonAuth(false) {
                    onSignUpClick(email.value, password.value)
                }
                DividerAuth()
                ButtonAuthGoogle(false) { onSignInClick() }
                Spacer(Modifier.size(5.dp))
                TextAuth(false) { navHostController.navigate(Screen.Register.route) }
                Spacer(Modifier.size(25.dp))
            }
        }
        if (loading == true) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}