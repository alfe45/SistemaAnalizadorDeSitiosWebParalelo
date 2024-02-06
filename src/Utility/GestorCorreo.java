package Utility;

import java.io.File;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class GestorCorreo {

    private static String correoEmisor = "kvegamendez@gmail.com";
    private static String passwordFrom = "xqdprcjkdjxjztkg";

    public GestorCorreo() {
    }

    public static boolean sendEmail(String correoDestino, String asunto, String contenido, File archivoPDF) {
        if (correoDestino.isBlank() || asunto.isBlank() || contenido.isBlank()) {
            return false;
        } else {
            Properties mProperties = new Properties();

            // Simple mail transfer protocol
            mProperties.put("mail.smtp.host", "smtp.gmail.com");
            mProperties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
            mProperties.setProperty("mail.smtp.starttls.enable", "true");
            mProperties.setProperty("mail.smtp.port", "587");
            mProperties.setProperty("mail.smtp.user", correoEmisor);
            mProperties.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
            mProperties.setProperty("mail.smtp.auth", "true");

            Session mSession = Session.getDefaultInstance(mProperties);

            MimeMultipart mElementoCorreo = new MimeMultipart();

            //Agregar contenido del correo
            MimeBodyPart mContendio = new MimeBodyPart();
            try {
                mContendio.setContent(contenido, "text/html; charset=utf-8");
                mElementoCorreo.addBodyPart(mContendio);
            } catch (MessagingException ex) {
                Logger.getLogger(GestorCorreo.class.getName()).log(Level.SEVERE, null, ex);
            }

            //Agregar el PDF
            MimeBodyPart mAdjunto = new MimeBodyPart();
            try {
                mAdjunto.setDataHandler(new DataHandler(new FileDataSource(archivoPDF.getAbsolutePath())));
                mAdjunto.setFileName(archivoPDF.getName());
                mElementoCorreo.addBodyPart(mAdjunto);
            } catch (MessagingException ex) {
                Logger.getLogger(GestorCorreo.class.getName()).log(Level.SEVERE, null, ex);
            }

            MimeMessage mCorreo = new MimeMessage(mSession);
            try {
                mCorreo.setFrom(new InternetAddress(correoEmisor));
                mCorreo.setRecipient(Message.RecipientType.TO, new InternetAddress(correoDestino));
                mCorreo.setSubject(asunto);
                mCorreo.setContent(mElementoCorreo);
            } catch (AddressException ex) {
                Logger.getLogger(GestorCorreo.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MessagingException ex) {
                Logger.getLogger(GestorCorreo.class.getName()).log(Level.SEVERE, null, ex);
            }

            //Envia el correo
            Transport mTransport;
            try {
                mTransport = mSession.getTransport("smtp");
                mTransport.connect(correoEmisor, passwordFrom);
                mTransport.sendMessage(mCorreo, mCorreo.getRecipients(Message.RecipientType.TO));
                mTransport.close();
            } catch (NoSuchProviderException ex) {
                Logger.getLogger(GestorCorreo.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MessagingException ex) {
                Logger.getLogger(GestorCorreo.class.getName()).log(Level.SEVERE, null, ex);
            }
            return true;
        }
    }//createEmail

}//class
