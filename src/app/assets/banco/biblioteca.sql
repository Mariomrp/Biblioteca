-- MySQL Script generated by MySQL Workbench
-- 06/21/17 10:19:37
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema biblioteca
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema biblioteca
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `biblioteca` DEFAULT CHARACTER SET utf8 ;
USE `biblioteca` ;

-- -----------------------------------------------------
-- Table `biblioteca`.`tb_autor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `biblioteca`.`tb_autor` (
  `id_autor` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(30) NOT NULL,
  `origem` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`id_autor`),
  UNIQUE INDEX `id_autor_UNIQUE` (`id_autor` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `biblioteca`.`tb_usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `biblioteca`.`tb_usuario` (
  `matricula` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(30) NOT NULL,
  `email` VARCHAR(30) NOT NULL,
  `fone` VARCHAR(15) NOT NULL,
  PRIMARY KEY (`matricula`),
  UNIQUE INDEX `matricula_UNIQUE` (`matricula` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `biblioteca`.`tb_livro`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `biblioteca`.`tb_livro` (
  `isbn` VARCHAR(13) NOT NULL,
  `titulo` VARCHAR(20) NOT NULL,
  `editora` VARCHAR(20) NOT NULL,
  `ano` YEAR NOT NULL,
  `tb_autor_id_autor` INT NOT NULL,
  PRIMARY KEY (`isbn`),
  INDEX `fk_tb_livro_tb_autor1_idx` (`tb_autor_id_autor` ASC),
  UNIQUE INDEX `isbn_UNIQUE` (`isbn` ASC),
  CONSTRAINT `fk_tb_livro_tb_autor1`
    FOREIGN KEY (`tb_autor_id_autor`)
    REFERENCES `biblioteca`.`tb_autor` (`id_autor`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `biblioteca`.`tb_retirada`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `biblioteca`.`tb_retirada` (
  `id_retirada` INT NOT NULL AUTO_INCREMENT,
  `tb_usuario_matricula` INT NOT NULL,
  `tb_livro_isbn` VARCHAR(13) NOT NULL,
  `dataEntrada` DATE NOT NULL,
  `dataSaida` DATE NOT NULL,
  PRIMARY KEY (`id_retirada`),
  UNIQUE INDEX `id_retirada_UNIQUE` (`id_retirada` ASC),
  INDEX `fk_tb_retirada_tb_livro1_idx` (`tb_livro_isbn` ASC),
  INDEX `fk_tb_retirada_tb_usuario1_idx` (`tb_usuario_matricula` ASC),
  CONSTRAINT `fk_tb_retirada_tb_livro1`
    FOREIGN KEY (`tb_livro_isbn`)
    REFERENCES `biblioteca`.`tb_livro` (`isbn`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tb_retirada_tb_usuario1`
    FOREIGN KEY (`tb_usuario_matricula`)
    REFERENCES `biblioteca`.`tb_usuario` (`matricula`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
