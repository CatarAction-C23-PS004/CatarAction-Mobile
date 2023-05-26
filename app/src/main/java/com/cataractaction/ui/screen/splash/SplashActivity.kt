package com.cataractaction.ui.screen.splash

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.cataractaction.MainActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        determineUserDirection()
    }

    private fun determineUserDirection() {
        lifecycleScope.launch {
            delay(2000)
            Intent(this@SplashActivity, MainActivity::class.java).also { intent ->
                startActivity(intent)
                finish()
            }
        }
    }
}