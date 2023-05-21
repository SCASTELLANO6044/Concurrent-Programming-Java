package Badsynchronization;

public class MalaSincronizacion {

    static class Contador{
        private int valor;
        public Contador(){
            valor = 0;
        }
        public /*synchronized*/ void incrementa(){
            valor++;
        }
        int dameValor(){
            return valor;
        }
    }

    static class HiloIncrementador extends Thread {
        private Contador contador;
        private int incrementos;
        public HiloIncrementador(Contador contador, int incrementos){
            this.contador = contador;
            this.incrementos = incrementos;
        }
        @Override
        public void run(){
            for (int i = 0; i < incrementos; i++){
                contador.incrementa();
            }
        }
    }

    public static void main(String[] ars){
        Contador contador = new Contador();
        HiloIncrementador hilo1 = new HiloIncrementador(contador, 1000);
        HiloIncrementador hilo2 = new HiloIncrementador(contador, 1056);
        hilo1.start();
        hilo2.start();
        try {
            hilo1.join();
            hilo2.join();
        }catch (InterruptedException e){
            System.out.println("Interrupted thread");
        }
        System.out.println("Valor final: "+contador.dameValor());
    }
}
