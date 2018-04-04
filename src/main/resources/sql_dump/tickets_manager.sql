CREATE TABLE tickets.manager
(
    manager_id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    password varchar(20) NOT NULL
) DEFAULT CHARACTER SET utf8 ;
INSERT INTO tickets.manager (manager_id, password) VALUES (1, '000');