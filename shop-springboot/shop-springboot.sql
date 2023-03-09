SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";

CREATE DATABASE IF NOT EXISTS `shop-springboot` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin;
USE `shop-springboot`;

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

DROP TABLE IF EXISTS `bill_product`;
CREATE TABLE `bill_product` (
  `id` int(11) NOT NULL,
  `id_bill` int(11) NOT NULL,
  `id_product` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `sub_price` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id` int(11) NOT NULL,
  `name` varchar(45) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

INSERT INTO `category` (`id`, `name`) VALUES
(1, 'Áo sơ mi'),
(2, 'Áo thun'),
(3, 'Áo khoác nhẹ'),
(4, 'Quần jean'),
(5, 'Quần tây');

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

INSERT INTO `product` (`id`, `image`, `name`, `id_category`, `price`, `sale`, `quantity`, `rating`, `description`) VALUES
(1, '1620986496658.jpg', 'Sơ mi nam Hàn Quốc', 2, 4, 3, 1, 4, 'Áo sơ mi'),
(2, '1620986516914.jpg', 'Áo khoác', 1, 5, 3, 2, 3, 'Áo khoác'),
(3, '1620986538279.jpg', 'Quần jean', 3, 6, 3, 3, 5, 'Quần jean'),
(4, '1620986548985.jpg', 'Áo khoác', 4, 7, 3, 4, 2, 'Áo khoác'),
(5, '1620986564686.jpg', 'Áo flane', 5, 8, 3, 5, 1, 'Áo flane'),
(6, '1620986602593.jpg', 'Áo len', 1, 9, 3, 0, 3, 'Áo len');

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

INSERT INTO `user` (`id`, `image`, `name`, `address`, `email`, `phone`, `username`, `password`, `role`, `active`) VALUES
(1, '1623252776857.png', 'Phạm Văn Minh', 'Vĩnh Phúc', 'acc.mnhphm@gmail.com', '0981576005', 'admin', '$2a$12$TigtgXnr68pAnP1yv3YiTuaGtxEt9TD6lfJeHOLLuTKmVrd4SGyPW', 'ROLE_ADMIN', b'1'),
(2, '1637337802994.png', 'Nguyễn Anh Tuấn', 'Hà Nội', 'nguyenanhtuan@gmail.com', '0975246839', 'nguyenanhtuan', '$2a$12$PlkR3bVLc//gFln1a7zwd.ZRsgriS2PUGx1.UQmE7p/UFr6fgSLDy', 'ROLE_USER', b'1');

ALTER TABLE `bill`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_id_idx` (`id_user`);

ALTER TABLE `bill_product`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_id_product_idx` (`id_product`),
  ADD KEY `fk_id_bill_idx` (`id_bill`);

ALTER TABLE `category`
  ADD PRIMARY KEY (`id`);

ALTER TABLE `product`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_category_idx` (`id_category`);

ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username_UNIQUE` (`username`),
  ADD UNIQUE KEY `email_UNIQUE` (`email`);

ALTER TABLE `bill`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

ALTER TABLE `bill_product`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

ALTER TABLE `category`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

ALTER TABLE `product`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

ALTER TABLE `bill`
  ADD CONSTRAINT `fk_id` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE `bill_product`
  ADD CONSTRAINT `fk_id_bill` FOREIGN KEY (`id_bill`) REFERENCES `bill` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_id_product` FOREIGN KEY (`id_product`) REFERENCES `product` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE `product`
  ADD CONSTRAINT `fk_category` FOREIGN KEY (`id_category`) REFERENCES `category` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;
