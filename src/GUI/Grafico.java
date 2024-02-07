package GUI;

import Domain.Analizador.Solicitud;
import Utility.Utility;
import java.util.ArrayList;
import org.jdom.Element;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

public class Grafico {

    public static void crearGraficoAnalisis1(String tipoGrafico, Solicitud solicitud) {
        //Se asigna como raiz el elemento que contiene los datos
        Element element = solicitud.getData().getChild(Utility.RESULTADO).getChild(Utility.ANALISIS1);
        int[] datos = new int[6];

        //Se comprueba que la raiz existe
        if (element != null) {
            //Se asignan los datos en valores enteros para usarse en el gráfico
            datos[0] = Integer.parseInt(element.getChild(Utility.IMAGES).getAttributeValue(Utility.CANTIDAD));
            datos[1] = Integer.parseInt(element.getChild(Utility.LINKS).getAttributeValue(Utility.CANTIDAD));
            datos[2] = Integer.parseInt(element.getChild(Utility.VIDEOS).getAttributeValue(Utility.CANTIDAD));
            datos[3] = Integer.parseInt(element.getChild(Utility.TITLES).getAttributeValue(Utility.CANTIDAD));
            datos[4] = Integer.parseInt(element.getChild(Utility.SUBTITLES).getAttributeValue(Utility.CANTIDAD));
            datos[5] = Integer.parseInt(element.getChild(Utility.TABLES).getAttributeValue(Utility.CANTIDAD));

            //Le pasa los datos al gráfico requerido
            if (tipoGrafico.equals(Utility.GRAFICO_BARRAS)) {
                initBarrasAnalisis1(datos);
            } else if (tipoGrafico.equals(Utility.GRAFICO_PASTEL)) {
                initPastelAnalisis1(datos);
            }
        }
    }//crearGraficoAnalisis1

    private static void initPastelAnalisis1(int[] datos) {
        //Se asignan los datos que usará el gráfico
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue(Utility.IMAGES, datos[0]);
        dataset.setValue(Utility.LINKS, datos[1]);
        dataset.setValue(Utility.VIDEOS, datos[2]);
        dataset.setValue(Utility.TITLES, datos[3]);
        dataset.setValue(Utility.SUBTITLES, datos[4]);
        dataset.setValue(Utility.TABLES, datos[5]);

        //Se crea el gráfico con los datos asignados
        JFreeChart chart = ChartFactory.createPieChart(Utility.ANALISIS1,
                dataset,
                true,
                true,
                false
        );
        ChartFrame frame = new ChartFrame("Grafica", chart);
        frame.pack();
        frame.setVisible(true);
    }//initPastelAnalisis1

    private static void initBarrasAnalisis1(int[] datos) {
        //Se asignan los datos que usará el gráfico
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.setValue(datos[0], Utility.IMAGES, Utility.IMAGES);
        dataset.setValue(datos[1], Utility.LINKS, Utility.LINKS);
        dataset.setValue(datos[2], Utility.VIDEOS, Utility.VIDEOS);
        dataset.setValue(datos[3], Utility.TITLES, Utility.TITLES);
        dataset.setValue(datos[4], Utility.SUBTITLES, Utility.SUBTITLES);
        dataset.setValue(datos[5], Utility.TABLES, Utility.TABLES);

        //Se crea el gráfico con los datos asignados
        JFreeChart chart = ChartFactory.createBarChart("Analisis 1",
                "Elementos",
                Utility.CANTIDAD,
                dataset,
                PlotOrientation.HORIZONTAL,
                true,
                false, false
        );
        ChartFrame frame = new ChartFrame("Grafica", chart);
        frame.pack();
        frame.setVisible(true);
    }//initBarrasAnalisis1

    public static void crearGraficoAnalisis2(String tipoGrafico, Solicitud solicitud) {
        //Se asigna como raiz el elemento que contiene los datos
        Element element = solicitud.getData().getChild(Utility.RESULTADO).getChild(Utility.ANALISIS2);

        boolean imagenes = false;//Este objeto avisará si se transmiten imagenes
        boolean links = false;//Este objeto avisará si se transmiten links

        ArrayList<Integer> datos = new ArrayList<>();
        //Se añaden los datos al arraylist
        if (solicitud.getData().getChild(Utility.ANALISIS2).getAttributeValue(Utility.ANALISIS2_IMG).equals("true")) {//Se comprueba si el boolean de imagenes es true
            datos.add(Integer.parseInt(element.getChild(Utility.IMAGENES).getAttributeValue(Utility.CANTIDAD)));//Se añaden los datos de las imagenes
            imagenes = true;
        }

        if (solicitud.getData().getChild(Utility.ANALISIS2).getAttributeValue(Utility.ANALISIS2_LINKS).equals("true")) {//Se comprueba si el boolean de links es true
            datos.add(Integer.parseInt(element.getChild(Utility.LINKS).getAttributeValue(Utility.CANTIDAD)));//Se añaden los datos de los links
            links = true;
        }

        //Le pasa los datos al gráfico requerido
        if (tipoGrafico.equals(Utility.GRAFICO_BARRAS)) {
            initBarrasAnalisis2(datos, imagenes, links);
        } else if (tipoGrafico.equals(Utility.GRAFICO_PASTEL)) {
            initPastelAnalisis2(datos, imagenes, links);
        }

    }//initAnalicrearGraficoAnalisis2

    private static void initBarrasAnalisis2(ArrayList<Integer> datos, boolean imagenes, boolean links) {
        //Se asignan los datos que usará el gráfico
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        //Se comprueba si se tranmitieron imagenes
        if (imagenes) {
            dataset.setValue(datos.get(0), Utility.IMAGES, Utility.IMAGES);
        }

        if (links) {
            dataset.setValue(datos.get(datos.size() - 1), Utility.LINKS, Utility.LINKS);
        }

        //Se crea el gráfico con los datos asignados
        JFreeChart chart = ChartFactory.createBarChart("Análisis 2",
                "Elementos",
                Utility.CANTIDAD,
                dataset,
                PlotOrientation.HORIZONTAL,
                true,
                false, false
        );
        ChartFrame frame = new ChartFrame("Grafica", chart);
        frame.pack();
        frame.setVisible(true);
    }//initBarrasAnalisis2

    private static void initPastelAnalisis2(ArrayList<Integer> datos, boolean imagenes, boolean links) {
        //Se asignan los datos que usará el gráfico
        DefaultPieDataset dataset = new DefaultPieDataset();

        //Se comprueba si se tranmitieron imagenes
        if (imagenes) {
            dataset.setValue(Utility.IMAGES, datos.get(0));
        }

        if (links) {
            dataset.setValue(Utility.LINKS, datos.get(datos.size() - 1));
        }

        //Se crea el gráfico con los datos asignados
        JFreeChart chart = ChartFactory.createPieChart("Análisis 2",
                dataset,
                true,
                true,
                false
        );
        ChartFrame frame = new ChartFrame("Grafica", chart);
        frame.pack();
        frame.setVisible(true);
    }//initPastelAnalisis2

}//class
