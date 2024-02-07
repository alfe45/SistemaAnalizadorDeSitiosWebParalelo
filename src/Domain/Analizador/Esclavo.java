package Domain.Analizador;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.jdom.Element;

public class Esclavo {

    private ExecutorService executorService;
    private Element data;
    private int subprocesos;
    private static int subprocesosUsados;

    public Esclavo(int subprocesos) {
        this.executorService = Executors.newFixedThreadPool(subprocesos);
        this.subprocesos = subprocesos;
        this.subprocesosUsados = 0;
    }

    public boolean execute(Runnable task) {
        if (subprocesosUsados < subprocesos) {
            this.executorService.execute(task);
            this.subprocesosUsados += 1;
            return true;
        }
        return false;
    }//execute

    public void shutDownExecutorService() {
        this.executorService.close();
    }//shutDownExecutorService

}//class
