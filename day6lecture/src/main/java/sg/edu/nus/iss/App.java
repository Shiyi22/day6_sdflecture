package sg.edu.nus.iss;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Hello world!
 */
public final class App {
    private App() {
    }

    /**
     * Says hello to the world.
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
        // #1 to show the 'long' way
        // Thread thread1 = new Thread(new Runnable() {
        //     @Override
        //     public void run() {
        //         for (int i = 0; i < 20; i++) {
        //             System.out.println(Thread.currentThread().getName() + 
        //             " \tRunnable ..." + i);
        //         }
        //     }
        // }); // note the difference in brackets format 
        // thread1.start(); 
        
        // #2 to show if we use Runnable interface, but on single thread 
        MyRunnableImplementation mRI = new MyRunnableImplementation("Task 1");
        MyRunnableImplementation mRI2 = new MyRunnableImplementation("Task 2"); 
        MyRunnableImplementation mRI3 = new MyRunnableImplementation("Task 3"); 
        MyRunnableImplementation mRI4 = new MyRunnableImplementation("Task 4"); 
        MyRunnableImplementation mRI5 = new MyRunnableImplementation("Task 5"); 

        // Thread thread2 = new Thread(mRI); 
        // thread2.start(); 

        // Thread thread3 = new Thread(mRI); 
        // thread3.start(); 

        // ExecutorService executorService = Executors.newSingleThreadExecutor(); 
        // executorService.execute(mRI); // task1
        // executorService.execute(mRI2); // task2 only gets executed after task1
        // executorService.shutdown(); // to release the memory 
        
        // #3 to show if we use Runnable interface, but on fixed thread pool (multi) 
        // ExecutorService executorService = Executors.newFixedThreadPool(3);
        // executorService.execute(mRI);
        // executorService.execute(mRI2); 
        // executorService.execute(mRI3); 
        // executorService.execute(mRI4); // task 4 and 5 can only run when thread is freed up from task1-3 
        // executorService.execute(mRI5); 
        // executorService.shutdown(); // to release the memory 

        ExecutorService executorService = Executors.newCachedThreadPool(); // threads will increase if there are more task to execute >> up to max your processor can handle 
        executorService.execute(mRI);
        executorService.execute(mRI2); 
        executorService.execute(mRI3); 
        executorService.execute(mRI4);
        executorService.execute(mRI5); 
        executorService.shutdown(); // to release the memory 

        // functional interface: below is the lambda expression 
        MyRunnableInterface<Integer> addOperation = (a,b) -> {
            return a + b; 
        };
        MyRunnableInterface<Integer> multiplyOperation = (a,b) -> {
            return a * b; 
        }; 
        MyRunnableInterface<Integer> minusOperation = (a, b) -> {
            return a - b; 
        }; 
        MyRunnableInterface<String> concatString = (a ,b) -> {
            return a + b; 
        };

        System.out.printf("addOperation: %d\n", addOperation.process(1, 1));
        System.out.printf("multiplyOperation: %d\n", multiplyOperation.process(2,5));
        System.out.printf("minusOperation: %d\n", minusOperation.process(2,5));
        System.out.printf("concatString: %s\n", concatString.process("The quick brown fox ", "jumps over the wall."));
    }
}
