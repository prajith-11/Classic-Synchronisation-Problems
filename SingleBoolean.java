// import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class SingleBoolean {

    AtomicBoolean flag = new AtomicBoolean(false);

    void cs(){
        while(!flag.compareAndSet(false,true))
            Thread.onSpinWait();
        System.out.println("Accessing critical section "+Thread.currentThread().getName());
        try{
            Thread.sleep(10000);
        }catch(InterruptedException ie){
            System.out.println(Thread.currentThread().getName()+" is leaving");
        }
        flag.set(false);
    }

    public static void main(String[] args) {
        SingleBoolean lockSystem = new SingleBoolean();

        Thread t1 = new Thread(lockSystem::cs,"Thread A");
        Thread t2 = new Thread(lockSystem::cs,"Thread B");

        t1.start();
        t2.start();
    }
}
