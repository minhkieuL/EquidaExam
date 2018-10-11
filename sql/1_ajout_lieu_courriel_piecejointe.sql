DROP TABLE IF EXISTS `lieu`;
CREATE TABLE IF NOT EXISTS `lieu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ville` varchar(50) NOT NULL,
  `nbBoxes` int(11) NOT NULL,
  `commentaire` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE `vente` ADD `lieu` INT NULL AFTER `codeCategVente`;
ALTER TABLE `vente` ADD CONSTRAINT `FK_VENTE_LIEU` FOREIGN KEY (`lieu`) REFERENCES `lieu`(`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;


DROP TABLE IF EXISTS `courriel`;
CREATE TABLE IF NOT EXISTS `courriel` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` date NOT NULL,
  `objet` varchar(50) NOT NULL,
  `corps` text NOT NULL,
  `vente` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE `courriel` ADD CONSTRAINT `FK_COUR_VENTE` FOREIGN KEY (`vente`) REFERENCES `vente`(`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;

DROP TABLE IF EXISTS `piecejointe`;
CREATE TABLE IF NOT EXISTS `piecejointe` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `chemin` varchar(255) NOT NULL,
  `description` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `joint`;
CREATE TABLE IF NOT EXISTS `joint` (
  `courriel` int(11) NOT NULL,
  `pieceJointe` int(11) NOT NULL,
  PRIMARY KEY (`courriel`,`pieceJointe`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE `joint` ADD CONSTRAINT `FK_JOINT_PIECE` FOREIGN KEY (`pieceJointe`) REFERENCES `piecejointe`(`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE `joint` ADD CONSTRAINT `FK_JOINT_COURRIEL` FOREIGN KEY (`courriel`) REFERENCES `courriel`(`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;

