package com.cataractaction.ui.screen.detailarticle

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import com.cataractaction.R

@Composable
fun DetailArticleScreen(
    image: String,
    title: String,
    description: String,
    navHostController: NavHostController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 27.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 23.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.arrow_back),
                contentDescription = "back",
                modifier = Modifier
                    .padding(16.dp)
                    .clickable { navHostController.popBackStack() },
                tint = MaterialTheme.colors.primary
            )
            Text(text = "Detail Article", style = MaterialTheme.typography.h5)
            Icon(
                imageVector = if (true) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                contentDescription = "back",
                modifier = Modifier
                    .padding(16.dp)
                    .clickable { },
                tint = MaterialTheme.colors.primary
            )
        }
        SubcomposeAsyncImage(
            model = image,
            contentDescription = null,
            contentScale = ContentScale.FillHeight,
            modifier = Modifier
                .height(350.dp)
                .fillMaxWidth()
                .padding(8.dp)
                .clip(RoundedCornerShape(20.dp)),
            loading = {
                Box(contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(
                        modifier = Modifier.padding(vertical = 20.dp)
                    )
                }
            },
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = title,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h5.copy(
                    fontWeight = FontWeight.ExtraBold
                ),
            )
            Spacer(Modifier.size(10.dp))
            Text(
                text = description,
                style = MaterialTheme.typography.body2,
                textAlign = TextAlign.Justify
            )
        }
    }
}