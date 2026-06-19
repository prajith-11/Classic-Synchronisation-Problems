public class CriticalSectionProblem {
    static int cnt = 0;

    void accessCriticalSection(){
        int x = 0;
        try{
            x = cnt;
            System.out.println(Thread.currentThread().getName()+" is accessing critical section");
            Thread.sleep(5000);
            x++;
            System.out.println(Thread.currentThread().getName()+" is leaving");
            cnt = x;
        }catch(InterruptedException ie){
            Thread.currentThread().interrupt();
        }finally{
        }
    }

    public static void main(String[] args) throws InterruptedException{
        CriticalSectionProblem csp = new CriticalSectionProblem();
        Thread t1 = new Thread(csp::accessCriticalSection,"Thread A");
        Thread t2 = new Thread(csp::accessCriticalSection,"Thread B");

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(cnt);
        // Expected value is 2 but getting only 1
        // Fixed this issue using semaphores in Sempahores.java
    }
}
