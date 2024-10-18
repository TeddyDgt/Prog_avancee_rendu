import javax.swing.*;
import java.awt.*;
import java.util.concurrent.Semaphore;

public class UneFenetre extends JFrame {
    private UnMobile[] mobiles;
    private Thread[] mobileThreads;
    private boolean[] enMouvement;
    private Semaphore[] semaphores;

    public UneFenetre(int nombreDeMobiles) {
        super("Fenêtre avec " + nombreDeMobiles + " mobiles");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        int nbColonnes = (int) Math.ceil(Math.sqrt(nombreDeMobiles));
        int nbLignes = (int) Math.ceil((double) nombreDeMobiles / nbColonnes);

        setLayout(new GridLayout(nbLignes, nbColonnes));

        mobiles = new UnMobile[nombreDeMobiles];
        mobileThreads = new Thread[nombreDeMobiles];
        enMouvement = new boolean[nombreDeMobiles];
        semaphores = new Semaphore[nombreDeMobiles];

        for (int i = 0; i < nombreDeMobiles; i++) {
            enMouvement[i] = true;
            semaphores[i] = new Semaphore(1);
            mobiles[i] = new UnMobile(400, 300, this, i, semaphores[i]);
            add(mobiles[i]);

            mobileThreads[i] = new Thread(mobiles[i]);
            mobileThreads[i].start();
        }

        setSize(800, 600);
        setVisible(true);
    }

    public boolean isEnMouvement(int mobileId) {
        return enMouvement[mobileId];
    }
}
