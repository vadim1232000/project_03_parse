package by.avramenko.app;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Parser parser = new Parser();
        parser.parse(100);
        JSONWriter writer = new JSONWriter(parser.getProductList());
        writer.write("E:\\project_03_parse-master\\project_03_parse-master","file1");
    }
}
