-- phpMyAdmin SQL Dump
-- version 5.2.3
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Dec 24, 2025 at 10:12 PM
-- Server version: 8.0.30
-- PHP Version: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_manajemen-kas`
--
CREATE DATABASE IF NOT EXISTS `db_manajemen-kas` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `db_manajemen-kas`;

-- --------------------------------------------------------

--
-- Table structure for table `pemasukan`
--

CREATE TABLE IF NOT EXISTS `pemasukan` (
  `id` int NOT NULL AUTO_INCREMENT,
  `namaTransaksi` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  `tanggal` date NOT NULL,
  `nominalMasuk` int NOT NULL,
  `keterangan` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `pemasukan`
--

INSERT INTO `pemasukan` (`id`, `namaTransaksi`, `tanggal`, `nominalMasuk`, `keterangan`) VALUES
(8, 'Kas Rafi', '2025-12-01', 10000, 'Pembayaran Kas Bulan Desember'),
(9, 'Kas Robin', '2025-12-05', 10000, 'Kas Bulan Desember'),
(10, 'Kas Hamba Allah', '2025-12-06', 10000, 'Kas Bulan Desember'),
(11, 'Kas Rain', '2025-12-07', 10000, 'Kas Bulan Desember'),
(12, 'Kas Saiko', '2025-12-08', 10000, 'Kas Bulan Desember'),
(13, 'Kas Fahrezy', '2025-12-09', 10000, 'Kas Bulan Desember'),
(14, 'Kas Zidan', '2025-12-10', 10000, 'Kas Bulan Desember'),
(15, 'Kas Dika', '2025-12-11', 10000, 'Kas Bulan Desember'),
(16, 'Kas Catur', '2025-12-12', 10000, 'Kas Bulan Desember'),
(17, 'Kas Jenny', '2025-12-13', 10000, 'Kas Bulan Desember'),
(18, 'Kas Vepi', '2025-12-14', 10000, 'Kas Bulan Desember'),
(19, 'Sumbangan ', '2025-12-11', 30000, 'Sumbangan ');

-- --------------------------------------------------------

--
-- Table structure for table `pengeluaran`
--

CREATE TABLE IF NOT EXISTS `pengeluaran` (
  `id` int NOT NULL AUTO_INCREMENT,
  `namaTransaksi` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  `tanggal` date NOT NULL,
  `nominalKeluar` int NOT NULL,
  `keterangan` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `pengeluaran`
--

INSERT INTO `pengeluaran` (`id`, `namaTransaksi`, `tanggal`, `nominalKeluar`, `keterangan`) VALUES
(5, 'Kabel HDMI', '2025-12-05', 50000, 'Pembelian Kabel HDMI Kelas'),
(6, 'Beli Spidol', '2025-12-06', 8000, 'Pembelian Spidol Kelas di Frezy ATK'),
(7, 'Beli Sapu', '2025-12-08', 15000, 'Kebersihan Kelas'),
(9, 'Beli Remote TV', '2025-12-12', 6000, 'Pembelian Remote TV Kelas'),
(10, 'MIB Berbagi', '2025-12-14', 25000, 'Buah Tangan Teman Sakit');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  `password` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  `namaLengkap` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `jabatan` enum('Ketua Kelas','Bendahara') CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `username`, `password`, `namaLengkap`, `jabatan`) VALUES
(8, 'admin', 'admin123', 'Administrator', 'Ketua Kelas'),
(9, 'frezy', '123', 'Fahrezy K.', 'Ketua Kelas'),
(10, 'muqo', '123', 'Robin', 'Bendahara');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
