package Domain.Analizador;

import Domain.Sistema.SistemaSingleton;
import Utility.Encryptor;
import Utility.Utility;
import java.io.File;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import temp.Download;

public class Analisis extends Thread {

    private Solicitud solicitud;
    private List<Esclavo> esclavos;
    private List<Runnable> tasks;
    private Document doc;
    private boolean running;
    private boolean flag_saveSolicitud;
    private int pendingTasks;
    private int startedTasks;
    private org.jdom.Element data_analisis1;
    private org.jdom.Element data_analisis2;
    private org.jdom.Element data_analisis3;

    private final Analisis analisis = this;

    public Analisis(Solicitud solicitud, int subprocesos, int esclavos) {
        this.running = false;
        this.flag_saveSolicitud = false;
        this.solicitud = solicitud;
        this.esclavos = new ArrayList<>();
        this.tasks = new ArrayList<>();
        for (int i = 0; i < esclavos; i++) {
            this.esclavos.add(new Esclavo(subprocesos, i + 1));
        }
        this.data_analisis1 = new org.jdom.Element(Utility.ANALISIS1);
        this.data_analisis2 = new org.jdom.Element(Utility.ANALISIS2);
        this.data_analisis3 = new org.jdom.Element(Utility.ANALISIS3);
        this.pendingTasks = 0;
        this.startedTasks = 0;
    }

