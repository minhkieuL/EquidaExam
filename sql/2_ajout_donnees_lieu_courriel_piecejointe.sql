INSERT INTO `lieu` (`id`, `ville`, `nbBoxes`, `commentaire`) VALUES (NULL, 'Caen', '5', 'Hippodrome de caen'), (NULL, 'Vire', '10', 'Hippodrome de vire');

UPDATE `vente` SET `lieu` = '1' WHERE `vente`.`id` = 1;
UPDATE `vente` SET `lieu` = '1' WHERE `vente`.`id` = 2;
UPDATE `vente` SET `lieu` = '2' WHERE `vente`.`id` = 3;
UPDATE `vente` SET `lieu` = '2' WHERE `vente`.`id` = 4;
UPDATE `vente` SET `lieu` = '1' WHERE `vente`.`id` = 5;

INSERT INTO `piecejointe` (`id`, `chemin`, `description`) VALUES (NULL, 'http://www.animauxdico.com/img/charte_graphique/cheval.jpg', 'Cheval n°1 en vente'), (NULL, 'http://ecoles.ac-rouen.fr/butot_venesville/cheval2.jpg', 'Cheval n°2 en vente');
INSERT INTO `piecejointe` (`id`, `chemin`, `description`) VALUES (NULL, 'http://photoemescontesetlegendes.fond-ecran-image.com/blog-photo/files/2009/03/cheval-bai-criniere-noire58.jpg', 'Cheval n°1 en vente'), (NULL, 'http://pascalinette971.p.a.pic.centerblog.net/ylmm815i.jpg', 'Cheval n°1 en vente');

INSERT INTO `courriel` (`id`, `date`, `objet`, `corps`, `vente`) VALUES (NULL, '2018-09-25', 'Vente Raspberry Sailling', 'Bonjour,\r\n\r\nVous trouverez ci joint les différents éléments de la vente.\r\n\r\nCordialement.', '3'), (NULL, '2018-09-04', 'Vente Djezair Star', 'Bonjour,\r\n\r\nVous trouverez ci joint les différents éléments de la vente Djezair Star.\r\n\r\nCordialement.', '5');
INSERT INTO `courriel` (`id`, `date`, `objet`, `corps`, `vente`) VALUES (NULL, '2018-09-13', 'Vente chevaux Djezair Star complément', 'Bonjour,\r\n\r\nVous trouverez ci joint des élément supplémentaire concernant la vente.\r\n\r\nCordialement.', '5'), (NULL, '2018-09-12', 'Vente de Jelly Bay', 'Bonjour,\r\n\r\nVous trouverez ci joint des élément concernant la vente à Jelly Bay.\r\n\r\nCordialement.', '4');

INSERT INTO `joint` (`courriel`, `pieceJointe`) VALUES ('2', '1'), ('2', '2');
INSERT INTO `joint` (`courriel`, `pieceJointe`) VALUES ('3', '3'), ('1', '4');