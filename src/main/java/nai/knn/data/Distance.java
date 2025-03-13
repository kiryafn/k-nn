package nai.knn.data;

public class Distance {
    private final Vector trainingVector;
    private final Vector testVector;
    private final double distance;

    public Distance(Vector trainingVector, Vector testVector, double distance) {
        this.trainingVector = trainingVector;
        this.testVector = testVector;
        this.distance = distance;
    }

    public Vector getTrainingVector() {
        return trainingVector;
    }

    public Vector getTestVector() {
        return testVector;
    }

    public double getDistance() {
        return distance;
    }
}