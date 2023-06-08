package com.cataractaction.ui.components.history

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.cataractaction.R

@Composable
fun LogoTitleHistory() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 23.dp).offset(x= (-13).dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = {}) {}
        Text(text = "History", style = MaterialTheme.typography.h5)
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.sort),
            contentDescription = "sort",
            tint = MaterialTheme.colors.primary,
            modifier = Modifier.size(15.dp)
        )
    }
}