package uk.ac.ucl.model;
import java.util.ArrayList;

public class Column {
    private String name;
    private ArrayList<String> rows = new ArrayList<>();


    public Column(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public int getSize() {
        return this.rows.size();
    }

    public int getPosition(String value) {
        for (int i = 0; i < this.rows.size(); i++) {
            if (this.rows.get(i).equals(value)) {
                return i;
            }
        }

        throw new IllegalArgumentException("Value not found: " + value);
    }

    public String getRowValue(int row) throws IndexOutOfBoundsException {
        if (row >= this.rows.size()) {
            throw new IndexOutOfBoundsException("Access of array index out of bound");
        } else {
            return this.rows.get(row);
        }
    }

    public void setRowValue(int row, String value) throws IndexOutOfBoundsException {
        if (row >= this.rows.size()) {
            throw new IndexOutOfBoundsException("Access of array index out of bound");
        } else {
            this.rows.set(row, value);
        }
    }

    public void addRowValue(String newRow) {
        this.rows.add(newRow);
    }
}