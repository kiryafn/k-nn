package nai.knn;

import nai.knn.config.Path;
import nai.knn.logic.KNN;
import nai.knn.utils.VectorParser;
import org.knowm.xchart.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        var trainingVectors = VectorParser.parseVectorsFromFile(Path.Training_Vectors2.getPath());
        var testVectors = VectorParser.parseVectorsFromFile(Path.Test_Vectors2.getPath());

        Map<Integer, Double> accuracyMap = new LinkedHashMap<>();

        for (int k = 1; k <= 50; k++) {
            double accuracy = KNN.calculateAccuracy(trainingVectors, testVectors, k);
            accuracyMap.put(k, accuracy);
        }

        drawGraph(accuracyMap);
    }


    private static void drawGraph(Map<Integer, Double> accuracyMap) {
        List<Integer> kValues = new ArrayList<>(accuracyMap.keySet());
        List<Double> accuracyValues = new ArrayList<>(accuracyMap.values());

        XYChart chart = QuickChart.getChart(
                "Accuracy vs k",
                "k (Number of Neighbors)",
                "Accuracy",
                "Accuracy",
                kValues,
                accuracyValues
        );

        new SwingWrapper<>(chart).displayChart();
    }
}