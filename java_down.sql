-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 27, 2020 at 08:36 PM
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
-- Database: `java_down`
--

-- --------------------------------------------------------

--
-- Table structure for table `tbl_ceq_sent_sms`
--

CREATE TABLE `tbl_ceq_sent_sms` (
  `id` bigint(20) NOT NULL,
  `datetime` datetime DEFAULT NULL,
  `destaddr` varchar(255) DEFAULT NULL,
  `flag` varchar(255) DEFAULT NULL,
  `msg` varchar(255) DEFAULT NULL,
  `num_of_try` int(11) NOT NULL,
  `orgaddr` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `updatedate` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_ceq_sent_sms`
--

INSERT INTO `tbl_ceq_sent_sms` (`id`, `datetime`, `destaddr`, `flag`, `msg`, `num_of_try`, `orgaddr`, `status`, `updatedate`) VALUES
(1, '2020-07-28 00:22:10', '8801819098905', 'OUT', 'Call the users of these numbers to complete re-reg & win 5GB FREE: 01817184370 ', 0, '1600', 'WAIT', '2020-07-28 00:22:10'),
(2, '2020-07-28 00:22:29', '8801817184370', 'OUT', 'No referral number found', 0, '1600', 'WAIT', '2020-07-28 00:22:29'),
(3, '2020-07-28 00:28:32', '8801817184370', 'OUT', 'No referral number found.', 0, '1600', 'WAIT', '2020-07-28 00:28:32');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_employee`
--

CREATE TABLE `tbl_employee` (
  `id` bigint(20) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_employees`
--

CREATE TABLE `tbl_employees` (
  `id` int(11) NOT NULL,
  `first_name` varchar(250) NOT NULL,
  `last_name` varchar(250) NOT NULL,
  `email` varchar(250) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_employees`
--

INSERT INTO `tbl_employees` (`id`, `first_name`, `last_name`, `email`) VALUES
(2, 'John', 'Doe', 'xyz@email.com'),
(3, 'opu', 'anirban', 'opu.anirban@gmail.com'),
(4, 'opu', 'dipu', 'nai');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_master_req`
--

CREATE TABLE `tbl_master_req` (
  `id` bigint(20) NOT NULL,
  `insert_time` datetime DEFAULT NULL,
  `msisdn` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `ussd_code` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_master_req`
--

INSERT INTO `tbl_master_req` (`id`, `insert_time`, `msisdn`, `status`, `ussd_code`) VALUES
(1, '2020-07-28 00:21:18', '8801819098905', 'WAIT3', '1600');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_ref_cam_master`
--

CREATE TABLE `tbl_ref_cam_master` (
  `id` bigint(20) NOT NULL,
  `reg_num` varchar(255) DEFAULT NULL,
  `un_reg_num` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_ref_cam_master`
--

INSERT INTO `tbl_ref_cam_master` (`id`, `reg_num`, `un_reg_num`) VALUES
(1, '01819098905', '01817184370');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tbl_ceq_sent_sms`
--
ALTER TABLE `tbl_ceq_sent_sms`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbl_employee`
--
ALTER TABLE `tbl_employee`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbl_employees`
--
ALTER TABLE `tbl_employees`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbl_master_req`
--
ALTER TABLE `tbl_master_req`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbl_ref_cam_master`
--
ALTER TABLE `tbl_ref_cam_master`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tbl_ceq_sent_sms`
--
ALTER TABLE `tbl_ceq_sent_sms`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `tbl_employee`
--
ALTER TABLE `tbl_employee`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `tbl_employees`
--
ALTER TABLE `tbl_employees`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `tbl_master_req`
--
ALTER TABLE `tbl_master_req`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `tbl_ref_cam_master`
--
ALTER TABLE `tbl_ref_cam_master`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
