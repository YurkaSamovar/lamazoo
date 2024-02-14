CREATE DATABASE db_lamazoo;

CREATE TABLE hashs(
	id INT NOT NULL,
	app_id VARCHAR(32) NOT NULL,
	PRIMARY KEY (id)
)ENGINE=INNODB;

CREATE TABLE categorie(
	id INT NOT NULL AUTO_INCREMENT,
	nom_categorie VARCHAR(32) NOT NULL,
	PRIMARY KEY(id)
)ENGINE=INNODB;

INSERT INTO categorie(nom_categorie) VALUES ('Oiseaux'), ('Mammifere'), ('Reptile');

CREATE TABLE veterinaire(
	id INT NOT NULL AUTO_INCREMENT,
	nom VARCHAR(32) NOT NULL,
	prenom VARCHAR(32) NOT NULL,
	token_id INT NOT NULL,
	categorie_id INT NOT NULL,
	PRIMARY KEY(id),
	FOREIGN KEY(token_id) REFERENCES hashs(id),
	FOREIGN KEY(categorie_id) REFERENCES categorie(id)
)ENGINE=INNODB;

INSERT INTO veterinaire(nom, prenom, token_id, categorie_id) VALUES ('Gustaf', 'Saucisse', 2, 1),
 ('Katusha', 'Table', 3, 2),
 ('Popandopola', 'Godzila', 4, 3);
 
CREATE TABLE animal(
	id INT NOT NULL AUTO_INCREMENT,
	nom VARCHAR(32) NOT NULL,
	surnom VARCHAR(32) NOT NULL,
	poids INT NOT NULL,
	pays_original VARCHAR(50) DEFAULT NULL,
	date_naissance DATE DEFAULT NULL,
	date_deces DATE DEFAULT NULL,
	categorie_id INT NOT NULL,
	PRIMARY KEY(id),
	FOREIGN KEY(categorie_id) REFERENCES categorie(id)  
)ENGINE=INNODB;

INSERT INTO animal(nom, surnom, poids, pays_original, date_naissance, categorie_id)
VALUES ('Castor', 'Bobr-Kurwa', 14, 'Poland', STR_TO_DATE('13/05/2015','%d/%m/%Y'), 2),	
	('Pigeon', 'Canadron', 7, 'France', STR_TO_DATE('16/11/2021','%d/%m/%Y'), 1),
	('X3', 'Reptile-Wins', 100, 'Une autre dimension', STR_TO_DATE('01/01/1992','%d/%m/%Y'), 3),
	('Cheval', 'Spirit', 20, 'Mongolia',	STR_TO_DATE('20/11/2023','%d/%m/%Y'), 2),
	('Perroquet', 'Shliapa', 1, 'Japone', STR_TO_DATE('30/03/2014','%d/%m/%Y'), 1),
	(' Varan', 'Glista', 50, 'Brasile', STR_TO_DATE('11/01/2000','%d/%m/%Y'), 3);
	
INSERT INTO animal(nom, surnom, poids, pays_original, date_naissance, date_deces, categorie_id)
VALUES ('Éléphant', 'Slon', 200, 'India', STR_TO_DATE('30/05/1950','%d/%m/%Y'), STR_TO_DATE('23/03/2015','%d/%m/%Y'), 2),	
	('Calibri', 'Pipiska', 7, 'Finland', STR_TO_DATE('03/07/2020','%d/%m/%Y'), STR_TO_DATE('27/10/2023','%d/%m/%Y'), 1),
	('Serpent', 'Udav', 50, 'Chili', STR_TO_DATE('10/9/2011','%d/%m/%Y'), STR_TO_DATE('31/12/2021','%d/%m/%Y'), 3);

CREATE TABLE soignant(
	id INT NOT NULL AUTO_INCREMENT,
	nom VARCHAR(32) NOT NULL,
	prenom VARCHAR(32) NOT NULL,
	veterinaire_id INT NOT NULL,
	PRIMARY KEY(id),
	FOREIGN KEY(veterinaire_id) REFERENCES veterinaire(id)
)ENGINE=INNODB;

INSERT INTO soignant(nom, prenom, veterinaire_id) VALUES ('Popandopola', 'Godzila', 1),
 ('Jesuis', 'Fatigue', 1),
 ('Magasin', 'Yoda', 2),
 ('Vittel', 'Logitech', 2),
 ('Hp', 'Samogon', 3),
 ('Yoglu', 'Steam', 3);

