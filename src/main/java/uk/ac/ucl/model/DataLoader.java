package uk.ac.ucl.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataLoader {
    private String path;
    private DataFrame dataFrame = new DataFrame();

    public DataLoader(String path) {
        this.path = path;
    }

    public DataLoader() {
        this.path = "";
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String newPath) {
        this.path = newPath;
    }

    public void addColumns(String[] columns) {
        for (int i = 0; i < columns.length; i++) {
            Column column = new Column(columns[i]);
            this.dataFrame.addColumn(column);
        }
    }

    private String[] extend(String[] oldColumns) {  // Create a new array that complements the last empty column with an empty string.
        int newLength = oldColumns.length + 1;
        String[] newColumns = new String[newLength];

        for (int i = 0; i < oldColumns.length; i++){
            newColumns[i] = oldColumns[i];
        }

        newColumns[oldColumns.length] = "";

        return newColumns;
    }

    public DataFrame read() {
        String line = "";

        try (BufferedReader br = new BufferedReader(new FileReader(this.path))) {
            line = br.readLine();
            String[] columns = line.split(",");
            this.addColumns(columns);

            while ((line = br.readLine()) != null) {
                // Use comma as separator
                String[] valuesArray = line.split(",");
                List<String> values = new ArrayList<>(Arrays.asList(valuesArray));
                
                if (values.size() == columns.length - 1) {  // Case when the ZIP column is empty!
                    values.add("");
                }

                try {
                    for (int i = 0; i < columns.length; i++) {
                        this.dataFrame.addValue(columns[i], values.get(i));
                    }
                } catch (IllegalArgumentException e) {
                    System.err.println("IllegalArgumentException: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return this.dataFrame;
    }
}
