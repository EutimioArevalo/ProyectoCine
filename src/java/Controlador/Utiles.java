/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.json.JSONArray;

/**
 *
 * @author timoa
 */
public class Utiles {

    public String obtenerenlace(String url) {
        String codigo = "";
        Boolean estado = false;
        for (int i = 0; i < url.length(); i++) {
            if (estado) {
                codigo += url.charAt(i);
            }
            if (url.charAt(i) == Character.valueOf('=')) {
                estado = true;
            }

        }
        return codigo;
    }

    public void enviarCorreo(String destinitarioCorreo) {
        Properties props = new Properties();

        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "587");
        props.put("mail.smtp.socketFactory.fallback", "false");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "587");

        Session sesion = Session.getDefaultInstance(props);

        String correoEnviar = "timoarva2017@gmail.com";
        String clave = "Timoarevalo14";
        String destinatario = destinitarioCorreo;
        String asunto = "Prueba";
        String mensaje = "Esta es una prueba";

        try {
            MimeMessage mail = new MimeMessage(sesion);
            mail.setFrom(new InternetAddress(correoEnviar));
            mail.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
            mail.setSubject(asunto);
            mail.setText(mensaje);

            Transport trasporte = sesion.getTransport("smtp");

            trasporte.connect(correoEnviar, clave);
            trasporte.sendMessage(mail, mail.getRecipients(Message.RecipientType.TO));
            trasporte.close();

        } catch (MessagingException ex) {
            Logger.getLogger(Utiles.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public int obtenerDuracion(String duracion, String HHMM) {
        if (HHMM.equals("HH")) {
            String aux = "";
            for (int i = 0; i < duracion.length(); i++) {
                if (duracion.charAt(i) != 'h') {
                    aux += duracion.charAt(i);
                } else {
                    break;
                }
            }
            return Integer.valueOf(aux);
        } else {
            String aux = "";
            int cont = 0;
            for (int i = 0; i < duracion.length(); i++) {
                if (duracion.charAt(i) == 'h') {
                    i++;
                    aux += duracion.charAt(i);
                    i++;
                    aux += duracion.charAt(i);
                    break;
                }
            }
            return Integer.valueOf(aux);
        }
    }

    public String toJson(int nroAsientos) {
        Boolean[] arry = new Boolean[nroAsientos];
        for (int i = 0; i < nroAsientos; i++) {
            arry[i] = false;
        }
        
        JSONArray jsarray = new JSONArray();
        
        for (int i = 0; i < arry.length; i++) {
            jsarray.put(arry[i]);
        }
        
        return jsarray.toString();
    }

}
