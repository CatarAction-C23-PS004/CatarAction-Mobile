package com.cataractaction.ui.screen.article

import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.cataractaction.ui.components.article.LogoTitleArticle
import com.cataractaction.ui.components.article.SearchField
import com.cataractaction.ui.components.article.TextBar
import com.cataractaction.ui.components.main.CardArticle
import com.cataractaction.ui.navigation.Screen
import com.cataractaction.ui.screen.viewmodel.RealtimeDbViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ArticleScreen(
    navHostController: NavHostController, viewModelRealtimeDb: RealtimeDbViewModel = hiltViewModel()
) {
    val list = listOf("1", "2", "3")
    val pagerState = rememberPagerState()
    val context = LocalContext.current
    var query by remember { mutableStateOf("") }

    val stateArticle by viewModelRealtimeDb.stateArticle.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 27.dp)
    ) {
        LogoTitleArticle { navHostController.navigate(Screen.Coming.route) }
        Spacer(Modifier.size(15.dp))
        SearchField(query) { query = it }
        Spacer(Modifier.size(21.dp))
        TextBar(pagerState)
        Spacer(Modifier.size(18.dp))
        if (stateArticle.isLoading) {
            Spacer(Modifier.size(20.dp))
            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
        } else if (stateArticle.data != null) {
            HorizontalPager(
                pageCount = list.size,
                state = pagerState,
                key = { list[it] },
                pageSpacing = 20.dp
            ) { article ->
                if (article == 0) {
                    val articleRecommended =
                        stateArticle.data!!.filter { it?.category == "recommended" }
                            .filter { it?.title!!.contains(query, ignoreCase = true) }
                    LazyColumn {
                        items(articleRecommended) { data ->
                            CardArticle(
                                data?.title!!,
                                data.description!!,
                                data.image!!
                            ) {
                                navHostController.navigate(
                                    Screen.DetailArticle.createRoute(
                                        Uri.encode(data.image!!),
                                        data.title!!,
                                        data.description!!
                                    )
                                )
                            }
                            Spacer(Modifier.size(24.dp))
                        }
                    }
                    if (articleRecommended.isEmpty()) {
                        Spacer(Modifier.size(50.dp))
                        Text(text = "Not Found :(")
                    }
                }
                if (article == 1) {
                    val articleLatest = stateArticle.data!!.filter { it?.category == "latest" }
                        .filter { it?.title!!.contains(query, ignoreCase = true) }
                    LazyColumn {
                        items(articleLatest) { data ->
                            CardArticle(
                                data?.title!!,
                                data.description!!,
                                data.image!!
                            ) {
                                navHostController.navigate(
                                    Screen.DetailArticle.createRoute(
                                        Uri.encode(data.image!!),
                                        data.title!!,
                                        data.description!!
                                    )
                                )
                            }
                            Spacer(Modifier.size(24.dp))
                        }
                    }
                }
                if (article == 2) {
                    val articleTrending = stateArticle.data!!.filter { it?.category == "trending" }
                        .filter { it?.title!!.contains(query, ignoreCase = true) }
                    LazyColumn {
                        items(articleTrending) { data ->
                            CardArticle(
                                data?.title!!,
                                data.description!!,
                                data.image!!
                            ) {
                                navHostController.navigate(
                                    Screen.DetailArticle.createRoute(
                                        Uri.encode(data.image!!),
                                        data.title!!,
                                        data.description!!
                                    )
                                )
                            }
                            Spacer(Modifier.size(24.dp))
                        }
                    }
                }
            }
        } else {
            Toast.makeText(context, stateArticle.errorMsg, Toast.LENGTH_LONG).show()
        }
    }
}