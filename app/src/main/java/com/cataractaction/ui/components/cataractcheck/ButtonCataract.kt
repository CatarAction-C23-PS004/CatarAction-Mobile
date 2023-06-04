package com.cataractaction.ui.components.cataractcheck

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DeleteOutline
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cataractaction.R

@Composable
fun ButtonImage(startGallery: () -> Unit, startCamera: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 55.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        val stroke = Stroke(
            width = 2f,
            pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
        )
        val primaryColor = MaterialTheme.colors.primary
        Box(
            Modifier
                .clickable { startCamera() }
                .drawBehind {
                    drawRoundRect(
                        color = primaryColor,
                        style = stroke,
                        cornerRadius = CornerRadius(8.dp.toPx())
                    )
                }
                .padding(20.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.PhotoCamera,
                    contentDescription = "camera",
                    tint = primaryColor
                )
                Spacer(Modifier.size(10.dp))
                Text(text = "Camera", color = primaryColor)
            }
        }
        Box(
            Modifier
                .clickable { startGallery() }
                .drawBehind {
                    drawRoundRect(
                        color = primaryColor,
                        style = stroke,
                        cornerRadius = CornerRadius(8.dp.toPx())
                    )
                }
                .padding(20.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.gallery),
                    contentDescription = "gallery",
                    tint = primaryColor
                )
                Spacer(Modifier.size(10.dp))
                Text(text = "Gallery", color = primaryColor)
            }
        }
    }
}

@Composable
fun ButtonDeleteImage(deleteImage: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 55.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        IconButton(onClick = { deleteImage() }) {
            Icon(
                imageVector = Icons.Filled.DeleteOutline,
                contentDescription = "delete",
                tint = MaterialTheme.colors.onError
            )
        }
        Text(
            text = "Delete Image",
            style = MaterialTheme.typography.body1.copy(fontSize = 12.sp),
            color = MaterialTheme.colors.onError
        )
    }
}

@Composable
fun ButtonLogout() {
    Button(
        onClick = { /*TODO*/ }, modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 40.dp), shape = RoundedCornerShape(60.dp)
    ) {
        Text(
            text = "Upload Image",
            style = MaterialTheme.typography.body2,
            modifier = Modifier.padding(5.dp)
        )
    }
}