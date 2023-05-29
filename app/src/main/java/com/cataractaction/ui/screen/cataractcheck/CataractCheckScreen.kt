package com.cataractaction.ui.screen.cataractcheck

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.cataractaction.R

@Composable
fun CataractCheckScreen() {
    val composition by rememberLottieComposition(LottieCompositionSpec.Asset("upload.json"))

    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(Modifier.size(32.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.arrow_back),
                contentDescription = "back"
            )
            Text(text = "Cataract Check", style = MaterialTheme.typography.h5)
            Spacer(Modifier.size(0.dp))
        }
        LottieAnimation(
            composition = composition,
            modifier = Modifier
                .width(250.dp)
                .height(250.dp)
                .align(Alignment.CenterHorizontally)
        )
        Spacer(Modifier.size(45.dp))
        Text(
            text = "Select Image from",
            modifier = Modifier.padding(start = 34.dp),
            style = MaterialTheme.typography.body2.copy(fontSize = 16.sp)
        )
        Spacer(Modifier.size(25.dp))
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
                    .clickable { }
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
                    .clickable { }
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
}