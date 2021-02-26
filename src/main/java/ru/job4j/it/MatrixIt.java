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
        boolean rsl = false;
        for (int i = row; i < data.length; i++) {
            if (column < data[i].length) {
                rsl = true;
                break;
            }
            column = 0;
        }
        return rsl;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        int rsl = 0;
        for (int i = row; i < data.length; i++) {
            if (column == data[i].length || data[i].length == 0) {
                column = 0;
                row++;
                continue;
            }
            rsl = data[i][column++];
            break;
        }
        return rsl;
    }
}
