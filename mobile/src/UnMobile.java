import java.awt.*;
import javax.swing.*;
import java.util.Random;
import java.util.concurrent.Semaphore;

class UnMobile extends JPanel implements Runnable {
    int saLargeur, saHauteur, sonDebDessin;
    final int sonPas = 10, sonCote = 40;
    int sonTemps;
    UneFenetre fenetre;
    int mobileId;
    Semaphore semaphore;

    UnMobile(int telleLargeur, int telleHauteur, UneFenetre fenetre, int mobileId, Semaphore semaphore) {
        super();
        saLargeur = telleLargeur;
        saHauteur = telleHauteur;
        setSize(telleLargeur, telleHauteur);
        this.fenetre = fenetre;
        this.mobileId = mobileId;
        this.semaphore = semaphore;

        Random random = new Random();
        sonTemps = 30 + random.nextInt(120);
    }

    public void run() {
        while (true) {
            if (!fenetre.isEnMouvement(mobileId)) {
                synchronized (this) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

            try {
                semaphore.acquire();
                for (sonDebDessin = 0; sonDebDessin < saLargeur - sonPas; sonDebDessin += sonPas) {
                    if (!fenetre.isEnMouvement(mobileId)) {
                        break;
                    }

                    repaint();
                    try {
                        Thread.sleep(sonTemps);
                    } catch (InterruptedException telleExcp) {
                        telleExcp.printStackTrace();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release();
            }
        }
    }

    public void paintComponent(Graphics telCG) {
        super.paintComponent(telCG);
        telCG.fillRect(sonDebDessin, saHauteur / 2, sonCote, sonCote);
    }
}
