package com.eugenics.tapthecar.ui.screens.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.eugenics.tapthecar.R
import com.eugenics.tapthecar.domain.util.dpPx
import com.eugenics.tapthecar.ui.theme.TapTheCarTheme
import com.eugenics.tapthecar.ui.viewmodels.MainViewModel
import kotlinx.coroutines.launch

@Composable
fun MainScreen(
    viewModel: MainViewModel = MainViewModel()
) {
    val screen = viewModel.getScreen()
    val offset = viewModel.offset.collectAsState()
    val pictureSize = 100.dp
    val x = offset.value.first.dpPx()
    val y = offset.value.second.dpPx()

    val scope = rememberCoroutineScope()

    Scaffold(modifier = Modifier.fillMaxSize()) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues = paddingValues)
                .offset(x = offset.value.first.dp, y = offset.value.second.dp)
                .size(pictureSize)
                .clickable {
                    scope.launch {
                        viewModel.startStop()
                    }
                }
        ) {
            Image(
                modifier = Modifier.fillMaxSize()
                    .padding(10.dp),
                painter = painterResource(R.drawable.taxi),
                contentDescription = null
            )
        }
        Box(
            contentAlignment = Alignment.BottomCenter,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                textAlign = TextAlign.Center,
                text = "size: ${screen.height} x ${screen.wight}; density: ${screen.density}\n" +
                        "position: x{${x}}:y{${y}}",
                style = MaterialTheme.typography.subtitle1,
                modifier = Modifier.fillMaxWidth()
                    .alpha(0.25F)
            )
        }
    }
}


@Preview
@Composable
private fun MainScreenPreview() {
    TapTheCarTheme {
        MainScreen()
    }
}