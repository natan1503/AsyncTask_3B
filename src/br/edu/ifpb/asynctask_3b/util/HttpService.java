package br.edu.ifpb.asynctask_3b.util;

import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Natan Oliveira on 20/12/2015.
 */
public class HttpService {

    // IP da máquina onde se encontra o servidor.
    private static final String URL_CONTEXT = "http://192.168.0.168:8080/rest-servlet-service/";
    
    private static final String POST_PARAMS = "nomeEditText=fulano";
    
    public static HttpURLConnection sendGetRequest(String service)
            throws MalformedURLException, IOException{

        HttpURLConnection connection = null;

        URL url = new URL(URL_CONTEXT + service);

        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.connect();

        return connection;
    }

    public void sendJsonPostRequest(String service, JSONObject json) {
    	URL obj = new URL(URL_CONTEXT);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("POST");
 
        // For POST only - START
        con.setDoOutput(true);
        OutputStream os = con.getOutputStream();
        os.write(POST_PARAMS.getBytes());
        os.flush();
        os.close();
        // For POST only - END
 
        int responseCode = con.getResponseCode();
        System.out.println("POST Response Code :: " + responseCode);
 
        if (responseCode == HttpURLConnection.HTTP_OK) { //success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
 
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
 
            // print result
            System.out.println(response.toString());
        } else {
            System.out.println("POST request not worked");
        }
    }

    public static String getHttpContent(HttpURLConnection connection) {

        StringBuilder builder = new StringBuilder();

        try {

            InputStream content = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    content, "iso-8859-1"), 8);

            String line;

            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }

            content.close();

        } catch (IOException e) {

            Log.e("NotificationWearApp", "IOException: " + e);
        }

        return builder.toString();
    }
}