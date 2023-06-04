package com.cataractaction.ui.components.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.cataractaction.R

@Composable
fun DashboardHome(navigateToCheck: () -> Unit, navigateToHistory: () -> Unit, name: String) {
    Row {
        Image(
            painter = painterResource(R.drawable.home_doctor),
            contentDescription = "doctor_home",
            modifier = Modifier
                .width(84.dp)
                .height(189.dp)
        )
        Spacer(Modifier.size(15.dp))
        Column {
            Box(
                modifier = Modifier
                    .background(
                        MaterialTheme.colors.primary,
                        RoundedCornerShape(
                            topEnd = 25.dp,
                            topStart = 25.dp,
                            bottomEnd = 25.dp,
                            bottomStart = 0.dp
                        )
                    )
                    .padding(15.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Hi ${name},\nWhat can we do for you?", color = Color.White)
            }
            Spacer(Modifier.size(20.dp))
            OutlinedButton(
                onClick = { navigateToCheck() },
                shape = RoundedCornerShape(60.dp),
                border = BorderStroke(1.dp, MaterialTheme.colors.primary),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.White
                )
            ) {
                Text(
                    text = "Cataract Check",
                    color = MaterialTheme.colors.primary,
                    style = MaterialTheme.typography.body2
                )
            }
            Spacer(Modifier.size(8.dp))
            OutlinedButton(
                onClick = { navigateToHistory() },
                shape = RoundedCornerShape(60.dp),
                border = BorderStroke(1.dp, MaterialTheme.colors.primary),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.White
                )
            ) {
                Text(
                    text = "View History",
                    color = MaterialTheme.colors.primary,
                    style = MaterialTheme.typography.body2
                )
            }
        }
    }
}