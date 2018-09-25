SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;


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


DROP TABLE IF EXISTS `Courriel`;
CREATE TABLE IF NOT EXISTS `courriel` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` date NOT NULL,
  `objet` varchar(50) NOT NULL,
  `corps` text NOT NULL,
  `vente` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE `courriel` ADD CONSTRAINT `FK_COUR_VENTE` FOREIGN KEY (`vente`) REFERENCES `vente`(`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;

DROP TABLE IF EXISTS `PieceJointe`;
CREATE TABLE IF NOT EXISTS `piecejointe` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `chemin` varchar(255) NOT NULL,
  `description` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `Joint`;
CREATE TABLE IF NOT EXISTS `joint` (
  `courriel` int(11) NOT NULL,
  `pieceJointe` int(11) NOT NULL,
  PRIMARY KEY (`courriel`,`pieceJointe`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE `joint` ADD CONSTRAINT `FK_JOINT_PIECE` FOREIGN KEY (`pieceJointe`) REFERENCES `piecejointe`(`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE `joint` ADD CONSTRAINT `FK_JOINT_COURRIEL` FOREIGN KEY (`courriel`) REFERENCES `courriel`(`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;


-- Ajout de données : 

INSERT INTO `lieu` (`id`, `ville`, `nbBoxes`, `commentaire`) VALUES (NULL, 'Caen', '5', 'Hippodrome de caen'), (NULL, 'Vire', '10', 'Hippodrome de vire');

UPDATE `vente` SET `lieu` = '1' WHERE `vente`.`id` = 30917;
UPDATE `vente` SET `lieu` = '1' WHERE `vente`.`id` = 90217;
UPDATE `vente` SET `lieu` = '2' WHERE `vente`.`id` = 210717;
UPDATE `vente` SET `lieu` = '2' WHERE `vente`.`id` = 210817;
UPDATE `vente` SET `lieu` = '1' WHERE `vente`.`id` = 250217;

INSERT INTO `piecejointe` (`id`, `chemin`, `description`) VALUES (NULL, 'http://www.animauxdico.com/img/charte_graphique/cheval.jpg', 'Cheval n°1 en vente'), (NULL, 'http://ecoles.ac-rouen.fr/butot_venesville/cheval2.jpg', 'Cheval n°2 en vente');
INSERT INTO `piecejointe` (`id`, `chemin`, `description`) VALUES (NULL, 'http://photoemescontesetlegendes.fond-ecran-image.com/blog-photo/files/2009/03/cheval-bai-criniere-noire58.jpg', 'Cheval n°1 en vente'), (NULL, 'http://pascalinette971.p.a.pic.centerblog.net/ylmm815i.jpg', 'Cheval n°1 en vente');

INSERT INTO `courriel` (`id`, `date`, `objet`, `corps`, `vente`) VALUES (NULL, '2018-09-25', 'Vente Raspberry Sailling', 'Bonjour,\r\n\r\nVous trouverez ci joint les différents éléments de la vente.\r\n\r\nCordialement.', '210717'), (NULL, '2018-09-04', 'Vente Djezair Star', 'Bonjour,\r\n\r\nVous trouverez ci joint les différents éléments de la vente Djezair Star.\r\n\r\nCordialement.', '250217');
INSERT INTO `courriel` (`id`, `date`, `objet`, `corps`, `vente`) VALUES (NULL, '2018-09-13', 'Vente chevaux Djezair Star complément', 'Bonjour,\r\n\r\nVous trouverez ci joint des élément supplémentaire concernant la vente.\r\n\r\nCordialement.', '250217'), (NULL, '2018-09-12', 'Vente de Jelly Bay', 'Bonjour,\r\n\r\nVous trouverez ci joint des élément concernant la vente à Jelly Bay.\r\n\r\nCordialement.', '210817');

INSERT INTO `joint` (`courriel`, `pieceJointe`) VALUES ('2', '1'), ('2', '2');
INSERT INTO `joint` (`courriel`, `pieceJointe`) VALUES ('3', '3'), ('1', '4');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;


