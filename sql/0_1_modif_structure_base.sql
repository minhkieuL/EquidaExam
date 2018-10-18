UPDATE `vente` 
SET `id` = '1' 
WHERE `vente`.`id` = 30917;

UPDATE `vente` 
SET `id` = '2' 
WHERE `vente`.`id` = 90217;

UPDATE `vente` 
SET `id` = '3' 
WHERE `vente`.`id` = 210717;

UPDATE `vente` 
SET `id` = '4' 
WHERE `vente`.`id` = 210817;

UPDATE `vente` 
SET `id` = '5' 
WHERE `vente`.`id` = 250217;

ALTER TABLE `vente`
MODIFY id INT(11) NOT NULL AUTO_INCREMENT;