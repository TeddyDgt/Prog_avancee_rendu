public class BAL {
    private String lettre = null;
    private boolean fin = false;

    public synchronized void deposer(String l) throws InterruptedException {
        while (lettre != null) {
            wait();
        }
        lettre = l;
        System.out.println("Lettre déposée: " + lettre);
        if (lettre.equals("Q")) {
            fin = true;
        }
        notifyAll();
    }

    public synchronized String retirer() throws InterruptedException {
        while (lettre == null && !fin) {
            wait();
        }
        String l = lettre;
        lettre = null;
        if (!fin) {
            System.out.println("Lettre retirée: " + l);
        }
        notifyAll();
        return l;
    }

    public boolean estFin() {
        return fin;
    }
}
