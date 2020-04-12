import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

// Simple HTTP client to be used with standard web servers
// Copyright Regan Willis 2020

public class http_client {
    public static void main(String[] args) throws Exception {
        // setup http connection with url from user
        URL url_site = new URL(args[0]);
        HttpURLConnection conn = (HttpURLConnection)url_site.openConnection();
        conn.setRequestMethod("GET");

        // get response code and redirect if necessary
        int response = conn.getResponseCode();
        String content = "";
        if (response >= 200 && response < 300) {
            // successful request, no need to redirect
            content += "Printing HTTP header info from " + url_site + "\n";
        } else if (response == 301 || response == 302) {
            // redirect once
            String new_url = conn.getHeaderField("Location");
            conn = (HttpURLConnection) new URL(new_url).openConnection();
            content += "URL redirected to " + new_url + "\nPrinting HTTP header info from " + new_url + "\n";
        } else {
            content = ("HTTP GET Request for " + url_site + " failed.");
        }

        // get headers
        Map<String, List <String>> headers = conn.getHeaderFields();
        for (Map.Entry<String, List<String>> entry : headers.entrySet()) {
            content += entry.getKey();
            content += entry.getValue();
            content += "\n";
        }

        // write to file
        FileWriter fw = new FileWriter("http_client_output");
        BufferedWriter bw = new BufferedWriter(fw);
        content += "\nURL Content:\n";
        bw.write(content);

        // read from input stream
        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        while ((inputLine = br.readLine()) != null) {
            bw.write(inputLine);
            bw.write("\n");
        }
        br.close();
        bw.close();
    }
}