package com.cataractaction.ui.components.article

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SearchField() {
    OutlinedTextField(
        value = "",
        onValueChange = {},
        label = {
            Text(
                "Search article",
                style = MaterialTheme.typography.body1
            )
        },
        shape = RoundedCornerShape(60.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(trailingIconColor = MaterialTheme.colors.primary),
        trailingIcon = {
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "search"
                )
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 10.dp)
    )
}