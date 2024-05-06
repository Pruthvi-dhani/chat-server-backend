-- create users table
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(100) NOT NULL,
  `display_name` varchar(256) NOT NULL,
  `email` varchar(256) DEFAULT NULL,
  `is_deleted` tinyint NOT NULL DEFAULT '0',
  `created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`)
);

-- create groups table
CREATE TABLE `chat_app`.`group_chats` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `group_name` VARCHAR(100) NOT NULL,
  `group_type` ENUM('private_conv', 'group_conv') NOT NULL DEFAULT 'private_conv',
  `is_deleted` TINYINT NOT NULL DEFAULT 0,
  `created_time` DATETIME NOT NULL DEFAULT  CURRENT_TIMESTAMP,
  `updated_time` DATETIME NULL DEFAULT  CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`));

-- create conversations table
CREATE TABLE `chat_app`.`conversations` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `group_id` INT NOT NULL,
  `is_deleted` TINYINT NOT NULL DEFAULT 0,
  `created_time` DATETIME NOT NULL DEFAULT current_timestamp,
  `updated_time` DATETIME NULL DEFAULT current_timestamp on update current_timestamp,
  PRIMARY KEY (`id`),
  INDEX `group_id_fk_idx_idx` (`group_id` ASC) VISIBLE,
  INDEX `user_id_fk_idx_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `group_id_fk_idx`
    FOREIGN KEY (`group_id`)
    REFERENCES `chat_app`.`group_chats` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `user_id_fk_idx`
    FOREIGN KEY (`user_id`)
    REFERENCES `chat_app`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

-- create message table
CREATE TABLE `chat_app`.`messages` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `group_id` INT NOT NULL,
  `message_content` VARCHAR(2048) NOT NULL,
  `is_deleted` TINYINT NOT NULL DEFAULT 0,
  `created_time` DATETIME NOT NULL DEFAULT current_timestamp,
  `updated_time` DATETIME NULL DEFAULT current_timestamp on update current_timestamp,
  PRIMARY KEY (`id`),
  INDEX `conv_id_fk_idx_idx` (`group_id` ASC) VISIBLE,
  CONSTRAINT `conv_id_fk_idx`
    FOREIGN KEY (`group_id`)
    REFERENCES `chat_app`.`conversations` (`group_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
