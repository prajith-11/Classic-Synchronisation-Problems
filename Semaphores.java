import java.util.concurrent.Semaphore;

public class Semaphores {

    Semaphore s = new Semaphore(1);
    static int cnt = 0;

    void accessCriticalSection(){
        int x = 0;
        try{
            s.acquire();
            x = cnt;
            System.out.println(Thread.currentThread().getName()+" is accessing critical section");
            Thread.sleep(5000);
            x++;
            System.out.println(Thread.currentThread().getName()+" is leaving");
            cnt = x;
        }catch(InterruptedException ie){
            Thread.currentThread().interrupt();
        }finally{
            s.release();
        }
    }

    public static void main(String[] args) throws InterruptedException{
        Semaphores sem = new Semaphores();
        Thread t1 = new Thread(sem::accessCriticalSection,"Thread A");
        Thread t2 = new Thread(sem::accessCriticalSection,"Thread B");

        t1.start();
        t2.start();

        t1.join();
        t2.join();
        System.out.println(cnt);
    }
}
