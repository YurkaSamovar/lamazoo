
package accesseBDD;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connexion {
    private Connection connect;
  
  /**
   * Constructeur
   * @param serveur  nom du serveur, localhost si local
   * @param bdd nom de la base de données
   * @param nomUtil nom utilisateur
   * @param mdp mot de passe lié à l'utilisateur
   */   
  public Connexion(String serveur, String bdd, String nomUtil, String mdp) { // prepare le connexion et connecter!!!    
    try {
      // 1. Chargement du driver
      //Class.forName("com.mysql.jdbc.Driver");
      Class.forName("com.mysql.cj.jdbc.Driver");
      System.out.println("Driver accessible");
 
      // 2. Initialisation des paramètres de connexion
      String host   = serveur;                              // Serveur de bd
      String dbname = bdd;                                  // Nom bd
      String url = "jdbc:mysql://" + host + "/" + dbname;   // url de connexion
      System.out.println("url : "+url);
      String user = nomUtil;                                // nom du user
      System.out.println("nomUtil : "+nomUtil);
      String passwd = mdp;                                  // mot de passe
      System.out.println("mdp : "+mdp);
      // 3. Connexion
      connect = (Connection) DriverManager.getConnection(url, user, passwd); // connenter ICI!!!
      System.out.println("Connexion réussie !");     
           
    } catch (Exception e) { //si nous avons une erreror de connexion
      e.printStackTrace();
    }     
  }
  
  public Connection getConnection() {
      return connect; //retourner notre connexion
  }
    
}
