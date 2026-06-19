import java.util.concurrent.Semaphore;

public class ReaderWriter {
    Semaphore reader = new Semaphore(0);
    Semaphore writer = new Semaphore(1);
    Semaphore mutex = new Semaphore(1);
    int rc = 0;

    void reader(){
        while(true){
            try{
                mutex.acquire();
                rc++;
                if(rc==1) writer.acquire();
                mutex.release();

                System.out.println("Reader on CS = "+Thread.currentThread().getName());
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName()+" done reading");

                mutex.acquire();
                rc--;
                if(rc==0) writer.release();
                mutex.release();
                Thread.sleep(1000);
            }catch(InterruptedException ie){
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    void writer(){
        while (true) {
            try{
                writer.acquire();

                System.out.println();
                System.out.println("Writer is updating information");
                Thread.sleep(2000);
                System.out.println("Writer done editing");
                System.out.println();

                writer.release();
                Thread.sleep(1000);
            }catch(InterruptedException ie){
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) {
        ReaderWriter rw = new ReaderWriter();

        Thread reader1 = new Thread(rw::reader,"Reader 1");
        Thread reader2 = new Thread(rw::reader,"Reader 2");
        Thread writer = new Thread(rw::writer,"Writer");

        writer.start();
        reader1.start();
        reader2.start();
    }

}
