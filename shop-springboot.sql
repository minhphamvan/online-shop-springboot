-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th10 19, 2021 lúc 05:20 PM
-- Phiên bản máy phục vụ: 10.4.18-MariaDB
-- Phiên bản PHP: 8.0.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `shop-springboot-jwt`
--
CREATE DATABASE IF NOT EXISTS `shop-springboot-jwt` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin;
USE `shop-springboot-jwt`;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `bill`
--

DROP TABLE IF EXISTS `bill`;
CREATE TABLE `bill` (
  `id` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `time` varchar(45) COLLATE utf8_bin NOT NULL,
  `note` varchar(250) COLLATE utf8_bin DEFAULT NULL,
  `buy_date` varchar(45) COLLATE utf8_bin NOT NULL,
  `total_price` int(11) NOT NULL,
  `pay_method` varchar(45) COLLATE utf8_bin NOT NULL,
  `status` varchar(45) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Đang đổ dữ liệu cho bảng `bill`
--

INSERT INTO `bill` (`id`, `id_user`, `time`, `note`, `buy_date`, `total_price`, `pay_method`, `status`) VALUES
(54, 20, 'Giờ hành chính', '', '2021-05-21', 13, 'COD', 'Đã lên đơn'),
(56, 20, 'Giờ hành chính', '', '2021-06-08', 13, 'COD', 'Đã lên đơn'),
(57, 20, 'Giờ hành chính', '', '2021-06-14', 13, 'COD', 'Đã lên đơn');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `bill_product`
--

DROP TABLE IF EXISTS `bill_product`;
CREATE TABLE `bill_product` (
  `id` int(11) NOT NULL,
  `id_bill` int(11) NOT NULL,
  `id_product` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `sub_price` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Đang đổ dữ liệu cho bảng `bill_product`
--

INSERT INTO `bill_product` (`id`, `id_bill`, `id_product`, `quantity`, `sub_price`) VALUES
(18, 54, 4, 1, 3),
(20, 56, 5, 1, 3),
(21, 57, 4, 1, 3);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `category`
--

DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id` int(11) NOT NULL,
  `name` varchar(45) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Đang đổ dữ liệu cho bảng `category`
--

INSERT INTO `category` (`id`, `name`) VALUES
(2, 'Áo sơ mi'),
(11, 'Áo thun'),
(12, 'Áo khoác nhẹ'),
(13, 'Quần jean'),
(14, 'Quần tây');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `product`
--

DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `id` int(11) NOT NULL,
  `image` varchar(50) COLLATE utf8_bin NOT NULL,
  `name` varchar(200) COLLATE utf8_bin NOT NULL,
  `id_category` int(11) NOT NULL,
  `price` int(11) NOT NULL,
  `sale` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `rating` int(11) NOT NULL,
  `description` varchar(250) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Đang đổ dữ liệu cho bảng `product`
--

INSERT INTO `product` (`id`, `image`, `name`, `id_category`, `price`, `sale`, `quantity`, `rating`, `description`) VALUES
(4, '1620986496658.jpg', 'Sơ mi nam Hàn Quốc', 2, 4, 3, 1, 4, 'Áo sơ mi'),
(5, '1620986516914.jpg', 'Áo khoác', 12, 5, 3, 2, 3, 'Áo khoác'),
(6, '1620986538279.jpg', 'Quần jean', 13, 6, 3, 3, 5, 'Quần jean'),
(7, '1620986548985.jpg', 'Áo khoác', 12, 7, 3, 4, 2, 'Áo khoác'),
(8, '1620986564686.jpg', 'Áo flane', 2, 8, 3, 5, 1, 'Áo flane'),
(9, '1620986602593.jpg', 'Áo len', 12, 9, 3, 0, 3, 'Áo len'),
(10, '1623604085071.jpg', 'Quần tây', 14, 45, 34, 2, 2, '');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `image` varchar(45) COLLATE utf8_bin NOT NULL,
  `name` varchar(45) COLLATE utf8_bin NOT NULL,
  `address` varchar(45) COLLATE utf8_bin NOT NULL,
  `email` varchar(45) COLLATE utf8_bin NOT NULL,
  `phone` varchar(45) COLLATE utf8_bin NOT NULL,
  `username` varchar(45) COLLATE utf8_bin NOT NULL,
  `password` varchar(250) COLLATE utf8_bin NOT NULL,
  `role` varchar(45) COLLATE utf8_bin NOT NULL,
  `active` bit(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Đang đổ dữ liệu cho bảng `user`
--

INSERT INTO `user` (`id`, `image`, `name`, `address`, `email`, `phone`, `username`, `password`, `role`, `active`) VALUES
(20, '1623252776857.png', 'Phạm Văn Minh', 'Vĩnh Phúc', 'acc.mnhphm@gmail.com', '0981576005', 'admin', '$2a$12$TigtgXnr68pAnP1yv3YiTuaGtxEt9TD6lfJeHOLLuTKmVrd4SGyPW', 'ROLE_ADMIN', b'1'),
(31, '1637337802994.png', 'Nguyễn Anh Tuấn', 'Hà Nội', 'nguyenanhtuan@gmail.com', '0975246839', 'nguyenanhtuan', '$2a$12$PlkR3bVLc//gFln1a7zwd.ZRsgriS2PUGx1.UQmE7p/UFr6fgSLDy', 'ROLE_USER', b'1');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `bill`
--
ALTER TABLE `bill`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_id_idx` (`id_user`);

--
-- Chỉ mục cho bảng `bill_product`
--
ALTER TABLE `bill_product`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_id_product_idx` (`id_product`),
  ADD KEY `fk_id_bill_idx` (`id_bill`);

--
-- Chỉ mục cho bảng `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_category_idx` (`id_category`);

--
-- Chỉ mục cho bảng `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username_UNIQUE` (`username`),
  ADD UNIQUE KEY `email_UNIQUE` (`email`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `bill`
--
ALTER TABLE `bill`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=58;

--
-- AUTO_INCREMENT cho bảng `bill_product`
--
ALTER TABLE `bill_product`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT cho bảng `category`
--
ALTER TABLE `category`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT cho bảng `product`
--
ALTER TABLE `product`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT cho bảng `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `bill`
--
ALTER TABLE `bill`
  ADD CONSTRAINT `fk_id` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Các ràng buộc cho bảng `bill_product`
--
ALTER TABLE `bill_product`
  ADD CONSTRAINT `fk_id_bill` FOREIGN KEY (`id_bill`) REFERENCES `bill` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_id_product` FOREIGN KEY (`id_product`) REFERENCES `product` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Các ràng buộc cho bảng `product`
--
ALTER TABLE `product`
  ADD CONSTRAINT `fk_category` FOREIGN KEY (`id_category`) REFERENCES `category` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
