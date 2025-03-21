package nai.knn.data;

public enum Colors {
    RED("\u001B[31m"), BOLD("\u001B[1m"), RESET("\u001B[0m"), BLACK("\u001B[30m"),
    GREEN("\u001B[32m"), YELLOW("\u001B[33m"), BLUE("\u001B[34m"), PURPLE("\u001B[35m"),
    CYAN("\u001B[36m"), WHITE("\u001B[37m"), BLACK_BACKGROUND("\u001B[40m"), RED_BACKGROUND("\u001B[41m"),
    GREEN_BACKGROUND("\u001B[42m"), YELLOW_BACKGROUND("\u001B[43m"), BLUE_BACKGROUND("\u001B[44m"),
    PURPLE_BACKGROUND("\u001B[45m"), CYAN_BACKGROUND("\u001B[46m"), WHITE_BACKGROUND("\u001B[47m");

    String color;
    Colors(String color){this.color = color;}

    public String getColor(){return this.color;}

    @Override
    public String toString(){
        return this.color;
    }
}
