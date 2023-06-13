package com.cataractaction.ui.components.history

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CardHistory(
    date: String,
    result: String,
    onCheckCataract: () -> Unit,
    onCataractResult: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onCataractResult() },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = "Cataract Check",
                style = MaterialTheme.typography.body2.copy(fontSize = 14.sp)
            )
            Spacer(Modifier.size(4.dp))
            Text(
                text = "Cataract check has been successful",
                style = MaterialTheme.typography.body1.copy(fontSize = 12.sp)
            )
            Spacer(Modifier.size(6.dp))
            Text(
                text = date,
                style = MaterialTheme.typography.body1.copy(fontSize = 10.sp)
            )
        }
        Column(
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = result,
                style = MaterialTheme.typography.body1.copy(fontSize = 12.sp),
                color = if (result == "Cataract") MaterialTheme.colors.onError else MaterialTheme.colors.onSecondary
            )
            Spacer(Modifier.size(5.dp))
            if (result == "Cataract") {
                OutlinedButton(
                    onClick = { onCheckCataract() },
                    shape = RoundedCornerShape(60.dp),
                    border = BorderStroke(1.dp, MaterialTheme.colors.primary),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.White
                    )
                ) {
                    Text(
                        text = "Recheck",
                        style = MaterialTheme.typography.body2,
                        color = MaterialTheme.colors.primary,
                        textAlign = TextAlign.Center
                    )
                }
            } else {
                Spacer(Modifier.size(0.dp))
            }
        }
    }
    Spacer(Modifier.size(15.dp))
    Divider()
}