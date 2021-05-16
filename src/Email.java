import java.util.ArrayList;

public class Email {
    private double emailClass;
    private double[] Features;

    public Email(double[] features) {
        Features = features;
    }

    public double getEmailClass() {
        return emailClass;
    }

    public double[] getFeatures() {
        return Features;
    }

    public void setEmailClass(double emailClass) {
        this.emailClass = emailClass;
    }


}
