package Domain.Analizador;

import java.util.Queue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Esclavo {

    private ExecutorService executorService;
    private int subprocesos;
    private int id;
    private Queue<Runnable> tasks;

    public Esclavo(int subprocesos, int id) {
        this.executorService = Executors.newFixedThreadPool(subprocesos);
        this.subprocesos = subprocesos;

        this.id = id;
        this.tasks = new LinkedBlockingDeque<>(subprocesos);
    }

    public void shutDownExecutorService() {
        this.executorService.close();
    }//shutDownExecutorService

    public boolean addTask(Runnable task) {
        return this.tasks.offer(task);
    }//addTask

    public int getId() {
        return id;
    }

    public void runTasks() {
        while (!tasks.isEmpty()) {    
            this.executorService.execute(tasks.poll());
        }
    }//runTasks

}//class
