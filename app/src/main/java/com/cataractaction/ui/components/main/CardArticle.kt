package com.cataractaction.ui.components.main

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.progressSemantics
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest

@Composable
fun CardArticle(name: String, description: String, image: String, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .background(MaterialTheme.colors.primary, RoundedCornerShape(10))
            .clickable { onClick() }
    ) {
        Column {
            SubcomposeAsyncImage(
                model = image,
                contentDescription = "article",
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .height(120.dp)
                    .width(330.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(bottomStart = 10.dp, bottomEnd = 10.dp)),
                loading = {
                    Box(contentAlignment = Alignment.Center) {
                        CircularProgressIndicator(
                            color = Color.White,
                            modifier = Modifier.padding(vertical = 20.dp)
                        )
                    }
                },
            )
            Column(modifier = Modifier.padding(12.dp)) {
                Text(
                    text = name,
                    color = Color.White,
                    style = MaterialTheme.typography.body2.copy(fontSize = 14.sp)
                )
                Text(
                    text = description,
                    color = Color.White,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.body1.copy(fontSize = 12.sp)
                )
            }
        }
    }
}