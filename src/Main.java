import java.security.SecureRandom;

public class Main {
    final static int NUM_Of_ARGS = 4;
    final static int FIRST_ARG = 0;
    final static int SECOND_ARG = 1;
    final static int THIRD_ARG = 2;
    final static int FOURTH_ARG = 3;

    public static void main(String[] args) {
        lunch(args);
    }

    private static void lunch(String[] args) {
        if (args.length != NUM_Of_ARGS) {
            System.err.println("Missing arguments");
            System.exit(-1);
        }
        MyMatrix matrixA = null;
        MyMatrix matrixB = null;
        try {
            if (Integer.parseInt(args[SECOND_ARG]) != Integer.parseInt(args[THIRD_ARG])) {
                System.err.println("Bad dimensions");
                System.exit(-1);
            }
            matrixA = buildAndFillMatrix(Integer.parseInt(args[FIRST_ARG]), Integer.parseInt(args[SECOND_ARG]));
            matrixB = buildAndFillMatrix(Integer.parseInt(args[THIRD_ARG]), Integer.parseInt(args[FOURTH_ARG]));
        } catch (NumberFormatException e) {
            System.err.println("Bad dimensions");
            System.exit(-1);
        }
        System.out.println("Matrices Built Successfully.\nMatrix A:\n" + matrixA + "\nMatrix B:\n" + matrixB);
        startRunning(matrixA, matrixB);
    }

    private static MyMatrix buildAndFillMatrix(int row, int column) {
        SecureRandom rand = new SecureRandom();
        MyMatrix matrix = new MyMatrix(row, column);
        for (int i = 0; i < matrix.getRowCount(); i++)
            for (int j = 0; j < matrix.getColumnCount(); j++)
                matrix.setCell(i, j, rand.nextDouble(10));
        return matrix;
    }

    private static void startRunning(MyMatrix matrixA, MyMatrix matrixB) {
        System.out.println("Calculating...");
        MyMatrix result = new MyMatrix(matrixA.getRowCount(), matrixB.getColumnCount());
        MulMatrices runner;
        for (int i = 0; i < result.getRowCount(); i++)
            for (int j = 0; j < result.getColumnCount(); j++) {
                runner = new MulMatrices(result, matrixA, matrixB, i, j);
                try {
                    runner.start();
                    runner.join();
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (InterruptedException ignored) {
                }
            }
        System.out.println(result);
    }
}