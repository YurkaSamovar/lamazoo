<?php 
    include_once('accesBD.php');
    include_once('authentification.php');

    $dbhAuthentification = connexion('bdauthentification'); // connecter à base de donnée "bdauthentification"   
    $data = filter_input_array(INPUT_GET, FILTER_SANITIZE_FULL_SPECIAL_CHARS); // prendre des données de le requet
    $dbhLamazoo = connexion('db_lamazoo'); //connecter à base de donnée "db_lamazoo"    
    if(existePersonnel($dbhAuthentification, $data)) { //si la personne est existée
        $id = takeId($dbhAuthentification, $data); //prendre ID de la personne
        ajoutToken($dbhLamazoo, $data, $id); // ajouter le token à la base de donnée "db_lamazoo"
        echo 'true'; //envoyer le token à l'aplication JAVA
    } 
