package ru.javaops.masterjava.matrix;

import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * gkislin
 * 03.07.2016
 */
public class MatrixUtil {

    // TODO implement parallel multiplication matrixA*matrixB
    public static int[][] concurrentMultiply(int[][] matrixA, int[][] matrixB, ExecutorService executor) throws InterruptedException, ExecutionException {
        final int matrixSize = matrixA.length;
        final int threadCount = Runtime.getRuntime().availableProcessors();
        final int[][] matrixC = new int[matrixSize][matrixSize];
        int kol = matrixSize / threadCount;

        List<Callable<int[][]>> list = Stream.iterate(0, n -> n + 1)
                .limit(threadCount)
                .map(n -> {
                    Callable<int[][]> callable = () -> concurrentThreadMultiply(matrixA, matrixB, matrixC, kol * n, kol* n + kol);
                    return callable;
                })
                .collect(Collectors.toList());

        executor.invokeAll(list);
        return matrixC;
    }


    private static int[][] concurrentThreadMultiply(int[][] matrixA, int[][] matrixB, int[][] matrixC, int rowStart, int rowEnd) {
        final int matrixSize = matrixA.length;
        //final int[][] matrixC = new int[rowEnd - rowStart][matrixSize];
        final int[] rowB = new int[matrixSize];

        for (int j = 0; j < matrixSize; j++) {
            for (int k = 0; k < matrixSize; k++) {
                rowB[k] = matrixB[k][j];
            }
            for (int i = rowStart; i < rowEnd; i++) {
                int[] rowA = matrixA[i];
                int sum = 0;
                for (int k = 0; k < matrixSize; k++) {
                    sum += rowA[k] * rowB[k];
                }
                matrixC[i][j] = sum;
            }
        }
        return matrixC;
    }

    // TODO optimize by https://habrahabr.ru/post/114797/
    public static int[][] singleThreadMultiply(int[][] matrixA, int[][] matrixB) {
        final int matrixSize = matrixA.length;
        final int[][] matrixC = new int[matrixSize][matrixSize];
        final int[] rowB = new int[matrixSize];

        for (int j = 0; j < matrixSize; j++) {

            for (int k = 0; k < matrixSize; k++) {
                rowB[k] = matrixB[k][j];
            }

            for (int i = 0; i < matrixSize; i++) {

                int[] rowA = matrixA[i];

                int sum = 0;
                for (int k = 0; k < matrixSize; k++) {
                    sum += rowA[k] * rowB[k];
                }
                matrixC[i][j] = sum;
            }
        }
        return matrixC;
    }

    public static int[][] create(int size) {
        int[][] matrix = new int[size][size];
        Random rn = new Random();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = rn.nextInt(10);
            }
        }
        return matrix;
    }

    public static boolean compare(int[][] matrixA, int[][] matrixB) {
        final int matrixSize = matrixA.length;
        for (int i = 0; i < matrixSize; i++) {
            for (int j = 0; j < matrixSize; j++) {
                if (matrixA[i][j] != matrixB[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
}
