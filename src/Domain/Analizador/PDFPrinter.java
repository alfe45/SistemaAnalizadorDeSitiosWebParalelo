/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Domain.Analizador;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import org.jdom.Element;

/**
 *
 * @author Kevin
 */
public class PDFPrinter {

    public PDFPrinter( Element[] elements,String tituloPagina)  {
        Document d=new Document();
        
    }
    
    
    
    public void imprimir(Document d,String tituloPagina) throws FileNotFoundException, DocumentException{
        PdfWriter pw=PdfWriter.getInstance(d, new FileOutputStream(tituloPagina));
        d.open();
        
        Phrase p=new Phrase("Aquí va lo que se quiere escribir");
        p.setFont(new Font());//Fuente
        
        d.add(p);
        
   
        
        d.newPage();//Nueva pagina
        
        d.close();
    }
            
    
}
