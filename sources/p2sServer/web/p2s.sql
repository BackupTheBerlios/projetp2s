-- MySQL dump 10.9
--
-- Host: localhost    Database: essai
-- ------------------------------------------------------
-- Server version	4.1.8-nt

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE="NO_AUTO_VALUE_ON_ZERO" */;

--
-- Table structure for table `artefacts`
--

DROP TABLE IF EXISTS `artefacts`;
CREATE TABLE `artefacts` (
  `idartefact` int(10) unsigned NOT NULL auto_increment,
  `livrable` enum('true','false') NOT NULL default 'true',
  `etat` int(10) unsigned default NULL,
  `nom` varchar(45) NOT NULL default '',
  `description` varchar(200) default NULL,
  `idresponsable` int(10) unsigned NOT NULL default '0',
  PRIMARY KEY  (`idartefact`),
  KEY `FK_artefacts_idresponsable` (`idresponsable`),
  CONSTRAINT `FK_artefacts_idresponsable` FOREIGN KEY (`idresponsable`) REFERENCES `membres` (`idmembre`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `artefacts`
--


/*!40000 ALTER TABLE `artefacts` DISABLE KEYS */;
LOCK TABLES `artefacts` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `artefacts` ENABLE KEYS */;

--
-- Table structure for table `artefacts_entrees_taches`
--

DROP TABLE IF EXISTS `artefacts_entrees_taches`;
CREATE TABLE `artefacts_entrees_taches` (
  `idartefact` int(10) unsigned NOT NULL auto_increment,
  `idtache` int(10) unsigned NOT NULL default '0',
  PRIMARY KEY  (`idartefact`,`idtache`),
  KEY `FK_artefacts_entrees_taches_idtache` (`idtache`),
  CONSTRAINT `FK_artefacts_entrees_taches_idartefact` FOREIGN KEY (`idartefact`) REFERENCES `artefacts` (`idartefact`),
  CONSTRAINT `FK_artefacts_entrees_taches_idtache` FOREIGN KEY (`idtache`) REFERENCES `taches` (`idtache`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `artefacts_entrees_taches`
--


/*!40000 ALTER TABLE `artefacts_entrees_taches` DISABLE KEYS */;
LOCK TABLES `artefacts_entrees_taches` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `artefacts_entrees_taches` ENABLE KEYS */;

--
-- Table structure for table `artefacts_entrees_tachescollaboratives`
--

DROP TABLE IF EXISTS `artefacts_entrees_tachescollaboratives`;
CREATE TABLE `artefacts_entrees_tachescollaboratives` (
  `idartefact` int(10) unsigned NOT NULL auto_increment,
  `idtache` int(10) unsigned NOT NULL default '0',
  PRIMARY KEY  (`idartefact`,`idtache`),
  KEY `FK_artefacts_entrees_tachescollaboratives_idtache` (`idtache`),
  CONSTRAINT `FK_artefacts_entrees_tachescollaboratives_idtache` FOREIGN KEY (`idtache`) REFERENCES `tachescollaboratives` (`idtache`),
  CONSTRAINT `FK_artefacts_entrees_tachescollaboratives_idartefact` FOREIGN KEY (`idartefact`) REFERENCES `artefacts` (`idartefact`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='InnoDB free: 3072 kB';

--
-- Dumping data for table `artefacts_entrees_tachescollaboratives`
--


/*!40000 ALTER TABLE `artefacts_entrees_tachescollaboratives` DISABLE KEYS */;
LOCK TABLES `artefacts_entrees_tachescollaboratives` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `artefacts_entrees_tachescollaboratives` ENABLE KEYS */;

--
-- Table structure for table `artefacts_sorties_taches`
--

DROP TABLE IF EXISTS `artefacts_sorties_taches`;
CREATE TABLE `artefacts_sorties_taches` (
  `idartefact` int(10) unsigned NOT NULL auto_increment,
  `idtache` int(10) unsigned NOT NULL default '0',
  PRIMARY KEY  (`idartefact`,`idtache`),
  KEY `FK_artefacts_sorties_taches_idtache` (`idtache`),
  CONSTRAINT `FK_artefacts_sorties_taches_idartefact` FOREIGN KEY (`idartefact`) REFERENCES `artefacts` (`idartefact`),
  CONSTRAINT `FK_artefacts_sorties_taches_idtache` FOREIGN KEY (`idtache`) REFERENCES `taches` (`idtache`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `artefacts_sorties_taches`
--


/*!40000 ALTER TABLE `artefacts_sorties_taches` DISABLE KEYS */;
LOCK TABLES `artefacts_sorties_taches` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `artefacts_sorties_taches` ENABLE KEYS */;

--
-- Table structure for table `artefacts_sorties_tachescollaboratives`
--

DROP TABLE IF EXISTS `artefacts_sorties_tachescollaboratives`;
CREATE TABLE `artefacts_sorties_tachescollaboratives` (
  `idartefact` int(10) unsigned NOT NULL auto_increment,
  `idtache` int(10) unsigned NOT NULL default '0',
  PRIMARY KEY  (`idartefact`,`idtache`),
  KEY `FK_artefacts_sorties_tachescollaboratives_idtache` (`idtache`),
  CONSTRAINT `FK_artefacts_sorties_tachescollaboratives_idtache` FOREIGN KEY (`idtache`) REFERENCES `tachescollaboratives` (`idtache`),
  CONSTRAINT `FK_artefacts_sorties_tachescollaboratives_idartefact` FOREIGN KEY (`idartefact`) REFERENCES `artefacts` (`idartefact`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `artefacts_sorties_tachescollaboratives`
--


/*!40000 ALTER TABLE `artefacts_sorties_tachescollaboratives` DISABLE KEYS */;
LOCK TABLES `artefacts_sorties_tachescollaboratives` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `artefacts_sorties_tachescollaboratives` ENABLE KEYS */;

--
-- Table structure for table `indicateurs_iteration`
--

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

--
-- Dumping data for table `indicateurs_iteration`
--


/*!40000 ALTER TABLE `indicateurs_iteration` DISABLE KEYS */;
LOCK TABLES `indicateurs_iteration` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `indicateurs_iteration` ENABLE KEYS */;

--
-- Table structure for table `indicateurs_projet`
--

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

--
-- Dumping data for table `indicateurs_projet`
--


/*!40000 ALTER TABLE `indicateurs_projet` DISABLE KEYS */;
LOCK TABLES `indicateurs_projet` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `indicateurs_projet` ENABLE KEYS */;

--
-- Table structure for table `indicateurs_tache`
--

DROP TABLE IF EXISTS `indicateurs_tache`;
CREATE TABLE `indicateurs_tache` (
  `idtache` int(10) unsigned NOT NULL auto_increment,
  `nombretotal` int(10) unsigned default NULL,
  `nombreterminees` int(10) unsigned default NULL,
  `dureemoyenne` int(10) unsigned default NULL,
  PRIMARY KEY  (`idtache`),
  CONSTRAINT `FK_indicateurs_tache_idtache` FOREIGN KEY (`idtache`) REFERENCES `taches` (`idtache`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `indicateurs_tache`
--


/*!40000 ALTER TABLE `indicateurs_tache` DISABLE KEYS */;
LOCK TABLES `indicateurs_tache` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `indicateurs_tache` ENABLE KEYS */;

--
-- Table structure for table `indicateurs_tachecollaborative`
--

DROP TABLE IF EXISTS `indicateurs_tachecollaborative`;
CREATE TABLE `indicateurs_tachecollaborative` (
  `idtache` int(10) unsigned NOT NULL auto_increment,
  `nombretotal` int(10) unsigned default NULL,
  `nombreterminees` int(10) unsigned default NULL,
  `dureemoyenne` int(10) unsigned default NULL,
  PRIMARY KEY  (`idtache`),
  CONSTRAINT `FK_indicateurs_tachecollaborative_idtache` FOREIGN KEY (`idtache`) REFERENCES `tachescollaboratives` (`idtache`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `indicateurs_tachecollaborative`
--


/*!40000 ALTER TABLE `indicateurs_tachecollaborative` DISABLE KEYS */;
LOCK TABLES `indicateurs_tachecollaborative` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `indicateurs_tachecollaborative` ENABLE KEYS */;

--
-- Table structure for table `iterations`
--

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

--
-- Dumping data for table `iterations`
--


/*!40000 ALTER TABLE `iterations` DISABLE KEYS */;
LOCK TABLES `iterations` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `iterations` ENABLE KEYS */;

--
-- Table structure for table `membres`
--

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

--
-- Dumping data for table `membres`
--


/*!40000 ALTER TABLE `membres` DISABLE KEYS */;
LOCK TABLES `membres` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `membres` ENABLE KEYS */;

--
-- Table structure for table `membres_projets`
--

DROP TABLE IF EXISTS `membres_projets`;
CREATE TABLE `membres_projets` (
  `idmembre` int(10) unsigned NOT NULL auto_increment,
  `idprojet` int(10) unsigned NOT NULL default '0',
  PRIMARY KEY  (`idmembre`,`idprojet`),
  KEY `FK_membres_projets_idprojet` (`idprojet`),
  CONSTRAINT `FK_membres_projets_idmembre` FOREIGN KEY (`idmembre`) REFERENCES `membres` (`idmembre`),
  CONSTRAINT `FK_membres_projets_idprojet` FOREIGN KEY (`idprojet`) REFERENCES `projets` (`idprojet`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `membres_projets`
--


/*!40000 ALTER TABLE `membres_projets` DISABLE KEYS */;
LOCK TABLES `membres_projets` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `membres_projets` ENABLE KEYS */;

--
-- Table structure for table `membres_tachescollaboratives`
--

DROP TABLE IF EXISTS `membres_tachescollaboratives`;
CREATE TABLE `membres_tachescollaboratives` (
  `idmembre` int(10) unsigned NOT NULL auto_increment,
  `idtache` int(10) unsigned NOT NULL default '0',
  PRIMARY KEY  (`idmembre`,`idtache`),
  KEY `FK_membrestachescolaboratives_idtache` (`idtache`),
  CONSTRAINT `FK_membrestachescolaboratives_idmembre` FOREIGN KEY (`idmembre`) REFERENCES `membres` (`idmembre`),
  CONSTRAINT `FK_membrestachescolaboratives_idtache` FOREIGN KEY (`idtache`) REFERENCES `tachescollaboratives` (`idtache`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `membres_tachescollaboratives`
--


/*!40000 ALTER TABLE `membres_tachescollaboratives` DISABLE KEYS */;
LOCK TABLES `membres_tachescollaboratives` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `membres_tachescollaboratives` ENABLE KEYS */;

--
-- Table structure for table `mesures`
--

DROP TABLE IF EXISTS `mesures`;
CREATE TABLE `mesures` (
  `idmesure` int(10) unsigned NOT NULL auto_increment,
  `nom` varchar(45) NOT NULL default '',
  `description` varchar(200) default NULL,
  `type` int(10) unsigned NOT NULL default '0',
  `valeur` varchar(45) NOT NULL default '',
  PRIMARY KEY  (`idmesure`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `mesures`
--


/*!40000 ALTER TABLE `mesures` DISABLE KEYS */;
LOCK TABLES `mesures` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `mesures` ENABLE KEYS */;

--
-- Table structure for table `projets`
--

DROP TABLE IF EXISTS `projets`;
CREATE TABLE `projets` (
  `idprojet` int(10) unsigned NOT NULL auto_increment,
  `nom` varchar(45) NOT NULL default '',
  `datedebut` date default NULL,
  `datefin` date default NULL,
  `description` varchar(200) default NULL,
  `budget` int(10) unsigned default '0',
  PRIMARY KEY  (`idprojet`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `projets`
--


/*!40000 ALTER TABLE `projets` DISABLE KEYS */;
LOCK TABLES `projets` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `projets` ENABLE KEYS */;

--
-- Table structure for table `risques`
--

DROP TABLE IF EXISTS `risques`;
CREATE TABLE `risques` (
  `idrisque` int(10) unsigned NOT NULL auto_increment,
  `nom` varchar(45) NOT NULL default '',
  `priorite` int(10) unsigned default NULL,
  `impact` int(10) unsigned default NULL,
  `etat` int(10) unsigned default NULL,
  `description` varchar(200) default NULL,
  `idprojet` int(10) unsigned NOT NULL default '0',
  PRIMARY KEY  (`idrisque`),
  KEY `FK_risques_idprojet` (`idprojet`),
  CONSTRAINT `FK_risques_idprojet` FOREIGN KEY (`idprojet`) REFERENCES `projets` (`idprojet`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `risques`
--


/*!40000 ALTER TABLE `risques` DISABLE KEYS */;
LOCK TABLES `risques` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `risques` ENABLE KEYS */;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles` (
  `idrole` int(10) unsigned NOT NULL auto_increment,
  `nom` varchar(45) NOT NULL default '',
  `description` varchar(200) default NULL,
  PRIMARY KEY  (`idrole`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `roles`
--


/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
LOCK TABLES `roles` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;

--
-- Table structure for table `roles_membres`
--

DROP TABLE IF EXISTS `roles_membres`;
CREATE TABLE `roles_membres` (
  `idrole` int(10) unsigned NOT NULL auto_increment,
  `idmembre` int(10) unsigned NOT NULL default '0',
  PRIMARY KEY  (`idrole`,`idmembre`),
  KEY `FK_roles_membres_idmembre` (`idmembre`),
  CONSTRAINT `FK_roles_membres_idmembre` FOREIGN KEY (`idmembre`) REFERENCES `membres` (`idmembre`),
  CONSTRAINT `FK_roles_membres_idrole` FOREIGN KEY (`idrole`) REFERENCES `roles` (`idrole`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `roles_membres`
--


/*!40000 ALTER TABLE `roles_membres` DISABLE KEYS */;
LOCK TABLES `roles_membres` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `roles_membres` ENABLE KEYS */;

--
-- Table structure for table `superviseur_projets`
--

DROP TABLE IF EXISTS `superviseur_projets`;
CREATE TABLE `superviseur_projets` (
  `login` varchar(45) NOT NULL default '',
  `idprojet` int(10) unsigned NOT NULL default '0',
  PRIMARY KEY  (`login`,`idprojet`),
  KEY `FK_superviseurprojets_idprojet` (`idprojet`),
  CONSTRAINT `FK_superviseurprojets_idprojet` FOREIGN KEY (`idprojet`) REFERENCES `projets` (`idprojet`),
  CONSTRAINT `FK_superviseurprojets_login` FOREIGN KEY (`login`) REFERENCES `utilisateurs` (`login`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='InnoDB free: 4096 kB';

--
-- Dumping data for table `superviseur_projets`
--


/*!40000 ALTER TABLE `superviseur_projets` DISABLE KEYS */;
LOCK TABLES `superviseur_projets` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `superviseur_projets` ENABLE KEYS */;

--
-- Table structure for table `taches`
--

DROP TABLE IF EXISTS `taches`;
CREATE TABLE `taches` (
  `idtache` int(10) unsigned NOT NULL auto_increment,
  `nom` varchar(45) NOT NULL default '',
  `description` varchar(125) default NULL,
  `etat` int(10) unsigned default NULL,
  `chargeprevue` int(10) unsigned default NULL,
  `tempspasse` int(10) unsigned default NULL,
  `tempsrestant` int(10) unsigned default NULL,
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

--
-- Dumping data for table `taches`
--


/*!40000 ALTER TABLE `taches` DISABLE KEYS */;
LOCK TABLES `taches` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `taches` ENABLE KEYS */;

--
-- Table structure for table `tachescollaboratives`
--

DROP TABLE IF EXISTS `tachescollaboratives`;
CREATE TABLE `tachescollaboratives` (
  `idtache` int(10) unsigned NOT NULL default '0',
  `nom` varchar(45) NOT NULL default '',
  `description` varchar(200) default NULL,
  `etat` int(10) unsigned default NULL,
  `chargeprevue` int(10) unsigned default NULL,
  `tempspasse` int(10) unsigned default NULL,
  `tempsrestant` int(10) unsigned default NULL,
  `datedebutprevue` date default NULL,
  `datefinprevue` date default NULL,
  `datedebutreelle` date default NULL,
  `datefinreelle` date default NULL,
  `iditeration` int(10) unsigned NOT NULL default '0',
  `idresponsable` int(10) unsigned NOT NULL default '0',
  PRIMARY KEY  (`idtache`),
  KEY `FK_tachescolaboratives_iditeration` (`iditeration`),
  KEY `FK_tachescollaboratives_idresponsable` (`idresponsable`),
  CONSTRAINT `FK_tachescollaboratives_idresponsable` FOREIGN KEY (`idresponsable`) REFERENCES `membres` (`idmembre`),
  CONSTRAINT `FK_tachescolaboratives_iditeration` FOREIGN KEY (`iditeration`) REFERENCES `iterations` (`iditeration`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='InnoDB free: 3072 kB';

--
-- Dumping data for table `tachescollaboratives`
--


/*!40000 ALTER TABLE `tachescollaboratives` DISABLE KEYS */;
LOCK TABLES `tachescollaboratives` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `tachescollaboratives` ENABLE KEYS */;

--
-- Table structure for table `utilisateurs`
--

DROP TABLE IF EXISTS `utilisateurs`;
CREATE TABLE `utilisateurs` (
  `login` varchar(45) NOT NULL default '',
  `password` varchar(45) NOT NULL default '',
  `fonction` varchar(45) NOT NULL default '',
  PRIMARY KEY  (`login`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `utilisateurs`
--


/*!40000 ALTER TABLE `utilisateurs` DISABLE KEYS */;
LOCK TABLES `utilisateurs` WRITE;
INSERT INTO `utilisateurs` VALUES ('directeur','directeurpass','dir');
UNLOCK TABLES;
/*!40000 ALTER TABLE `utilisateurs` ENABLE KEYS */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

