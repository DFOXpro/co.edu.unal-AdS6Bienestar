SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

DROP SCHEMA IF EXISTS `bienestar` ;
CREATE SCHEMA IF NOT EXISTS `bienestar` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `bienestar` ;

-- -----------------------------------------------------
-- Table `bienestar`.`USUARIO`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bienestar`.`USUARIO` (
  `ID_USUARIO` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `DOCUMENTO` VARCHAR(30) NOT NULL COMMENT 'NUMERO DE DOCUMENTO DEL USUARIO',
  `T_DOCUMENTO` VARCHAR(2) NOT NULL DEFAULT 'CC' COMMENT 'TIPO DE DOCUMENTO DEL USUARIO, \nCC=CEDULA \nTI= TARJETA DE IDENTIDAD, \nCE=CEDULA DE EXTRANJERIA',
  `NOMBRES` VARCHAR(100) NOT NULL,
  `APELLIDOS` VARCHAR(100) NOT NULL,
  `EMAIL` VARCHAR(50) NOT NULL COMMENT 'CORREO DEL USUARIO, SERÁ TAMBIÉN SU NOMBRE DE USUARIO PARA ENTRAR AL SISTEMA',
  `PASSWORD` VARCHAR(1000) NOT NULL COMMENT 'CLAVE CIFRADA',
  `ROL` CHAR(1) NOT NULL DEFAULT 'E' COMMENT 'Los tipos de roles son\nA=Adminsitrador\nP=Profesor\nE=Estudiante',
  `DATOS_EXTRA` VARCHAR(2000) NULL COMMENT 'DATOS EXTRA PROVENIENTES DE LA APLICACIÓN DE ENTIDAD EDUCATIVA',
  PRIMARY KEY (`ID_USUARIO`),
  UNIQUE INDEX `DOCUMENTO_UNIQUE` (`DOCUMENTO` ASC),
  UNIQUE INDEX `EMAIL_UNIQUE` (`EMAIL` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bienestar`.`TALLER`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bienestar`.`TALLER` (
  `ID_TALLER` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `TIPO_TALLER` VARCHAR(1) NOT NULL COMMENT 'TIPO DE TALLER T=TALLER C=CURSO LIBRE, Taller - se inscribe Cualquiera y si hay cupos libres queda inscrito\ncurso libre - se inscribe estudiante Estudiantes y si hay cupos libres queda inscrito\n\nUn curso libre tiene un docente, un taller puede tener vario /* comment truncated */ /*s profesores.*/',
  `NOMBRE` VARCHAR(500) NOT NULL,
  `DESCRIPCION` VARCHAR(1000) NOT NULL,
  `FECHA_FIN_REGISTRO` DATE NOT NULL,
  `FECHA_INICIO` DATE NOT NULL,
  `FECHA_FIN` DATE NOT NULL,
  `COSTO` INT UNSIGNED NOT NULL,
  `CUPOS` INT NOT NULL,
  PRIMARY KEY (`ID_TALLER`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bienestar`.`PROFESOR_TALLER`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bienestar`.`PROFESOR_TALLER` (
  `ID_PROFESOR` INT UNSIGNED NOT NULL,
  `ID_TALLER` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`ID_PROFESOR`, `ID_TALLER`),
  INDEX `fk_USUARIO_has_TALLER_TALLER1_idx` (`ID_TALLER` ASC),
  INDEX `fk_USUARIO_has_TALLER_USUARIO1_idx` (`ID_PROFESOR` ASC),
  CONSTRAINT `fk_USUARIO_has_TALLER_USUARIO1`
    FOREIGN KEY (`ID_PROFESOR`)
    REFERENCES `bienestar`.`USUARIO` (`ID_USUARIO`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_USUARIO_has_TALLER_TALLER1`
    FOREIGN KEY (`ID_TALLER`)
    REFERENCES `bienestar`.`TALLER` (`ID_TALLER`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bienestar`.`USUARIO_TALLER`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bienestar`.`USUARIO_TALLER` (
  `ID_USUARIO` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `ID_TALLER` INT UNSIGNED NOT NULL,
  `FECHA_REGISTRO` TIMESTAMP NOT NULL DEFAULT NOW(),
  PRIMARY KEY (`ID_USUARIO`, `ID_TALLER`),
  INDEX `fk_USUARIO_has_TALLER_TALLER2_idx` (`ID_TALLER` ASC),
  INDEX `fk_USUARIO_has_TALLER_USUARIO2_idx` (`ID_USUARIO` ASC),
  CONSTRAINT `fk_USUARIO_has_TALLER_USUARIO2`
    FOREIGN KEY (`ID_USUARIO`)
    REFERENCES `bienestar`.`USUARIO` (`ID_USUARIO`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_USUARIO_has_TALLER_TALLER2`
    FOREIGN KEY (`ID_TALLER`)
    REFERENCES `bienestar`.`TALLER` (`ID_TALLER`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bienestar`.`CONVOCATORIA`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bienestar`.`CONVOCATORIA` (
  `ID_CONVOCATORIA` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `NOMBRE` VARCHAR(200) NOT NULL,
  `DESCRIPCION` VARCHAR(1000) NOT NULL,
  `FECHA_FIN_REGISTRO` DATE NOT NULL,
  `CUPOS` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`ID_CONVOCATORIA`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bienestar`.`USUARIO_CONVOCATORIA`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bienestar`.`USUARIO_CONVOCATORIA` (
  `ID_USUARIO` INT UNSIGNED NOT NULL,
  `ID_CONVOCATORIA` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`ID_USUARIO`, `ID_CONVOCATORIA`),
  INDEX `fk_USUARIO_has_CONVOCATORIA_CONVOCATORIA1_idx` (`ID_CONVOCATORIA` ASC),
  INDEX `fk_USUARIO_has_CONVOCATORIA_USUARIO1_idx` (`ID_USUARIO` ASC),
  CONSTRAINT `fk_USUARIO_has_CONVOCATORIA_USUARIO1`
    FOREIGN KEY (`ID_USUARIO`)
    REFERENCES `bienestar`.`USUARIO` (`ID_USUARIO`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_USUARIO_has_CONVOCATORIA_CONVOCATORIA1`
    FOREIGN KEY (`ID_CONVOCATORIA`)
    REFERENCES `bienestar`.`CONVOCATORIA` (`ID_CONVOCATORIA`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
USE `bienestar`;

DELIMITER $$
USE `bienestar`$$
CREATE TRIGGER `USUARIO_BINS` BEFORE INSERT ON `USUARIO` FOR EACH ROW
begin
	declare msg varchar(255);
    if new.T_DOCUMENTO not in ('CC','TI','PP') then
        set msg = concat('USUARIO_BINS Error: el tipo de Documento no es válido : ', cast(new.ID_USUARIO as char));
        signal sqlstate '45000' set message_text = msg;
    end if;
	if new.ROL not in ('A','P','E') then
        set msg = concat('USUARIO_BINS Error: el Rol no es válido : ', cast(new.ID_USUARIO as char));
        signal sqlstate '45000' set message_text = msg;
    end if;
end
$$

USE `bienestar`$$
CREATE TRIGGER `USUARIO_BUPD` BEFORE UPDATE ON `USUARIO` FOR EACH ROW
begin
	declare msg varchar(255);
    if new.T_DOCUMENTO not in ('CC','TI','PP') then
        set msg = concat('USUARIO_BUPD Error: el tipo de Documento no es válido : ', cast(new.T_DOCUMENTO as char));
        signal sqlstate '45000' set message_text = msg;
    end if;
	if new.ROL not in ('A','P','E') then
        set msg = concat('USUARIO_BUPD Error: el Rol no es válido : ', cast(new.ROL as char));
        signal sqlstate '45000' set message_text = msg;
    end if;
end

$$

USE `bienestar`$$
CREATE TRIGGER `TALLER_BINS` BEFORE INSERT ON `TALLER` FOR EACH ROW
begin
	declare msg varchar(255);
    if new.TIPO_TALLER not in ('T','C') then
        set msg = concat('TALLER_BINS Error: el tipo de taller no es válido : ', cast(new.TIPO_TALLER as char));
        signal sqlstate '45000' set message_text = msg;
    end if;
end
$$

USE `bienestar`$$
CREATE TRIGGER `TALLER_BUPD` BEFORE UPDATE ON `TALLER` FOR EACH ROW
begin
	declare msg varchar(255);
    if new.TIPO_TALLER not in ('T','C') then
        set msg = concat('TALLER_BUPD Error: el tipo de taller no es válido : ', cast(new.TIPO_TALLER as char));
        signal sqlstate '45000' set message_text = msg;
    end if;
end$$


DELIMITER ;

