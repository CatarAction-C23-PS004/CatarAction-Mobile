package com.cataractaction.ui.screen.article

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cataractaction.ui.components.article.LogoTitleArticle
import com.cataractaction.ui.components.article.SearchField
import com.cataractaction.ui.components.article.TextBar
import com.cataractaction.ui.components.auth.LogoTitle
import com.cataractaction.ui.components.main.CardArticle

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ArticleScreen(navigateToComing: () -> Unit) {
    val list = listOf("1", "2", "3")
    val pagerState = rememberPagerState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 27.dp)
    ) {
        LogoTitleArticle(navigateToComing)
        Spacer(Modifier.size(20.dp))
        SearchField()
        Spacer(Modifier.size(21.dp))
        TextBar(pagerState)
        Spacer(Modifier.size(18.dp))
        HorizontalPager(
            pageCount = list.size,
            state = pagerState,
            key = { list[it] },
            pageSpacing = 20.dp
        ) {
            LazyColumn {
                items(list.size, key = { it }) {
                    CardArticle()
                    Spacer(Modifier.size(24.dp))
                }
            }
        }
    }
}