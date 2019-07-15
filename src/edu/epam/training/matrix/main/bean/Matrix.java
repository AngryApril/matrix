package edu.epam.training.matrix.main.bean;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by alexey.valiev on 6/6/19.
 */
public final class Matrix {


    private ArrayList<MatrixLine> matrixLines = new ArrayList<>();

    private static final AtomicReference<Matrix> reference = new AtomicReference<>();

    public static Matrix get() {
        if (reference.get() == null)
            reference.compareAndSet(null, new Matrix());
        return reference.get();
    }

    private Matrix(){}

    public ArrayList<MatrixLine> getMatrixLines() {
        return matrixLines;
    }

    public void setMatrixLines(ArrayList<MatrixLine> matrixLines) {
        this.matrixLines = matrixLines;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Matrix)) return false;

        Matrix matrix = (Matrix) o;

        return matrixLines != null ? matrixLines.equals(matrix.matrixLines) : matrix.matrixLines == null;

    }

    @Override
    public int hashCode() {
        return matrixLines != null ? matrixLines.hashCode() : 0;
    }

    @Override
    public String toString() {

        StringBuilder matrix = new StringBuilder();

        for(MatrixLine matrixLine : matrixLines){
            matrix.append(matrixLine.toString());
            matrix.append("\n");
        }

        return matrix.toString();
    }
}
