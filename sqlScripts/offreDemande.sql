-- MySQL dump 10.13  Distrib 5.5.35, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: offreDemande
-- ------------------------------------------------------
-- Server version	5.5.35-0ubuntu0.13.10.2

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Current Database: `offreDemande`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `offreDemande` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `offreDemande`;

--
-- Table structure for table `Categories`
--

DROP TABLE IF EXISTS `Categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Categories` (
  `idCategories` int(11) NOT NULL AUTO_INCREMENT,
  `categorieParente` int(11) DEFAULT NULL,
  `nomCategorie` varchar(45) NOT NULL,
  `pourProfil` int(11) DEFAULT '0',
  `valeur` int(11) NOT NULL,
  `description` text,
  `semantique` int(11) DEFAULT NULL,
  PRIMARY KEY (`idCategories`),
  KEY `fk_Categories_2_idx` (`pourProfil`),
  KEY `fk_Categories_1_idx` (`categorieParente`),
  KEY `fk_Categories_3_idx` (`semantique`),
  CONSTRAINT `fk_Categories_1` FOREIGN KEY (`categorieParente`) REFERENCES `Categories` (`idCategories`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Categories_2` FOREIGN KEY (`pourProfil`) REFERENCES `Profil` (`idProfil`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Categories_3` FOREIGN KEY (`semantique`) REFERENCES `Semantique` (`idSemantique`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Categories`
--

LOCK TABLES `Categories` WRITE;
/*!40000 ALTER TABLE `Categories` DISABLE KEYS */;
INSERT INTO `Categories` VALUES (1,NULL,'Pays',1,10,'Categorie de lieu',1),(2,NULL,'Metiers',1,10,'Categorie des metiers',2),(3,1,'Liban',1,10,'Liban',3),(4,3,'Beyrouth',1,5,'',4),(5,3,'MontLiban',1,1,'',4),(6,2,'Informatique',1,10,'',4),(7,6,'Myql',1,10,'',4),(8,6,'Java',1,10,'',4),(9,NULL,'Diplôme',1,1,NULL,NULL),(10,9,'Ingénieur',1,10,NULL,NULL),(12,NULL,'Diplôme',3,1,NULL,NULL),(16,12,'Ingénieur',3,10,NULL,NULL),(17,NULL,'Pays',5,1,NULL,NULL),(18,NULL,'Pays',6,2,NULL,NULL),(20,21,'Java',NULL,5,NULL,4),(21,22,'Informatique',NULL,10,NULL,4),(22,NULL,'Metiers',5,1,NULL,2),(23,NULL,'Metiers',7,10,NULL,2),(24,25,'Ingénieur',6,10,NULL,NULL),(25,NULL,'Diplôme',6,1,NULL,NULL),(26,27,'Beyrouth',2,5,NULL,4),(27,28,'Liban',2,10,NULL,3),(28,NULL,'Pays',2,10,NULL,1);
/*!40000 ALTER TABLE `Categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `CritereDemandeur`
--

DROP TABLE IF EXISTS `CritereDemandeur`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CritereDemandeur` (
  `idDemandeur` int(11) NOT NULL,
  `idtType` int(11) NOT NULL,
  `valeur` int(11) NOT NULL,
  PRIMARY KEY (`idDemandeur`,`idtType`),
  KEY `fk_ValeurType_1_idx` (`idtType`),
  KEY `fk_ValeurType_20_idx` (`idDemandeur`),
  CONSTRAINT `fk_ValeurType_10` FOREIGN KEY (`idtType`) REFERENCES `Type` (`idType`) ON UPDATE NO ACTION,
  CONSTRAINT `fk_ValeurType_20` FOREIGN KEY (`idDemandeur`) REFERENCES `Demandeur` (`idDemandeur`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CritereDemandeur`
--

LOCK TABLES `CritereDemandeur` WRITE;
/*!40000 ALTER TABLE `CritereDemandeur` DISABLE KEYS */;
INSERT INTO `CritereDemandeur` VALUES (1,1,2),(1,2,3),(1,4,1),(2,3,5),(2,4,10),(2,6,2),(3,5,10),(3,6,1),(6,4,1),(6,6,5);
/*!40000 ALTER TABLE `CritereDemandeur` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `CritereOffrant`
--

DROP TABLE IF EXISTS `CritereOffrant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CritereOffrant` (
  `idOffrant` int(11) NOT NULL,
  `idtType` int(11) NOT NULL,
  `valeur` int(11) NOT NULL,
  PRIMARY KEY (`idOffrant`,`idtType`),
  KEY `fk_ValeurType_2_idx` (`idOffrant`),
  KEY `fk_ValeurType_1_idx` (`idtType`),
  CONSTRAINT `fk_ValeurType_1` FOREIGN KEY (`idtType`) REFERENCES `Type` (`idType`) ON UPDATE NO ACTION,
  CONSTRAINT `fk_ValeurType_2` FOREIGN KEY (`idOffrant`) REFERENCES `Offrant` (`idOffrant`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CritereOffrant`
--

LOCK TABLES `CritereOffrant` WRITE;
/*!40000 ALTER TABLE `CritereOffrant` DISABLE KEYS */;
INSERT INTO `CritereOffrant` VALUES (1,4,2),(2,1,3),(2,2,5),(3,3,5),(3,6,5),(4,5,3);
/*!40000 ALTER TABLE `CritereOffrant` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Demandeur`
--

DROP TABLE IF EXISTS `Demandeur`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Demandeur` (
  `idDemandeur` int(11) NOT NULL AUTO_INCREMENT,
  `informations` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idDemandeur`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Demandeur`
--

LOCK TABLES `Demandeur` WRITE;
/*!40000 ALTER TABLE `Demandeur` DISABLE KEYS */;
INSERT INTO `Demandeur` VALUES (1,'Pascal Info'),(2,'Safa eco'),(3,'chercheCivil'),(6,'secretaires');
/*!40000 ALTER TABLE `Demandeur` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Domain`
--

DROP TABLE IF EXISTS `Domain`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Domain` (
  `idDomain` int(11) NOT NULL AUTO_INCREMENT,
  `nomDomain` varchar(45) NOT NULL,
  `descriptionDomain` text,
  PRIMARY KEY (`idDomain`),
  UNIQUE KEY `nomDomain_UNIQUE` (`nomDomain`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Domain`
--

LOCK TABLES `Domain` WRITE;
/*!40000 ALTER TABLE `Domain` DISABLE KEYS */;
INSERT INTO `Domain` VALUES (1,'Informatique',''),(2,'EcoGest',''),(3,'GenieCivil','');
/*!40000 ALTER TABLE `Domain` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Offrant`
--

DROP TABLE IF EXISTS `Offrant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Offrant` (
  `idOffrant` int(11) NOT NULL AUTO_INCREMENT,
  `informations` text,
  PRIMARY KEY (`idOffrant`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Offrant`
--

LOCK TABLES `Offrant` WRITE;
/*!40000 ALTER TABLE `Offrant` DISABLE KEYS */;
INSERT INTO `Offrant` VALUES (1,'ISSAE'),(2,'CoFares'),(3,'Banque Liban'),(4,'SuperBatiment');
/*!40000 ALTER TABLE `Offrant` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Profil`
--

DROP TABLE IF EXISTS `Profil`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Profil` (
  `idProfil` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(45) DEFAULT NULL,
  `typeProfil` char(1) NOT NULL COMMENT 'Offrant (o) ou Demandeur (d)',
  PRIMARY KEY (`idProfil`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Profil`
--

LOCK TABLES `Profil` WRITE;
/*!40000 ALTER TABLE `Profil` DISABLE KEYS */;
INSERT INTO `Profil` VALUES (1,'Pascal Fares','D'),(2,'Mohamad Safa','D'),(3,'Ingénieur Civil','D'),(4,'Secrétaire','D'),(5,'ISSAE','O'),(6,'Banque Liban','O'),(7,'SuperBatiment','O');
/*!40000 ALTER TABLE `Profil` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Semantique`
--

DROP TABLE IF EXISTS `Semantique`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Semantique` (
  `idSemantique` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Une sémantique associé au catégories.\n\nPar exemple Java, java, language java => une sématique unique',
  `description` text,
  PRIMARY KEY (`idSemantique`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Semantique`
--

LOCK TABLES `Semantique` WRITE;
/*!40000 ALTER TABLE `Semantique` DISABLE KEYS */;
INSERT INTO `Semantique` VALUES (1,'Categorie de lieu'),(2,'Categorie des metiers'),(3,'Liban'),(4,'Le nom de la catégorie qui définie la sémantique'),(8,'Categorie de lieu'),(9,'Categorie des metiers'),(10,'Liban'),(11,''),(15,'Diplômes');
/*!40000 ALTER TABLE `Semantique` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Type`
--

DROP TABLE IF EXISTS `Type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Type` (
  `idType` int(11) NOT NULL AUTO_INCREMENT,
  `nomType` varchar(45) NOT NULL,
  `descriptionType` text,
  `forDomain` int(11) NOT NULL,
  PRIMARY KEY (`idType`),
  UNIQUE KEY `nomType_UNIQUE` (`nomType`),
  KEY `fk_Type_1_idx` (`forDomain`),
  CONSTRAINT `fk_Type_1` FOREIGN KEY (`forDomain`) REFERENCES `Domain` (`idDomain`) ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Type`
--

LOCK TABLES `Type` WRITE;
/*!40000 ALTER TABLE `Type` DISABLE KEYS */;
INSERT INTO `Type` VALUES (1,'Java','',1),(2,'MySql','',1),(3,'Finance','',2),(4,'Compta','',2),(5,'Ouvrage d\'art','',3),(6,'bureautique','',1);
/*!40000 ALTER TABLE `Type` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-02-13  1:46:21
