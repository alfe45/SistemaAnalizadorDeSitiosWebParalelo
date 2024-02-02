/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import Utility.Utility;
import org.jdom.Document;
import org.jdom.Element;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author Kevin
 */
public class Grafico {

    public Grafico(Document document,String tipoGrafico) {
        init(tipoGrafico, document);
    }
    
    
    
    
     private void initPastel(int[]datos) {
          DefaultPieDataset dataset=new DefaultPieDataset();
          dataset.setValue(Utility.IMAGES, datos[0]);
          dataset.setValue(Utility.LINKS, datos[1]);
          dataset.setValue(Utility.VIDEOS, datos[2]);
          dataset.setValue(Utility.TITLES, datos[3]);
          dataset.setValue(Utility.SUBTITLES, datos[4]);
          dataset.setValue(Utility.TABLES, datos[5]);
          JFreeChart chart= ChartFactory.createPieChart(Utility.ANALISIS1, 
                 dataset,
                 true,
                 true,
                 false
                );
           ChartFrame frame= new ChartFrame("Grafica", chart);
           frame.pack();
           frame.setVisible(true);
          System.out.println("final");
          
    }

    private void init(String tipoGrafico, Document document) {
        Element element=document.getRootElement();
//        Element eAnalisis1 = element.getChild(Utility.ANALISIS1);
//        Element eAnalisis2 = element.getChild(Utility.ANALISIS2);
//        Element eAnalisis3 = element.getChild(Utility.ANALISIS3);
        tipoGrafico=Utility.GRAFICO_BARRAS;
        int[]datos=new int[6];
        
        if(element!=null){
             
           datos[0] = Integer.parseInt(element.getChild(Utility.IMAGES).getAttributeValue(Utility.CANTIDAD));
            datos[1] = Integer.parseInt(element.getChild(Utility.LINKS).getAttributeValue(Utility.CANTIDAD));
            datos[2] = Integer.parseInt(element.getChild(Utility.VIDEOS).getAttributeValue(Utility.CANTIDAD));
            datos[3] = Integer.parseInt(element.getChild(Utility.TITLES).getAttributeValue(Utility.CANTIDAD));
            datos[4] = Integer.parseInt(element.getChild(Utility.SUBTITLES).getAttributeValue(Utility.CANTIDAD));
            datos[5] = Integer.parseInt(element.getChild(Utility.TABLES).getAttributeValue(Utility.CANTIDAD));
            
            if (tipoGrafico.equals(Utility.GRAFICO_BARRAS))
                initBarras(datos);
            else if (tipoGrafico.equals(Utility.GRAFICO_PASTEL))
                initPastel(datos);
       
       
        }
        
//         if(eAnalisis2!=null){
//            
//        }
//          if(eAnalisis3!=null){
//            
//        }
          
          
          
          
        
       
    }

    private void initBarras(int[] datos) {
         DefaultCategoryDataset dataset= new DefaultCategoryDataset();
         dataset.setValue(datos[0],Utility.IMAGES,Utility.IMAGES );
          dataset.setValue(datos[1],Utility.LINKS,Utility.LINKS);
          dataset.setValue(datos[2],Utility.VIDEOS,Utility.VIDEOS);
          dataset.setValue(datos[3],Utility.TITLES,Utility.TITLES);
          dataset.setValue(datos[4],Utility.SUBTITLES,Utility.SUBTITLES);
          dataset.setValue(datos[5],Utility.TABLES,Utility.TABLES);
          
           JFreeChart chart= ChartFactory.createBarChart("Analisis 1",
                "Elementos",
                Utility.CANTIDAD, 
                dataset,
                PlotOrientation.HORIZONTAL,
                true,
                false, false
                );  
           ChartFrame frame= new ChartFrame("Grafica", chart);
                frame.pack();
                frame.setVisible(true);
        
    }
}
