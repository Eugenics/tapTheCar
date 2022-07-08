package com.eugenics.tapthecar.domain.util

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.random.Random

class Generator {
    private val random = Random(1)

    suspend fun generate(): Flow<Pair<Int, Int>> =
        flow {
            while (true) {
                emit(
                    Pair(
                        random.nextInt(from = 0, until = 2),
                        random.nextInt(from = 0, until = 2)
                    )
                )
                delay(5)
            }
        }
}