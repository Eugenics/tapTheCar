package com.eugenics.tapthecar.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eugenics.tapthecar.domain.model.Screen
import com.eugenics.tapthecar.domain.util.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class MainViewModel() : ViewModel() {
    private val _status: MutableStateFlow<StartStopStatus> = MutableStateFlow(StartStopStatus.STOP)
    private val status: StateFlow<StartStopStatus> = _status

    private val _offset: MutableStateFlow<Pair<Int, Int>> = MutableStateFlow(Pair(0, 0))
    val offset: StateFlow<Pair<Int, Int>> = _offset

    private var screen = Screen(0, 0, 0F)

    fun startStop() {
        _status.value =
            if (status.value == StartStopStatus.START)
                StartStopStatus.STOP
            else
                StartStopStatus.START
    }

    fun setScreen(screen: Screen) {
        this.screen = screen
        viewModelScope.launch(Dispatchers.IO) {
            generateRoute().collect {
                _offset.value = it
            }
        }
    }

    fun getScreen() = this.screen

    private suspend fun generateRoute(): Flow<Pair<Int, Int>> = flow {
        val matrix = RoutMatrix()
        val route = matrix.generateRoute(
            height = convertPixelsToDp(screen.height.toFloat()).toInt(),
            weight = convertPixelsToDp(screen.wight.toFloat()).toInt()
        )
        var curColIndex = 0

        while (true) {
            if (status.value == StartStopStatus.START) {
                for ((i, row) in route.withIndex()) {
                    while (row[curColIndex] != 1) {
                        curColIndex++
                    }
                    emit(Pair(curColIndex, i))
                    curColIndex = 0
                    delay(10)
                }
                _status.value = StartStopStatus.STOP
            }
        }
    }
}