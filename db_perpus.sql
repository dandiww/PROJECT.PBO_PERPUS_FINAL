-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 03, 2025 at 01:07 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_perpus`
--

-- --------------------------------------------------------

--
-- Table structure for table `buku`
--

CREATE TABLE `buku` (
  `id_buku` varchar(10) NOT NULL,
  `judul` varchar(200) DEFAULT NULL,
  `pengarang` varchar(100) DEFAULT NULL,
  `tahun_terbit` year(4) DEFAULT NULL,
  `id_penerbit` varchar(10) DEFAULT NULL,
  `stok` int(11) DEFAULT NULL,
  `sinopsis` text DEFAULT NULL,
  `foto_path` varchar(255) DEFAULT NULL,
  `file_path` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `buku`
--

INSERT INTO `buku` (`id_buku`, `judul`, `pengarang`, `tahun_terbit`, `id_penerbit`, `stok`, `sinopsis`, `foto_path`, `file_path`) VALUES
('BK001', 'Laskar Pelangi', 'Andrea Hirata', '2005', 'PEN001', 5, 'Kisah perjuangan anak-anak di Belitung yang penuh semangat dan mimpi.', 'images/Laskar_pelangi.jpg', 'https://online.fliphtml5.com/imzvb/nbmi/#p=3'),
('BK002', 'Perahu Kertas', 'Dee Lestari', '2009', 'PEN002', 8, 'Kisah cinta dan pencarian jati diri dua anak muda, Kugy dan Keenan, yang terpisah oleh mimpi dan waktu.', 'images/perahu_kertas.jpg', 'https://fliphtml5.com/dlbeq/evpi/Perahu_Kertas/'),
('BK003', 'Supernova', 'Dee Lestari', '2001', 'PEN003', 4, 'Eksplorasi filsafat, sains, dan cinta dalam kisah dua penulis yang menciptakan tokoh fiksi yang justru menjadi nyata.', 'images/supernova.jpg', 'https://us.macmillan.com/books/9781250220165/supernova/'),
('BK004', 'Bumi', 'Tere Liye', '2014', 'PEN002', 6, 'Petualangan Raib dan teman-temannya menjelajah dunia paralel untuk menyelamatkan bumi dari ancaman besar.', 'images/bumi.jpg', 'https://www.bukabuku.com/browses/product/9786239726263/bumi.html'),
('BK005', 'Rindu', 'Tere Liye', '2015', 'PEN002', 3, 'Kisah perjalanan spiritual di kapal haji menuju Mekah, penuh konflik batin dan penemuan makna sejati dari rindu.', 'resources/images/rindu.jpg', 'https://www.bukabuku.com/browses/product/9786239726263/bumi.html'),
('BK006', 'Ayat-Ayat Cinta', 'Habiburrahman El Shirazy', '2004', 'PEN002', 7, 'Kisah cinta segitiga yang diwarnai nilai-nilai Islam, budaya, dan keteguhan iman dalam menghadapi cobaan hidup.', 'images/ayat_ayat_cinta.jpg', 'https://fliphtml5.com/btfii/ojrf/Ayat_Ayat_Cinta/'),
('BK007', 'Negeri 5 Menara', 'Ahmad Fuadi', '2006', 'PEN004', 9, 'Perjuangan enam santri dari berbagai daerah di pesantren yang memegang teguh moto \"man jadda wajada\".', 'images/negri_5_menara.jpg', 'https://fliphtml5.com/nfiwww/fhjt/Negeri_5_Menara/'),
('BK008', 'Pulang', 'Leila S. Chudori', '2012', 'PEN005', 2, 'Perjalanan pulang seorang eksil politik Indonesia dari Paris ke tanah air yang telah banyak berubah.', 'images/pulang.jpg', 'https://fliphtml5.com/izjcm/trpq/Pulang_karya_Tere_Liye/'),
('BK009', 'Dilan 1990', 'Pidi Baiq', '2014', 'PEN004', 9, 'Cerita cinta remaja Bandung era 90-an antara Dilan yang unik dan Milea yang anggun.', 'images/dilan.jpg', 'https://fliphtml5.com/wixkq/wgew/Dilan_1/'),
('BK010', 'Surat Kecil untuk Tuhan', 'Agnes Davonar', '2008', 'PEN001', 4, 'Kisah haru seorang gadis kecil pengidap kanker yang menulis surat kepada Tuhan sebelum ajal menjemputnya.', 'images/suratuntuktuhan.jpg', 'https://books.google.co.id/books?id=Qi5wDwAAQBAJ&printsec=copyright&redir_esc=y#v=onepage&q&f=false'),
('BK011', 'Hujan', 'Tere Liye', '2016', 'PEN003', 8, 'Kisah masa depan pasca bencana, antara cinta, kehilangan, dan teknologi yang menghapus kenangan.', 'images/hujan.jpg', 'https://fliphtml5.com/vlzyo/vxiz/Tere_Liye_-'),
('BK012', 'Rectoverso', 'Dee Lestari', '2008', 'PEN001', 6, 'Gabungan cerpen dan lagu yang menceritakan cinta yang dalam, namun tak terucap.', 'images/rectoverso.jpg', 'https://fliphtml5.com/evfxp/rggi/Rectoverso/94/'),
('BK013', 'Sepotong Hati yang Baru', 'Tere Liye', '2017', 'PEN005', 3, 'Kumpulan cerita yang menggambarkan patah hati, kehilangan, dan semangat untuk bangkit kembali.', 'images/sepotonghatiyangbaru.jpg', 'https://fliphtml5.com/xjshq/srag/Tere_Liye_-_Sepotong_Hati_Yang_Baru/'),
('BK014', '9 Summers 10 Autumns', 'Iwan Setyawan', '2011', 'PEN001', 2, 'Perjalanan hidup dari keluarga sopir angkot di Batu hingga menjadi eksekutif di New York.', 'images/9summers10autums.jpg', 'https://books.google.co.id/books/about/9_Summers_10_Autumns.html?id=vtNCDwAAQBAJ&redir_esc=y'),
('BK015', 'Ketika Cinta Bertasbih', 'Habiburrahman El Shirazy', '2007', 'PEN001', 5, 'Kisah mahasiswa Indonesia di Mesir yang berjuang meraih cinta dan keberkahan lewat kesabaran dan takwa.', 'images/ketika_cinta_bertasbih.jpg', 'https://fliphtml5.com/vtrui/lslz/Ketika_Cinta_Bertasbih_1/'),
('BK016', 'Garis Waktu', 'Fiersa Besari', '2016', 'PEN004', 7, 'Rangkaian puisi dan prosa tentang patah hati, kenangan, dan perjalanan cinta yang tak selalu mulus.', 'images/garis_waktu.jpg', 'https://bukukita.com/Buku-Novel/Romance/145634-Garis-Waktu-:-Sebuah-Perjalanan-Menghapus-Luka.html'),
('BK017', 'Langit Merah di Jembatan Ampera', 'Remy Sylado', '2002', 'PEN003', 2, 'Kisah perjuangan hidup seorang pemuda Palembang yang mencari harapan di tengah konflik sosial dan ekonomi.', 'images/default.jpg', 'https://www.scribd.com/document/840348187/Jembatan-Ampera-dan-Sejarahnya-Mona-Hara-Princes-Rimi-SMPN-16-Plg'),
('BK018', 'Amba', 'Laksmi Pamuntjak', '2012', 'PEN002', 6, 'Interpretasi modern dari kisah Mahabharata, berlatar tragedi politik 1965 di Indonesia.', 'images/amba.jpg', 'https://fliphtml5.com/qiwwt/hmov/Amba_by_Laksmi_Pamuntjak_/'),
('BK019', 'Kukila', 'M. Aan Mansyur', '2015', 'PEN004', 4, 'Kumpulan cerpen dengan gaya minimalis yang menyentuh sisi-sisi sunyi dalam kehidupan sehari-hari.', 'images/kukila.jpg', 'https://fliphtml5.com/qiwwt/hmov/Amba_by_Laksmi_Pamuntjak_/'),
('BK020', 'Hana', 'Ayu', '2003', 'PEN001', 12, 'Huha huha', 'images/11.jpg', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `peminjaman`
--

CREATE TABLE `peminjaman` (
  `id_peminjaman` int(11) NOT NULL,
  `id_user` varchar(10) DEFAULT NULL,
  `id_buku` varchar(10) DEFAULT NULL,
  `tanggal_pinjam` date DEFAULT NULL,
  `tanggal_jatuh_tempo` date DEFAULT NULL,
  `jumlah` int(11) DEFAULT 1,
  `status` enum('Dipinjam','Dikembalikan') DEFAULT 'Dipinjam',
  `akses_habis` tinyint(1) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `peminjaman`
--

INSERT INTO `peminjaman` (`id_peminjaman`, `id_user`, `id_buku`, `tanggal_pinjam`, `tanggal_jatuh_tempo`, `jumlah`, `status`, `akses_habis`) VALUES
(1, 'USR003', 'BK007', '2025-04-10', '2025-05-10', 1, 'Dipinjam', 0),
(2, 'USR008', 'BK002', '2025-04-15', '2025-05-15', 1, 'Dikembalikan', 0),
(3, 'USR012', 'BK015', '2025-04-12', '2025-05-12', 1, 'Dipinjam', 0),
(4, 'USR006', 'BK001', '2025-04-08', '2025-05-08', 1, 'Dipinjam', 0),
(5, 'USR010', 'BK004', '2025-04-25', '2025-05-25', 1, 'Dikembalikan', 0),
(7, 'USR002', 'BK008', '2025-04-30', '2025-05-30', 1, 'Dikembalikan', 0),
(8, 'USR005', 'BK011', '2025-04-18', '2025-05-18', 1, 'Dipinjam', 0),
(9, 'USR014', 'BK003', '2025-04-22', '2025-05-22', 1, 'Dipinjam', 0),
(10, 'USR018', 'BK006', '2025-04-13', '2025-05-13', 1, 'Dikembalikan', 0),
(11, 'USR016', 'BK009', '2025-04-19', '2025-05-19', 1, 'Dipinjam', 0),
(12, 'USR019', 'BK017', '2025-04-14', '2025-05-14', 1, 'Dipinjam', 0),
(13, 'USR007', 'BK010', '2025-04-21', '2025-05-21', 1, 'Dikembalikan', 0),
(14, 'USR011', 'BK012', '2025-04-17', '2025-05-17', 1, 'Dipinjam', 0),
(15, 'USR018', 'BK016', '2025-04-20', '2025-05-20', 1, 'Dipinjam', 0),
(16, 'USR013', 'BK018', '2025-04-09', '2025-05-09', 1, 'Dipinjam', 0),
(17, 'USR004', 'BK013', '2025-04-11', '2025-05-11', 1, 'Dipinjam', 0),
(18, 'USR015', 'BK014', '2025-04-23', '2025-05-23', 1, 'Dikembalikan', 0),
(19, 'USR020', 'BK019', '2025-04-16', '2025-05-16', 1, 'Dipinjam', 0),
(20, 'USR003', 'BK005', '2025-05-26', '2025-06-02', 1, 'Dipinjam', 0),
(21, 'USR001', 'BK007', '2025-05-26', '2025-06-02', 1, 'Dipinjam', 0),
(22, 'USR001', 'BK003', '2025-06-02', '2025-06-09', 1, 'Dipinjam', 0),
(23, 'USR021', 'BK015', '2025-06-02', '2025-06-09', 1, 'Dipinjam', 0),
(24, 'USR001', 'BK015', '2025-06-02', '2025-06-09', 1, 'Dipinjam', 0),
(25, 'USR022', 'BK011', '2025-06-02', '2025-06-09', 1, 'Dipinjam', 0),
(26, 'USR001', 'BK001', '2025-06-03', '2025-06-10', 1, 'Dipinjam', 0),
(27, 'USR001', 'BK011', '2025-06-03', '2025-06-10', 1, 'Dipinjam', 0),
(28, 'USR003', 'BK015', '2025-06-03', '2025-06-10', 1, 'Dipinjam', 0),
(29, 'USR003', 'BK015', '2025-06-03', '2025-06-10', 1, 'Dipinjam', 0),
(30, 'USR023', 'BK001', '2025-06-03', '2025-06-10', 1, 'Dipinjam', 0);

-- --------------------------------------------------------

--
-- Table structure for table `penerbit`
--

CREATE TABLE `penerbit` (
  `id_penerbit` varchar(10) NOT NULL,
  `nama_penerbit` varchar(100) DEFAULT NULL,
  `alamat` text DEFAULT NULL,
  `telp` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `penerbit`
--

INSERT INTO `penerbit` (`id_penerbit`, `nama_penerbit`, `alamat`, `telp`) VALUES
('PEN001', 'Gramedia', 'Jl. Braga No. 10, Bandung', '0218736542'),
('PEN002', 'Erlangga', 'Jl. Dago No. 21, Bandung', '0219852736'),
('PEN003', 'Tiga Serangkai', 'Jl. Asia Afrika No. 3, Bandung', '0217425361'),
('PEN004', 'Mizan', 'Jl. Merdeka No. 5, Bogor', '0219345761'),
('PEN005', 'Andi Publisher', 'Jl. Malioboro No. 88, Yogyakarta', '0216374289');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id_user` varchar(10) NOT NULL,
  `nama` varchar(100) DEFAULT NULL,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `alamat` text DEFAULT NULL,
  `jenis_kelamin` enum('Laki-laki','Perempuan') DEFAULT NULL,
  `tgl_lahir` date DEFAULT NULL,
  `no_hp` varchar(15) DEFAULT NULL,
  `role` enum('admin','user') DEFAULT 'user'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id_user`, `nama`, `username`, `password`, `alamat`, `jenis_kelamin`, `tgl_lahir`, `no_hp`, `role`) VALUES
('USR001', 'Ali', 'user1', 'user123', 'Jl. Kalibata No.42, Jakarta', 'Perempuan', '2005-11-23', '0812345678901', 'user'),
('USR002', 'Budi', 'admin1', 'admin123', 'Jl. MH Thamrin No.13, Jakarta', 'Laki-laki', '2003-04-17', '0812345678902', 'admin'),
('USR003', 'Citra', 'user2', 'user123', 'Jl. Tomang No.31, Jakarta', 'Perempuan', '2004-08-09', '0812345678903', 'user'),
('USR004', 'Dewi', 'user3', 'user123', 'Jl. Fatmawati No.20, Jakarta', 'Laki-laki', '2006-01-14', '0812345678904', 'user'),
('USR005', 'Eka', 'admin2', 'admin123', 'Jl. Salemba No.7, Jakarta', 'Laki-laki', '2007-03-02', '0812345678905', 'admin'),
('USR006', 'Fajar', 'admin3', 'admin123', 'Jl. Medan Merdeka No.99, Jakarta', 'Perempuan', '2004-10-18', '0812345678906', 'admin'),
('USR007', 'Gita', 'user4', 'user123', 'Jl. Pasar Minggu No.88, Jakarta', 'Laki-laki', '2005-06-22', '0812345678907', 'user'),
('USR008', 'Hadi', 'admin4', 'admin123', 'Jl. Pondok Indah No.15, Jakarta', 'Laki-laki', '2003-07-05', '0812345678908', 'admin'),
('USR009', 'Indah', 'user5', 'user123', 'Jl. Cikini No.24, Jakarta', 'Perempuan', '2002-12-01', '0812345678909', 'user'),
('USR010', 'Joko', 'admin5', 'admin123', 'Jl. Kramat Raya No.18, Jakarta', 'Laki-laki', '2006-02-14', '0812345678910', 'admin'),
('USR011', 'Kiki', 'user6', 'user123', 'Jl. Rasuna Said No.26, Jakarta', 'Laki-laki', '2005-03-11', '0812345678911', 'user'),
('USR012', 'Lina', 'user7', 'user123', 'Jl. Pramuka No.56, Jakarta', 'Perempuan', '2002-10-06', '0812345678912', 'user'),
('USR013', 'Made', 'user8', 'user123', 'Jl. Gatot Subroto No.11, Jakarta', 'Perempuan', '2003-09-29', '0812345678913', 'user'),
('USR014', 'Nina', 'admin6', 'admin123', 'Jl. Mangga Dua No.19, Jakarta', 'Perempuan', '2006-05-27', '0812345678914', 'admin'),
('USR015', 'Oka', 'admin7', 'admin123', 'Jl. Kemang No.35, Jakarta', 'Laki-laki', '2002-07-19', '0812345678915', 'admin'),
('USR016', 'Putri', 'user9', 'user123', 'Jl. Hayam Wuruk No.40, Jakarta', 'Perempuan', '2005-11-01', '0812345678916', 'user'),
('USR017', 'Qori', 'user10', 'user123', 'Jl. Pluit No.17, Jakarta', 'Laki-laki', '2004-06-15', '0812345678917', 'user'),
('USR018', 'Rian', 'admin8', 'admin123', 'Jl. MH Thamrin No.21, Jakarta', 'Laki-laki', '2003-12-24', '0812345678918', 'admin'),
('USR019', 'Sari', 'user11', 'user123', 'Jl. Sudirman No.44, Jakarta', 'Perempuan', '2006-08-08', '0812345678919', 'user'),
('USR020', 'Tono', 'user12', 'user123', 'Jl. Cilandak No.55, Jakarta', 'Perempuan', '2007-04-02', '0812345678920', 'user'),
('USR021', 'y', 'dd', 'uu', 'hu', 'Laki-laki', '2005-03-23', '689', 'user'),
('USR022', 'nazwa', 'balerina', 'uiuaa', 'Cikarang', 'Perempuan', '2000-04-05', '088812344444', 'user'),
('USR023', 'asle', 'mamih', 'mamih', 'bandung', 'Perempuan', '2000-09-07', '02345678', 'user');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `buku`
--
ALTER TABLE `buku`
  ADD PRIMARY KEY (`id_buku`),
  ADD KEY `id_penerbit` (`id_penerbit`);

--
-- Indexes for table `peminjaman`
--
ALTER TABLE `peminjaman`
  ADD PRIMARY KEY (`id_peminjaman`),
  ADD KEY `id_user` (`id_user`),
  ADD KEY `id_buku` (`id_buku`);

--
-- Indexes for table `penerbit`
--
ALTER TABLE `penerbit`
  ADD PRIMARY KEY (`id_penerbit`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id_user`),
  ADD UNIQUE KEY `username` (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `peminjaman`
--
ALTER TABLE `peminjaman`
  MODIFY `id_peminjaman` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `buku`
--
ALTER TABLE `buku`
  ADD CONSTRAINT `buku_ibfk_1` FOREIGN KEY (`id_penerbit`) REFERENCES `penerbit` (`id_penerbit`);

--
-- Constraints for table `peminjaman`
--
ALTER TABLE `peminjaman`
  ADD CONSTRAINT `peminjaman_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `users` (`id_user`),
  ADD CONSTRAINT `peminjaman_ibfk_2` FOREIGN KEY (`id_buku`) REFERENCES `buku` (`id_buku`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
