public class Email {
    private double emailClass;
    private double[] Features;

    public Email(double[] features, double Class) {
        Features = features;
        emailClass = Class;

    }

    public double getEmailClass() {
        return emailClass;
    }

    public double[] getFeatures() {
        return Features;
    }
}
