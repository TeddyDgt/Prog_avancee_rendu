public class Task extends Thread {

    public Task(){super();}

    public void run() {

    }

    static public void main(String[] args) {
        new Task().start();
    }
}