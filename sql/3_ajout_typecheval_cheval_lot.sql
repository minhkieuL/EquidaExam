CREATE TABLE IF NOT EXISTS `typecheval` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `libelle` varchar(50) NOT NULL,
  `description` varchar(255) NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `cheval` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(50) DEFAULT NULL,
  `sexe` tinyint(1) DEFAULT NULL,
  `sire` varchar(100) DEFAULT NULL,
  `typeCheval` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `lot` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idCheval` int(11) NOT NULL,
  `idVente` int(11) NOT NULL,
  `prixDepart` decimal(8,2) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE `lot`
  ADD CONSTRAINT `FK_LOT_CHEVAL` FOREIGN KEY (`idCheval`) REFERENCES `cheval` (`id`),
  ADD CONSTRAINT `FK_LOT_VENTE` FOREIGN KEY (`idVente`) REFERENCES `vente` (`id`);

ALTER TABLE `cheval` ADD CONSTRAINT `FK_CHEVAL_TYPECHEVAL` FOREIGN KEY (`typeCheval`) REFERENCES `typecheval`(`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;