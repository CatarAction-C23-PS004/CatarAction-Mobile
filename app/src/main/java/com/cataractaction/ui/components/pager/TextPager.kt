package com.cataractaction.ui.components.pager

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.cataractaction.R

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TextPager(pagerState: PagerState) {
    when (pagerState.currentPage) {
        0 -> Column(Modifier.padding(horizontal = 20.dp)) {
            Text(
                text = stringResource(R.string.page_1_title),
                style = MaterialTheme.typography.h5,
                modifier = Modifier.padding(bottom = 24.dp)
            )
            Text(
                text = stringResource(R.string.page_1_body),
                style = MaterialTheme.typography.body1
            )
        }

        1 -> Column(Modifier.padding(horizontal = 20.dp)) {
            Text(
                text = stringResource(R.string.page_2_title),
                style = MaterialTheme.typography.h5,
                modifier = Modifier.padding(bottom = 24.dp)
            )
            Text(
                text = stringResource(R.string.page_2_body),
                style = MaterialTheme.typography.body1
            )
        }

        2 -> Column(Modifier.padding(horizontal = 20.dp)) {
            Text(
                text = stringResource(R.string.page_3_title),
                style = MaterialTheme.typography.h5,
                modifier = Modifier.padding(bottom = 24.dp)
            )
            Text(
                text = stringResource(R.string.page_3_body),
                style = MaterialTheme.typography.body1
            )
        }
    }
}