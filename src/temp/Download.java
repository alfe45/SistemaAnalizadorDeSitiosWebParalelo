package temp;

import Domain.Analizador.Analisis;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Download implements Runnable{

    private URL url;
    private String rute;
    private int id;
    private String extension;
    private Analisis analisis;

    public Download(String url, String rute, int id, Analisis analisis) throws MalformedURLException {
        this.url = new URL(url);
        this.id = id;
        String[] array = url.split("\\.");
        this.extension = "." + array[array.length - 1];
        this.rute = rute + "/img_" + id + extension;
        this.analisis = analisis;
    }

    @Override
    public void run() {
        try {
            InputStream inputStream = url.openStream();
            FileOutputStream fileOutputStream = new FileOutputStream(rute);
            byte[] buffer = new byte[10024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                fileOutputStream.write(buffer, 0, bytesRead);
                System.out.println("(" + id + ") Downloading : " + rute);
            }//while
            System.out.println("(" + id + ") Downloaded: " + rute);
            try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Analisis.class.getName()).log(Level.SEVERE, null, ex);
                }
                analisis.taskCompleted("task_download"+id);
        } catch (IOException e) {
            System.out.println("Couldn't download: " + url);
            analisis.taskCompleted("task_download"+id);
//            System.err.println("Couldn't download: " + url + ": " + e.getMessage());
        }//try
    }//run

}//class
