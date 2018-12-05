-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Dec 05, 2018 at 01:24 AM
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
-- Database: `pm-clinica-saracura`
--

-- --------------------------------------------------------

--
-- Table structure for table `AgendaEquipamento`
--

CREATE TABLE `AgendaEquipamento` (
  `dataAgendamento` datetime NOT NULL,
  `descricao` varchar(255) DEFAULT NULL,
  `idEquipamento` int(11) DEFAULT NULL,
  `idMedico` int(11) DEFAULT NULL,
  `idPaciente` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
(27, 'Unimed', NULL),
(28, 'Ipsemg', NULL);

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
(3, 'Terça-feira'),
(4, 'Quarta-feira'),
(5, 'Quinta-feira'),
(6, 'Sexta-feira'),
(7, 'Sabado');

-- --------------------------------------------------------

--
-- Table structure for table `Equipamento`
--

CREATE TABLE `Equipamento` (
  `idEquipamento` int(11) NOT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `statusFuncionamento` bit(1) DEFAULT NULL,
  `idTipoExame` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
(7, 'Pediatria', NULL),
(8, 'Dermatologia', NULL),
(9, 'Pediatria', NULL),
(10, 'Dermatologia', NULL);

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
(123321, 'Dra. Felicia', 8, '08:00', '00:30', '93931317', '123321@teste.com'),
(123456, 'Dr. Felisbino', 7, '10:00', '00:15', '73377337', '123456@teste.com');

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
(8606, 'Joao Carlos', '2015-11-23', 27, 'M'),
(8607, 'Maria Carla', '2015-11-23', 28, 'F');

-- --------------------------------------------------------

--
-- Table structure for table `TipoExame`
--

CREATE TABLE `TipoExame` (
  `idTipoExame` int(11) NOT NULL,
  `descricao` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `AgendaEquipamento`
--
ALTER TABLE `AgendaEquipamento`
  ADD PRIMARY KEY (`dataAgendamento`),
  ADD KEY `FKat4s2xxfwwdouirrhrdq9gqf5` (`idEquipamento`),
  ADD KEY `FKimorx7gnakdvhm283w6vco25l` (`idMedico`),
  ADD KEY `FKe675x304fb6nsikfh9srvfs5k` (`idPaciente`);

--
-- Indexes for table `AgendaMedica`
--
ALTER TABLE `AgendaMedica`
  ADD PRIMARY KEY (`idMedico`,`idPaciente`,`diaAgendamento`),
  ADD KEY `fk_AgendaMedica_Paciente` (`idPaciente`),
  ADD KEY `FK_9y7e5thjntu94jjs6vpycl135` (`crm`);

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
-- Indexes for table `Equipamento`
--
ALTER TABLE `Equipamento`
  ADD PRIMARY KEY (`idEquipamento`),
  ADD KEY `FKj1dwpvtl44mhpawsvnrrgwnaw` (`idTipoExame`);

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
-- Indexes for table `TipoExame`
--
ALTER TABLE `TipoExame`
  ADD PRIMARY KEY (`idTipoExame`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `Convenio`
--
ALTER TABLE `Convenio`
  MODIFY `idConvenio` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT for table `Equipamento`
--
ALTER TABLE `Equipamento`
  MODIFY `idEquipamento` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `Especialidade`
--
ALTER TABLE `Especialidade`
  MODIFY `idEspecialidade` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `Paciente`
--
ALTER TABLE `Paciente`
  MODIFY `idPaciente` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8610;

--
-- AUTO_INCREMENT for table `TipoExame`
--
ALTER TABLE `TipoExame`
  MODIFY `idTipoExame` int(11) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `AgendaEquipamento`
--
ALTER TABLE `AgendaEquipamento`
  ADD CONSTRAINT `FKat4s2xxfwwdouirrhrdq9gqf5` FOREIGN KEY (`idEquipamento`) REFERENCES `Equipamento` (`idEquipamento`),
  ADD CONSTRAINT `FKe675x304fb6nsikfh9srvfs5k` FOREIGN KEY (`idPaciente`) REFERENCES `Paciente` (`idPaciente`),
  ADD CONSTRAINT `FKimorx7gnakdvhm283w6vco25l` FOREIGN KEY (`idMedico`) REFERENCES `Medico` (`crm`);

--
-- Constraints for table `AgendaMedica`
--
ALTER TABLE `AgendaMedica`
  ADD CONSTRAINT `FK6xmjl9d2aifsjl2xjhj6xf4p` FOREIGN KEY (`idPaciente`) REFERENCES `Paciente` (`idPaciente`),
  ADD CONSTRAINT `FK_9y7e5thjntu94jjs6vpycl135` FOREIGN KEY (`crm`) REFERENCES `Medico` (`crm`),
  ADD CONSTRAINT `FKq9rasbd4afh3y72yy9nbhl97u` FOREIGN KEY (`idMedico`) REFERENCES `Medico` (`crm`),
  ADD CONSTRAINT `fk_AgendaMedica_Medico` FOREIGN KEY (`idMedico`) REFERENCES `Medico` (`crm`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_AgendaMedica_Paciente` FOREIGN KEY (`idPaciente`) REFERENCES `Paciente` (`idPaciente`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `Equipamento`
--
ALTER TABLE `Equipamento`
  ADD CONSTRAINT `FKj1dwpvtl44mhpawsvnrrgwnaw` FOREIGN KEY (`idTipoExame`) REFERENCES `TipoExame` (`idTipoExame`);

--
-- Constraints for table `Medico`
--
ALTER TABLE `Medico`
  ADD CONSTRAINT `FKdmclvr2kjnm3jmdlidvshffwt` FOREIGN KEY (`idEspecialidade`) REFERENCES `Especialidade` (`idEspecialidade`),
  ADD CONSTRAINT `fk_Medico_1` FOREIGN KEY (`idEspecialidade`) REFERENCES `Especialidade` (`idEspecialidade`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `Medico_DiaAtendimento`
--
ALTER TABLE `Medico_DiaAtendimento`
  ADD CONSTRAINT `FK2lj37oje00so0ng85tipoxtd3` FOREIGN KEY (`diaAtendimento_idDia`) REFERENCES `DiaAtendimento` (`idDia`),
  ADD CONSTRAINT `FK_hdqwogvnl7lmmfig966a7ptar` FOREIGN KEY (`Medico_crm`) REFERENCES `Medico` (`crm`),
  ADD CONSTRAINT `FK_qu11lsml9lhvlgrw1f20vpwfi` FOREIGN KEY (`diaAtendimento_idDia`) REFERENCES `DiaAtendimento` (`idDia`),
  ADD CONSTRAINT `FKkevn72ws8vstfls0jhv50wiej` FOREIGN KEY (`Medico_crm`) REFERENCES `Medico` (`crm`);

--
-- Constraints for table `Paciente`
--
ALTER TABLE `Paciente`
  ADD CONSTRAINT `FK74s4f2eonv2jf2pksp0ujldhk` FOREIGN KEY (`idConvenio`) REFERENCES `Convenio` (`idConvenio`),
  ADD CONSTRAINT `fk_convenio` FOREIGN KEY (`idConvenio`) REFERENCES `Convenio` (`idConvenio`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
