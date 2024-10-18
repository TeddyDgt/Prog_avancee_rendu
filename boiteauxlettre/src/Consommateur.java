public class Consommateur implements Runnable {
    private BAL bal;

    public Consommateur(BAL bal) {
        this.bal = bal;
    }

    @Override
    public void run() {
        try {
            String lettre;
            while (!bal.estFin()) {
                lettre = bal.retirer();
                if (lettre.equals("Q")) {
                    System.out.println("Consommateur arrêté.");
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
