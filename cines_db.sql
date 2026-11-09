-- MariaDB dump 10.19  Distrib 10.4.32-MariaDB, for Win64 (AMD64)
--
-- Host: localhost    Database: cines_db
-- ------------------------------------------------------
-- Server version	10.4.32-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Current Database: `cines_db`
--

/*!40000 DROP DATABASE IF EXISTS `cines_db`*/;

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `cines_db` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;

USE `cines_db`;

--
-- Table structure for table `cine`
--

DROP TABLE IF EXISTS `cine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cine` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `salas` int(11) NOT NULL,
  `abierto` tinyint(1) NOT NULL,
  `fechaInauguracion` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cine`
--

LOCK TABLES `cine` WRITE;
/*!40000 ALTER TABLE `cine` DISABLE KEYS */;
INSERT INTO `cine` VALUES (1,'Cinemark',20,1,'1996-04-16'),(2,'Cinemark',10,1,'1996-04-16'),(3,'Terrazas',19,1,'2020-09-25'),(4,'Cine Aurora',6,1,'2015-03-20'),(5,'Cine Central',8,1,'2018-07-15'),(6,'Cine Plaza',5,1,'2012-11-10'),(7,'Cine del Parque',4,0,'2009-05-22'),(8,'Cine Estrella',7,1,'2020-02-14'),(9,'Cine Horizonte',10,1,'2017-09-08'),(10,'Cine Premier',6,1,'2014-06-30'),(11,'Cine Boulevard',8,1,'2019-12-05'),(12,'Cine Gran Rex',5,0,'2010-04-18'),(13,'Cine Metropolitan',9,1,'2022-08-12');
/*!40000 ALTER TABLE `cine` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `funcion`
--

DROP TABLE IF EXISTS `funcion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `funcion` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pelicula_id` int(11) NOT NULL,
  `sala_id` int(11) NOT NULL,
  `horario` datetime NOT NULL,
  `precio` decimal(10,2) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_funcion_pelicula` (`pelicula_id`),
  KEY `fk_funcion_sala` (`sala_id`),
  CONSTRAINT `fk_funcion_pelicula` FOREIGN KEY (`pelicula_id`) REFERENCES `pelicula` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_funcion_sala` FOREIGN KEY (`sala_id`) REFERENCES `sala` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `funcion`
--

LOCK TABLES `funcion` WRITE;
/*!40000 ALTER TABLE `funcion` DISABLE KEYS */;
INSERT INTO `funcion` VALUES (1,1,1,'2026-11-10 18:00:00',50.00),(2,5,55,'2026-11-10 18:00:00',30.00);
/*!40000 ALTER TABLE `funcion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pelicula`
--

DROP TABLE IF EXISTS `pelicula`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pelicula` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `titulo` varchar(150) NOT NULL,
  `duracion` int(11) NOT NULL,
  `disponible` tinyint(1) NOT NULL,
  `fechaEstreno` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pelicula`
--

LOCK TABLES `pelicula` WRITE;
/*!40000 ALTER TABLE `pelicula` DISABLE KEYS */;
INSERT INTO `pelicula` VALUES (1,'Gladiador',120,0,'2001-05-02'),(2,'El Último Viaje',128,1,'2025-03-15'),(3,'Código Eclipse',115,1,'2025-04-10'),(4,'Más Allá del Horizonte',142,1,'2024-12-20'),(5,'La Última Misión',131,1,'2025-01-25'),(6,'Sombras del Pasado',108,0,'2024-10-18'),(7,'El Reino Perdido',125,1,'2025-02-14'),(8,'Operación Medianoche',119,1,'2025-05-02'),(9,'Viaje a las Estrellas',136,1,'2024-11-08'),(10,'El Misterio del Lago',104,0,'2024-09-27'),(11,'Guardianes del Tiempo',127,1,'2025-06-13'),(12,'La Ciudad Invisible',112,1,'2025-03-28'),(13,'Noche de Tormenta',98,0,'2025-01-17'),(14,'El Último Detective',121,1,'2025-04-25'),(15,'Aventuras en el Espacio',133,1,'2025-05-16'),(16,'El Secreto de la Montaña',110,1,'2024-12-06');
/*!40000 ALTER TABLE `pelicula` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sala`
--

DROP TABLE IF EXISTS `sala`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sala` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cine_id` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `capacidad` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_sala_cine` (`cine_id`),
  CONSTRAINT `fk_sala_cine` FOREIGN KEY (`cine_id`) REFERENCES `cine` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=118 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sala`
--

