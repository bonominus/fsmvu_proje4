CREATE TABLE `borrows` (
  `book_uuid` varchar(36) COLLATE utf8mb3_bin NOT NULL,
  `user_uuid` varchar(36) COLLATE utf8mb3_bin NOT NULL,
  PRIMARY KEY (`user_uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin