package com.cataractaction.ui.components.cataractcheck

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun DummyCataractAnimation() {
    val composition by rememberLottieComposition(LottieCompositionSpec.Asset("upload.json"))
    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        LottieAnimation(
            composition = composition,
            modifier = Modifier
                .width(250.dp)
                .height(250.dp)
        )
    }

}