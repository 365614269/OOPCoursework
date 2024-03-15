package uk.ac.ucl.model;

import java.io.Reader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.json.JSONObject;
import org.json.JSONArray;

public class JSONWriter {
    private final String fileName;
    private final DataFrame dataFrame;
    public JSONWriter(String fileName, DataFrame dataFrame) {
        this.fileName = fileName;
        this.dataFrame = dataFrame;
    }

    public JSONWriter() {
        this.fileName = "";
        this.dataFrame = new DataFrame();
    }
    public void write() {
        ArrayList<String> columnNames = this.dataFrame.getColumnNames();
        int size = this.dataFrame.getRowCount();

        try (FileWriter file = new FileWriter(this.fileName + ".json")){
            JSONArray jsonArray = new JSONArray();

            for (int i = 0; i < size; i++) {
                ArrayList<String> row = this.dataFrame.getRow(i);
                JSONObject person = new JSONObject();

                for (int j = 0; j < row.size(); j++) {
                    person.put(columnNames.get(j), row.get(j));
                }

                jsonArray.put(person);
            }

            file.write(jsonArray.toString(4));  // Indent with 4 spaces.
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
