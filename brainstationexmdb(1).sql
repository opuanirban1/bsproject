-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 28, 2020 at 05:01 AM
-- Server version: 10.1.37-MariaDB
-- PHP Version: 7.3.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `brainstationexmdb`
--

-- --------------------------------------------------------

--
-- Table structure for table `bankaccount`
--

CREATE TABLE `bankaccount` (
  `id` int(11) NOT NULL,
  `accounttype` varchar(255) DEFAULT NULL,
  `accountname` varchar(255) DEFAULT NULL,
  `accountnumber` varchar(255) DEFAULT NULL,
  `currentbalance` double NOT NULL DEFAULT '0',
  `bankname` varchar(255) DEFAULT NULL,
  `bankbranch` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `bankaccount`
--

INSERT INTO `bankaccount` (`id`, `accounttype`, `accountname`, `accountnumber`, `currentbalance`, `bankname`, `bankbranch`) VALUES
(1, 'mainaccount', 'Main Office', '100', 879849, 'Main bank', 'Gulshan'),
(2, 'savings_current', 'emp 1', '101', 4050, 'babk 1', 'branch 1'),
(3, 'savings_current', 'emp 2', '102', 4550, 'bank 2', 'branch 2'),
(4, 'savings_current', 'emp 3', '103', 24900, 'bank 3', 'gulshan 3'),
(5, 'savings_current', 'emp 4', '104', 8100, 'b4', 'b4'),
(6, 'savings_current', 'emp 5', '105', 14850, 'b5', 'b5'),
(7, 'savings_current', 'emp 6', '106', 21600, 'b6', 'b6'),
(8, 'savings_current', 'emp7', '107', 28350, 'b7', 'asfasf'),
(9, 'savings_current', 'emp 8', '108', 28350, 'b8', 'ljljl'),
(10, 'savings_current', 'emp9', '109', 35100, 'adad', 'adsasd'),
(11, 'savings_current', 'emp10', '110', 35100, 'asdasd', 'asdasd');

-- --------------------------------------------------------

--
-- Table structure for table `employee`
--

CREATE TABLE `employee` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `mobileno` varchar(255) DEFAULT NULL,
  `bankaccount` varchar(255) DEFAULT NULL,
  `salary_id` int(11) DEFAULT '1',
  `status` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `employee`
--

INSERT INTO `employee` (`id`, `name`, `address`, `mobileno`, `bankaccount`, `salary_id`, `status`) VALUES
(9, 'emp 1', 'gopibag dhaka', '01817184370', '101', 1, 1),
(10, 'employee 2', 'address1', '01817184370', '102', 1, 1),
(11, 'emp 3', 'add 3', '01817184370', '103', 2, 1),
(12, 'emp4', 'weqew', '01817184370', '104', 2, 1),
(13, 'emp5', 'daD', '01817184370', '105', 3, 1),
(14, 'emp 6', 'sdadasd', '01817184370', '106', 4, 1),
(15, 'emp7', 'daD', '01817184370', '107', 5, 1),
(16, 'EMP8', 'ASDASD', '01817184370', '108', 5, 1),
(17, 'EMP9', 'fasfasf', '01817184370', '109', 6, 1),
(18, 'emp10', 'asdad', '01817184370', '110', 6, 1);

-- --------------------------------------------------------

--
-- Table structure for table `gradewisesalary`
--

CREATE TABLE `gradewisesalary` (
  `id` int(11) NOT NULL,
  `company_grade` varchar(255) DEFAULT NULL,
  `basic_salary` double DEFAULT NULL,
  `house_rent` double DEFAULT NULL,
  `medical_allowence` double NOT NULL,
  `total_salary` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `gradewisesalary`
--

INSERT INTO `gradewisesalary` (`id`, `company_grade`, `basic_salary`, `house_rent`, `medical_allowence`, `total_salary`) VALUES
(1, 'grade_one', 1000, 200, 150, 1350),
(2, 'grade_two', 6000, 1200, 900, 8100),
(3, 'grade_three', 11000, 2200, 1650, 14850),
(4, 'grade_four', 16000, 3200, 2400, 21600),
(5, 'grade_five', 21000, 4200, 3150, 28350),
(6, 'grade_six', 26000, 5200, 3900, 35100);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `bankaccount`
--
ALTER TABLE `bankaccount`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `gradewisesalary`
--
ALTER TABLE `gradewisesalary`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `bankaccount`
--
ALTER TABLE `bankaccount`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `employee`
--
ALTER TABLE `employee`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `gradewisesalary`
--
ALTER TABLE `gradewisesalary`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
