import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Parser {

    private ArrayList<Email> class0 = new ArrayList<>();
    private ArrayList<Email> class1 = new ArrayList<>();
    private ArrayList<Email> testEmails = new ArrayList<>();
    private ArrayList<Email> trainingEmails = new ArrayList<>();

    public Parser() { }


    public void parseTrainingData(File file) throws FileNotFoundException {

        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            double[] feature = new double[12];
            double Class;
            for (int i=0; i<12; i++) {
                feature[i] = scanner.nextDouble();
            }
            Class = scanner.nextInt();
            Email email = new Email(feature);
            email.setEmailClass(Class);
            if(Class==0) class0.add(email);
            else class1.add(email);

            trainingEmails.add(email);
            scanner.nextLine();

        }

    }

    public void parseTestData(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            double[] feature = new double[12];
            for (int i = 0; i < 12; i++) {
                feature[i] = scanner.nextDouble();
            }
            Email email = new Email(feature);
            testEmails.add(email);
            scanner.nextLine();

        }

    }

    public ArrayList<Email> getClass0() {
        return class0;
    }

    public ArrayList<Email> getClass1() {
        return class1;
    }

    public ArrayList<Email> getTestEmails() {
        return testEmails;
    }

    public ArrayList<Email> getTrainingEmails() {
        return trainingEmails;
    }
}


