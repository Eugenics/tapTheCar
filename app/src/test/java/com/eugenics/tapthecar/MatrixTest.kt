package com.eugenics.tapthecar

import com.eugenics.tapthecar.domain.util.RoutMatrix
import org.junit.Test

class MatrixTest {
    val matrix = RoutMatrix()

    @Test
    fun testPrintMatrixFunc() {
        matrix.printMatrix()
    }

    @Test
    fun testGenerateMatrixFunc() {
        matrix.generateRoute(height = 1280, weight = 720)
        matrix.printMatrix()
    }
}