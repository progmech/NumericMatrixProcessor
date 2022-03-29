package processor

fun main() {
    do {
        println("1. Add matrices")
        println("2. Multiply matrix by a constant")
        println("3. Multiply matrices")
        println("0. Exit")
        print("Your choice: ")
        val choice = readln().toInt()
        when (choice) {
            1 -> doMatrixAddition()
            2 -> doMatrixMultiplicationByConstant()
            3 -> doMatrixMultiplication()
        }
    } while (choice != 0)
}

fun doMatrixMultiplication() {
    val matrix1 = fillMatrix(" first")
    val matrix2 = fillMatrix(" second")
    if (matrix1.first().size != matrix2.size) {
        println("The operation cannot be performed.")
        return
    }
    val resultMatrix = multiplyMatrices(matrix1, matrix2)
    printMatrix(resultMatrix)
}

fun multiplyMatrices(m1: Array<Array<Double>>, m2: Array<Array<Double>>): Array<Array<Double>> {
    val resultMatrix = Array(m1.size) {Array(m2.first().size) { 0.0 } }
    for (n in m1.indices) {
        for (k in m2.first().indices) {
            for (m in m1.first().indices) {
                resultMatrix[n][k] += m1[n][m] * m2[m][k]
            }
        }
    }
    return resultMatrix
}

fun doMatrixMultiplicationByConstant() {
    val matrix = fillMatrix("")
    print("Enter constant: ")
    val multiplier = readln().toDouble()
    val resultMatrix = multiplyMatrixByNumber(matrix, multiplier)
    printMatrix(resultMatrix)
}

fun doMatrixAddition() {
    val matrix1 = fillMatrix(" first")
    val matrix2 = fillMatrix(" second")
    if (matrix2.size != matrix1.size || matrix2.first().size != matrix1.first().size) {
        println("The operation cannot be performed.")
        return
    }
    val resultMatrix = sumMatrices(matrix1, matrix2)
    printMatrix(resultMatrix)
}

fun fillMatrix(substitution: String): Array<Array<Double>> {
    print("Enter size of$substitution matrix: ")
    val (rowNum, columnNum) = readln().split(" ").map { it.toInt() }
    val matrix = Array(rowNum) {Array(columnNum) { 0.0 } }
    println("Enter$substitution matrix:")
    for (r in 0 until rowNum) {
        val rowValues = readln().split(" ").map { it.toDouble() }
        for (c in rowValues.indices) {
            matrix[r][c] = rowValues[c]
        }
    }
    return matrix
}

fun sumMatrices (m1: Array<Array<Double>>, m2: Array<Array<Double>>): Array<Array<Double>> {
    val resultMatrix = Array(m1.size) {Array(m1.first().size) { 0.0 } }
    for (r in m1.indices) {
        for (c in m1.first().indices) {
            resultMatrix[r][c] = m1[r][c] + m2[r][c]
        }
    }
    return resultMatrix
}

fun <T> printMatrix(matrix: Array<Array<T>>) {
    println("The result is:")
    for (r in matrix.indices) {
        for (c in matrix.first().indices) {
            print("${matrix[r][c]} ")
        }
        println()
    }
}

fun multiplyMatrixByNumber(matrix: Array<Array<Double>>, multiplier: Double): Array<Array<Double>> {
    val resultMatrix = Array(matrix.size) {Array(matrix.first().size) { 0.0 } }
    for (r in matrix.indices) {
        for (c in matrix.first().indices) {
            resultMatrix[r][c] = matrix[r][c] * multiplier
        }
    }
    return resultMatrix
}