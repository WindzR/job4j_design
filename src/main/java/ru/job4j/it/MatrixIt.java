package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int row;
    private int column;

    public MatrixIt(int[][] data) {
        this.data = data;
        this.row = 0;
        this.column = 0;
    }

    @Override
    public boolean hasNext() {
        while (data.length > row && column == data[row].length) {
            column = 0;
            row++;
        }
        return data.length > row;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[row][column++];
    }
}
