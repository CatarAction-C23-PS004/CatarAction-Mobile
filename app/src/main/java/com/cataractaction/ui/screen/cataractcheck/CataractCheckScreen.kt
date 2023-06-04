package com.cataractaction.ui.screen.cataractcheck

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import com.cataractaction.ui.common.uriToFile
import com.cataractaction.ui.components.cataractcheck.ButtonDeleteImage
import com.cataractaction.ui.components.cataractcheck.ButtonImage
import com.cataractaction.ui.components.cataractcheck.ButtonLogout
import com.cataractaction.ui.components.cataractcheck.ImageCataract
import com.cataractaction.ui.components.cataractcheck.TextCataract
import com.cataractaction.ui.components.cataractcheck.TitleCataract
import com.cataractaction.ui.navigation.Screen
import java.io.File

@Composable
fun CataractCheckScreen(navHostController: NavHostController, uriFile: Uri?) {
    val context = LocalContext.current
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    var getFile by remember { mutableStateOf<File?>(null) }

    var shouldShowCamera by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = Unit) {
        if (uriFile != Uri.parse("{uriFile}")) {
            imageUri = uriFile
        }
    }

    val launcherIntentGallery = rememberLauncherForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == AppCompatActivity.RESULT_OK) {
            val selectedImg: Uri = result.data?.data as Uri
            val myFile = uriToFile(selectedImg, context)
            getFile = myFile
            imageUri = selectedImg
        }
    }

    fun startGallery() {
        val intent = Intent()
        intent.action = Intent.ACTION_GET_CONTENT
        intent.type = "image/*"
        val chooser = Intent.createChooser(intent, "Choose a Picture")
        launcherIntentGallery.launch(chooser)
    }

    val requestPermissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            shouldShowCamera = true
            Toast.makeText(context, "Permission granted", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(context, "Permission denied", Toast.LENGTH_LONG).show()
        }
    }

    fun requestCameraPermission() {
        when {
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED -> {
                shouldShowCamera = true
            }

            ActivityCompat.shouldShowRequestPermissionRationale(
                context as Activity,
                Manifest.permission.CAMERA
            ) -> Toast.makeText(context, "Show camera permission", Toast.LENGTH_LONG).show()

            else -> requestPermissionLauncher.launch(
                Manifest.permission.CAMERA
            )
        }
    }

    LaunchedEffect(key1 = shouldShowCamera) {
        if (shouldShowCamera) {
            navHostController.navigate(Screen.Camera.route)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(Modifier.size(32.dp))
        TitleCataract { navHostController.popBackStack() }
        ImageCataract(imageUri)
        Spacer(Modifier.size(45.dp))
        TextCataract()
        Spacer(Modifier.size(25.dp))
        ButtonImage(startGallery = { startGallery() }, startCamera = { requestCameraPermission() })
        Spacer(Modifier.size(if (imageUri != null) 15.dp else 35.dp))
        if (imageUri != null) ButtonDeleteImage(deleteImage = { imageUri = null })
        Spacer(Modifier.size(if (imageUri != null) 15.dp else 35.dp))
        ButtonLogout()
        Spacer(Modifier.size(30.dp))
    }
}