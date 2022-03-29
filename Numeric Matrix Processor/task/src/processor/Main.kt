package processor

fun main() {
    val (rowNum1, columnNum1) = readln().split(" ").map { it.toInt() }
    val matrix1 = fillMatrix(rowNum1, columnNum1)
    val (rowNum2, columnNum2) = readln().split(" ").map { it.toInt() }
    if (rowNum2 != rowNum1 || columnNum2 != columnNum1) {
        println("ERROR")
        return
    }

    val matrix2 = fillMatrix(rowNum2, columnNum2)
    val resultMatrix = sumMatrix(matrix1, matrix2)
    printMatrix(resultMatrix)
}

fun printMatrix(matrix: Array<Array<Int>>) {
    for (r in matrix.indices) {
        for (c in matrix.first().indices) {
            print("${matrix[r][c]} ")
        }
        println()
    }
}

fun fillMatrix(rowNum: Int, columnNum: Int): Array<Array<Int>> {
    val matrix = Array(rowNum) {Array(columnNum) { 0 } }
    for (r in 0 until rowNum) {
        val rowValues = readln().split(" ").map { it.toInt() }
        for (c in rowValues.indices) {
            matrix[r][c] = rowValues[c]
        }
    }
    return matrix
}

fun sumMatrix (m1: Array<Array<Int>>, m2: Array<Array<Int>>): Array<Array<Int>> {
    val resultMatrix = Array(m1.size) {Array(m1.first().size) { 0 } }
    for (r in m1.indices) {
        for (c in m1.first().indices) {
            resultMatrix[r][c] = m1[r][c] + m2[r][c]
        }
    }
    return resultMatrix
}
