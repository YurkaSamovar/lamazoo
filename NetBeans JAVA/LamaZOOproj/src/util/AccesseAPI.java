/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;

/**
 *
 * @author admin
 */
public class AccesseAPI {
    
    private String url;
    private HashMap<String, String> param;
    private long appId;
    
    public AccesseAPI(String url, HashMap<String, String> param, long appId) throws ProtocolException, IOException {
        this.url = url;
        this.param = param;
        this.appId = appId;
    }
    
    
    public boolean sendGet() throws MalformedURLException, ProtocolException, IOException {
        URL url = new URL(this.url + "?mail=" + this.param.get("login") + "&mdp=" + this.param.get("password") + "&appid=" + this.appId);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");        
        
        int responseCode = connection.getResponseCode();
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line;
        StringBuilder response = new StringBuilder();
 
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();
        return Boolean.parseBoolean(response.toString());
    }
        
}