CREATE TABLE soin(
	id INT NOT NULL AUTO_INCREMENT,
	titre VARCHAR(128) NOT NULL,	
	description TEXT NOT NULL,
	date_prescrire DATE NOT NULL,
	date_realisation DATE DEFAULT(NULL),
	check_look BOOL NOT NULL DEFAULT(FALSE),
	soignant_id INT NOT NULL,
	animal_id INT NOT NULL,
	PRIMARY KEY(id),
	FOREIGN KEY(soignant_id) REFERENCES soignant(id),
	FOREIGN KEY(animal_id) REFERENCES animal(id)
)ENGINE=INNODB;

CREATE TABLE commentaire(
	id INT NOT NULL AUTO_INCREMENT,
	titre VARCHAR(128) NOT NULL,
	description TEXT NOT NULL,
	date_creation DATE NOT NULL,
	check_look BOOL NOT NULL DEFAULT(FALSE),
	soignant_id INT NOT NULL,
	animal_id INT NOT NULL,
	PRIMARY KEY(id),
	FOREIGN KEY(soignant_id) REFERENCES soignant(id),
	FOREIGN KEY(animal_id) REFERENCES animal(id)
)ENGINE=INNODB;

INSERT INTO commentaire(titre, description, date_creation, soignant_id, animal_id) 
VALUES ('Il ne veut pas manger de pain.',
 'Je lui donne une baguette entière, et il ne veut pas manger. Je l\'ai même trempé dans de la sauce tomate.  Je crois qu\'il prend de la nourriture quelque part. Mais où?', 
 STR_TO_DATE('11/02/2024','%d/%m/%Y'),
 3,
 4);


DROP TABLE soin;
SET FOREIGN_KEY_CHECKS=1;
TRUNCATE TABLE soin;


	
SELECT animal.id, animal.nom, animal.surnom, animal.poids, animal.pays_original, animal.date_naissance, animal.date_deces
FROM animal 
WHERE animal.id = 1;

SELECT animal.nom, animal.surnom
FROM animal
INNER JOIN categorie ON (categorie.id = animal.categorie_id)
INNER JOIN veterinaire ON (veterinaire.categorie_id = categorie.id)
WHERE veterinaire.id = 2 AND animal.date_deces IS NULL;

SELECT veterinaire.id
FROM veterinaire 
INNER JOIN hashs ON (hashs.id = veterinaire.token_id) 
WHERE hashs.app_id = '49e201da0f988868dc2e34eb0411031a';

SELECT commentaire.check_look
FROM commentaire
INNER JOIN animal ON (animal.id = commentaire.animal_id)
WHERE animal.id = 1;

SELECT commentaire.id, commentaire.titre, commentaire.description, commentaire.date_creation, commentaire.check_look, soignant.nom, soignant.prenom
FROM commentaire
INNER JOIN animal ON (animal.id = commentaire.animal_id)
INNER JOIN soignant ON (soignant.id = commentaire.soignant_id)
WHERE animal.id = 1;

UPDATE commentaire
SET check_look = TRUE
WHERE id = 1;

SELECT soin.id, soin.titre, soin.description, soin.date_prescrire, soin.date_realisation, soin.check_look, soignant.nom, soignant.prenom
FROM soin
INNER JOIN animal ON (animal.id = soin.animal_id)
INNER JOIN soignant ON (soignant.id = soin.soignant_id)
WHERE animal.id = 1;

INSERT INTO soin (titre, description, date_prescrire, soignant_id, animal_id)
VALUES ('opachki', 'opachki', STR_TO_DATE('30/05/1950','%d/%m/%Y'), 3, 1) ;

SELECT soignant.id, soignant.nom, soignant.prenom
FROM soignant
INNER JOIN veterinaire ON (veterinaire.id = soignant.veterinaire_id)
WHERE veterinaire.id = 2;

SELECT soin.id, soin.titre, soin.description, soin.date_prescrire, soin.date_realisation, soin.check_look, soignant.nom, soignant.prenom
FROM soin
INNER JOIN soignant ON (soignant.id = soin.soignant_id)
INNER JOIN animal ON (animal.id = soin.animal_id)
WHERE animal.id = 1;














	