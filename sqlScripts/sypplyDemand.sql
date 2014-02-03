SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

DROP SCHEMA IF EXISTS `sypplyDemand` ;
CREATE SCHEMA IF NOT EXISTS `sypplyDemand` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `sypplyDemand` ;

-- -----------------------------------------------------
-- Table `sypplyDemand`.`Domain`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sypplyDemand`.`Domain` ;

CREATE  TABLE IF NOT EXISTS `sypplyDemand`.`Domain` (
  `idDomain` INT NOT NULL ,
  `nomDomain` VARCHAR(45) NOT NULL ,
  `descriptionDomain` TEXT NULL ,
  PRIMARY KEY (`idDomain`) ,
  UNIQUE INDEX `nomDomain_UNIQUE` (`nomDomain` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sypplyDemand`.`Type`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sypplyDemand`.`Type` ;

CREATE  TABLE IF NOT EXISTS `sypplyDemand`.`Type` (
  `idType` INT NOT NULL ,
  `nomType` VARCHAR(45) NOT NULL ,
  `descriptionType` TEXT NULL ,
  `forDomain` INT NOT NULL ,
  PRIMARY KEY (`idType`) ,
  UNIQUE INDEX `nomType_UNIQUE` (`nomType` ASC) ,
  INDEX `fk_Type_1_idx` (`forDomain` ASC) ,
  CONSTRAINT `fk_Type_1`
    FOREIGN KEY (`forDomain` )
    REFERENCES `sypplyDemand`.`Domain` (`idDomain` )
    ON DELETE RESTRICT
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sypplyDemand`.`ValeurType`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sypplyDemand`.`ValeurType` ;

CREATE  TABLE IF NOT EXISTS `sypplyDemand`.`ValeurType` (
  `idValeurType` INT NOT NULL ,
  `type` INT NOT NULL ,
  `valeur` INT NOT NULL ,
  PRIMARY KEY (`idValeurType`) ,
  INDEX `fk_ValeurType_1_idx` (`type` ASC) ,
  CONSTRAINT `fk_ValeurType_1`
    FOREIGN KEY (`type` )
    REFERENCES `sypplyDemand`.`Type` (`idType` )
    ON DELETE RESTRICT
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sypplyDemand`.`Offrant`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sypplyDemand`.`Offrant` ;

CREATE  TABLE IF NOT EXISTS `sypplyDemand`.`Offrant` (
  `idOffrant` INT NOT NULL ,
  `informations` TEXT NULL ,
  PRIMARY KEY (`idOffrant`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sypplyDemand`.`Demandeur`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sypplyDemand`.`Demandeur` ;

CREATE  TABLE IF NOT EXISTS `sypplyDemand`.`Demandeur` (
  `idDemandeur` INT NOT NULL ,
  `informations` VARCHAR(45) NULL ,
  PRIMARY KEY (`idDemandeur`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sypplyDemand`.`CriteresOffrant`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sypplyDemand`.`CriteresOffrant` ;

CREATE  TABLE IF NOT EXISTS `sypplyDemand`.`CriteresOffrant` (
  `idOffrant` INT NOT NULL ,
  `idValeurType` INT NOT NULL ,
  PRIMARY KEY (`idOffrant`, `idValeurType`) ,
  INDEX `fk_CriteresOffrant_1_idx` (`idOffrant` ASC) ,
  INDEX `fk_CriteresOffrant_2_idx` (`idValeurType` ASC) ,
  CONSTRAINT `fk_CriteresOffrant_1`
    FOREIGN KEY (`idOffrant` )
    REFERENCES `sypplyDemand`.`Offrant` (`idOffrant` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_CriteresOffrant_2`
    FOREIGN KEY (`idValeurType` )
    REFERENCES `sypplyDemand`.`ValeurType` (`idValeurType` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sypplyDemand`.`CriteresDemandeur`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sypplyDemand`.`CriteresDemandeur` ;

CREATE  TABLE IF NOT EXISTS `sypplyDemand`.`CriteresDemandeur` (
  `idDemandeur` INT NOT NULL ,
  `idValeurType` INT NOT NULL ,
  PRIMARY KEY (`idDemandeur`, `idValeurType`) ,
  INDEX `fk_CriteresOffrant_2_idx` (`idValeurType` ASC) ,
  INDEX `fk_CriteresOffrant_10_idx` (`idDemandeur` ASC) ,
  CONSTRAINT `fk_CriteresOffrant_10`
    FOREIGN KEY (`idDemandeur` )
    REFERENCES `sypplyDemand`.`Demandeur` (`idDemandeur` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_CriteresOffrant_20`
    FOREIGN KEY (`idValeurType` )
    REFERENCES `sypplyDemand`.`ValeurType` (`idValeurType` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

USE `sypplyDemand` ;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
