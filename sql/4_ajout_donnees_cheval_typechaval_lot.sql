INSERT INTO `typecheval` (`id`, `libelle`, `description`) VALUES 
(NULL, 'Arabo-frison', 'L’Arabo-frison est une race chevaline récente, sélectionnée sur plusieurs générations depuis les années 1960 pour obtenir la morphologie du Frison moderne associée aux qualités d\'endurance du Pur-sang arabe.'), 
(NULL, 'Lombok', 'Le Lombok (indonésien : Kuda lombok), est la race de poneys présente sur l\'île de Lombok, en Indonésie.'), 
(NULL, 'Halla ', 'Le Halla est une race de chevaux de course originaire de Corée du Sud,');

INSERT INTO `cheval` (`id`, `nom`, `sexe`, `sire`, `typeCheval`) VALUES 
(NULL, 'Phoenix', '0', '0346797643', '1'), 
(NULL, 'Orlando', '1', '1679879463', '3'), 
(NULL, 'Kanelle', '0', '0346798543', '3'), 
(NULL, 'Colorado', '1', '1346798524', '2'), 
(NULL, 'Coquelicot', '1', '1243768956', '2');

INSERT INTO `lot` (`idCheval`, `idVente`, `id`, `prixDepart`) VALUES
(1, 3, 0, '10.00'),
(2, 1, 1, '20.00'),
(2, 5, 2, '30.00'),
(3, 2, 3, '40.00'),
(3, 3, 4, '50.00'),
(3, 4, 5, '60.00'),
(4, 2, 6, '70.00'),
(4, 5, 7, '80.00'),
(5, 2, 8, '90.00'),
(5, 3, 9, '100.00');