LOCK TABLES `sala` WRITE;
/*!40000 ALTER TABLE `sala` DISABLE KEYS */;
INSERT INTO `sala` VALUES (1,1,'Sala 2D',150),(2,1,'Sala 2',100),(3,1,'Sala 3',100),(4,1,'Sala 4',100),(5,1,'Sala 5',100),(6,1,'Sala 6',100),(7,1,'Sala 7',100),(8,1,'Sala 8',100),(9,1,'Sala 9',100),(10,1,'Sala 10',100),(11,1,'Sala 11',100),(12,1,'Sala 12',100),(13,1,'Sala 13',100),(14,1,'Sala 14',100),(15,1,'Sala 15',100),(16,1,'Sala 16',100),(17,1,'Sala 17',100),(18,1,'Sala 18',100),(19,1,'Sala 19',100),(20,1,'Sala 20',100),(21,2,'Sala 1',100),(22,2,'Sala 2',100),(23,2,'Sala 3',100),(24,2,'Sala 4',100),(25,2,'Sala 5',100),(26,2,'Sala 6',100),(27,2,'Sala 7',100),(28,2,'Sala 8',100),(29,2,'Sala 9',100),(30,2,'Sala 10',100),(31,3,'Sala 1',100),(32,3,'Sala 2',100),(33,3,'Sala 3',100),(34,3,'Sala 4',100),(35,3,'Sala 5',100),(36,3,'Sala 6',100),(37,3,'Sala 7',100),(38,3,'Sala 8',100),(39,3,'Sala 9',100),(40,3,'Sala 10',100),(41,3,'Sala 11',100),(42,3,'Sala 12',100),(43,3,'Sala 13',100),(44,3,'Sala 14',100),(45,3,'Sala 15',100),(46,3,'Sala 16',100),(47,3,'Sala 17',100),(48,3,'Sala 18',100),(49,3,'Sala 19',100),(50,4,'Sala 1',100),(51,4,'Sala 2',100),(52,4,'Sala 3',100),(53,4,'Sala 4',100),(54,4,'Sala 5',100),(55,4,'Sala 6',100),(56,5,'Sala 1',100),(57,5,'Sala 2',100),(58,5,'Sala 3',100),(59,5,'Sala 4',100),(60,5,'Sala 5',100),(61,5,'Sala 6',100),(62,5,'Sala 7',100),(63,5,'Sala 8',100),(64,6,'Sala 1',100),(65,6,'Sala 2',100),(66,6,'Sala 3',100),(67,6,'Sala 4',100),(68,6,'Sala 5',100),(69,7,'Sala 1',100),(70,7,'Sala 2',100),(71,7,'Sala 3',100),(72,7,'Sala 4',100),(73,8,'Sala 1',100),(74,8,'Sala 2',100),(75,8,'Sala 3',100),(76,8,'Sala 4',100),(77,8,'Sala 5',100),(78,8,'Sala 6',100),(79,8,'Sala 7',100),(80,9,'Sala 1',100),(81,9,'Sala 2',100),(82,9,'Sala 3',100),(83,9,'Sala 4',100),(84,9,'Sala 5',100),(85,9,'Sala 6',100),(86,9,'Sala 7',100),(87,9,'Sala 8',100),(88,9,'Sala 9',100),(89,9,'Sala 10',100),(90,10,'Sala 1',100),(91,10,'Sala 2',100),(92,10,'Sala 3',100),(93,10,'Sala 4',100),(94,10,'Sala 5',100),(95,10,'Sala 6',100),(96,11,'Sala 1',100),(97,11,'Sala 2',100),(98,11,'Sala 3',100),(99,11,'Sala 4',100),(100,11,'Sala 5',100),(101,11,'Sala 6',100),(102,11,'Sala 7',100),(103,11,'Sala 8',100),(104,12,'Sala 1',100),(105,12,'Sala 2',100),(106,12,'Sala 3',100),(107,12,'Sala 4',100),(108,12,'Sala 5',100),(109,13,'Sala 1',100),(110,13,'Sala 2',100),(111,13,'Sala 3',100),(112,13,'Sala 4',100),(113,13,'Sala 5',100),(114,13,'Sala 6',100),(115,13,'Sala 7',100),(116,13,'Sala 8',100),(117,13,'Sala 9',100);
/*!40000 ALTER TABLE `sala` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuarios` (
  `idUsuario` int(11) NOT NULL AUTO_INCREMENT,
  `usuario` varchar(100) NOT NULL,
  `clave` varchar(100) NOT NULL,
  PRIMARY KEY (`idUsuario`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (1,'cines','9d519737ef33ee8e4e3a9788887c3aaa');
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'cines_db'
--

--
-- Dumping routines for database 'cines_db'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-11-09  2:34:17
