<?php 
    function connexion($dbn) {
        // Définition des variables de connexion
        $user = "publicVisiteur";
        $pass = "mdpVisiteur";
        $dsn ='mysql:host=localhost;dbname=' . $dbn; //Data Source Name

        // Connexion 
        try {
            $dbh = new PDO($dsn, $user, $pass, array(PDO::ATTR_PERSISTENT=>true, 
            PDO::MYSQL_ATTR_INIT_COMMAND => 'SET NAMES \'UTF8\''));  // Connexion persistante
        }
        catch (PDOException $e) {
            die("Erreur : " . $e->getMessage());
        }
        return $dbh;
    }

    function ajouterTravailleur($dbh, $data) {
		/*if (existeTravailleur($dbh, $data)) {
			return false;
		}*/
		
        $sql = 
		
		/*"INSERT INTO personnel(nom, prenom, mail, mdp, date_naissance, adresse, telephone, service_id)
			VALUES ('Opachki', 'Popachki', 'opachki@lamazoo.fr', :mdp, STR_TO_DATE('11/05/1990','%d/%m/%Y'), 
			'1er Jardin, Dijon 21000', '88005553535', 1);";*/

		/*"INSERT INTO personnel(nom, prenom, mail, mdp, date_naissance, adresse, telephone, service_id)
		VALUES ('Gustaf', 'Saucisse', 'ilovesosaucisse@lamazoo.fr', :mdp, STR_TO_DATE('09/03/1995','%d/%m/%Y'),
			'14 Restorant de mer, Dijon 21000', '+33761039237', 2);";*/

		/*"INSERT INTO personnel(nom, prenom, mail, mdp, date_naissance, adresse, telephone, service_id)
		VALUES ('Katusha', 'Table', 'ohnomdie@lamazoo.fr', :mdp, STR_TO_DATE('01/01/2000','%d/%m/%Y'),
			'3 Rue de répas, Dijon 21000', '+33728943801', 2);";*/

		/*"INSERT INTO personnel(nom, prenom, mail, mdp, date_naissance, adresse, telephone, service_id)
		VALUES ('Dimidrol', 'Himique', 'brakeinbad@lamazoo.fr', :mdp, STR_TO_DATE('06/12/1987','%d/%m/%Y'),
			'90 Rue de la Post, Dijon 21000', '+33764908730', 2);";*/

		/*"INSERT INTO personnel(nom, prenom, mail, mdp, date_naissance, adresse, telephone, service_id)
		VALUES ('Popandopola', 'Godzila', 'lebatimo@lamazoo.fr', :mdp, STR_TO_DATE('13/11/1954','%d/%m/%Y'),
			'11 Rromenad de la Souris, Dijon 21000', '+33618936534', 3);";*/
			
		/*"INSERT INTO personnel(nom, prenom, mail, mdp, date_naissance, adresse, telephone, service_id)
		VALUES ('Jesuis', 'Fatigue', 'jeveuxdormir@lamazoo.fr', :mdp, STR_TO_DATE('02/06/1994','%d/%m/%Y'),
			'44 Rue de la Magasin, Dijon 21000', '+33787873030', 3);";*/

		/*"INSERT INTO personnel(nom, prenom, mail, mdp, date_naissance, adresse, telephone, service_id)
		VALUES('Magasin', 'Yoda', 'starwars@lamazoo.fr', :mdp, STR_TO_DATE('24/12/1977','%d/%m/%Y'),
			'Nouvelle planete, Dijon 21000', '+33700000001', 3);";*/	
			
		/*"INSERT INTO personnel(nom, prenom, mail, mdp, date_naissance, adresse, telephone, service_id)
		VALUES ('Vittel', 'Logitech', 'nomoriginal@lamazoo.fr', :mdp, STR_TO_DATE('23/05/2005','%d/%m/%Y'),
			'32 Rue de Pokimon, Dijon 21000', '+33714151617', 3);";*/
			
		/*"INSERT INTO personnel(nom, prenom, mail, mdp, date_naissance, adresse, telephone, service_id)
		VALUES ('Hp', 'Samogon', 'alcogolique@lamazoo.fr', :mdp, STR_TO_DATE('05/10/2002','%d/%m/%Y'),
			'105 Rue de le Numero', '+33790125189', 3);";*/
			
		/*"INSERT INTO personnel(nom, prenom, mail, mdp, date_naissance, adresse, telephone, service_id)
		VALUES ('Yoglu', 'Steam', 'logpidr@lamazoo.fr', :mdp, STR_TO_DATE('06/11/1989','%d/%m/%Y'),
			'73 Rue de sam Loh sam Pidr', '+33798543686', 3);";*/	
			
		"INSERT INTO personnel(nom, prenom, mail, mdp, date_naissance, adresse, telephone, service_id)
		VALUES ('last', 'Personne', 'personne@lamazoo.fr', :mdp, STR_TO_DATE('13/12/1998','%d/%m/%Y'), 
		'13 Rue de Last of Ass, Dijon 21000', '+33798123421', 2);";

		// Préparation de la requête
		$stmt = $dbh ->prepare($sql);

		// Éxécution de la requête
    	$resultat = $stmt->execute([
			'mdp' => password_hash('opachki', PASSWORD_DEFAULT)
		]);
       	
		if ($resultat === false) {
            afficherErreurSQL("Pb lors de l ajout d'un utilisateur.", $sql, $dbh);
		}
		return true;
    }

    function existePersonnel($dbh, $data) {
		$sql = "SELECT mail, mdp, service_id FROM personnel WHERE mail = :mail";    
		$stmt = $dbh->prepare($sql); 
		$stmt->bindValue('mail', $data['mail'], PDO::PARAM_STR);
		    
		$stmt->execute();
		if ($stmt === false) { //si il n'a pas faire le requet
			echo 'oh my god'; //affiche le text de error    
		} else {
			if($stmt->rowCount()>0) { //si vous avez le donnée à votre requet
				$result = $stmt->fetch(PDO::FETCH_ASSOC); // prendre les donnée de le requet
				if(password_verify($data['mdp'], $result['mdp']) & $result['service_id'] != 3) { //si le mdp pareil que l'utilisateur tapper
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}			
		}
		
	}