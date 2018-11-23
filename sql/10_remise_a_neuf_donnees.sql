UPDATE cheval SET client = '1' WHERE client IS NULL;

DELETE FROM lot WHERE lot.id = 1;
DELETE FROM lot WHERE lot.id = 5;
DELETE FROM lot WHERE lot.id = 4;
DELETE FROM lot WHERE lot.id = 6;
DELETE FROM lot WHERE lot.id = 8;

UPDATE typecheval SET description = 'Le Halla est une race de chevaux de course originaire de Corée du Sud.' WHERE typecheval.id = 3;

DELETE FROM client WHERE client.id = 48;
DELETE FROM client WHERE client.id = 49;
DELETE FROM client WHERE client.id = 50;

DELETE FROM clientcategvente WHERE clientcategvente.codeClient = 49 AND clientcategvente.codeCategVente = 'AUT';
DELETE FROM clientcategvente WHERE clientcategvente.codeClient = 49 AND clientcategvente.codeCategVente = 'ETE';
DELETE FROM clientcategvente WHERE clientcategvente.codeClient = 50 AND clientcategvente.codeCategVente = 'ETE';

DELETE FROM utilisateur WHERE utilisateur.id = 48;
DELETE FROM utilisateur WHERE utilisateur.id = 49;
DELETE FROM utilisateur WHERE utilisateur.id = 50;
