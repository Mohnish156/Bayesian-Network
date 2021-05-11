import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Parser {

    private double[][] data = new double[200][13];

    public Parser() { }


    public void ParseData(File file) throws FileNotFoundException {

        Scanner scanner = new Scanner(file);

        for(int i=0; i<200;i++ ){
            for(int j=0; j<13;j++){
                data[i][j] = scanner.nextDouble();
            }
        }
    }


    public double[][] getData() {
        return data;
    }
}
