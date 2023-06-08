package com.cataractaction.ui.components.pager

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ImagePager(pagerState: PagerState) {
    val composition by rememberLottieComposition(LottieCompositionSpec.Asset("eye_one.json"))
    val composition2 by rememberLottieComposition(LottieCompositionSpec.Asset("eye_two.json"))
    val composition3 by rememberLottieComposition(LottieCompositionSpec.Asset("eye_three.json"))
    val listComposition = listOf(composition, composition2, composition3)
    val list = listOf("1", "2", "3")
    HorizontalPager(
        pageCount = list.size,
        modifier = Modifier.padding(bottom = 60.dp),
        state = pagerState,
        key = { list[it] }) { index ->
        val progress by animateLottieCompositionAsState(
            composition = listComposition[index],
            iterations = LottieConstants.IterateForever
        )
        LottieAnimation(
            composition = listComposition[index],
            progress = { progress },
            modifier = Modifier
                .width(200.dp)
                .height(200.dp)
        )
    }
}