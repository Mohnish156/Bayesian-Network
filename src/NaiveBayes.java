import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class NaiveBayes {



    private ArrayList<Email> Class0;
    private ArrayList<Email> Class1;
    private ArrayList<Email> testEmails = new ArrayList<>();
    public NaiveBayes() throws FileNotFoundException {

        Parser parser = new Parser();
        File trainingFile = new File("data/spamLabelled.dat");
        File testFile = new File("data/spamUnlabelled.dat");
        parser.parseTrainingData(trainingFile);
        parser.parseTestData(testFile);
        Class0 = parser.getClass0();
        Class1 = parser.getClass1();
        testEmails = parser.getTestEmails();
        NaiveBayesAlgo();
    }

    private void NaiveBayesAlgo(){

        ArrayList<Integer> spamOrNot = new ArrayList<>();
        for(Email email : testEmails){

            double[] class1Probs = getFeatureProb(Class1);
            double[] class0Probs = getFeatureProb(Class0);

            double probTrue =  Class1.size()/200.0;
            double probFalse =  Class0.size()/200.0;

            for(int i=0; i<email.getFeatures().length; i++){

                if(email.getFeatures()[i] == 1){
                    probTrue = probTrue * class1Probs[i];
                    probFalse = probFalse * class0Probs[i];
                }else{

                    double not0Prob = 1-class0Probs[i];
                    double not1prob = 1-class1Probs[i];
                    probTrue = probTrue * (not1prob);
                    probFalse = probFalse * (not0Prob);
                }
            }
            if(probTrue > probFalse){
                spamOrNot.add(1);
            }else{
                spamOrNot.add(0);
            }
        }

        for(int i=0; i<spamOrNot.size(); i++){
            System.out.println("Unlabelled instance" +i+": "+spamOrNot.get(i));
        }

    }

    private double[] getFeatureProb(ArrayList<Email> emails){
        double[] probabilities = new double[12];
        double[] countFeature = new double[12];
        for(Email email : emails){
            for(int i = 0; i<email.getFeatures().length;i++){
                countFeature[i] += email.getFeatures()[i];
            }
        }
        for(int i = 0; i<countFeature.length; i++){
            double featureProb = (countFeature[i]/emails.size());
            probabilities[i]=(featureProb);
        }
        return probabilities;
    }

    public static void main(String[] args) throws FileNotFoundException {
        new NaiveBayes();

    }


}
