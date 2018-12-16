-- MySQL dump 10.16  Distrib 10.1.37-MariaDB, for Linux (x86_64)
--
-- Host: localhost    Database: pm_clinica_saracura
-- ------------------------------------------------------
-- Server version	10.1.37-MariaDB

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
-- Table structure for table `AgendaEquipamento`
--

DROP TABLE IF EXISTS `AgendaEquipamento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `AgendaEquipamento` (
  `dataAgendamento` datetime NOT NULL,
  `descricao` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `idEquipamento` int(11) DEFAULT NULL,
  `idMedico` int(11) DEFAULT NULL,
  `idPaciente` int(11) DEFAULT NULL,
  PRIMARY KEY (`dataAgendamento`),
  KEY `FKat4s2xxfwwdouirrhrdq9gqf5` (`idEquipamento`),
  KEY `FKimorx7gnakdvhm283w6vco25l` (`idMedico`),
  KEY `FKe675x304fb6nsikfh9srvfs5k` (`idPaciente`),
  CONSTRAINT `FKat4s2xxfwwdouirrhrdq9gqf5` FOREIGN KEY (`idEquipamento`) REFERENCES `Equipamento` (`idEquipamento`),
  CONSTRAINT `FKe675x304fb6nsikfh9srvfs5k` FOREIGN KEY (`idPaciente`) REFERENCES `Paciente` (`idPaciente`),
  CONSTRAINT `FKimorx7gnakdvhm283w6vco25l` FOREIGN KEY (`idMedico`) REFERENCES `Medico` (`crm`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `AgendaEquipamento`
--

LOCK TABLES `AgendaEquipamento` WRITE;
/*!40000 ALTER TABLE `AgendaEquipamento` DISABLE KEYS */;
INSERT INTO `AgendaEquipamento` VALUES ('2018-12-12 10:20:00',NULL,NULL,325857,3);
/*!40000 ALTER TABLE `AgendaEquipamento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `AgendaMedica`
--

