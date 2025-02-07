drop database if exists mediosdepago_tests;
CREATE DATABASE  IF NOT EXISTS `mediosdepago_tests` /*!40100 DEFAULT CHARACTER SET latin1 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `mediosdepago_tests`;
-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: localhost    Database: mediosdepago_tests
-- ------------------------------------------------------
-- Server version	8.0.27

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `clientes`
--

DROP TABLE IF EXISTS `clientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clientes` (
  `idpersonas` int NOT NULL,
  `nrocliente` int DEFAULT '0',
  PRIMARY KEY (`idpersonas`),
  CONSTRAINT `clientes_personas` FOREIGN KEY (`idpersonas`) REFERENCES `personas` (`idpersonas`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `cuentas`
--

DROP TABLE IF EXISTS `cuentas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cuentas` (
  `idcuentas` int NOT NULL AUTO_INCREMENT,
  `numero` varchar(45) NOT NULL,
  `idclientes` int NOT NULL,
  PRIMARY KEY (`idcuentas`),
  KEY `cuenta_cliente_idx` (`idclientes`),
  CONSTRAINT `cuentas_clientes` FOREIGN KEY (`idclientes`) REFERENCES `clientes` (`idpersonas`)
) ENGINE=InnoDB AUTO_INCREMENT=199 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `extractos`
--

DROP TABLE IF EXISTS `extractos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `extractos` (
  `idextractos` int NOT NULL AUTO_INCREMENT,
  `idcuenta` int NOT NULL,
  `anyo` int NOT NULL,
  `mes` int NOT NULL,
  PRIMARY KEY (`idextractos`),
  KEY `extractos_cuentas_idx` (`idcuenta`),
  CONSTRAINT `extractos_cuentas` FOREIGN KEY (`idcuenta`) REFERENCES `cuentas` (`idcuentas`)
) ENGINE=InnoDB AUTO_INCREMENT=1789 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `movimientos`
--

DROP TABLE IF EXISTS `movimientos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `movimientos` (
  `idmovimientos` int NOT NULL AUTO_INCREMENT,
  `idextracto` int NOT NULL,
  `idtipo` int NOT NULL,
  `idtarjeta` int NOT NULL,
  `fecha` date NOT NULL,
  `importe` decimal(10,2) NOT NULL,
  `proveedor` varchar(45) NOT NULL,
  PRIMARY KEY (`idmovimientos`),
  KEY `movimientos_extractos_idx` (`idextracto`),
  KEY `movimientos_tipos_idx` (`idtipo`),
  KEY `movimientos_tarjetas_idx` (`idtarjeta`),
  CONSTRAINT `movimientos_extractos` FOREIGN KEY (`idextracto`) REFERENCES `extractos` (`idextractos`),
  CONSTRAINT `movimientos_tarjetas` FOREIGN KEY (`idtarjeta`) REFERENCES `tarjetas` (`idtarjetas`),
  CONSTRAINT `movimientos_tipos` FOREIGN KEY (`idtipo`) REFERENCES `tipos_movimientos` (`idtipos_movimientos`)
) ENGINE=InnoDB AUTO_INCREMENT=20012 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `personas`
--

DROP TABLE IF EXISTS `personas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `personas` (
  `idpersonas` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `apellido1` varchar(45) NOT NULL,
  `apellido2` varchar(45) DEFAULT NULL,
  `nif` varchar(45) NOT NULL,
  `sexo` varchar(1) NOT NULL,
  `municipio` varchar(45) NOT NULL,
  `provincia` varchar(45) NOT NULL,
  PRIMARY KEY (`idpersonas`)
) ENGINE=InnoDB AUTO_INCREMENT=1002 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tarjetas`
--

DROP TABLE IF EXISTS `tarjetas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tarjetas` (
  `idtarjetas` int NOT NULL AUTO_INCREMENT,
  `pan` varchar(16) NOT NULL,
  `marca` varchar(45) NOT NULL,
  `tipo` varchar(45) NOT NULL,
  `idcuentas` int NOT NULL,
  `anyo_vencimiento` int NOT NULL,
  `mes_vencimiento` int NOT NULL,
  PRIMARY KEY (`idtarjetas`),
  KEY `tarjetas_cuentas_idx` (`idcuentas`),
  CONSTRAINT `tarjetas_cuentas` FOREIGN KEY (`idcuentas`) REFERENCES `cuentas` (`idcuentas`)
) ENGINE=InnoDB AUTO_INCREMENT=326 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tipos_movimientos`
--

DROP TABLE IF EXISTS `tipos_movimientos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tipos_movimientos` (
  `idtipos_movimientos` int NOT NULL AUTO_INCREMENT,
  `tipo_movimiento` varchar(45) NOT NULL,
  PRIMARY KEY (`idtipos_movimientos`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-02-01 15:52:01
