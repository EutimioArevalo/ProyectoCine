/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Cartelera;
import Modelo.Persona;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.ImageIcon;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;
import org.json.JSONArray;

/**
 *
 * @author timoa
 */
public class Utiles {

    /**
     * Metodo para obtener el id del video de Youtube
     *
     * @param url Se obtiene la url del video de Youtube
     * @return Retorna el valor que es necesario para la inserción del trailer
     * en la pagina.
     */
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

    /**
     * Metodo para enviar un correo electronico
     *
     * @param destinitarioCorreo Recibe el correo de la persona a la que se le
     * mandará el mensaje.
     */
    public void enviarCorreo(String destinitarioCorreo, String mensaje) {
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

    public String generarTxt(Cartelera cartelera, Persona persona, float precioT, String Asientos) {
        String text = "";

        text
                = "***********************CINE UNL**************************"
                + "\n"
                + "\nDatos del Cliente----------------------------------------"
                + "\nNombre:" + persona.getNombres() + " " + persona.getApellidos()
                + "\nCedula:" + persona.getCedula()
                + "\nDatos de la Factura----------------------------------------"
                + "\nPelicula: " + cartelera.getIdPelicula().getTitulo()
                + "\nDuración: " + cartelera.getIdPelicula().getTitulo()
                + "\n---------------------------------------------------------"
                + "\nPRECIO TOTAL: " + precioT
                + "**********************************************************";

        return text;
    }

    /**
     * Metodo para obtener la duración de la pelicula a partir de un String
     *
     * @param duracion Recibe el string de la duración
     * @param HHMM Recibe el formato que se desea obtener.
     * @return Retorna el valor de formato recibido.
     */
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


    /**
     * Metodo para obtener un JSON con valores booleanos de los asientos.
     *
     * @param nroAsientos Recibe el total de los asientos de una sala.
     * @return Retorna el JSON Convertido en un String.
     */
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

    /**
     * Metodo que convierte un JSON convertido en String a un ArrayList de tipo
     * Booleano
     *
     * @param json Recibe el Json convertido en String
     * @return Retorna un ArrayList de tipo Booleano
     */
    public List<Boolean> toArry(String json) {
        JSONArray js = new JSONArray(json);
        List<Boolean> list = new ArrayList<>();
        for (int i = 0; i < js.length(); i++) {
            list.add(js.getBoolean(i));
        }
        return list;

    }

    /**
     * Metodo para asignar los asientos seleccionados en la pagina de Seleccion
     * de Asientos.
     *
     * @param lista Obtiene la lista de Booleanos
     * @return Retorna un JSON convertido en string para la inserccion en la
     * base de datos.
     */
    public String asignarAsientos(List<Boolean> lista) {
        JSONArray js = new JSONArray();
        for (int i = 0; i < lista.size(); i++) {
            js.put(lista.get(i));
        }
        return js.toString();
    }

}
