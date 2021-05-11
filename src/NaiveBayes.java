import java.io.File;
import java.io.FileNotFoundException;

public class NaiveBayes {


    private double[][] DataLabelled;

    public NaiveBayes() throws FileNotFoundException {
        Parser parser = new Parser();
        File file = new File("data/spamLabelled.dat");
        parser.ParseData(file);

        DataLabelled = parser.getData();
    }



    private void NaiveBayesAlgo(){


    }


    public static void main(String[] args) throws FileNotFoundException {
        new NaiveBayes();

    }


}
