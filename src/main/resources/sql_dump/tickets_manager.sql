CREATE TABLE tickets.manager
(
    manager_id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    password varchar(20) NOT NULL
);
INSERT INTO tickets.manager (manager_id, password) VALUES (1, '000');