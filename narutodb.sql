-- phpMyAdmin SQL Dump
-- version 4.8.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 13, 2019 at 03:23 PM
-- Server version: 10.1.33-MariaDB
-- PHP Version: 7.2.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `narutodb`
--

DELIMITER $$
--
-- Procedures
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `checkId` (IN `id` INT)  NO SQL
select player.playerID from player where player.playerID = id$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getAllPlayers` ()  NO SQL
SELECT * from player$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `insertPlayer` (IN `id` INT, IN `posX` INT, IN `posY` INT, IN `isDead` BOOLEAN, IN `isClimbing` BOOLEAN, IN `canClimb` BOOLEAN, IN `canJump` BOOLEAN, IN `isJumping` BOOLEAN, IN `facingDirection` INT, IN `imageArrayIndex` INT)  NO SQL
insert into player values(id,posX,posY,isDead,isClimbing,canClimb,canJump,isJumping,facingDirection,imageArrayIndex)$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `selectPlayer` (IN `id` INT)  NO SQL
SELECT * from player where player.playerID = id$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `updatePlayer` (IN `id` INT, IN `posX` INT, IN `posY` INT, IN `isDead` BOOLEAN, IN `isClimbing` BOOLEAN, IN `canClimb` BOOLEAN, IN `canJump` BOOLEAN, IN `isJumping` BOOLEAN, IN `facingDirection` INT, IN `imageArrayIndex` INT)  NO SQL
update player set player.posX = posX, player.posY = posY, player.isDead = isDead, player.isClimbing = isClimbing, player.canClimb = canClimb, player.canJump = canJump, player.isJumping = isJumping, player.facingDirection = facingDirection, player.imageArrayIndex = imageArrayIndex where player.playerID = id$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `collision`
--

CREATE TABLE `collision` (
  `playerId` int(3) NOT NULL,
  `direction` varchar(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `direction`
--

CREATE TABLE `direction` (
  `direction` varchar(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `direction`
--

INSERT INTO `direction` (`direction`) VALUES
('DOWN'),
('LEFT'),
('NULL'),
('RIGHT'),
('UP');

-- --------------------------------------------------------

--
-- Table structure for table `gameturn`
--

CREATE TABLE `gameturn` (
  `id` int(11) NOT NULL,
  `playerId` int(11) NOT NULL,
  `score` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `player`
--

CREATE TABLE `player` (
  `playerID` int(3) NOT NULL,
  `posX` int(5) DEFAULT NULL,
  `posY` int(5) DEFAULT NULL,
  `isDead` tinyint(1) DEFAULT NULL,
  `isClimbing` tinyint(1) DEFAULT NULL,
  `canClimb` tinyint(1) DEFAULT NULL,
  `canJump` tinyint(1) DEFAULT NULL,
  `isJumping` tinyint(1) DEFAULT NULL,
  `facingDirection` int(2) DEFAULT NULL,
  `imageArrayIndex` int(3) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `collision`
--
ALTER TABLE `collision`
  ADD PRIMARY KEY (`playerId`,`direction`),
  ADD KEY `direction` (`direction`);

--
-- Indexes for table `direction`
--
ALTER TABLE `direction`
  ADD PRIMARY KEY (`direction`);

--
-- Indexes for table `gameturn`
--
ALTER TABLE `gameturn`
  ADD PRIMARY KEY (`id`),
  ADD KEY `playerId` (`playerId`);

--
-- Indexes for table `player`
--
ALTER TABLE `player`
  ADD PRIMARY KEY (`playerID`),
  ADD KEY `facingDirection` (`facingDirection`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
