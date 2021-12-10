DROP DATABASE  IF EXISTS `market`;

CREATE DATABASE  IF NOT EXISTS `market`;
USE `market`;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
	`id` int unique auto_increment,
  `username` varchar(50) NOT NULL,
  `password` char(68) NOT NULL,
  `email` varchar(30) NOT NULL,
  `phone_number` varchar(20),
  `address` varchar(50),
  `enabled` tinyint(1) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--
-- NOTE: The passwords are encrypted using BCrypt
--
-- A generation tool is avail at: http://www.luv2code.com/generate-bcrypt-password
--
-- Default passwords here are: fun123
--

INSERT INTO `users` (`username`,`password`,`email`,`phone_number`,`address`,`enabled`)
VALUES 
('john','$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K','john@gmail.com','9701209751','delhi',1),
('mary','$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K','mary@gmail.com','9701209751','kolata',1),
('susan','$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K','susan@gmail.com','9701209751','telangana',1),
('ram','$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K','ram@gmail.com','9701209751','chennai',1);

--
-- Table for Roles
--
create table roles 
			(authority varchar(50) primary key)ENGINE=InnoDB DEFAULT CHARSET=latin1;

insert into roles values('ROLE_USER'),('ROLE_STAFF');

--
-- Table structure for table `authorities`
--

DROP TABLE IF EXISTS `authorities`;
CREATE TABLE `authorities` (
  `username` varchar(50) NOT NULL,
  `authority` varchar(50) NOT NULL,
  FOREIGN KEY (`username`) REFERENCES `users` (`username`) ON DELETE CASCADE,
  FOREIGN KEY (`authority`) REFERENCES `roles` (`authority`) ON DELETE CASCADE,
  primary key (`username`,`authority`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `authorities`
--

INSERT INTO `authorities` 
VALUES 
('john','ROLE_USER'),
('mary','ROLE_USER'),
('susan','ROLE_STAFF'),
('ram','ROLE_STAFF');


create table items
		(item_id int(20) PRIMARY KEY auto_increment,
		item_name varchar(30) NOT NULL unique,
        cost int NOT NULL,
        company varchar(50) NOT NULL
        )ENGINE=InnoDB DEFAULT CHARSET=latin1;
        
insert into items values
			(1,"maggie",10,'nestle'),
            (2,"coke",30,'coca cola'),
            (3,"green lays",5,'lays');
        
        
create table orders
		(username varchar(50) NOT NULL,
        item_id int(10),
        foreign key(item_id) references items(item_id) ON DELETE CASCADE,
        foreign key(username) references users(username) ON DELETE CASCADE
		)ENGINE=InnoDB DEFAULT CHARSET=latin1;


insert into orders(username,item_id) values
		('john',1),
        ('susan',1),
        ('john',3);