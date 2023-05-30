package com.cataractaction.ui.screen.coming

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.cataractaction.R

@Composable
fun ComingScreen(navigateBack: () -> Unit) {
    val composition by rememberLottieComposition(LottieCompositionSpec.Asset("coming_soon.json"))
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        IconButton(onClick = {navigateBack()}) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.arrow_back),
                contentDescription = "back",
                tint = MaterialTheme.colors.primary,
                modifier = Modifier.padding(top = 27.dp, start = 32.dp)
            )
        }
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            LottieAnimation(
                composition = composition,
                modifier = Modifier
                    .width(250.dp)
                    .height(250.dp)
            )
        }
    }
}