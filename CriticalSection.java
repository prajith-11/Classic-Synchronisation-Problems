import java.util.concurrent.locks.ReentrantLock;

public class CriticalSection {

    // Using locks to solve critical section problem
    static ReentrantLock lock = new ReentrantLock();

    static int count = 0;
    public static void main(String[] args) throws InterruptedException {
        CriticalSection.runDemo();
        
    }

    static void runDemo() throws InterruptedException{
        Runnable work = ()->{
            // Chose 625 as this is the minimum number where i got different answer.
            for(int i=0;i<100_000_000;i++){
                operate();
            }
        };
        Thread t1 = new Thread(work);
        Thread t2 = new Thread(work);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(count);
    }

    static void operate(){
        // remove the first and last line to get wrong values in the final output
        lock.lock();
        count++;
        lock.unlock();
    }
}
