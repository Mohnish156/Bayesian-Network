import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class NaiveBayes {



    private ArrayList<Email> Class0;
    private ArrayList<Email> Class1;

    public NaiveBayes() throws FileNotFoundException {
        Parser parser = new Parser();
        File file = new File("data/spamLabelled.dat");
        parser.ParseData(file);

        Class0 = parser.getClass0();
        Class1 = parser.getClass1();

        NaiveBayesAlgo();
    }



    private void NaiveBayesAlgo(){


    }


    public static void main(String[] args) throws FileNotFoundException {
        new NaiveBayes();

    }


}
