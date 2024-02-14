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
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VeterinaireSQL {
    private int id;
    private String sql;
    private PreparedStatement prepStmt;
    private ResultSet result;
    private LogIn accesse;
    private Connexion lamazoo;
    
    public VeterinaireSQL(LogIn accesse, Connexion lamazoo) {
        this.accesse = accesse;
        this.lamazoo = lamazoo;
        takeId();
    }
    // ce fichier garde des requets de SQL pour travailler avec la dase de donnée
    private void takeId() {
        try {
            sql = "SELECT veterinaire.id " +
                  "FROM veterinaire " + 
                  "INNER JOIN hashs ON (hashs.id = veterinaire.token_id) " + 
                  "WHERE hashs.app_id=MD5('" + accesse.getAppId() + "');";
            prepStmt = lamazoo.getConnection().prepareStatement(sql);
            result = prepStmt.executeQuery();
            if(result.next()) {
                id = result.getInt("id");
            }   
            result.close();
            prepStmt.close();  
        } catch (SQLException ex) {
            System.out.println("SQLExeption : " + ex.getMessage());
            System.out.println("SQLState : " + ex.getSQLState());
            System.out.println("Code erreur : " + ex.getErrorCode());
        }      
    }
    
    public HashMap<Integer, String[]> getListDesAnimals() {
        try {
            HashMap<Integer, String[]> listDesAnimaux = new HashMap<Integer, String[]>();
            sql = "SELECT animal.id, animal.nom, animal.surnom " +
                "FROM animal " +
                "INNER JOIN categorie ON (categorie.id = animal.categorie_id) " +
                "INNER JOIN veterinaire ON (veterinaire.categorie_id = categorie.id) " +
                "WHERE veterinaire.id = " + id + " AND animal.date_deces IS NULL;";
            prepStmt = lamazoo.getConnection().prepareStatement(sql);
            result = prepStmt.executeQuery();
            while(result.next()) {
                listDesAnimaux.put(result.getInt("id"), new String[]{result.getString("nom"), result.getString("surnom")});
            }
            result.close();
            prepStmt.close();         
            return listDesAnimaux;
        } catch (SQLException ex) {
            System.out.println("SQLExeption : " + ex.getMessage());
            System.out.println("SQLState : " + ex.getSQLState());
            System.out.println("Code erreur : " + ex.getErrorCode());
            return null;
        }      
    }
    
    public ArrayList getInfoDesAlimeaux(int animalId) {
        try {
            ArrayList animalInfo = new ArrayList();
            sql = "SELECT animal.nom, animal.surnom, animal.poids, animal.pays_original, animal.date_naissance, animal.date_deces " +
                "FROM animal " + 
                "WHERE animal.id = "+ animalId +";";
            prepStmt = lamazoo.getConnection().prepareStatement(sql);
            result = prepStmt.executeQuery();
            if(result.next()) {
                animalInfo.addAll(List.of(
                        result.getString("nom"),
                        result.getString("surnom"),
                        result.getInt("poids"),
                        result.getString("pays_original")== null ? "null" : result.getString("pays_original"),
                        (String)result.getString("date_naissance") == null ? "null" : result.getString("date_naissance"),
                        (String)result.getString("date_deces") == null ? "null" : result.getString("date_deces")
                ));                
            }
            return animalInfo;
        } catch (SQLException ex) {
            Logger.getLogger(VeterinaireSQL.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public int getNouveauxComm(int animalId) {
        try {
            int counter = 0;
            sql = "SELECT commentaire.check_look " +
                "FROM commentaire " +
                "INNER JOIN animal ON (animal.id = commentaire.animal_id) " +
                "WHERE animal.id = " + animalId +" AND commentaire.check_look = 0;";
            prepStmt = lamazoo.getConnection().prepareStatement(sql);
            result = prepStmt.executeQuery();
            while(result.next()) {
                counter++;               
            }            
            return counter;
        } catch (SQLException ex) {
            Logger.getLogger(VeterinaireSQL.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    
    public HashMap<Integer, String[]> getCommentaires(int animalId) {
        try {
            HashMap<Integer, String[]> listDesComm = new HashMap<Integer, String[]>();
            sql = "SELECT commentaire.id, commentaire.titre, commentaire.description, commentaire.date_creation, " +
                "commentaire.check_look, soignant.nom, soignant.prenom " +
                "FROM commentaire " +
                "INNER JOIN animal ON (animal.id = commentaire.animal_id) " +
                "INNER JOIN soignant ON (soignant.id = commentaire.soignant_id) " +
                "WHERE animal.id = " + animalId + ";";
            prepStmt = lamazoo.getConnection().prepareStatement(sql);
            result = prepStmt.executeQuery();
            while(result.next()) {
                listDesComm.put(result.getInt("id"),
                       new String[]{result.getString("titre"),
                       result.getString("description"),
                       (String)result.getString("date_creation"),
                       result.getString("check_look"),
                       result.getString("nom"),
                       result.getString("prenom")});
            }
            result.close();
            prepStmt.close();         
            return listDesComm;
        } catch (SQLException ex) {
            System.out.println("SQLExeption : " + ex.getMessage());
            System.out.println("SQLState : " + ex.getSQLState());
            System.out.println("Code erreur : " + ex.getErrorCode());
            return null;
        }
    }
    
    public void checkComm(int commId) {        
        try {
            sql = "UPDATE commentaire " +
            "SET check_look = TRUE " +
            "WHERE id = " + commId + ";";
            prepStmt = lamazoo.getConnection().prepareStatement(sql);
            prepStmt.executeUpdate();
            result.close();
            prepStmt.close();
        } catch (SQLException ex) {
            System.out.println("SQLExeption : " + ex.getMessage());
            System.out.println("SQLState : " + ex.getSQLState());
            System.out.println("Code erreur : " + ex.getErrorCode());
        }
    }
    
    public ArrayList<ArrayList<Object>> getSoignant() {
        try {
            ArrayList<ArrayList<Object>> listDesSoignants = new ArrayList<ArrayList<Object>>();
            sql = "SELECT soignant.id, soignant.nom, soignant.prenom " +
                "FROM soignant " +
                "INNER JOIN veterinaire ON (veterinaire.id = soignant.veterinaire_id) " +
                "WHERE veterinaire.id = " + id + ";";
            prepStmt = lamazoo.getConnection().prepareStatement(sql);
            result = prepStmt.executeQuery();
            while(result.next()) {
                ArrayList<Object> index = new ArrayList<Object>();                
                index.add(result.getInt("id"));
                index.add(result.getString("nom"));
                index.add(result.getString("prenom"));
                listDesSoignants.add(index);
            }
            result.close();
            prepStmt.close();
            return listDesSoignants;
        } catch (SQLException ex) {
            System.out.println("SQLExeption : " + ex.getMessage());
            System.out.println("SQLState : " + ex.getSQLState());
            System.out.println("Code erreur : " + ex.getErrorCode());
            return null;
        }
    }
    
    public void setSoin(String titre, String description, int animalId, int soignantId) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY");
            sql = "INSERT INTO soin (titre, description, date_prescrire, soignant_id, animal_id) " +
                "VALUES ('" + titre + "', '" + description + "', STR_TO_DATE('" + sdf.format(new Date()) + "','%d/%m/%Y'), " + soignantId + ", " + animalId + ");";
            prepStmt = lamazoo.getConnection().prepareStatement(sql);
            prepStmt.executeUpdate();
            prepStmt.close();
        } catch (SQLException ex) {
            System.out.println("SQLExeption : " + ex.getMessage());
            System.out.println("SQLState : " + ex.getSQLState());
            System.out.println("Code erreur : " + ex.getErrorCode());
        }
    }
    
    public HashMap<Integer, String[]> getSoin(int animalId) {
        try {
            HashMap<Integer, String[]> listDesSoin = new HashMap<Integer, String[]>();
            sql = "SELECT soin.id, soin.titre, soin.description, soin.date_prescrire, soin.date_realisation, soin.check_look, soignant.nom, soignant.prenom " +
                "FROM soin " +
                "INNER JOIN soignant ON (soignant.id = soin.soignant_id) " +
                "INNER JOIN animal ON (animal.id = soin.animal_id) " +
                "WHERE animal.id = " + animalId + ";";
            prepStmt = lamazoo.getConnection().prepareStatement(sql);
            result = prepStmt.executeQuery();
            while(result.next()) {
                listDesSoin.put(result.getInt("id"),
                       new String[]{result.getString("titre"),
                       result.getString("description"),
                       (String)result.getString("date_prescrire"),
                       result.getString("date_realisation")== null ? "null" : result.getString("date_realisation"),
                       result.getString("check_look"),
                       result.getString("nom"),
                       result.getString("prenom")});
            }
            result.close();
            prepStmt.close();         
            return listDesSoin;
        } catch (SQLException ex) {
            System.out.println("SQLExeption : " + ex.getMessage());
            System.out.println("SQLState : " + ex.getSQLState());
            System.out.println("Code erreur : " + ex.getErrorCode());
            return null;
        }
    } 
}
