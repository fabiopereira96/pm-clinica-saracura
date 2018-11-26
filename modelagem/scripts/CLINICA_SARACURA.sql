-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Nov 27, 2018 at 12:01 AM
-- Server version: 10.1.35-MariaDB
-- PHP Version: 7.2.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `CLINICA_SARACURA`
--

-- --------------------------------------------------------

--
-- Table structure for table `AgendaMedica`
--

CREATE TABLE `AgendaMedica` (
  `diaAgendamento` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `idPaciente` int(11) NOT NULL,
  `idMedico` int(11) NOT NULL,
  `crm` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `AgendaMedica`
--

INSERT INTO `AgendaMedica` (`diaAgendamento`, `idPaciente`, `idMedico`, `crm`) VALUES
('2018-11-26 01:17:40', 8600, 123456, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `Convenio`
--

CREATE TABLE `Convenio` (
  `idConvenio` int(11) NOT NULL,
  `nome` varchar(45) DEFAULT NULL,
  `porcentagemCorbetura` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `Convenio`
--

INSERT INTO `Convenio` (`idConvenio`, `nome`, `porcentagemCorbetura`) VALUES
(21, 'Unimed', NULL),
(22, 'Ipsemg', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `DiaAtendimento`
--

CREATE TABLE `DiaAtendimento` (
  `idDia` int(1) NOT NULL,
  `nomeDia` varchar(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `DiaAtendimento`
--

INSERT INTO `DiaAtendimento` (`idDia`, `nomeDia`) VALUES
(1, 'Domingo'),
(2, 'Segunda-feira'),
(3, 'Terça-feira');

-- --------------------------------------------------------

--
-- Table structure for table `Especialidade`
--

CREATE TABLE `Especialidade` (
  `idEspecialidade` int(11) NOT NULL,
  `nome` varchar(45) DEFAULT NULL,
  `preco` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `Especialidade`
--

INSERT INTO `Especialidade` (`idEspecialidade`, `nome`, `preco`) VALUES
(1, 'Pediatria', NULL),
(2, 'Dermatologia', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `Medico`
--

CREATE TABLE `Medico` (
  `crm` int(11) NOT NULL,
  `nome` varchar(45) DEFAULT NULL,
  `idEspecialidade` int(11) DEFAULT NULL,
  `horarioAtendimento` varchar(5) DEFAULT NULL,
  `intervaloAtendimento` varchar(5) DEFAULT NULL,
  `celular` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `Medico`
--

INSERT INTO `Medico` (`crm`, `nome`, `idEspecialidade`, `horarioAtendimento`, `intervaloAtendimento`, `celular`, `email`) VALUES
(123321, 'Dra. Felicia', 2, '08:00', '00:30', '93931317', '123321@teste.com'),
(123456, 'Dr. Felisbino', 1, '10:00', '00:15', '73377337', '123456@teste.com');

-- --------------------------------------------------------

--
-- Table structure for table `Medico_DiaAtendimento`
--

CREATE TABLE `Medico_DiaAtendimento` (
  `Medico_crm` int(11) NOT NULL,
  `diaAtendimento_idDia` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `Medico_DiaAtendimento`
--

INSERT INTO `Medico_DiaAtendimento` (`Medico_crm`, `diaAtendimento_idDia`) VALUES
(123456, 1),
(123456, 2),
(123456, 3);

-- --------------------------------------------------------

--
-- Table structure for table `Paciente`
--

CREATE TABLE `Paciente` (
  `idPaciente` int(11) NOT NULL,
  `nome` varchar(45) DEFAULT NULL,
  `dataNascimento` date DEFAULT NULL,
  `idConvenio` int(11) DEFAULT NULL,
  `sexo` char(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `Paciente`
--

INSERT INTO `Paciente` (`idPaciente`, `nome`, `dataNascimento`, `idConvenio`, `sexo`) VALUES
(8600, 'Joao Carlos', '2015-11-23', 21, 'M'),
(8601, 'Maria Carla', '2015-11-23', 22, 'F');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `AgendaMedica`
--
ALTER TABLE `AgendaMedica`
  ADD PRIMARY KEY (`idMedico`,`idPaciente`,`diaAgendamento`),
  ADD KEY `fk_AgendaMedica_Paciente` (`idPaciente`);

--
-- Indexes for table `Convenio`
--
ALTER TABLE `Convenio`
  ADD PRIMARY KEY (`idConvenio`);

--
-- Indexes for table `DiaAtendimento`
--
ALTER TABLE `DiaAtendimento`
  ADD PRIMARY KEY (`idDia`);

--
-- Indexes for table `Especialidade`
--
ALTER TABLE `Especialidade`
  ADD PRIMARY KEY (`idEspecialidade`);

--
-- Indexes for table `Medico`
--
ALTER TABLE `Medico`
  ADD PRIMARY KEY (`crm`),
  ADD KEY `fk_Medico_1` (`idEspecialidade`);

--
-- Indexes for table `Medico_DiaAtendimento`
--
ALTER TABLE `Medico_DiaAtendimento`
  ADD UNIQUE KEY `UK_qu11lsml9lhvlgrw1f20vpwfi` (`diaAtendimento_idDia`),
  ADD KEY `FK_qu11lsml9lhvlgrw1f20vpwfi` (`diaAtendimento_idDia`),
  ADD KEY `FK_hdqwogvnl7lmmfig966a7ptar` (`Medico_crm`);

--
-- Indexes for table `Paciente`
--
ALTER TABLE `Paciente`
  ADD PRIMARY KEY (`idPaciente`),
  ADD KEY `fk_convenio` (`idConvenio`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `Convenio`
--
ALTER TABLE `Convenio`
  MODIFY `idConvenio` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT for table `Especialidade`
--
ALTER TABLE `Especialidade`
  MODIFY `idEspecialidade` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `Paciente`
--
ALTER TABLE `Paciente`
  MODIFY `idPaciente` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8602;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `AgendaMedica`
--
ALTER TABLE `AgendaMedica`
  ADD CONSTRAINT `FK_9y7e5thjntu94jjs6vpycl135` FOREIGN KEY (`crm`) REFERENCES `Medico` (`crm`),
  ADD CONSTRAINT `fk_AgendaMedica_Medico` FOREIGN KEY (`idMedico`) REFERENCES `Medico` (`crm`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_AgendaMedica_Paciente` FOREIGN KEY (`idPaciente`) REFERENCES `Paciente` (`idPaciente`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `Medico`
--
ALTER TABLE `Medico`
  ADD CONSTRAINT `fk_Medico_1` FOREIGN KEY (`idEspecialidade`) REFERENCES `Especialidade` (`idEspecialidade`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `Medico_DiaAtendimento`
--
ALTER TABLE `Medico_DiaAtendimento`
  ADD CONSTRAINT `FK_hdqwogvnl7lmmfig966a7ptar` FOREIGN KEY (`Medico_crm`) REFERENCES `Medico` (`crm`),
  ADD CONSTRAINT `FK_qu11lsml9lhvlgrw1f20vpwfi` FOREIGN KEY (`diaAtendimento_idDia`) REFERENCES `DiaAtendimento` (`idDia`);

--
-- Constraints for table `Paciente`
--
ALTER TABLE `Paciente`
  ADD CONSTRAINT `fk_convenio` FOREIGN KEY (`idConvenio`) REFERENCES `Convenio` (`idConvenio`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
