package com.cataractaction.ui.screen.camera

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.Preview
import androidx.camera.view.PreviewView
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Lens
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import com.cataractaction.R
import com.cataractaction.ui.common.getCameraProvider
import com.cataractaction.ui.common.rotateBitmap
import com.cataractaction.ui.common.takePhoto
import com.cataractaction.ui.common.uriToFile
import com.cataractaction.ui.navigation.Screen
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun CameraScreen(navHostController: NavHostController) {
    var lensFacing by remember { mutableStateOf(CameraSelector.LENS_FACING_BACK) }
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    val preview = Preview.Builder().build()
    val previewView = remember { PreviewView(context) }
    val imageCapture: ImageCapture = remember { ImageCapture.Builder().build() }
    val cameraSelector = CameraSelector.Builder()
        .requireLensFacing(lensFacing)
        .build()
    var resultUri by remember { mutableStateOf<Uri?>(null) }

    val mediaDir = context.externalCacheDirs.firstOrNull()?.let {
        File(it, context.getString(R.string.app_name)).apply { mkdirs() }
    }

    LaunchedEffect(lensFacing) {
        val cameraProvider = context.getCameraProvider()
        cameraProvider.unbindAll()
        cameraProvider.bindToLifecycle(
            lifecycleOwner,
            cameraSelector,
            preview,
            imageCapture
        )
        preview.setSurfaceProvider(previewView.surfaceProvider)
    }

    fun handleImageCapture(photoFile: File) {
        val os: OutputStream

        val result = rotateBitmap(
            BitmapFactory.decodeFile(photoFile.path),
            lensFacing == CameraSelector.LENS_FACING_BACK
        )

        try {
            os = FileOutputStream(photoFile)
            result.compress(Bitmap.CompressFormat.JPEG, 100, os)
            os.flush()
            os.close()

            resultUri = Uri.fromFile(photoFile)

        } catch (e: Exception) {
            e.printStackTrace()
        }

        navHostController.navigate(Screen.CataractCheck.createRoute(Uri.encode(resultUri.toString()))) {
            popUpTo(Screen.Home.route)
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        AndroidView({ previewView }, modifier = Modifier.fillMaxSize())
        Card(
            modifier = Modifier
                .align(Alignment.Center)
                .height(450.dp)
                .width(250.dp),
            border = BorderStroke(3.dp, MaterialTheme.colors.onError),
            elevation = 0.dp,
            backgroundColor = Color.Transparent
        ) {}
        IconButton(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(end = 10.dp, top = 10.dp),
            onClick = {
                lensFacing = if (lensFacing != CameraSelector.LENS_FACING_BACK) {
                    CameraSelector.LENS_FACING_BACK
                } else {
                    CameraSelector.LENS_FACING_FRONT
                }
            },
            content = {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.switch_camera),
                    contentDescription = "Take picture front",
                    tint = Color.White,
                    modifier = Modifier
                        .size(25.dp)
                        .padding(1.dp)
                        .border(1.dp, Color.White, CircleShape)
                )
            }
        )
        IconButton(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 20.dp),
            onClick = {
                takePhoto(
                    imageCapture = imageCapture,
                    outputDirectory = if (mediaDir != null && mediaDir.exists()) mediaDir else context.filesDir,
                    executor = context.mainExecutor,
                    onImageCaptured = ::handleImageCapture,
                    onError = { Toast.makeText(context, it.toString(), Toast.LENGTH_LONG).show() }
                )
            },
            content = {
                Icon(
                    imageVector = Icons.Sharp.Lens,
                    contentDescription = "Take picture",
                    tint = Color.White,
                    modifier = Modifier
                        .size(100.dp)
                        .padding(1.dp)
                        .border(1.dp, Color.White, CircleShape)
                )
            }
        )
    }
}


