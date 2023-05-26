package com.cataractaction.ui.components.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cataractaction.R

@Composable
fun CardArticle() {
    Box(
        modifier = Modifier
            .background(MaterialTheme.colors.primary, RoundedCornerShape(10))
    ) {
        Column {
            Image(
                painter = painterResource(R.drawable.dummy_article),
                contentDescription = "article",
                modifier = Modifier.clip(RoundedCornerShape(bottomStart = 10.dp, bottomEnd = 10.dp))
            )
            Column(modifier = Modifier.padding(12.dp)) {
                Text(
                    text = "How to Prevent Cataract ?",
                    color = Color.White,
                    style = MaterialTheme.typography.body2.copy(fontSize = 14.sp)
                )
                Text(
                    text = "Cataracts are a common age-related condition that can develop slowly over many years. In hospital",
                    color = Color.White,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.body1.copy(fontSize = 12.sp)
                )
            }
        }
    }
}