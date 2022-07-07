package com.eugenics.tapthecar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.eugenics.tapthecar.ui.theme.TapTheCarTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TapTheCarTheme {
            }
        }
    }
}