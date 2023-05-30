package com.cataractaction.ui.components.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.cataractaction.R

@Composable
fun PhotoProfile(navigateToComing: () -> Unit) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .padding(10.dp).clickable { navigateToComing() }
        ) {
            Box(
                modifier = Modifier
                    .clip(
                        RoundedCornerShape(100)
                    )
                    .background(MaterialTheme.colors.secondary)
                    .padding(10.dp),
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.profile_blue),
                    contentDescription = "profile",
                    modifier = Modifier
                        .align(Alignment.Center)
                        .height(30.dp)
                        .width(30.dp),
                )
            }
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(100))
                    .background(Color.White)
                    .align(Alignment.BottomEnd)
                    .padding(2.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.PhotoCamera,
                    contentDescription = "camera",
                    modifier = Modifier
                        .height(10.dp)
                        .width(10.dp)
                )
            }
        }
    }
}