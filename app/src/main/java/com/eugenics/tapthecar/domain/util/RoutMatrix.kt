package com.eugenics.tapthecar.domain.util

import java.util.*

class RoutMatrix {
    private var matrix: Array<Array<Int>>? = null

    fun generateRoute(height: Int = 0, weight: Int = 0): Array<Array<Int>> {
        matrix = Array(height) { Array(weight) { j -> 0 } }
        matrix?.let { rout ->
            //first element = 1
            rout[0][0] = 1

            var curRowIndex = 0
            var curColIndex = 0
            val randomList = listOf(1, 1, -1, 1, 0, 1, -1, 1, 1, -1, 1)

            for (i in 1 until rout.size) {
                curRowIndex = i
                var random = randomList.random()
                if (curColIndex + random < 0 || curColIndex + random > rout[i].size - 1) {
                    random = 0
                }
                println("row:$curRowIndex; col:$curColIndex; random:$random")
                curColIndex += random
                rout[curRowIndex][curColIndex] = 1
            }
            while (curColIndex != rout[curRowIndex].size - 1) {
                curColIndex++
                rout[curRowIndex][curColIndex] = 1
            }
        }
        return matrix ?: emptyArray()
    }

    fun printMatrix() {
        matrix?.let { arr ->
            for (i in arr) {
                println(Arrays.toString(i))
            }
        }
    }
}