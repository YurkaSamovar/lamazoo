/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package accesseBDD.sql;

import accesseBDD.Connexion;
import accesseBDD.LogIn;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author admin
 */
public class CheckAccesse {   
    private LogIn accesse;
    private Connexion connexion;
    
    public CheckAccesse(LogIn accesse, Connexion connexion){
        this.accesse = accesse;
        this.connexion = connexion;
    }
    
    public boolean check() {       
        if(checkAppId() & checkDate()) {
            return true;
        } else {
            return false;
        }
    }
    
    private boolean checkAppId() {//retourne true si l'application ID est même que l'aplicattion ID dans la base de donnée
        try {
            String sql = "SELECT id FROM hashs WHERE app_id=MD5('" + accesse.getAppId() + "');";
            PreparedStatement prepStmt = connexion.getConnection().prepareStatement(sql);
            ResultSet result = prepStmt.executeQuery();
            if(result.next()) {
                prepStmt.close();
                return true;
            } else {
                prepStmt.close();
                return false;
            }
        } catch (SQLException ex) {
              System.out.println("SQLExeption : " + ex.getMessage());
              System.out.println("SQLState : " + ex.getSQLState());
              System.out.println("Code erreur : " + ex.getErrorCode());
              return false;
        }         
    }
    
    private boolean checkDate() { //retourne true si la date pareile que la date dans la base de donnée
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY");
        String dateAccesse = sdf.format(accesse.getAppId());
        String dateNow = sdf.format(new Date());
        return dateNow.equals(dateAccesse);
    }
}
