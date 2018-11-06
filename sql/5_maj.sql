--
-- TABLE CLIENT TO UTILISATEUR 
--

ALTER TABLE client RENAME utilisateur;

--
-- CREATE TABLE 
--

--
-- Structure de la table `participer`
--
CREATE TABLE IF NOT EXISTS `participer` (
  `idCheval` int(11) NOT NULL,
  `idCourse` int(11) NOT NULL,
  `classement` int(11),
  PRIMARY KEY (idCheval, idCourse)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


--
-- Structure de la table `client`
--
CREATE TABLE IF NOT EXISTS `client` (
  `id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


--
-- Structure de la table `directeurgeneral`
--
CREATE TABLE IF NOT EXISTS `directeurgeneral` (
  `id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


--
-- Structure de la table `enchere`
--
CREATE TABLE IF NOT EXISTS `enchere` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `montant` decimal(8, 2),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


--
-- Structure de la table `course`
--
CREATE TABLE IF NOT EXISTS `course` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `nom` char(32),
  `date` date,
  `ville` char(32),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


--
-- Structure de la table `compte`
--
CREATE TABLE IF NOT EXISTS `compte` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `login` char(32),
  `mdp` char(64),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


--
-- Structure de la table `role`
--
CREATE TABLE IF NOT EXISTS `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `libelle` char(32),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


--
-- Structure de la table `permission`
--
CREATE TABLE IF NOT EXISTS `permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `libelle` char(32),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


--
-- Structure de la table `avoir`
--
CREATE TABLE IF NOT EXISTS `avoir` (
  `idRole` int(11) NOT NULL,
  `idPermission` int(11) NOT NULL,
  PRIMARY KEY (idRole, idPermission)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- ALTER TABLE 
--

--
-- Modification de la table `participer`
--
ALTER TABLE `participer`
  ADD FOREIGN KEY (`idCheval`) REFERENCES `cheval` (`id`);
ALTER TABLE `participer`
  ADD FOREIGN KEY (`idCourse`) REFERENCES `course` (`id`);

--
-- Modification de la table `compte`
--
ALTER TABLE `compte`
  ADD `role` int(11);
ALTER TABLE `compte`
  ADD FOREIGN KEY (`role`) REFERENCES `role` (`id`);
  
  
--
-- Modification de la table `avoir`
--
ALTER TABLE `avoir`
  ADD FOREIGN KEY (`idRole`) REFERENCES `role` (`id`);
ALTER TABLE `avoir`
  ADD FOREIGN KEY (`idPermission`) REFERENCES `permission` (`id`);
  
--
-- Modification de la table `vente`
--
ALTER TABLE `vente`
  ADD `dateFin` date;
ALTER TABLE `vente`
  ADD `dateDebutInscription` date;


--
-- Modification de la table `cheval`
--
ALTER TABLE `cheval`
  ADD `archiver` tinyint(1) default 0;
ALTER TABLE `cheval`
  ADD `valider` tinyint(1) default 0;
  
ALTER TABLE `cheval`
  ADD `mere` int(11);
ALTER TABLE `cheval`
  ADD FOREIGN KEY (`mere`) REFERENCES `cheval` (`id`);
  
ALTER TABLE `cheval`
  ADD `pere` int(11);
ALTER TABLE `cheval`
  ADD FOREIGN KEY (`pere`) REFERENCES `cheval` (`id`);
  
ALTER TABLE `cheval`
  ADD `client` int(11);
ALTER TABLE `cheval`
  ADD FOREIGN KEY (`client`) REFERENCES `client` (`id`);

--
-- Modification de la table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD `archiver` tinyint(1) default 0;
ALTER TABLE utilisateur 
  ADD compte INT(11);
ALTER TABLE utilisateur 
  ADD FOREIGN KEY (compte) REFERENCES compte(id);

--
-- Modification de la table `client`
--
ALTER TABLE `client`
  ADD FOREIGN KEY (`id`) REFERENCES `utilisateur` (`id`);

--
-- Modification de la table `directeurgeneral`
--
ALTER TABLE `directeurgeneral`
  ADD FOREIGN KEY (`id`) REFERENCES `utilisateur` (`id`);
  
--
-- Modification de la table `enchere`
--
ALTER TABLE `enchere`
  ADD `lot` int(11);
ALTER TABLE `enchere`
  ADD FOREIGN KEY (`lot`) REFERENCES `lot` (`id`);
  
ALTER TABLE `enchere`
  ADD `client` int(11);
ALTER TABLE `enchere`
  ADD FOREIGN KEY (`client`) REFERENCES `client` (`id`);

  