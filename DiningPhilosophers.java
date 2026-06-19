import java.util.concurrent.Semaphore;

public class DiningPhilosophers {
    
    Semaphore[] fork = {
        new Semaphore(1),
        new Semaphore(1),
        new Semaphore(1),
        new Semaphore(1),
        new Semaphore(1)
    };

    void philosopher(int id){
        int left = id;
        int right = (id+1)%5;
        while(true){
            try {
                System.out.println("Philosopher "+id+" is thinking");
                Thread.sleep(1000);
                if(id==4){
                    fork[right].acquire();
                    fork[left].acquire();
                }else{
                    fork[left].acquire();
                    fork[right].acquire();
                }

                System.out.println("Philosopher "+id+" is eating");
                Thread.sleep(2000);
                
                System.out.println("Philisopher "+id+" finished");

                fork[left].release();
                fork[right].release();
                
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    public static void main(String[] args) {
        DiningPhilosophers dp = new DiningPhilosophers();

        for (int i = 0; i < 5; i++) {
            final int id = i;
            new Thread(()->dp.philosopher(id),"Philosopher "+id).start();
        }
    }
}
