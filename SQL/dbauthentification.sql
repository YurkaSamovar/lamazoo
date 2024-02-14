CREATE TABLE personnel(
	id INT NOT NULL AUTO_INCREMENT,
	nom VARCHAR(32) NOT NULL,
	prenom VARCHAR(32) NOT NULL,
	mail VARCHAR(64) NOT NULL,
	mdp VARCHAR(64) NOT NULL,
	date_naissance DATE,
	adresse VARCHAR(128),
	telephone VARCHAR(12),
	service_id INT,
	PRIMARY KEY(id),
	FOREIGN KEY(service_id) REFERENCES service(id)
)ENGINE=INNODB;

CREATE TABLE service(
	id INT NOT NULL AUTO_INCREMENT,
	nom_service VARCHAR(32),
	PRIMARY KEY(id)
)ENGINE=INNODB;

INSERT INTO service(nom_service) VALUES ('administrateur'), ('veterinaire'), ('soignant');
INSERT INTO personnel(nom, prenom, mail, mdp, date_naissance, adresse, telephone, service_id)
VALUES ('Opachki', 'Popachki', 'opachki@lamazoo.fr', 'opachki', STR_TO_DATE('11/05/1990','%d/%m/%Y'), 
'1er Jardin, Dijon 21000', '88005553535', 1),
('Gustaf', 'Saucisse', 'ilovesosaucisse@lamazoo.fr', 'opachki', STR_TO_DATE('09/03/1995','%d/%m/%Y'),
'14 Restorant de mer, Dijon 21000', '+33761039237', 2),
('Katusha', 'Table', 'ohnomdie@lamazoo.fr', 'opachki', STR_TO_DATE('01/01/2000','%d/%m/%Y'),
'3 Rue de répas, Dijon 21000', '+33728943801', 2),
('Dimidrol', 'Himique', 'brakeinbad@lamazoo.fr', 'opachki', STR_TO_DATE('06/12/1987','%d/%m/%Y'),
'90 Rue de la Post, Dijon 21000', '+33764908730', 2),
('Popandopola', 'Godzila', 'lebatimo@lamazoo.fr', 'opachki', STR_TO_DATE('13/11/1954','%d/%m/%Y'),
'11 Rromenad de la Souris, Dijon 21000', '+33618936534', 3),
('Jesuis', 'Fatigue', 'jeveuxdormir@lamazoo.fr', 'opachki', STR_TO_DATE('02/06/1994','%d/%m/%Y'),
'44 Rue de la Magasin, Dijon 21000', '+33787873030', 3),
('Magasin', 'Yoda', 'starwars@lamazoo.fr', 'opachki', STR_TO_DATE('24/12/1977','%d/%m/%Y'),
'Nouvelle planete, Dijon 21000', '+33700000001', 3),
('Vittel', 'Logitech', 'nomoriginal@lamazoo.fr', 'opachki', STR_TO_DATE('23/05/2005','%d/%m/%Y'),
'32 Rue de Pokimon, Dijon 21000', '+33714151617', 3),
('Hp', 'Samogon', 'alcogolique@lamazoo.fr', 'opachki', STR_TO_DATE('05/10/2002','%d/%m/%Y'),
'105 Rue de le Numero', '+33790125189', 3),
('Yoglu', 'Steam', 'logpidr@lamazoo.fr', 'opachki', STR_TO_DATE('06/11/1989','%d/%m/%Y'),
'73 Rue de sam Loh sam Pidr', '+33798543686', 3);

INSERT INTO personnel(nom, prenom, mail, mdp, date_naissance, adresse, telephone, service_id)
VALUES ('last', 'Personne', 'personne@lamazoo.fr', 'opachki', STR_TO_DATE('13/12/1998','%d/%m/%Y'), 
'13 Rue de Last of Ass, Dijon 21000', '+33798123421', 2);

DELETE FROM personnel WHERE id = 11;

SELECT mail, mdp FROM personnel;

TRUNCATE TABLE personnel;

SELECT `AUTO_INCREMENT`
FROM  INFORMATION_SCHEMA.TABLES
WHERE TABLE_SCHEMA = 'bdauthentification'
AND   TABLE_NAME   = 'personnel';
		