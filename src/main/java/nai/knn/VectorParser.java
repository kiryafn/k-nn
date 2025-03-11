package nai.knn;

import nai.knn.Vector;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class VectorParser {

    public static List<Vector> parseVectorsFromFile(String filePath) {
        List<Vector> vectors = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");

                if (parts.length < 2) {
                    continue;
                }

                String name = parts[parts.length - 1];

                List<Double> values = new ArrayList<>();
                for (int i = 0; i < parts.length - 1; i++) {
                    values.add(Double.parseDouble(parts[i]));
                }

                vectors.add(new Vector(name, values));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return vectors;
    }
}