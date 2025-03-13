package nai.knn;

import nai.knn.data.Colors;
import nai.knn.data.Path;
import nai.knn.logic.KNN;
import nai.knn.utils.VectorParser;
import org.knowm.xchart.*;
import nai.knn.data.Vector;

import java.util.*;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        var trainingVectors = select_training_vectors(scanner);
        var testVectors = select_test_vectors(scanner);
        int k = select_k(scanner);

        Map<Integer, Double> accuracyMap = new LinkedHashMap<>();

        for (int i = 1; i <= k; i++) {
            double accuracy = KNN.calculate_accuracy(trainingVectors, testVectors, i);
            accuracyMap.put(i, accuracy);
        }

        draw_graph(accuracyMap);
    }

    private static void draw_graph(Map<Integer, Double> accuracyMap) {
        List<Integer> kValues = new ArrayList<>(accuracyMap.keySet());
        List<Double> accuracyValues = new ArrayList<>(accuracyMap.values());

        XYChart chart = QuickChart.getChart(
                "Accuracy vs k",
                "k (Number of Neighbors)",
                "Accuracy of correct predictions",
                "Accuracy",
                kValues,
                accuracyValues
        );

        new SwingWrapper<>(chart).displayChart();
    }

    private static int get_valid_input(Scanner scanner, int maxChoice) {
        int choice;
        while (true) {
            try {
                choice = Integer.parseInt(scanner.nextLine());
                if (choice < 1 || choice > maxChoice) {
                    throw new IllegalArgumentException();
                }
                break;
            } catch (Exception e) {
                System.out.println(Colors.RED + "Incorrect input. Please enter a number between 1 and " + maxChoice + Colors.RESET);
            }
        }
        return choice;
    }

    private static List<Vector> select_training_vectors(Scanner scanner){
        System.out.println(Colors.BOLD + "Select the training file (enter a number):" + Colors.RESET);
        Path[] trainingPaths = {Path.Training_Vectors, Path.Training_Vectors2};
        for (int i = 0; i < trainingPaths.length; i++)
            System.out.println(Colors.CYAN.getColor() + (i + 1) + ". " + Colors.RESET + trainingPaths[i].getPath());

        int trainingChoice = get_valid_input(scanner, trainingPaths.length);
        String trainingPath = trainingPaths[trainingChoice - 1].getPath();

        return VectorParser.parseVectorsFromFile(trainingPath);


    }

    private static List<Vector> select_test_vectors(Scanner scanner){
        System.out.println(Colors.BOLD + "Select the file you want to test (enter the number):" + Colors.RESET);
        Path[] testPaths = {Path.Test_Vectors, Path.Test_Vectors2};
        for (int i = 0; i < testPaths.length; i++)
            System.out.println(Colors.CYAN.getColor() + (i + 1) + ". " + Colors.RESET + testPaths[i].getPath());

        int testChoice = get_valid_input(scanner, testPaths.length);
        String testPath = testPaths[testChoice - 1].getPath();

        return VectorParser.parseVectorsFromFile(testPath);
    }

    private static int select_k(Scanner scanner){
        System.out.println(Colors.BOLD + "Enter the value k (number of neighbors):" + Colors.RESET);
        return scanner.nextInt();
    }
}