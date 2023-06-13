package com.cataractaction.ui.components.cataractcheck

import android.net.Uri
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.compose.rememberAsyncImagePainter
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun ImageCataract(uri: Uri?) {
    val composition by rememberLottieComposition(LottieCompositionSpec.Asset("upload.json"))
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = LottieConstants.IterateForever
    )
    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        if (uri != null) {
            Image(
                painter = rememberAsyncImagePainter(model = uri),
                contentDescription = "image cataract",
                modifier = Modifier
                    .width(350.dp)
                    .height(350.dp)
                    .padding(top = 30.dp),
                contentScale = ContentScale.Fit
            )
        } else {
            LottieAnimation(
                composition = composition,
                progress = { progress },
                modifier = Modifier
                    .width(250.dp)
                    .height(250.dp)
            )
        }
    }
}

@Composable
fun ImageCataractResult(uri: String?) {
    val composition by rememberLottieComposition(LottieCompositionSpec.Asset("upload.json"))
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = LottieConstants.IterateForever
    )
    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        if (uri != null) {
            SubcomposeAsyncImage(
                model = uri.replace("/images/", "/images%2F"),
                contentDescription = "image cataract",
                modifier = Modifier
                    .width(350.dp)
                    .height(350.dp)
                    .padding(top = 30.dp),
                contentScale = ContentScale.Fit,
                loading = {
                    Box(contentAlignment = Alignment.Center) {
                        CircularProgressIndicator(
                            modifier = Modifier.padding(vertical = 20.dp)
                        )
                    }
                },
            )
        } else {
            LottieAnimation(
                composition = composition,
                progress = { progress },
                modifier = Modifier
                    .width(250.dp)
                    .height(250.dp)
            )
        }
    }

}