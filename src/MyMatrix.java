public class MyMatrix {
    private double[][] matrix;

    public MyMatrix(int row, int column) {
        matrix = new double[row][column];
    }

    public synchronized int getRowCount() {
        return matrix.length;
    }

    public synchronized int getColumnCount() {
        return matrix[0].length;
    }

    public synchronized double getCell(int row, int column) {
        return matrix[row][column];
    }

    public synchronized void setCell(int row, int column, double val) {
        this.matrix[row][column] = val;
    }


    @Override
    public synchronized String toString() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < this.getRowCount(); i++) {
            str.append("|");
            for (int j = 0; j < this.getColumnCount(); j++) {
                str.append(String.format("%6.1f", this.getCell(i, j))).append("\t");
            }
            str.append("|\n");
        }
        return str.toString();
    }
}
