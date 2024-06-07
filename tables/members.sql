CREATE TABLE `members` (
  `email` varchar(100) COLLATE utf8mb3_bin NOT NULL,
  `password` varchar(100) COLLATE utf8mb3_bin NOT NULL,
  `fullname` varchar(200) COLLATE utf8mb3_bin NOT NULL,
  `birthday` date NOT NULL,
  `uuid` char(36) COLLATE utf8mb3_bin NOT NULL,
  `admin` tinyint NOT NULL,
  PRIMARY KEY (`uuid`,`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin