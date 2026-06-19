import java.util.concurrent.Semaphore;
import java.util.*;

public class ProducerConsumer {
    private final Queue<Integer> buffer = new LinkedList<>();
    private final int MAX_SIZE = 5;

    Semaphore mutex = new Semaphore(1);
    Semaphore empty = new Semaphore(MAX_SIZE);
    Semaphore full = new Semaphore(0);

    void produce(){
        int value = 0;
        while(value<10){
            try{
                empty.acquire();
                mutex.acquire();

                value++;
                buffer.add(value);
                System.out.println("Producer produced item "+value);
                Thread.sleep(500);

                mutex.release();
                full.release();
            }catch(InterruptedException ie){
                Thread.currentThread().interrupt();
            }
        }
    }

    void consume(){
        int consumed = 0;
        while(consumed<10){
            try {
                full.acquire();
                mutex.acquire();
                
                int value = buffer.poll();
                System.out.println("Consumer consumed item "+value);
                consumed++;
                Thread.sleep(900);

                mutex.release();
                empty.release();
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) {
        ProducerConsumer pc = new ProducerConsumer();
        Thread prod = new Thread(pc::produce,"Producer");
        Thread cons = new Thread(pc::consume,"Consumer");

        prod.start();
        cons.start();
    }
}
