CREATE DATABASE  IF NOT EXISTS `acme` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `acme`;
-- MySQL dump 10.13  Distrib 8.0.16, for Win64 (x86_64)
--
-- Host: localhost    Database: acme
-- ------------------------------------------------------
-- Server version	5.7.26-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `dia_atendimento`
--

DROP TABLE IF EXISTS `dia_atendimento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `dia_atendimento` (
  `medico_id` bigint(20) NOT NULL,
  `dia` varchar(255) DEFAULT NULL,
  KEY `FKi2j3qf07vf1kwltav898h6uxv` (`medico_id`),
  CONSTRAINT `FKi2j3qf07vf1kwltav898h6uxv` FOREIGN KEY (`medico_id`) REFERENCES `tb_medicos` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dia_atendimento`
--

LOCK TABLES `dia_atendimento` WRITE;
/*!40000 ALTER TABLE `dia_atendimento` DISABLE KEYS */;
INSERT INTO `dia_atendimento` VALUES (1,'SEGUNDA'),(1,'TERÇA'),(1,'QUARTA'),(1,'SEXTA'),(2,'DOMINGO'),(2,'SEGUNDA'),(2,'TERÇA');
/*!40000 ALTER TABLE `dia_atendimento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (1);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_agendamentos`
--

DROP TABLE IF EXISTS `tb_agendamentos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tb_agendamentos` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `dataAtendimento` datetime NOT NULL,
  `hora` varchar(255) NOT NULL,
  `status` varchar(255) DEFAULT NULL,
  `medico_id` bigint(20) NOT NULL,
  `paciente_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2yu1uhrx295a51ausnmpnegqc` (`medico_id`),
  KEY `FKsy82jl7hfwre03o9ewkqdmaew` (`paciente_id`),
  CONSTRAINT `FK2yu1uhrx295a51ausnmpnegqc` FOREIGN KEY (`medico_id`) REFERENCES `tb_medicos` (`id`),
  CONSTRAINT `FKsy82jl7hfwre03o9ewkqdmaew` FOREIGN KEY (`paciente_id`) REFERENCES `tb_pacientes` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_agendamentos`
--

LOCK TABLES `tb_agendamentos` WRITE;
/*!40000 ALTER TABLE `tb_agendamentos` DISABLE KEYS */;
INSERT INTO `tb_agendamentos` VALUES (1,'2019-08-21 00:00:00','ONZE','CANCELADA',1,1),(2,'2019-08-20 00:00:00','TREZE','CANCELADA',2,1),(3,'2019-10-01 00:00:00','ONZE','ATIVA',2,1),(4,'2019-08-27 00:00:00','TREZE','CANCELADA',1,3),(5,'2019-09-02 00:00:00','DOZE','CANCELADA',1,3),(6,'2019-12-01 00:00:00','DOZE','CANCELADA',2,1),(7,'2019-11-04 00:00:00','DOZE','ATIVA',2,2),(8,'2019-11-04 00:00:00','ONZE','CANCELADA',2,1),(9,'2019-09-03 00:00:00','DOZE','CANCELADA',2,3);
/*!40000 ALTER TABLE `tb_agendamentos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_contatos`
--

DROP TABLE IF EXISTS `tb_contatos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tb_contatos` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) DEFAULT NULL,
  `telefone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_contatos`
--

LOCK TABLES `tb_contatos` WRITE;
/*!40000 ALTER TABLE `tb_contatos` DISABLE KEYS */;
INSERT INTO `tb_contatos` VALUES (1,'joao','1231'),(2,'asdf','2342342'),(3,'asdf','çlkjç'),(4,'dfas','asdfa'),(5,'asdf','sdf'),(6,'sdf','sdfa');
/*!40000 ALTER TABLE `tb_contatos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_grupo`
--

DROP TABLE IF EXISTS `tb_grupo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tb_grupo` (
  `id` bigint(20) NOT NULL,
  `descricao` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_grupo`
--

LOCK TABLES `tb_grupo` WRITE;
/*!40000 ALTER TABLE `tb_grupo` DISABLE KEYS */;
INSERT INTO `tb_grupo` VALUES (1,'Administradores','ADMINISTRADORES');
/*!40000 ALTER TABLE `tb_grupo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_medicos`
--

DROP TABLE IF EXISTS `tb_medicos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tb_medicos` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `crm` varchar(255) NOT NULL,
  `nome` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_medicos`
--

LOCK TABLES `tb_medicos` WRITE;
/*!40000 ALTER TABLE `tb_medicos` DISABLE KEYS */;
INSERT INTO `tb_medicos` VALUES (1,'1234','João'),(2,'12343','Antonio da Silva');
/*!40000 ALTER TABLE `tb_medicos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_pacientes`
--

DROP TABLE IF EXISTS `tb_pacientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tb_pacientes` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cpf` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `nome` varchar(255) NOT NULL,
  `telefone` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_pacientes`
--

LOCK TABLES `tb_pacientes` WRITE;
/*!40000 ALTER TABLE `tb_pacientes` DISABLE KEYS */;
INSERT INTO `tb_pacientes` VALUES (1,'23242','maria@sdf','Maria','3232'),(2,'433','joao@sd','João','2342'),(3,'4534','carlos@sdfs','Carlos','4524353'),(4,'534','dani@sdfs','Daniela','234234'),(5,'43323','fran@sds','Francisco','445345'),(6,'342342','asdfas','Geraldo','234234');
/*!40000 ALTER TABLE `tb_pacientes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_pacientes_tb_contatos`
--

DROP TABLE IF EXISTS `tb_pacientes_tb_contatos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tb_pacientes_tb_contatos` (
  `Paciente_id` bigint(20) NOT NULL,
  `contatos_id` bigint(20) NOT NULL,
  UNIQUE KEY `UK_ir61kn0a4kmjn04b9a2b4t228` (`contatos_id`),
  KEY `FK15x0nktafp3i86uupvoki12xo` (`Paciente_id`),
  CONSTRAINT `FK15x0nktafp3i86uupvoki12xo` FOREIGN KEY (`Paciente_id`) REFERENCES `tb_pacientes` (`id`),
  CONSTRAINT `FKas4p45britry23p99wk4lp9x8` FOREIGN KEY (`contatos_id`) REFERENCES `tb_contatos` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_pacientes_tb_contatos`
--

LOCK TABLES `tb_pacientes_tb_contatos` WRITE;
/*!40000 ALTER TABLE `tb_pacientes_tb_contatos` DISABLE KEYS */;
INSERT INTO `tb_pacientes_tb_contatos` VALUES (1,1),(2,2),(3,3),(4,4),(5,5),(6,6);
/*!40000 ALTER TABLE `tb_pacientes_tb_contatos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_usuarios`
--

DROP TABLE IF EXISTS `tb_usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tb_usuarios` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `login` varchar(255) DEFAULT NULL,
  `nome` varchar(255) NOT NULL,
  `senha` varchar(255) DEFAULT NULL,
  `grupo_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKefqus50likfufc9yywsn8rdvt` (`grupo_id`),
  CONSTRAINT `FKefqus50likfufc9yywsn8rdvt` FOREIGN KEY (`grupo_id`) REFERENCES `tb_grupo` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_usuarios`
--

LOCK TABLES `tb_usuarios` WRITE;
/*!40000 ALTER TABLE `tb_usuarios` DISABLE KEYS */;
INSERT INTO `tb_usuarios` VALUES (1,'marcio@marcio','admin','Márcio Costa','admin',1);
/*!40000 ALTER TABLE `tb_usuarios` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-08-20 14:32:34