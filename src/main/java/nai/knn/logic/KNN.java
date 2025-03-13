package nai.knn.logic;

import nai.knn.data.Distance;
import nai.knn.data.Vector;

import java.util.*;
import java.util.stream.Collectors;

public class KNN {
    public static List<Distance> calculate_distances(List<Vector> training_vectors, Vector test_vector){
        List<Distance> distances = new ArrayList<>();

        for (Vector training_vector : training_vectors) {
            double distance = 0;

            for (int j = 0; j < training_vector.getValues().size(); j++) {
                distance += Math.pow(training_vector.getValues().get(j) - test_vector.getValues().get(j), 2);
            }
            distance = Math.sqrt(distance);

            distances.add(new Distance(training_vector, test_vector, distance));
        }

        return distances;
    }

    public static List<Distance> k_nearest_neighbors(int k, List<Distance> distances) {
        return distances.stream()
                .sorted(Comparator.comparing(Distance::getDistance))
                .limit(k)
                .toList();
    }

    public static String predict(List<Distance> distances){
        Map<String, Long> nameCountMap = distances.stream()
                .collect(Collectors.groupingBy(d -> d.getTrainingVector().getName(), Collectors.counting()));

        long maxCount = nameCountMap.values().stream()
                .mapToLong(Long::longValue)
                .max()
                .orElse(0);

        List<String> mostFrequentNames = nameCountMap.entrySet().stream()
                .filter(entry -> entry.getValue() == maxCount)
                .map(Map.Entry::getKey)
                .toList();

        return mostFrequentNames.isEmpty()
                ? null
                : mostFrequentNames.get(new Random().nextInt(mostFrequentNames.size()));
    }

    public static double calculate_accuracy(List<Vector> trainingVectors, List<Vector> testVectors, int k) {
        int correctPredictions = 0;

        for (var testVector : testVectors) {
            List<Distance> distances = KNN.calculate_distances(trainingVectors, testVector);
            distances = KNN.k_nearest_neighbors(k, distances);
            String prediction = KNN.predict(distances);

            if (prediction.equals(testVector.getName())) {
                correctPredictions++;
            }

            System.out.println("[Prediction: " + prediction + "]" + " [Actual: " + testVector.getName() + "]");
        }

        return (double) correctPredictions / testVectors.size();
    }
}
