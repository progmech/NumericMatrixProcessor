package processor

fun main() {
    do {
        println("1. Add matrices")
        println("2. Multiply matrix by a constant")
        println("3. Multiply matrices")
        println("4. Transpose matrix")
        println("5. Calculate a determinant")
        println("0. Exit")
        print("Your choice: ")
        val choice = readln().toInt()
        when (choice) {
            1 -> doMatrixAddition()
            2 -> doMatrixMultiplicationByConstant()
            3 -> doMatrixMultiplication()
            4 -> doMatrixTranspose()
            5 -> doDeterminant()
        }
    } while (choice != 0)
}

fun doDeterminant() {
    val matrix = fillMatrix("")
    println("The result is:\n${calculateDeterminant(matrix, matrix.size, matrix.size)}")
}

fun calculateDeterminant(matrix: Array<Array<Double>>, initialSize:Int, currentSize: Int): Double {
    if (currentSize == 1) {
        return matrix[0][0]
    }

    var sign = 1
    var determinant = 0.0
    val minorMatrix = Array(initialSize) {Array(initialSize) { 0.0 } }

    for (exColumn in 0 until currentSize) {
        fillMinorMatrix(matrix, minorMatrix, 0, exColumn, currentSize)
        determinant += sign * matrix[0][exColumn] * calculateDeterminant(minorMatrix, initialSize, currentSize - 1)
        sign = -sign
    }

    return determinant
}

fun fillMinorMatrix(matrix: Array<Array<Double>>, minorMatrix: Array<Array<Double>>, exRow: Int, exColumn: Int, currentSize: Int) {
    var i = 0
    var j = 0
    for (row in 0 until currentSize) {
        for (column in 0 until currentSize) {
            if (row != exRow && column != exColumn) {
                minorMatrix[i][j++] = matrix[row][column]
                if (j == currentSize - 1) {
                    j = 0
                    i++
                }
            }
        }
    }
}

fun doMatrixTranspose() {
    println("1. Main diagonal")
    println("2. Side diagonal")
    println("3. Vertical line")
    println("4. Horizontal line")
    print("Your choice: ")
    val choice = readln().toInt()
    val matrix = fillMatrix("")
    when (choice) {
        1 -> transposeMain(matrix)
        2 -> transposeSide(matrix)
        3 -> transposeVertical(matrix)
        4 -> transposeHorizontal(matrix)
        else -> println("Incorrect choice.")
    }
}

fun transposeHorizontal(matrix: Array<Array<Double>>) {
    val resultMatrix = Array(matrix.size) {Array(matrix.first().size) { 0.0 } }
    val maxRow = resultMatrix.lastIndex
    for (r in matrix.indices) {
        for (c in matrix.first().indices) {
            resultMatrix[maxRow - r][c] = matrix[r][c]
        }
    }
    printMatrix(resultMatrix)
}

fun transposeVertical(matrix: Array<Array<Double>>) {
    val resultMatrix = Array(matrix.size) {Array(matrix.first().size) { 0.0 } }
    val maxColumn = resultMatrix.first().lastIndex
    for (r in matrix.indices) {
        for (c in matrix.first().indices) {
            resultMatrix[r][maxColumn - c] = matrix[r][c]
        }
    }
    printMatrix(resultMatrix)
}

fun transposeSide(matrix: Array<Array<Double>>) {
    val resultMatrix = Array(matrix.first().size) {Array(matrix.size) { 0.0 } }
    val maxRow = resultMatrix.lastIndex
    val maxColumn = resultMatrix.first().lastIndex
    for (r in matrix.indices) {
        for (c in matrix.first().indices) {
            resultMatrix[maxColumn - c][maxRow - r] = matrix[r][c]
        }
    }
    printMatrix(resultMatrix)
}

fun transposeMain(matrix: Array<Array<Double>>){
    val resultMatrix = Array(matrix.first().size) {Array(matrix.size) { 0.0 } }
    for (r in matrix.indices) {
        for (c in matrix.first().indices) {
            resultMatrix[c][r] = matrix[r][c]
        }
    }
    printMatrix(resultMatrix)
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