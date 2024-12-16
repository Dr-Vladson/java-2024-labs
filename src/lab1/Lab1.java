package lab1;

public class Lab1 {
    public Lab1() {
    }

    public static void main(String[] var0) {
        try {
            System.out.println("Лаба1 Любашенко Владислав IС-22 Варiант 14");
            int studentId = 14;
            int C5 = studentId % 5;
            int C7 = studentId % 7;
            int C11 = studentId % 11;
            System.out.println("C5 = " + C5 + "; C7 = " + C7 + "; C11 = " + C11);

            double[][] firstMatrix = {
                    {1.5, 2.3434, 3},
                    {4.5677, 0, 6.999}
            };
            double[][] secondMatrix = {
                    {7.6776676, 8.88888},
                    {9.0, 10.1000},
                    {0, 9090}
            };
            double[][] resultMatrix = multiplyMatrices(firstMatrix, secondMatrix);
            System.out.println("Матричний добуток:");
            printMatrix(resultMatrix);

            double sumMaxElements = calculateSumOfMaxElements(resultMatrix);
            System.out.println("Сума найбiльших елементiв кожного рядка матрицi: " + sumMaxElements);
        } catch (Exception e) {
            System.err.println("Помилка: " + e.getMessage());
        }
    }

    public static double[][] multiplyMatrices(double[][] firstMatrix, double[][] secondMatrix) {
        if (firstMatrix == null || secondMatrix == null) {
            throw new IllegalArgumentException("null matrices not allowed");
        }
        for (int i = 1; i < firstMatrix.length; i++) {
            if (firstMatrix[i].length != firstMatrix[0].length) {
                throw new IllegalArgumentException("Rows of the first matrix must have the same length");
            }
        }
        for (int i = 1; i < secondMatrix.length; i++) {
            if (secondMatrix[i].length != secondMatrix[0].length) {
                throw new IllegalArgumentException("Rows of the second matrix must have the same length");
            }
        }
        if (firstMatrix[0].length != secondMatrix.length) {
            throw new IllegalArgumentException("Not suitable matrices sizes for multiplying");
        }

        int rowsFirstMatrix = firstMatrix.length;
        int colsFirstMatrix = firstMatrix[0].length;
        int colsSecondMatrix = secondMatrix[0].length;
        double[][] resultMatrix = new double[rowsFirstMatrix][colsSecondMatrix];
        for (int i = 0; i < rowsFirstMatrix; i++) {
            for (int j = 0; j < colsSecondMatrix; j++) {
                for (int k = 0; k < colsFirstMatrix; k++) {
                    resultMatrix[i][j] += firstMatrix[i][k] * secondMatrix[k][j];
                }
            }
        }
        return resultMatrix;
    }

    public static double calculateSumOfMaxElements(double[][] matrix) {
        if (matrix == null) {
            throw new IllegalArgumentException("Matrix cant be null.");
        }

        double sum = 0;
        for (double[] row : matrix) {
            double max = row[0];
            for (double value : row) {
                if (value > max) {
                    max = value;
                }
            }
            sum += max;
        }
        return sum;
    }

    public static void printMatrix(double[][] matrix) {
        if (matrix == null) {
            throw new IllegalArgumentException("Matrix cant be null.");
        }

        for (double[] row : matrix) {
            for (double value : row) {
                System.out.printf("%8.2f ", value);
            }
            System.out.println();
        }
    }
}

