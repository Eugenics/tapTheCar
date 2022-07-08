package com.eugenics.tapthecar

import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.WindowInsets
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import com.eugenics.tapthecar.domain.model.Screen
import com.eugenics.tapthecar.ui.screens.main.MainScreen
import com.eugenics.tapthecar.ui.theme.TapTheCarTheme
import com.eugenics.tapthecar.ui.viewmodels.MainViewModel

@RequiresApi(Build.VERSION_CODES.R)
class MainActivity : ComponentActivity() {

    private val viewModel = MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val orientation = when {
            this.resources.displayMetrics.heightPixels >
                    this.resources.displayMetrics.widthPixels -> 0
            else -> 1
        }

        val windowMetrics = this.windowManager.currentWindowMetrics
        val inserts = windowMetrics.windowInsets.getInsets(WindowInsets.Type.systemBars())
        val windowHeight =
            this.resources.displayMetrics.heightPixels - inserts.top - inserts.bottom -
                    if (orientation == 0) 0 else 150
        val windowWight =
            this.resources.displayMetrics.widthPixels - inserts.left - inserts.right -
                    if (orientation == 1) 100 else 200
        val density = this.resources.displayMetrics.densityDpi.toFloat()
        val screen = Screen(height = windowHeight, wight = windowWight, density = density)
        viewModel.setScreen(screen = screen)

        setContent {
            TapTheCarTheme(darkTheme = true) {
                MainScreen(viewModel = viewModel)
            }
        }
    }
}