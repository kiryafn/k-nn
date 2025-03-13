package nai.knn.models;

import java.util.List;

public class Vector {

    private String name;

    private List<Double> values;

    public Vector(String name, List<Double> values){
        this.name = name;
        this.values = values;
    }

    public Vector(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Double> getValues() {
        return values;
    }

    public void setValues(List<Double> values) {
        this.values = values;
    }
}
