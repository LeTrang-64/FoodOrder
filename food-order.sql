
CREATE SCHEMA IF NOT EXISTS `foodorderapp` DEFAULT CHARACTER SET utf8 ;
USE `foodorderapp` ;






CREATE TABLE IF NOT EXISTS `foodorderapp`.`food` (
  `food_id` INT NOT NULL auto_increment,
  `food_name` VARCHAR(300) NOT NULL,  
  `food_price` DOUBLE NOT NULL ,
  `img` VARCHAR(255) NOT NULL,
  `info` VARCHAR(255) NOT NULL, 
  PRIMARY KEY (`food_id`))
 
 
ENGINE = InnoDB
;
CREATE TABLE IF NOT EXISTS `foodorderapp`.`User` (
  `user_id` int(100) NOT NULL auto_increment,
  `user_email` VARCHAR(45) NOT NULL,
  `password` varchar(255) NOT NULL,
  `first_name` VARCHAR(30) NOT NULL,
  `last_name` VARCHAR(30) NOT NULL,
  `phone_number` VARCHAR(45) NOT NULL,
  `shipping_address` VARCHAR(100) NOT NULL,
  `is_manager` TINYINT NULL DEFAULT 0,
  PRIMARY KEY (`user_id`))
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `foodorderapp`.`Order` (
  `order_id` int(100) NOT NULL ,
  `user_id` INT  NOT NULL,
  `total` INT  NOT NULL,
  `ship` varchar(255)  NOT NULL,
  `pay` varchar(255)  NOT NULL,
  PRIMARY KEY (`order_id`),
  CONSTRAINT `fk_user_id_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `foodorderapp`.`user` (`user_id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
ENGINE = InnoDB;






CREATE TABLE IF NOT EXISTS `foodorderapp`.`item` (
  `item_id` int(255) NOT NULL auto_increment,
  `order_id` int(100) not NULL,
  `food_id` INT not NULL,
  `price` double not NULL,
  `quantity` INT not NULL,
  PRIMARY KEY (`item_id`),
  CONSTRAINT `fk_order_id_order`
    FOREIGN KEY (`order_id`)
    REFERENCES `foodorderapp`.`order` (`order_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_food_id_food`
    FOREIGN KEY (`food_id`)
    REFERENCES `foodorderapp`.`food` (`food_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `foodorderapp`.`user_food` (
  `user_id` int(255) NOT NULL ,
  `food_id` INT not NULL,
  `quantity` INT not NULL,
  primary key(`user_id`,`food_id`),

  CONSTRAINT `fk_user_id_food`
    FOREIGN KEY (`user_id`)
    REFERENCES `foodorderapp`.`user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_food_id_user`
    FOREIGN KEY (`food_id`)
    REFERENCES `foodorderapp`.`food` (`food_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

