/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package accesseBDD;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import util.AccesseAPI;

/**
 *
 * @author admin
 */
public class LogIn {
    private String url;
    private String login;
    private String password;
    private long appId;
    
    public LogIn(String url, String login, String password) { //constructeur de login
        this.url = url;
        this.login = login;
        this.password = password;
        appId = new Date().getTime();
    }
    
    public boolean logIn() throws IOException {
        HashMap<String, String> param = new HashMap<>(); //creer la collection pour parameter
        param.put("login", this.login); //ajout le login
        param.put("password", this.password);// ajout un mdp
        AccesseAPI accesse = new AccesseAPI(this.url, param, appId); //donne nos parameter à API qui travailler avec PHP
        return accesse.sendGet(); //retourner le reponse de API
    }
    
    public long getAppId() {
        return this.appId;
    }
}
