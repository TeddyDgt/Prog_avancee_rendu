import java.util.Scanner;

public class Producteur implements Runnable {
    private BAL bal;
    private Scanner scanner = new Scanner(System.in);

    public Producteur(BAL bal) {
        this.bal = bal;
    }

    @Override
    public void run() {
        try {
            String lettre;
            do {
                System.out.print("Entrez une lettre à déposer (Q pour quitter) : ");
                lettre = scanner.nextLine();
                bal.deposer(lettre);
            } while (!lettre.equals("Q"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
