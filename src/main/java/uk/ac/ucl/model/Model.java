package uk.ac.ucl.model;

import java.io.Reader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class Model {
  // The example code in this class should be replaced by your Model class code.
  // The data should be stored in a suitable data structure.
  private DataFrame dataFrame;
  private final DataLoader dataLoader;

  public Model() {
    this.dataFrame = new DataFrame();
    this.dataLoader = new DataLoader();
  }

  public void readFile(String path) {
    this.dataLoader.setPath(path);
    this.dataFrame = dataLoader.read();
  }

  public ArrayList<String> getPatientNames() {
    int size = this.dataFrame.getRowCount();
    ArrayList<String> patientNames = new ArrayList<>();

    for (int i = 0; i < size; i++) {
      String nowName = this.dataFrame.getValue("ID", i);
      patientNames.add(nowName);
    }

    return patientNames;
  }

  public ArrayList<String> getPatientDetails(String ID) {
    Column columnID = this.dataFrame.getColumn("ID");
    int rowNum = columnID.getPosition(ID);

    return this.dataFrame.getRow(rowNum);
  }

  // This method illustrates how to read csv data from a file.
  // The data files are stored in the root directory of the project (the directory your project is in),
  // in the directory named data.

  // This also returns dummy data. The real version should use the keyword parameter to search
  // the data and return a list of matching items.

  public ArrayList<String> getColumnNames() {
    return this.dataFrame.getColumnNames();
  }

  public ArrayList<String> searchFor(String keyword) {
//     Implement search logic across all columns or specific columns
//     For simplicity, searching in the "PREFIX", "FIRST" and "LAST" column here
    int size = this.dataFrame.getRowCount();
    ArrayList<String> searchResults = new ArrayList<>();

    for (int i = 0; i < size; i++) {
      String prefix = this.dataFrame.getValue("PREFIX", i).replaceAll("\\d+$", "");  // Delete the trailing digits
      String first = this.dataFrame.getValue("FIRST", i).replaceAll("\\d+$", "");
      String last = this.dataFrame.getValue("LAST", i).replaceAll("\\d+$", "");

      if (prefix.contains(keyword) || first.contains(keyword) || last.contains(keyword)) {
        searchResults.add(this.dataFrame.getValue("ID", i));
      }
    }

    return searchResults;
  }

  public ArrayList<String> getDeadPatients() {
    int size = this.dataFrame.getRowCount();
    ArrayList<String> searchResults = new ArrayList<>();

    for (int i = 0; i < size; i++) {
      if (!this.dataFrame.getValue("DEATHDATE", i).isBlank()) {
        searchResults.add(this.dataFrame.getValue("ID", i));
      }
    }

    return searchResults;
  }

  public ArrayList<String> getDriverPatients() {
    int size = this.dataFrame.getRowCount();
    ArrayList<String> searchResults = new ArrayList<>();

    for (int i = 0; i < size; i++) {
      if (!this.dataFrame.getValue("DRIVERS", i).isBlank()) {
        searchResults.add(this.dataFrame.getValue("ID", i));
      }
    }

    return searchResults;
  }

}
