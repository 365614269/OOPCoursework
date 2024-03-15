package uk.ac.ucl.model;
import java.util.ArrayList;

public class DataFrame {
    private ArrayList<Column> columns;

    public DataFrame() {
        this.columns = new ArrayList<>();
    }

    public DataFrame(ArrayList<Column> newColumns) {
        this.columns = newColumns;
    }

    public void addColumn(Column newColumn) {
        this.columns.add(newColumn);
    }

    public ArrayList<String> getColumnNames() {
        ArrayList<String> columnNames = new ArrayList<>();

        for (Column column : this.columns) {
            columnNames.add(column.getName());
        }

        return columnNames;
    }

    public Column getColumn(String columnName) {
        for (Column column: this.columns) {
            if (column.getName().equals(columnName)) {
                return column;
            }
        }

        throw new IllegalArgumentException("Column name not found: " + columnName);
    }

    public ArrayList<String> getRow(int row) {
        ArrayList<String> rowValue = new ArrayList<>();

        for (Column column: this.columns) {
            rowValue.add(column.getRowValue(row));
        }

        return rowValue;
    }

    public int getRowCount() {
        if (this.columns.isEmpty()) {
            throw new IndexOutOfBoundsException("DataFrame has no columns.");
        } else {
            Column column = this.columns.get(0);
            return column.getSize();
        }
    }

    public String getValue(String columnName, int row) {
        for (Column nowColumn : this.columns) {
            if (nowColumn.getName().equals(columnName)) {
                return nowColumn.getRowValue(row);
            }
        }

        throw new IllegalArgumentException("Column name not found: " + columnName);
    }

    public void putValue(String columnName, int row, String value) {
        for (Column nowColumn : this.columns) {
            if (nowColumn.getName().equals(columnName)) {
                nowColumn.setRowValue(row, value);
                return;
            }
        }

        throw new IllegalArgumentException("Column name not found: " + columnName);
    }

    public void addValue(String columnName, String value) {
        for (Column nowColumn : this.columns) {
            if (nowColumn.getName().equals(columnName)) {
                nowColumn.addRowValue(value);
                return;
            }
        }

        throw new IllegalArgumentException("Column name not found: " + columnName);
    }

    public void deleteRow(String ID) {
        for (int i = 0; i < this.getRowCount(); i++) {
            if (this.getValue("ID", i).equals(ID)) {
                for (Column column : this.columns) {
                    column.deleteRow(i);
                }
                return;
            }
        }

        throw new IllegalArgumentException("ID not found: " + ID);
    }
}
