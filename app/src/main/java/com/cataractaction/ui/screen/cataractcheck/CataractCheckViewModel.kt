package com.cataractaction.ui.screen.cataractcheck

import android.content.Context
import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import com.cataractaction.ml.Cataract
import dagger.hilt.android.lifecycle.HiltViewModel
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import javax.inject.Inject

@HiltViewModel
class CataractCheckViewModel @Inject constructor() : ViewModel() {

    fun objectDetector(context: Context, bitmap: Bitmap): String {

        val image = Bitmap.createScaledBitmap(bitmap, 224, 224, true)

        val tensorImage = TensorImage(DataType.FLOAT32)
        tensorImage.load(image)

        val cataractModel = Cataract.newInstance(context)
        val inputModel = TensorBuffer.createFixedSize(intArrayOf(1, 224, 224, 3), DataType.FLOAT32)

        inputModel.loadBuffer(tensorImage.buffer)

        val outputsPrediction = cataractModel.process(inputModel)
        val resultPrediction = outputsPrediction.outputFeature0AsTensorBuffer.floatArray

        var max = 0
        resultPrediction.forEachIndexed { index, fl ->
            if (resultPrediction[max] < fl) {
                max = index
            }
        }

        cataractModel.close()

        return max.toString()
    }
}