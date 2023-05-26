package com.cataractaction.ui.components.article

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TextBar(pagerState: PagerState) {
    val scope = rememberCoroutineScope()
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column {
            Text(
                text = "Recommended",
                style = MaterialTheme.typography.body1.copy(fontSize = 12.sp),
                color = if (pagerState.currentPage == 0) MaterialTheme.colors.primary else MaterialTheme.colors.primaryVariant,
                modifier = Modifier.clickable {
                    scope.launch {
                        pagerState.animateScrollToPage(0)
                    }
                }
            )
            if (pagerState.currentPage == 0) {
                Spacer(Modifier.size(4.dp))
                Box(
                    modifier = Modifier
                        .clip(
                            RoundedCornerShape(100)
                        )
                        .background(MaterialTheme.colors.primary)
                        .padding(3.dp)
                        .align(Alignment.CenterHorizontally)
                )
            }
        }
        Column {
            Text(
                text = "Latest",
                style = MaterialTheme.typography.body1.copy(fontSize = 12.sp),
                color = if (pagerState.currentPage == 1) MaterialTheme.colors.primary else MaterialTheme.colors.primaryVariant,
                modifier = Modifier.clickable {
                    scope.launch {
                        pagerState.animateScrollToPage(1)
                    }
                }
            )
            if (pagerState.currentPage == 1) {
                Spacer(Modifier.size(4.dp))
                Box(
                    modifier = Modifier
                        .clip(
                            RoundedCornerShape(100)
                        )
                        .background(MaterialTheme.colors.primary)
                        .padding(3.dp)
                        .align(Alignment.CenterHorizontally)
                )
            }
        }
        Column {
            Text(
                text = "Trending",
                style = MaterialTheme.typography.body1.copy(fontSize = 12.sp),
                color = if (pagerState.currentPage == 2) MaterialTheme.colors.primary else MaterialTheme.colors.primaryVariant,
                modifier = Modifier.clickable {
                    scope.launch {
                        pagerState.animateScrollToPage(2)
                    }
                }
            )
            if (pagerState.currentPage == 2) {
                Spacer(Modifier.size(4.dp))
                Box(
                    modifier = Modifier
                        .clip(
                            RoundedCornerShape(100)
                        )
                        .background(MaterialTheme.colors.primary)
                        .padding(3.dp)
                        .align(Alignment.CenterHorizontally)
                )
            }
        }
    }
}