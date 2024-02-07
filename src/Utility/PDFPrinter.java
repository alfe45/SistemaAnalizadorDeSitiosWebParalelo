package Utility;

import Domain.Analizador.Solicitud;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;

import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PDFPrinter {

    public static boolean imprimir(Solicitud solicitud) {
        try {
            Document doc = new Document();
            String nombreArchivo = Encryptor.encrypt(solicitud.data_getUrl(), Encryptor.MD2);;

            PdfWriter pdfWriter = PdfWriter.getInstance(doc, new FileOutputStream(nombreArchivo + ".pdf"));
            doc.open();

            Phrase p = new Phrase("La URL del sitio es: " + solicitud.data_getUrl() + "\n");
            doc.add(p);

            p = Phrase.getInstance("El digitador de la operación fue: " + solicitud.data_getDigitador() + "\n");
            doc.add(p);

            p = Phrase.getInstance("El gestor de la operación fue: " + solicitud.data_getGestor() + "\n");
            doc.add(p);

            p = Phrase.getInstance("El analista de la operación fue: " + solicitud.data_getAnalista() + "\n");
            doc.add(p);

            p = Phrase.getInstance("El estado de la operación es: " + solicitud.data_getEstado() + "\n");
            doc.add(p);

            if (solicitud.doAnalisis1() == true) {
                p = Phrase.getInstance("La cantidad de imagenes en la página es de: " + solicitud.getData().getChild(Utility.RESULTADO).getChild(Utility.ANALISIS1).getChild(Utility.IMAGES).getAttributeValue(Utility.CANTIDAD) + "\n");
                doc.add(p);
                p = Phrase.getInstance("La cantidad de subtitulos en la página es de: " + solicitud.getData().getChild(Utility.RESULTADO).getChild(Utility.ANALISIS1).getChild(Utility.SUBTITLES).getAttributeValue(Utility.CANTIDAD) + "\n");
                doc.add(p);
                p = Phrase.getInstance("La cantidad de links en la página es de: " + solicitud.getData().getChild(Utility.RESULTADO).getChild(Utility.ANALISIS1).getChild(Utility.LINKS).getAttributeValue(Utility.CANTIDAD) + "\n");
                doc.add(p);
                p = Phrase.getInstance("La cantidad de videos en la página es de: " + solicitud.getData().getChild(Utility.RESULTADO).getChild(Utility.ANALISIS1).getChild(Utility.VIDEOS).getAttributeValue(Utility.CANTIDAD) + "\n");
                doc.add(p);
                p = Phrase.getInstance("La cantidad de títulos en la página es de: " + solicitud.getData().getChild(Utility.RESULTADO).getChild(Utility.ANALISIS1).getChild(Utility.TITLES).getAttributeValue(Utility.CANTIDAD) + "\n");
                doc.add(p);
                p = Phrase.getInstance("La cantidad de tablas en la página es de: " + solicitud.getData().getChild(Utility.RESULTADO).getChild(Utility.ANALISIS1).getChild(Utility.TABLES).getAttributeValue(Utility.CANTIDAD) + "\n");
                doc.add(p);
            }

            if (solicitud.doAnalisis2_extract_img() == true) {
                p = Phrase.getInstance("La cantidad de imagenes descargadas de la página fue de: " + solicitud.getData().getChild(Utility.RESULTADO).getChild(Utility.ANALISIS2).getChild(Utility.IMAGENES).getAttributeValue(Utility.CANTIDAD) + "\n");
                doc.add(p);
            }

            if (solicitud.doAnalisis2_extract_links() == true) {
                p = Phrase.getInstance("La cantidad de link guardados de la página fue de: " + solicitud.getData().getChild(Utility.RESULTADO).getChild(Utility.ANALISIS2).getChild(Utility.LINKS).getAttributeValue(Utility.CANTIDAD) + "\n");
                doc.add(p);
            }

            doc.close();

        }//imprimir
        catch (FileNotFoundException | DocumentException | NoSuchAlgorithmException ex) {
            return false;
//            Logger.getLogger(PDFPrinter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }//imprimir

}//class
