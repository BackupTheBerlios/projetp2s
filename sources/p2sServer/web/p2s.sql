SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT;
SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS;
SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION;
SET NAMES utf8;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE=NO_AUTO_VALUE_ON_ZERO */;


CREATE DATABASE /*!32312 IF NOT EXISTS*/ `p2s`;
USE `p2s`;

DROP TABLE IF EXISTS `artefacts`;
CREATE TABLE `artefacts` (
  `idartefact` int(10) unsigned NOT NULL auto_increment,
  `livrable` enum('true','false') default NULL,
  `etat` int(11) default NULL,
  `nom` varchar(45) NOT NULL default '',
  `description` varchar(200) default NULL,
  `idresponsable` int(10) unsigned NOT NULL default '0',
  PRIMARY KEY  (`idartefact`),
  KEY `FK_artefacts_idresponsable` (`idresponsable`),
  CONSTRAINT `FK_artefacts_idresponsable` FOREIGN KEY (`idresponsable`) REFERENCES `membres` (`idmembre`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `artefacts_entrees_taches`;
CREATE TABLE `artefacts_entrees_taches` (
  `idartefact` int(10) unsigned NOT NULL auto_increment,
  `idtache` int(10) unsigned NOT NULL default '0',
  PRIMARY KEY  (`idartefact`,`idtache`),
  KEY `FK_artefacts_entrees_taches_idtache` (`idtache`),
  CONSTRAINT `FK_artefacts_entrees_taches_idartefact` FOREIGN KEY (`idartefact`) REFERENCES `artefacts` (`idartefact`),
  CONSTRAINT `FK_artefacts_entrees_taches_idtache` FOREIGN KEY (`idtache`) REFERENCES `taches` (`idtache`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `artefacts_entrees_tachescollaboratives`;
CREATE TABLE `artefacts_entrees_tachescollaboratives` (
  `idartefact` int(10) unsigned NOT NULL auto_increment,
  `idtache` int(10) unsigned NOT NULL default '0',
  PRIMARY KEY  (`idartefact`,`idtache`),
  KEY `FK_artefacts_entrees_tachescollaboratives_idtache` (`idtache`),
  CONSTRAINT `FK_artefacts_entrees_tachescollaboratives_idartefact` FOREIGN KEY (`idartefact`) REFERENCES `artefacts` (`idartefact`),
  CONSTRAINT `FK_artefacts_entrees_tachescollaboratives_idtache` FOREIGN KEY (`idtache`) REFERENCES `tachescollaboratives` (`idtache`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='InnoDB free: 3072 kB';

DROP TABLE IF EXISTS `artefacts_sorties_taches`;
CREATE TABLE `artefacts_sorties_taches` (
  `idartefact` int(10) unsigned NOT NULL auto_increment,
  `idtache` int(10) unsigned NOT NULL default '0',
  PRIMARY KEY  (`idartefact`,`idtache`),
  KEY `FK_artefacts_sorties_taches_idtache` (`idtache`),
  CONSTRAINT `FK_artefacts_sorties_taches_idartefact` FOREIGN KEY (`idartefact`) REFERENCES `artefacts` (`idartefact`),
  CONSTRAINT `FK_artefacts_sorties_taches_idtache` FOREIGN KEY (`idtache`) REFERENCES `taches` (`idtache`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `artefacts_sorties_tachescollaboratives`;
CREATE TABLE `artefacts_sorties_tachescollaboratives` (
  `idartefact` int(10) unsigned NOT NULL auto_increment,
  `idtache` int(10) unsigned NOT NULL default '0',
  PRIMARY KEY  (`idartefact`,`idtache`),
  KEY `FK_artefacts_sorties_tachescollaboratives_idtache` (`idtache`),
  CONSTRAINT `FK_artefacts_sorties_tachescollaboratives_idartefact` FOREIGN KEY (`idartefact`) REFERENCES `artefacts` (`idartefact`),
  CONSTRAINT `FK_artefacts_sorties_tachescollaboratives_idtache` FOREIGN KEY (`idtache`) REFERENCES `tachescollaboratives` (`idtache`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `chefprojet_projets`;
CREATE TABLE `chefprojet_projets` (
  `login` varchar(45) NOT NULL default '',
  `idprojet` int(10) unsigned NOT NULL default '0',
  PRIMARY KEY  (`login`,`idprojet`),
  KEY `FK_chefprojet_projets_idprojet` (`idprojet`),
  CONSTRAINT `FK_chefprojet_projets_idprojet` FOREIGN KEY (`idprojet`) REFERENCES `projets` (`idprojet`),
  CONSTRAINT `FK_chefprojet_projets_login` FOREIGN KEY (`login`) REFERENCES `utilisateurs` (`login`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `indicateurs_iteration`;
CREATE TABLE `indicateurs_iteration` (
  `iditeration` int(10) unsigned NOT NULL auto_increment,
  `totalcharges` int(10) unsigned default NULL,
  `nombretachesterminees` int(10) unsigned default NULL,
  `dureemoyennetache` int(10) unsigned default NULL,
  `nombreparticipants` int(10) unsigned default NULL,
  `chargemoyenneparticipants` int(10) unsigned default NULL,
  `nombremoyentachesparticipants` int(10) unsigned default NULL,
  PRIMARY KEY  (`iditeration`),
  CONSTRAINT `FK_indicateurs_iteration_iditeration` FOREIGN KEY (`iditeration`) REFERENCES `iterations` (`iditeration`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `indicateurs_projet`;
CREATE TABLE `indicateurs_projet` (
  `idprojet` int(10) unsigned NOT NULL auto_increment,
  `totalcharges` int(10) unsigned default NULL,
  `tachesterminees` int(10) unsigned default NULL,
  `dureemoyennetache` int(10) unsigned default NULL,
  `nombreparticipants` int(10) unsigned default NULL,
  `avancementprojet` float default NULL,
  PRIMARY KEY  (`idprojet`),
  CONSTRAINT `FK_indicateurs_projet_idprojet` FOREIGN KEY (`idprojet`) REFERENCES `projets` (`idprojet`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `iterations`;
CREATE TABLE `iterations` (
  `iditeration` int(10) unsigned NOT NULL auto_increment,
  `numero` int(10) unsigned NOT NULL default '0',
  `datedebutprevue` date default NULL,
  `datedebutreelle` date default NULL,
  `datefinprevue` date default NULL,
  `datefinreelle` date default NULL,
  `idprojet` int(10) unsigned NOT NULL default '0',
  PRIMARY KEY  (`iditeration`),
  KEY `FK_iterations_idprojet` (`idprojet`),
  CONSTRAINT `FK_iterations_idprojet` FOREIGN KEY (`idprojet`) REFERENCES `projets` (`idprojet`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='InnoDB free: 4096 kB';

DROP TABLE IF EXISTS `membres`;
CREATE TABLE `membres` (
  `idmembre` int(10) unsigned NOT NULL default '0',
  `nom` varchar(45) NOT NULL default '',
  `prenom` varchar(45) default NULL,
  `adresse` varchar(45) default NULL,
  `telephone` varchar(20) default NULL,
  `email` varchar(45) default NULL,
  PRIMARY KEY  (`idmembre`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `membres_projets`;
CREATE TABLE `membres_projets` (
  `idmembre` int(10) unsigned NOT NULL auto_increment,
  `idprojet` int(10) unsigned NOT NULL default '0',
  PRIMARY KEY  (`idmembre`,`idprojet`),
  KEY `FK_membres_projets_idprojet` (`idprojet`),
  CONSTRAINT `FK_membres_projets_idmembre` FOREIGN KEY (`idmembre`) REFERENCES `membres` (`idmembre`),
  CONSTRAINT `FK_membres_projets_idprojet` FOREIGN KEY (`idprojet`) REFERENCES `projets` (`idprojet`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `membres_tachescollaboratives`;
CREATE TABLE `membres_tachescollaboratives` (
  `idmembre` int(10) unsigned NOT NULL auto_increment,
  `idtache` int(10) unsigned NOT NULL default '0',
  PRIMARY KEY  (`idmembre`,`idtache`),
  KEY `FK_membrestachescolaboratives_idtache` (`idtache`),
  CONSTRAINT `FK_membrestachescolaboratives_idmembre` FOREIGN KEY (`idmembre`) REFERENCES `membres` (`idmembre`),
  CONSTRAINT `FK_membrestachescolaboratives_idtache` FOREIGN KEY (`idtache`) REFERENCES `tachescollaboratives` (`idtache`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `mesures`;
CREATE TABLE `mesures` (
  `idmesure` int(10) unsigned NOT NULL auto_increment,
  `nom` varchar(45) NOT NULL default '',
  `description` varchar(200) default NULL,
  `type` int(11) NOT NULL default '0',
  `valeur` varchar(45) NOT NULL default '',
  PRIMARY KEY  (`idmesure`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `projets`;
CREATE TABLE `projets` (
  `idprojet` int(10) unsigned NOT NULL auto_increment,
  `nom` varchar(45) NOT NULL default '',
  `datedebut` date default NULL,
  `datefin` date default NULL,
  `description` varchar(200) default NULL,
  `budget` int(11) default '0',
  PRIMARY KEY  (`idprojet`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `risques`;
CREATE TABLE `risques` (
  `idrisque` int(10) unsigned NOT NULL auto_increment,
  `nom` varchar(45) NOT NULL default '',
  `priorite` int(11) default NULL,
  `impact` int(11) default NULL,
  `etat` int(11) default NULL,
  `description` varchar(200) default NULL,
  `idprojet` int(10) unsigned NOT NULL default '0',
  PRIMARY KEY  (`idrisque`),
  KEY `FK_risques_idprojet` (`idprojet`),
  CONSTRAINT `FK_risques_idprojet` FOREIGN KEY (`idprojet`) REFERENCES `projets` (`idprojet`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles` (
  `idrole` varchar(45) NOT NULL default '',
  `nom` varchar(45) NOT NULL default '',
  `description` varchar(200) default NULL,
  PRIMARY KEY  (`idrole`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `roles_membres`;
CREATE TABLE `roles_membres` (
  `idrole` varchar(45) NOT NULL default '',
  `idmembre` int(10) unsigned NOT NULL default '0',
  PRIMARY KEY  (`idrole`,`idmembre`),
  KEY `FK_roles_membres_idmembre` (`idmembre`),
  CONSTRAINT `FK_roles_membres_idmembre` FOREIGN KEY (`idmembre`) REFERENCES `membres` (`idmembre`),
  CONSTRAINT `FK_roles_membres_idrole` FOREIGN KEY (`idrole`) REFERENCES `roles` (`idrole`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `superviseur_projets`;
CREATE TABLE `superviseur_projets` (
  `login` varchar(45) NOT NULL default '',
  `idprojet` int(10) unsigned NOT NULL default '0',
  PRIMARY KEY  (`login`,`idprojet`),
  KEY `FK_superviseurprojets_idprojet` (`idprojet`),
  CONSTRAINT `FK_superviseurprojets_idprojet` FOREIGN KEY (`idprojet`) REFERENCES `projets` (`idprojet`),
  CONSTRAINT `FK_superviseurprojets_login` FOREIGN KEY (`login`) REFERENCES `utilisateurs` (`login`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='InnoDB free: 4096 kB';

DROP TABLE IF EXISTS `taches`;
CREATE TABLE `taches` (
  `idtache` int(10) unsigned NOT NULL auto_increment,
  `nom` varchar(45) NOT NULL default '',
  `description` varchar(125) default NULL,
  `etat` int(11) default NULL,
  `chargeprevue` int(11) default NULL,
  `tempspasse` int(11) default NULL,
  `tempsrestant` int(11) default NULL,
  `datedebutprevue` date default NULL,
  `datefinprevue` date default NULL,
  `datedebutreelle` date default NULL,
  `datefinreelle` date default NULL,
  `iditeration` int(10) unsigned NOT NULL default '0',
  `idmembre` int(10) unsigned NOT NULL default '0',
  PRIMARY KEY  (`idtache`),
  KEY `FK_taches_iditeration` (`iditeration`),
  KEY `FK_taches_idmembre` (`idmembre`),
  CONSTRAINT `FK_taches_iditeration` FOREIGN KEY (`iditeration`) REFERENCES `iterations` (`iditeration`),
  CONSTRAINT `FK_taches_idmembre` FOREIGN KEY (`idmembre`) REFERENCES `membres` (`idmembre`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `tachescollaboratives`;
CREATE TABLE `tachescollaboratives` (
  `idtache` int(10) unsigned NOT NULL default '0',
  `nom` varchar(45) NOT NULL default '',
  `description` varchar(200) default NULL,
  `etat` int(11) default NULL,
  `chargeprevue` int(11) default NULL,
  `tempspasse` int(11) default NULL,
  `tempsrestant` int(11) default NULL,
  `datedebutprevue` date default NULL,
  `datefinprevue` date default NULL,
  `datedebutreelle` date default NULL,
  `datefinreelle` date default NULL,
  `iditeration` int(10) unsigned NOT NULL default '0',
  `idresponsable` int(10) unsigned NOT NULL default '0',
  PRIMARY KEY  (`idtache`),
  KEY `FK_tachescolaboratives_iditeration` (`iditeration`),
  KEY `FK_tachescollaboratives_idresponsable` (`idresponsable`),
  CONSTRAINT `FK_tachescolaboratives_iditeration` FOREIGN KEY (`iditeration`) REFERENCES `iterations` (`iditeration`),
  CONSTRAINT `FK_tachescollaboratives_idresponsable` FOREIGN KEY (`idresponsable`) REFERENCES `membres` (`idmembre`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='InnoDB free: 3072 kB';

DROP TABLE IF EXISTS `utilisateurs`;
CREATE TABLE `utilisateurs` (
  `login` varchar(45) NOT NULL default '',
  `password` varchar(45) NOT NULL default '',
  `fonction` varchar(45) NOT NULL default '',
  PRIMARY KEY  (`login`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
INSERT INTO `utilisateurs` (`login`,`password`,`fonction`) VALUES 
 ('directeur','902c77af765b79c19f2d71fa427008d0','dir'),
 ('sup','2eeecd72c567401e6988624b179d0b14','sup');
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT;
SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS;
SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
