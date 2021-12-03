-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema anotherSocialMedia
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema anotherSocialMedia
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `anotherSocialMedia` DEFAULT CHARACTER SET utf8 ;
USE `anotherSocialMedia` ;

-- -----------------------------------------------------
-- Table `anotherSocialMedia`.`Users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `anotherSocialMedia`.`Users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(90) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `anotherSocialMedia`.`Friends`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `anotherSocialMedia`.`Friends` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(45) NOT NULL,
  `host` VARCHAR(45) NOT NULL,
  `Users_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Friends_Users_idx` (`Users_id` ASC),
  CONSTRAINT `fk_Friends_Users`
    FOREIGN KEY (`Users_id`)
    REFERENCES `anotherSocialMedia`.`Users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `anotherSocialMedia`.`Friendship_Request`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `anotherSocialMedia`.`Friendship_Request` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(45) NOT NULL,
  `host` VARCHAR(45) NOT NULL,
  `status` VARCHAR(45) NOT NULL,
  `type` TINYINT NOT NULL,
  `time` TIMESTAMP NOT NULL,
  `Users_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Friendship_Request_Users1_idx` (`Users_id` ASC),
  CONSTRAINT `fk_Friendship_Request_Users1`
    FOREIGN KEY (`Users_id`)
    REFERENCES `anotherSocialMedia`.`Users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
