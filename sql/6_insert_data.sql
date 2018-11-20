INSERT INTO client(id) SELECT id FROM utilisateur; 

INSERT INTO compte (id, login, mdp, role) 
VALUES(1, 'cdeltour', '9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08', NULL);

INSERT INTO compte (id, login, mdp, role) 
VALUES(2, 'nfime', '9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08', NULL);

UPDATE utilisateur 
SET compte = 1
WHERE id = 1;

UPDATE utilisateur 
SET compte = 2
WHERE id = 2;

INSERT INTO directeurgeneral VALUES(2);
DELETE FROM client WHERE id = 2;

UPDATE cheval 
SET client = 1
WHERE id = 1;
