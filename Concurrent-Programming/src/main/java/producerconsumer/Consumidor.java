package producerconsumer;

public class Consumidor extends Thread {
    private Buffer buffer;
    private int milisegundos;
    private int iteraciones;

    public Consumidor(Buffer b, int s, int n) {
        buffer = b;
        milisegundos = s;
        iteraciones = n;
    }

    public void run() {
        try {
            for (int i = 0; i < iteraciones; i++) {
                System.out.println(buffer.get());
                Thread.sleep(milisegundos);
            }
        } catch (InterruptedException exc) {
            System.out.println("Interrupted Execption");
        }
    }
}