DROP TABLE IF EXISTS `AgendaMedica`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `AgendaMedica` (
  `diaAgendamento` datetime NOT NULL,
  `idMedico` int(11) DEFAULT NULL,
  `idPaciente` int(11) DEFAULT NULL,
  PRIMARY KEY (`diaAgendamento`),
  KEY `FKq9rasbd4afh3y72yy9nbhl97u` (`idMedico`),
  KEY `FK6xmjl9d2aifsjl2xjhj6xf4p` (`idPaciente`),
  CONSTRAINT `FK6xmjl9d2aifsjl2xjhj6xf4p` FOREIGN KEY (`idPaciente`) REFERENCES `Paciente` (`idPaciente`),
  CONSTRAINT `FKq9rasbd4afh3y72yy9nbhl97u` FOREIGN KEY (`idMedico`) REFERENCES `Medico` (`crm`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `AgendaMedica`
--

LOCK TABLES `AgendaMedica` WRITE;
/*!40000 ALTER TABLE `AgendaMedica` DISABLE KEYS */;
INSERT INTO `AgendaMedica` VALUES ('2018-12-18 10:00:00',325857,1);
/*!40000 ALTER TABLE `AgendaMedica` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Convenio`
--

DROP TABLE IF EXISTS `Convenio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Convenio` (
  `idConvenio` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`idConvenio`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Convenio`
--

LOCK TABLES `Convenio` WRITE;
/*!40000 ALTER TABLE `Convenio` DISABLE KEYS */;
INSERT INTO `Convenio` VALUES (1,'Unimed'),(2,'Ipsemg'),(3,'Golden'),(4,'Prevent'),(5,'Samed'),(6,'Bluemed'),(7,'Biovida'),(8,'Health'),(9,'Greenline'),(10,'Notre');
/*!40000 ALTER TABLE `Convenio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `DiaAtendimento`
--

DROP TABLE IF EXISTS `DiaAtendimento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `DiaAtendimento` (
  `idDia` int(11) NOT NULL,
  `nomeDia` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`idDia`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `DiaAtendimento`
--

LOCK TABLES `DiaAtendimento` WRITE;
/*!40000 ALTER TABLE `DiaAtendimento` DISABLE KEYS */;
INSERT INTO `DiaAtendimento` VALUES (1,'Domingo'),(2,'Segunda-feira'),(3,'Terça-feira'),(4,'Quarta-feira'),(5,'Quinta-feira'),(6,'Sexta-feira'),(7,'Sabado');
/*!40000 ALTER TABLE `DiaAtendimento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Equipamento`
--

DROP TABLE IF EXISTS `Equipamento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Equipamento` (
  `idEquipamento` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `statusFuncionamento` bit(1) DEFAULT NULL,
  `idTipoExame` int(11) DEFAULT NULL,
  PRIMARY KEY (`idEquipamento`),
  KEY `FKj1dwpvtl44mhpawsvnrrgwnaw` (`idTipoExame`),
  CONSTRAINT `FKj1dwpvtl44mhpawsvnrrgwnaw` FOREIGN KEY (`idTipoExame`) REFERENCES `TipoExame` (`idTipoExame`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Equipamento`
--

LOCK TABLES `Equipamento` WRITE;
/*!40000 ALTER TABLE `Equipamento` DISABLE KEYS */;
INSERT INTO `Equipamento` VALUES (1,'Sonógrafo','',2),(2,'Tomógrafo','',1),(3,'Mamógrafo','',3),(4,'Máquina de Ressonância','',4),(5,'RaioX','',5);
/*!40000 ALTER TABLE `Equipamento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Especialidade`
--

DROP TABLE IF EXISTS `Especialidade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Especialidade` (
  `idEspecialidade` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`idEspecialidade`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Especialidade`
--

LOCK TABLES `Especialidade` WRITE;
/*!40000 ALTER TABLE `Especialidade` DISABLE KEYS */;
INSERT INTO `Especialidade` VALUES (1,'Pediatria'),(2,'Dermatologia'),(3,'Urologia'),(4,'Cardiologia'),(5,'Psiquiatria'),(6,'Psicologia'),(7,'Angiologia'),(8,'Dentista'),(9,'Obstetrícia'),(10,'Ginecologia');
/*!40000 ALTER TABLE `Especialidade` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Medico`
--

DROP TABLE IF EXISTS `Medico`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Medico` (
  `crm` int(11) NOT NULL,
  `celular` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `email` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `horarioAtendimento` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `intervaloAtendimento` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `nome` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `idEspecialidade` int(11) DEFAULT NULL,
  PRIMARY KEY (`crm`),
  KEY `FKdmclvr2kjnm3jmdlidvshffwt` (`idEspecialidade`),
  CONSTRAINT `FKdmclvr2kjnm3jmdlidvshffwt` FOREIGN KEY (`idEspecialidade`) REFERENCES `Especialidade` (`idEspecialidade`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Medico`
--

LOCK TABLES `Medico` WRITE;
/*!40000 ALTER TABLE `Medico` DISABLE KEYS */;
INSERT INTO `Medico` VALUES (123321,'93931317','123321@teste.com','08:00','00:30','Dra. Felicia',2),(123456,'73377337','123456@teste.com','10:00','00:15','Dr. Felisbino',1),(321456,'991546567','321456@teste.com','10:30','00:20','Dr. João',3),(321457,'991546568','321457@teste.com','10:30','00:15','Dr. Pedro',3),(324537,'971876560','324537@teste.com','09:30','00:20','Dr. Ricardo',10),(324837,'984546568','324837@teste.com','08:00','00:20','Dr. Maria',6),(324857,'983546568','324857@teste.com','10:00','00:20','Dr. Griezman',5),(325457,'992546568','325457@teste.com','10:30','00:15','Dra. Mônica',4),(325857,'993546568','325857@teste.com','10:00','00:20','Dr. Eduardo',4),(424837,'987546568','424837@teste.com','08:30','00:30','Dr. Carlos',6),(724837,'987546560','724837@teste.com','08:30','00:30','Dr. Cabral',7),(824637,'987876560','824637@teste.com','10:30','00:15','Dra. Jessica',8),(824737,'987576560','824737@teste.com','09:30','00:15','Dra. Patrícia',8),(924637,'981876560','924637@teste.com','10:30','00:15','Dra. Andreia',9);
/*!40000 ALTER TABLE `Medico` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Medico_DiaAtendimento`
--

DROP TABLE IF EXISTS `Medico_DiaAtendimento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Medico_DiaAtendimento` (
  `Medico_crm` int(11) NOT NULL,
  `diaAtendimento_idDia` int(11) NOT NULL,
  KEY `FK2lj37oje00so0ng85tipoxtd3` (`diaAtendimento_idDia`),
  KEY `FKkevn72ws8vstfls0jhv50wiej` (`Medico_crm`),
  CONSTRAINT `FK2lj37oje00so0ng85tipoxtd3` FOREIGN KEY (`diaAtendimento_idDia`) REFERENCES `DiaAtendimento` (`idDia`),
  CONSTRAINT `FKkevn72ws8vstfls0jhv50wiej` FOREIGN KEY (`Medico_crm`) REFERENCES `Medico` (`crm`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Medico_DiaAtendimento`
--

LOCK TABLES `Medico_DiaAtendimento` WRITE;
/*!40000 ALTER TABLE `Medico_DiaAtendimento` DISABLE KEYS */;
INSERT INTO `Medico_DiaAtendimento` VALUES (123321,1),(123321,2),(123321,3),(123456,2),(123456,3),(123456,4),(321456,2),(321456,3),(321456,4),(321457,1),(321457,2),(321457,3),(324537,1),(324537,2),(324537,3),(324837,5),(324837,6),(324837,7),(324857,2),(324857,3),(324857,4),(325457,2),(325457,3),(325457,4),(325857,3),(325857,4),(325857,5),(424837,4),(424837,5),(424837,6),(724837,4),(724837,5),(724837,6),(824637,1),(824637,2),(824637,3),(824737,2),(824737,3),(824737,4),(924637,3),(924637,4),(924637,5);
/*!40000 ALTER TABLE `Medico_DiaAtendimento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Paciente`
--

DROP TABLE IF EXISTS `Paciente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Paciente` (
  `idPaciente` int(11) NOT NULL AUTO_INCREMENT,
  `dataNascimento` datetime DEFAULT NULL,
  `nome` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `sexo` char(1) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `idConvenio` int(11) DEFAULT NULL,
  PRIMARY KEY (`idPaciente`),
  KEY `FK74s4f2eonv2jf2pksp0ujldhk` (`idConvenio`),
  CONSTRAINT `FK74s4f2eonv2jf2pksp0ujldhk` FOREIGN KEY (`idConvenio`) REFERENCES `Convenio` (`idConvenio`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Paciente`
--

LOCK TABLES `Paciente` WRITE;
/*!40000 ALTER TABLE `Paciente` DISABLE KEYS */;
INSERT INTO `Paciente` VALUES (1,'2015-11-23 00:00:00','Joao Carlos','M',1),(2,'2015-11-23 00:00:00','Maria Carla','F',2),(3,'1997-11-27 00:20:30','Pedro Paulo','M',3),(4,'1997-11-27 00:20:30','Maria José','F',4),(5,'1997-11-27 00:20:30','Maria Mendes','F',5),(6,'1998-11-27 00:20:30','Marcelo','F',6),(7,'1998-11-27 00:20:30','Eduardo','M',7),(8,'1996-11-27 00:20:30','Edvaldo','M',8),(9,'1996-12-27 00:20:30','Ana Paula','F',9),(10,'1996-12-27 00:20:30','João Pedro','M',10);
/*!40000 ALTER TABLE `Paciente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TipoExame`
--

DROP TABLE IF EXISTS `TipoExame`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TipoExame` (
  `idTipoExame` int(11) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`idTipoExame`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TipoExame`
--

LOCK TABLES `TipoExame` WRITE;
/*!40000 ALTER TABLE `TipoExame` DISABLE KEYS */;
INSERT INTO `TipoExame` VALUES (1,'Tomografia'),(2,'Sonografia'),(3,'Mamografia'),(4,'Ressonância'),(5,'Exame de Imagem');
/*!40000 ALTER TABLE `TipoExame` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-12-16 11:28:55
