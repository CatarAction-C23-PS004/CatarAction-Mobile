package com.cataractaction.ui.screen.article

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.cataractaction.ui.components.article.LogoTitleArticle
import com.cataractaction.ui.components.article.SearchField
import com.cataractaction.ui.components.article.TextBar
import com.cataractaction.ui.components.main.CardArticle
import com.cataractaction.ui.navigation.Screen

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ArticleScreen(navHostController: NavHostController) {
    val list = listOf("1", "2", "3")
    val pagerState = rememberPagerState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 27.dp)
    ) {
        LogoTitleArticle { navHostController.navigate(Screen.Coming.route) }
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