    @Override
    public void run() {
        System.out.println("Analisis iniciado\n");

        loadTasks();
        runTasks();

        while (running) {
            if (!flag_saveSolicitud) {
                try {
                    synchronized (this) {
                        System.out.println("Waiting");
                        wait();
                    }
                } catch (InterruptedException ex) {
                    Logger.getLogger(Analisis.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                killThreads();
                running = false;
                //guarda la solicitud y sus resultados en el archivo
                this.solicitud.getData().getChild(Utility.RESULTADO).addContent(data_analisis1);
                this.solicitud.getData().getChild(Utility.RESULTADO).addContent(data_analisis2);
                this.solicitud.getData().getChild(Utility.RESULTADO).addContent(data_analisis3);
                this.solicitud.data_setEstado(Utility.ESTADO_FINALIZADO);
                SistemaSingleton.getInstance().sobreEscribirSolicitud(solicitud);//editar
                break;
            }
        }
        System.out.println("Analisis finalizado");
    }//run

    public synchronized List<Runnable> analisisDeElementos() throws IOException, NoSuchAlgorithmException, KeyManagementException {
        List<Runnable> tasks = new ArrayList<>();

        Runnable task_Subtitles = new Runnable() {
            @Override
            public void run() {
                System.out.println("Runnable iniciado: task_Subtitles");
                int subtitulos = doc.getElementsByTag(Utility.TAG_H1).size();
                subtitulos += doc.getElementsByTag(Utility.TAG_H2).size();
                subtitulos += doc.getElementsByTag(Utility.TAG_H3).size();
                subtitulos += doc.getElementsByTag(Utility.TAG_H4).size();
                subtitulos += doc.getElementsByTag(Utility.TAG_H5).size();
                subtitulos += doc.getElementsByTag(Utility.TAG_H6).size();

                org.jdom.Element eSubtitulos = new org.jdom.Element(Utility.SUBTITLES);
                eSubtitulos.setAttribute(Utility.CANTIDAD, subtitulos + "");
                data_analisis1.addContent(eSubtitulos);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Analisis.class.getName()).log(Level.SEVERE, null, ex);
                }
                taskCompleted("task_Subtitles");

            }
        };
        tasks.add(task_Subtitles);

        Runnable task_imagenes = new Runnable() {
            @Override
            public void run() {
                System.out.println("Runnable iniciado: task_imagenes");
                org.jdom.Element eImagenes = new org.jdom.Element(Utility.IMAGES);
                eImagenes.setAttribute(Utility.CANTIDAD,
                        doc.getElementsByTag(Utility.TAG_IMG).size() + "");
                data_analisis1.addContent(eImagenes);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Analisis.class.getName()).log(Level.SEVERE, null, ex);
                }
                taskCompleted("task_imagenes");
            }
        };
        tasks.add(task_imagenes);

        Runnable task_links = new Runnable() {
            @Override
            public void run() {
                System.out.println("Runnable iniciado: task_links");
                org.jdom.Element eA = new org.jdom.Element(Utility.LINKS);
                eA.setAttribute(Utility.CANTIDAD,
                        doc.getElementsByTag(Utility.TAG_A).size() + "");
                data_analisis1.addContent(eA);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Analisis.class.getName()).log(Level.SEVERE, null, ex);
                }
                taskCompleted("task_links");
            }
        };
        tasks.add(task_links);

        Runnable task_videos = new Runnable() {
            @Override
            public void run() {
                System.out.println("Runnable iniciado: task_videos");
                org.jdom.Element eVideos = new org.jdom.Element(Utility.VIDEOS);
                eVideos.setAttribute(Utility.CANTIDAD,
                        doc.getElementsByTag(Utility.TAG_VIDEO).size() + "");
                data_analisis1.addContent(eVideos);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Analisis.class.getName()).log(Level.SEVERE, null, ex);
                }
                taskCompleted("task_videos");
            }
        };
        tasks.add(task_videos);

        Runnable task_titulos = new Runnable() {
            @Override
            public void run() {
                System.out.println("Runnable iniciado: task_titulos");
                org.jdom.Element eTitulos = new org.jdom.Element(Utility.TITLES);
                eTitulos.setAttribute(Utility.CANTIDAD,
                        doc.getElementsByTag(Utility.TAG_TITLE).size() + "");
                data_analisis1.addContent(eTitulos);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Analisis.class.getName()).log(Level.SEVERE, null, ex);
                }
                taskCompleted("task_titulos");
            }
        };
        tasks.add(task_titulos);

        Runnable task_tablas = new Runnable() {
            @Override
            public void run() {
                System.out.println("Runnable iniciado: task_tablas");
                org.jdom.Element eTablas = new org.jdom.Element(Utility.TABLES);
                eTablas.setAttribute(Utility.CANTIDAD,
                        doc.getElementsByTag(Utility.TAG_TABLE).size() + "");
                data_analisis1.addContent(eTablas);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Analisis.class.getName()).log(Level.SEVERE, null, ex);
                }
                taskCompleted("task_tablas");
            }
        };
        tasks.add(task_tablas);
        System.out.println("Tasks analisisDeElementos: " + tasks.size());
        return tasks;
    }//analisisDeElementos

    protected List<Runnable> analisisDeElementosyExtraccion() throws IOException, NoSuchAlgorithmException, KeyManagementException {
        List<Runnable> tasks = new ArrayList<>();

        if (solicitud.doAnalisis2_extract_img()) {
            File dir = new File("./downloads");
            if (!dir.exists()) {
                if (dir.mkdirs()) {
                    System.out.println("Directorio creado " + dir.getAbsolutePath());
                } else {
                    System.out.println("Error al crear directorio " + dir.getAbsolutePath());
                }
            }
            int i = 0;
            for (Element element : doc.getElementsByTag(Utility.TAG_IMG)) {
                String url = element.attr(Utility.ATTRI_SRC);
                String rute = dir.getPath() + "\\" + Encryptor.encrypt(solicitud.data_getUrl(), Encryptor.MD2);
                File dir2 = new File(rute);
                if (!dir2.exists()) {
                    if (dir2.mkdirs()) {
                        System.out.println("Directorio creado " + dir2.getAbsolutePath());
                    } else {
                        System.out.println("Error al crear directorio " + dir2.getAbsolutePath());
                    }
                }
                tasks.add(new Download(url, rute, i, this));
                i++;
            }
            org.jdom.Element eCantidadImagenes = new org.jdom.Element(Utility.IMAGENES);
            eCantidadImagenes.setAttribute(Utility.CANTIDAD, doc.getElementsByTag(Utility.TAG_IMG).size() + "");
            this.data_analisis2.addContent(eCantidadImagenes);

        }
        if (solicitud.doAnalisis2_extract_links()) {
            Runnable task_extraerLinks = new Runnable() {
                @Override
                public void run() {
                    System.out.println("Runnable iniciado: task_extraerLinks");
                    org.jdom.Element eLinksExtraidos = new org.jdom.Element(Utility.LINKS);
                    System.out.println("Links: " + doc.getElementsByTag(Utility.TAG_A).size());
                    int cantidad = 0;
                    for (Element element : doc.getElementsByTag(Utility.TAG_A)) {
                        String linkText = element.attr(Utility.ATTRI_HREF);

                        if (!linkText.isBlank() && linkText.contains("http")) {
                            org.jdom.Element eLink = new org.jdom.Element(Utility.LINK);
                            eLink.addContent(linkText);
                            eLinksExtraidos.addContent(eLink);
                            cantidad++;
                        }
                    }
                    eLinksExtraidos.setAttribute(Utility.CANTIDAD, cantidad + "");
                    data_analisis2.addContent(eLinksExtraidos);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Analisis.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    taskCompleted("task_extraerLinks");
                }
            };
            tasks.add(task_extraerLinks);
        }

        System.out.println("Tasks analisisDeElementosyExtraccion: " + tasks.size());
        return tasks;
    }//analisisDeElementosyExtraccion

    protected List<Runnable> analisisDeExtraccionYComparacion() {
        List<Runnable> tasks = new ArrayList<>();
        //agregar tareas
        System.out.println("Tasks analisisDeExtraccionYComparacion: " + tasks.size());
        return tasks;
    }//analisisDeExtraccionYComparacion

    protected boolean validarSolicitud(Solicitud solicitud) throws NoSuchAlgorithmException, IOException, KeyManagementException {
        boolean urlValida = urlValida = AnalizadorURL.conectarURL(solicitud.data_getUrl()) != null;
        return (solicitud.esValida() && urlValida);
    }//validarSolicitud

    private boolean loadTasks() {
        try {
            this.doc = AnalizadorURL.conectarURL(this.solicitud.data_getUrl());
        } catch (IOException | NoSuchAlgorithmException | KeyManagementException ex) {
            Logger.getLogger(Analisis.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

        boolean analisis1 = this.solicitud.doAnalisis1();
        boolean analisis2_img = this.solicitud.doAnalisis2_extract_img();
        boolean analisis2_links = this.solicitud.doAnalisis2_extract_links();
        boolean analisis2 = analisis2_img || analisis2_links;
        boolean analisis3 = this.solicitud.doAnalisis3();

        if (analisis1) {
            try {
                List<Runnable> tasks_Analisis1 = analisisDeElementos();
                for (Runnable runnable : tasks_Analisis1) {
                    this.tasks.add(runnable);
                }
            } catch (IOException | NoSuchAlgorithmException | KeyManagementException ex) {
                Logger.getLogger(Analisis.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (analisis2) {
            try {
                List<Runnable> tasks_Analisis2 = analisisDeElementosyExtraccion();
                for (Runnable runnable : tasks_Analisis2) {
                    this.tasks.add(runnable);
                }
            } catch (IOException | NoSuchAlgorithmException | KeyManagementException ex) {
                Logger.getLogger(Analisis.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        if (analisis3) {
            List<Runnable> tasks_Analisis2 = analisisDeExtraccionYComparacion();
            for (Runnable runnable : tasks_Analisis2) {
                this.tasks.add(runnable);
            }
        }
        this.pendingTasks = tasks.size();
        System.out.println("Loaded tasks: " + tasks.size() + "\n");
        return true;
    }//loadTasks

    private boolean runTasks() {
        this.running = true;
        while (!this.tasks.isEmpty()) {
            for (Esclavo esclavo : esclavos) {
                for (Runnable task : tasks) {
                    if (esclavo.addTask(task)) {
                        System.out.println("Task agregada a esclavo " + esclavo.getId());
                        this.tasks.remove(task);
                        break;
                    }
                }
            }
            for (Esclavo esclavo : esclavos) {
                esclavo.runTasks();
            }
        }
        for (Esclavo esclavo : esclavos) {
            esclavo.shutDownExecutorService();
        }
        return true;
    }//runTasks

    private void killThreads() {
        for (Esclavo esclavo : esclavos) {
            esclavo.shutDownExecutorService();
        }
    }//killThreads

    public void taskCompleted(String taskName) {
        synchronized (analisis) {
            this.pendingTasks--;
            System.out.println("Runnable terminado: " + taskName + " Pending tasks: " + pendingTasks);
            if (this.pendingTasks == 0) {
                System.out.println("NotifyAll() : " + taskName + "\n");
                flag_saveSolicitud = true;
                analisis.notifyAll();
            }
        }
    }//taskCompleted

}//class
