package nai.knn;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        var training_vectors = VectorParser.parseVectorsFromFile(Path.Training_Vectors2.getPath());
        var test_vectors = VectorParser.parseVectorsFromFile(Path.Test_Vectors2.getPath());
        int k = 5;
        int ok = 0;
        int nook = 0;

        for (var test_vector : test_vectors){
            List<Distance> distances = KNN.calculate_distances(training_vectors, test_vector);
            distances = KNN.k_nearest_neighbors(k, distances);
            String predict = KNN.predict(distances);
            if (predict.equals(test_vector.getName())) ok++;
            else nook++;
        }

        System.out.println(ok);
        System.out.println(nook);
    }
}