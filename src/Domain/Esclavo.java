
package Domain;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Esclavo {
    
    private int subprocesos;

    ExecutorService executorService;
    
    public Esclavo(int subprocesos) {
        this.subprocesos = subprocesos;
        this.executorService = Executors.newFixedThreadPool(subprocesos);

    }
    
    public int getSubprocesos() {
        return subprocesos;
    }

    public void setSubprocesos(int subprocesos) {
        this.subprocesos = subprocesos;
    }
    
    
    
    
    
    
}//class
