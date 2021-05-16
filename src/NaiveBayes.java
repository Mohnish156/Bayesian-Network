import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class NaiveBayes {

    private final ArrayList<Email> Class0;
    private final ArrayList<Email> Class1;
    private final ArrayList<Email> testEmails;
    private final ArrayList<Email> trainingEmails;

    public NaiveBayes(File training, File test) throws FileNotFoundException {

        Parser parser = new Parser();
        parser.parseTrainingData(training);
        parser.parseTestData(test);
        Class0 = parser.getClass0();
        Class1 = parser.getClass1();
        testEmails = parser.getTestEmails();
        trainingEmails = parser.getTrainingEmails();
        NaiveBayesAlgo(testEmails);
    }

    private void NaiveBayesAlgo(ArrayList<Email> emails){


        initaliseZeroOccurrence();

        ArrayList<Integer> spamOrNot = new ArrayList<>();
        ArrayList<Double> probabilitiesTrue = new ArrayList<>();
        ArrayList<Double> probabilitiesFalse = new ArrayList<>();

        double[] class1Probabilities = getFeatureProb(Class1);
        double[] class0Probabilities = getFeatureProb(Class0);

        double probTrue;
        double probFalse;

        output(class1Probabilities,class0Probabilities);

        for(Email email : emails){

             probTrue =  ((double) Class1.size()/(Class1.size()+(double)Class0.size()));
             probFalse =  ((double) Class0.size()/ (Class1.size()+(double)Class0.size()));


            for(int i=0; i<email.getFeatures().length; i++){

                if(email.getFeatures()[i] == 1){
                    probTrue *= class1Probabilities[i];
                    probFalse *= class0Probabilities[i];
                }else{
                    double not0Prob =  1-class0Probabilities[i];
                    double not1prob =  1-class1Probabilities[i];
                    probTrue *= (not1prob);
                    probFalse *= (not0Prob);
                }
            }
            if(probTrue > probFalse){
                spamOrNot.add(1);
            }else{
                spamOrNot.add(0);
            }

            probabilitiesTrue.add(probTrue);
            probabilitiesFalse.add(probFalse);

        }


        for(int i=0; i<spamOrNot.size(); i++){
            System.out.println("Unlabelled instance " +i+" classified : "+spamOrNot.get(i));
            System.out.println("Probability of spam:" + probabilitiesTrue.get(i));
            System.out.println("Probability of not spam:" + probabilitiesFalse.get(i));
            System.out.println("\t");
        }



    }


    private void initaliseZeroOccurrence(){

        double[] initial1 = {1,1,1,1,1,1,1,1,1,1,1,1};
        double[] initial0 = {0,0,0,0,0,0,0,0,0,0,0,0};

        Email email1True = new Email(initial1);
        email1True.setEmailClass(1);
        Email email1False = new Email(initial0);
        email1False.setEmailClass(1);

        Email email0True = new Email(initial1);
        email0True.setEmailClass(0);
        Email email0False = new Email(initial0);
        email0True.setEmailClass(0);


        Class0.add(0,email0True);
        Class0.add(0,email0False);

        Class1.add(0,email1False);
        Class1.add(0,email1True);


    }

    private double[] getFeatureProb(ArrayList<Email> emails){
        double[] probabilities = new double[12];
        double[] countFeature = new double[12];
        double[][] featureTable = new double[emails.size()][12];

        for(int i = 0; i<emails.size(); i++){
            for(int j = 0; j<12;j++){
                featureTable[i][j] = emails.get(i).getFeatures()[j];
            }
        }

        for(int i = 0; i<12; i++){
            double count = 0;
            for(int j = 0; j<featureTable.length;j++){
                count+=featureTable[j][i];
            }
            countFeature[i] = count;
        }

        for(int i = 0; i<countFeature.length; i++){
            double featureProb = (countFeature[i]/(double) emails.size());
            probabilities[i]=(featureProb);
        }
        return probabilities;
    }

    private void output(double[] trueProbs, double[] falseProbs){
        for (int i = 0; i<falseProbs.length; i++){
            double val = falseProbs[i];
            System.out.println("P(F=" + i +"|C=0) = " +(val));
        }
        System.out.println("\t");

        for (int i = 0; i<trueProbs.length; i++){
            double val = trueProbs[i];
            System.out.println("P(F=" + i +"|C=1) = " +(val));
        }
        System.out.println("\t");

    }


    public static void main(String[] args) throws FileNotFoundException {
        String trainingFile = args[0];
        String testFile = args[1];
        File training = new File(trainingFile);
        File test = new File(testFile);
        new NaiveBayes(training,test);

    }


}
