CREATE TABLE `books` (
  `name` varchar(500) COLLATE utf8mb3_bin NOT NULL,
  `author` varchar(500) COLLATE utf8mb3_bin NOT NULL,
  `isbn` varchar(14) COLLATE utf8mb3_bin NOT NULL,
  `publisher` varchar(500) COLLATE utf8mb3_bin NOT NULL,
  `edition` int NOT NULL,
  `uuid` varchar(36) COLLATE utf8mb3_bin NOT NULL,
  `category` varchar(100) COLLATE utf8mb3_bin NOT NULL,
  `duedate` date DEFAULT NULL,
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin