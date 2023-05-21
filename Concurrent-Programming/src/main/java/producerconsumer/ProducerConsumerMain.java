package producerconsumer;

public class ProducerConsumerMain {
    public static void main (String[] args){
        Buffer buffer = new Buffer(5);
        Productor miproductor;
        Consumidor miconsumidor;
        miproductor = new Productor (buffer, 1000, 20);
        miproductor.start();
        miconsumidor = new Consumidor (buffer, 3000, 20);
        miconsumidor.start();
    }
}
