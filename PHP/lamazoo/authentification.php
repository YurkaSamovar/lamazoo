<?php

    function takeId($dbh, $data) { 
        $sql = "SELECT id FROM personnel WHERE mail=:mail;"; //creer le sql
        $stmt = $dbh ->prepare($sql); //preparer le sql
        $stmt->bindValue('mail', $data['mail'], PDO::PARAM_STR); //indique quel donnée utiliser 
        $stmt->execute(); //executer le requet
        $result = $stmt->fetch(PDO::FETCH_ASSOC); //prendre ID de la repondre
        return $result['id']; // returner ID de la repondre
    }

    function ajoutToken($dbh, $data, $id) {
        $appid = md5($data['appid']); //creer le hash de ID de l'aplication JAVA
        $sql = "INSERT INTO hashs(id, app_id) VALUES ($id,'$appid')
        ON DUPLICATE KEY UPDATE app_id='$appid';"; // c'est le text de requet SQL
        $stmt = $dbh ->prepare($sql); //preparer le sql        
        $stmt->execute(); // executer le requet
    }