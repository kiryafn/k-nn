package nai.knn.config;

public enum Path {
    Training_Vectors("src/main/resources/iris.data"),
    Training_Vectors2("src/main/resources/wdbc.data"),
    Test_Vectors("src/main/resources/iris.test.data"),
    Test_Vectors2("src/main/resources/wdbc.test.data");

    private String path;

    Path(String path){
        this.path = path;
    }

    public String getPath(){
        return path;
    }
}
