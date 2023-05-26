package com.cataractaction.ui.components.pager

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DotPager(pagerState: PagerState) {
    Row(
        modifier = Modifier
            .padding(horizontal = 20.dp)
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(100))
                .background(if (pagerState.currentPage == 0) MaterialTheme.colors.primary else MaterialTheme.colors.secondary)
                .padding(6.dp)
                .width(if (pagerState.currentPage == 0) 20.dp else 0.dp)

        )
        Spacer(Modifier.size(11.dp))
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(100))
                .background(if (pagerState.currentPage == 1) MaterialTheme.colors.primary else MaterialTheme.colors.secondary)
                .padding(6.dp)
                .width(if (pagerState.currentPage == 1) 20.dp else 0.dp)

        )
        Spacer(Modifier.size(11.dp))
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(100))
                .background(if (pagerState.currentPage == 2) MaterialTheme.colors.primary else MaterialTheme.colors.secondary)
                .padding(6.dp)
                .width(if (pagerState.currentPage == 2) 20.dp else 0.dp)

        )
    }
}