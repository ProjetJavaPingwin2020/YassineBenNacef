/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.awt.HeadlessException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.swing.JOptionPane;

public class SMS {

    public static void sendTrait(String mess) {
        String api = "BkPdn6fqtBY-kiTfMRFnMnDFPgqQZapxziqQ3A2Yim";
        String send = "HuntKingdom";
        String num = "21658709502";
        try {
            String user = "username=" + "mohamedyassine.bennacef@esprit.tn";
            String apiKey = "&apiKey=" + api;
            String message = "&message=" + mess;
            String sender = "&sender=" + send;
            String numbers = "&numbers=" + num;

            // Send data
            HttpURLConnection conn = (HttpURLConnection) new URL("https://api.txtlocal.com/send/?").openConnection();
            
            String data = user + apiKey + numbers + message + sender;
            
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
            conn.getOutputStream().write(data.getBytes("UTF-8"));
            final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                        System.out.println(rd);

            final StringBuffer stringBuffer = new StringBuffer();
            String line;
            while ((line = rd.readLine()) != null) {
                JOptionPane.showMessageDialog(null, "message" + line);
               
            }
            rd.close();
        } catch (IOException | HeadlessException e) {
            JOptionPane.showMessageDialog(null, e);
            
        }

    }

}
