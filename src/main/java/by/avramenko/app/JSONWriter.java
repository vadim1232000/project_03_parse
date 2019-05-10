package by.avramenko.app;

import com.google.gson.Gson;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class JSONWriter {
    private ArrayList<Product> productList;

    public JSONWriter(ArrayList<Product> productList) {
        this.productList = productList;
    }

    public void write(String filepath, String filename) {
        String path = filepath + "\\" + filename + ".json";

        Gson gson = new Gson();
        String json = gson.toJson(productList);
        try {
            FileWriter fileWriter = new FileWriter(path);
            fileWriter.write("{\"results\":"+json+"}");
            fileWriter.close();
            System.out.println("Ready!");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
