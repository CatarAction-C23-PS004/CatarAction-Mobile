package com.cataractaction.ui.screen.profile

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Camera
import androidx.compose.material.icons.filled.CameraFront
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cataractaction.R
import com.cataractaction.ui.components.profile.ButtonLogout
import com.cataractaction.ui.components.profile.ButtonProfile

@Composable
fun ProfileScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(Modifier.size(23.dp))
        Text(
            text = "Profile",
            style = MaterialTheme.typography.h5,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(Modifier.size(33.dp))
        Box(
            modifier = Modifier
                .padding(10.dp)
                .align(Alignment.CenterHorizontally),
        ) {
            Box(
                modifier = Modifier
                    .clip(
                        RoundedCornerShape(100)
                    )
                    .background(MaterialTheme.colors.secondary)
                    .padding(10.dp),
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.profile_blue),
                    contentDescription = "profile",
                    modifier = Modifier
                        .align(Alignment.Center)
                        .height(30.dp)
                        .width(30.dp),
                )
            }
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(100))
                    .background(Color.White)
                    .align(Alignment.BottomEnd)
                    .padding(2.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.PhotoCamera,
                    contentDescription = "camera",
                    modifier = Modifier
                        .height(10.dp)
                        .width(10.dp)
                )
            }
        }
        Spacer(Modifier.size(10.dp))
        Text(
            text = "Cupa Alexander Lukesky",
            style = MaterialTheme.typography.body1,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Text(
            text = "@cupacupalukeskyy@gmail.com",
            style = MaterialTheme.typography.body1.copy(fontSize = 12.sp),
            modifier = Modifier.align(Alignment.CenterHorizontally),
            color = MaterialTheme.colors.primaryVariant
        )
        Spacer(Modifier.size(40.dp))
        ButtonProfile()
        Spacer(Modifier.size(15.dp))
        ButtonProfile()
        Spacer(Modifier.size(15.dp))
        ButtonProfile()
        Spacer(Modifier.size(15.dp))
        ButtonProfile()
        Spacer(Modifier.size(40.dp))
        ButtonLogout()
    }
}