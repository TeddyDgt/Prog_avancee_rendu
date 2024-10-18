public class Main {
    public static void main(String[] args) {
        BAL bal = new BAL();

        Thread producteurThread = new Thread(new Producteur(bal));
        Thread consommateurThread = new Thread(new Consommateur(bal));

        producteurThread.start();
        consommateurThread.start();
    }
}
