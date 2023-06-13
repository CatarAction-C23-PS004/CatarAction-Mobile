package com.cataractaction.ui.screen.cataractresult

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.cataractaction.ui.components.cataractcheck.ImageCataractResult
import com.cataractaction.ui.components.cataractcheck.TextAnalysis
import com.cataractaction.ui.components.cataractcheck.TextDesc
import com.cataractaction.ui.components.cataractcheck.TextDescResult
import com.cataractaction.ui.components.cataractcheck.TextResult
import com.cataractaction.ui.components.cataractcheck.TextResult2
import com.cataractaction.ui.components.cataractcheck.TitleCataractResult

@Composable
fun CataractResultScreen(navHostController: NavHostController, imageUri: String, resultMl: String) {


    Log.d("a", resultMl)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(Modifier.size(32.dp))
        TitleCataractResult { navHostController.popBackStack() }
        ImageCataractResult(imageUri)
        Spacer(Modifier.size(25.dp))
        TextAnalysis()
        Spacer(Modifier.size(25.dp))
        TextResult2(resultMl)
        Spacer(Modifier.size(15.dp))
        TextDescResult(resultMl)
        Spacer(Modifier.size(30.dp))
    }
}