/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

/**
 *
 * @author timoa
 */
public class CtrlPayment {

    public String prepareCheckout() throws IOException {
        URL url = new URL("https://test.oppwa.com/v1/checkouts");

        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Authorization", "Bearer OGE4Mjk0MTc1ZDYwMjM2OTAxNWQ3M2JmMDBlNTE4MGN8ZE1xNU1hVEQ1cg==");
        conn.setDoInput(true);
        conn.setDoOutput(true);

        String data = ""
                + "entityId=8a8294175d602369015d73bf009f1808"
                + "&amount=92.00"
                + "&currency=EUR"
                + "&paymentType=DB";

        DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
        wr.writeBytes(data);
        wr.flush();
        wr.close();
        int responseCode = conn.getResponseCode();
        InputStream is;

        if (responseCode >= 400) {
            is = conn.getErrorStream();
        } else {
            is = conn.getInputStream();
        }

        return IOUtils.toString(is);
    }

    public String paymentStatus(String id) throws IOException {
        URL url = new URL("https://test.oppwa.com/v1/checkouts/" + id + " /paymen");

        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Authorization", "Bearer OGE4Mjk0MTc1ZDYwMjM2OTAxNWQ3M2JmMDBlNTE4MGN8ZE1xNU1hVEQ1cg==");
        int responseCode = conn.getResponseCode();
        InputStream is;

        if (responseCode >= 400) {
            is = conn.getErrorStream();
        } else {
            is = conn.getInputStream();
        }

        return IOUtils.toString(is);
    }

    public String obtenerId(String json) {
        String id = "Vacio";
        try {
            JSONObject j = new JSONObject(json);
            id = j.getString("id");
        } catch (Exception e) {
        }
        return id;
    }
}
