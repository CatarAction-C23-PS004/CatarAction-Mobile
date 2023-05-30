package com.cataractaction.ui.screen.cataractcheck

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cataractaction.ui.components.cataractcheck.ButtonDeleteImage
import com.cataractaction.ui.components.cataractcheck.ButtonImage
import com.cataractaction.ui.components.cataractcheck.ButtonLogout
import com.cataractaction.ui.components.cataractcheck.DummyCataractAnimation
import com.cataractaction.ui.components.cataractcheck.TextCataract
import com.cataractaction.ui.components.cataractcheck.TitleCataract

@Composable
fun CataractCheckScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(Modifier.size(32.dp))
        TitleCataract()
        DummyCataractAnimation()
        Spacer(Modifier.size(45.dp))
        TextCataract()
        Spacer(Modifier.size(25.dp))
        ButtonImage()
        Spacer(Modifier.size(35.dp))
//        ButtonDeleteImage()
        Spacer(Modifier.size(65.dp))
        ButtonLogout()
    }
}