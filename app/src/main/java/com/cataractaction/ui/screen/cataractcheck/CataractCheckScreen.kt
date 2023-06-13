package com.cataractaction.ui.screen.cataractcheck

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.cataractaction.core.domain.model.RealtimeDBHistory
import com.cataractaction.ui.common.uriToFile
import com.cataractaction.ui.components.cataractcheck.ButtonHistory
import com.cataractaction.ui.components.cataractcheck.ButtonImage
import com.cataractaction.ui.components.cataractcheck.ImageCataract
import com.cataractaction.ui.components.cataractcheck.TextAnalysis
import com.cataractaction.ui.components.cataractcheck.TextCataract
import com.cataractaction.ui.components.cataractcheck.TextDesc
import com.cataractaction.ui.components.cataractcheck.TextResult
import com.cataractaction.ui.components.cataractcheck.TitleCataract
import com.cataractaction.ui.navigation.Screen
import com.cataractaction.ui.screen.viewmodel.RealtimeDbViewModel
import java.io.File
import java.io.FileInputStream
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun CataractCheckScreen(
    navHostController: NavHostController,
    uriFile: Uri?,
    viewModelCataract: CataractCheckViewModel = hiltViewModel(),
    viewModelRealtimeDb: RealtimeDbViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    var getFile by remember { mutableStateOf<File?>(null) }
    var resultMl by remember { mutableStateOf("Result here") }

    var shouldShowCamera by remember { mutableStateOf(false) }

    val state by viewModelRealtimeDb.stateHistory.collectAsState()

    fun objectDetector() {
        val inputStream = FileInputStream(getFile)
        val fileBitmap = BitmapFactory.decodeStream(inputStream)
        val result = viewModelCataract.objectDetector(context, fileBitmap)
        resultMl = result
    }

    LaunchedEffect(key1 = Unit) {
        if (uriFile != Uri.parse("{uriFile}")) {
            imageUri = uriFile
            getFile = uriToFile(uriFile!!, context)
        }
    }

    LaunchedEffect(key1 = getFile) {
        if (getFile != null) {
            objectDetector()
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

//    fun deleteImage() {
//        imageUri = null
//        getFile = null
//        resultMl = "Result here"
//    }


    fun getCurrentDateFormatted(): String {
        val currentDate = Date()
        val dateFormat = SimpleDateFormat("dd MMM, HH:mm", Locale.ENGLISH)
        return dateFormat.format(currentDate)
    }

    Box(modifier = Modifier.fillMaxSize()) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(Modifier.size(32.dp))
            TitleCataract { navHostController.popBackStack() }
            ImageCataract(imageUri)
            Spacer(Modifier.size(25.dp))
            if (imageUri != null) TextAnalysis() else TextCataract()
            Spacer(Modifier.size(25.dp))
            if (imageUri != null) TextResult(resultMl) else ButtonImage(
                startGallery = { startGallery() },
                startCamera = { requestCameraPermission() })
            Spacer(Modifier.size(if (imageUri != null) 15.dp else 35.dp))
            if (imageUri != null) TextDesc(resultMl)
//        if (imageUri != null) ButtonDeleteImage(deleteImage = { deleteImage() })
            if (imageUri != null) Spacer(Modifier.size(40.dp))
            if (imageUri != null) ButtonHistory {
                viewModelRealtimeDb.createHistory(
                    onClick = { navHostController.popBackStack() },
                    getFile!!,
                    RealtimeDBHistory(
                        result = if (resultMl == "1") "Cataract" else "Normal",
                        date = getCurrentDateFormatted()
                    ), context
                )
            }
            Spacer(Modifier.size(30.dp))
        }
        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }

}