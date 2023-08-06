public class MulMatrices extends Thread {

    private MyMatrix result;
    private MyMatrix matrixA;
    private MyMatrix matrixB;
    private int row;
    private int column;

    public MulMatrices(MyMatrix result, MyMatrix matrixA, MyMatrix matrixB, int row, int column) {
        this.result = result;
        this.matrixA = matrixA;
        this.matrixB = matrixB;
        this.row = row;
        this.column = column;
    }


    @Override
    public synchronized void run() throws IllegalArgumentException {
        if (matrixA.getColumnCount() != matrixB.getRowCount())
            throw new IllegalArgumentException("Cannot Multiply Matrices");
        double sum = 0;
        for (int i = 0; i < matrixA.getColumnCount(); i++)
            sum += matrixA.getCell(row, i) * matrixB.getCell(i, column);
        System.out.printf("%s = %6.1f\n", currentThread().getName(), sum);
        result.setCell(row, column, sum);
    }
}