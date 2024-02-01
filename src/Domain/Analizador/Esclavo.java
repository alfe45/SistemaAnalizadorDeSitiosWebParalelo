
package Domain.Analizador;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Esclavo {
    
    ExecutorService executorService;
    
    public Esclavo(int subprocesos) {
        this.executorService = Executors.newFixedThreadPool(subprocesos);
    }
    
}//class
