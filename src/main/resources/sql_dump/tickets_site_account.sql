CREATE TABLE tickets.site_account
(
    site_id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name varchar(50) NOT NULL,
    password varchar(20) NOT NULL,
    active tinyint(1) DEFAULT '0' NOT NULL
);
INSERT INTO tickets.site_account (site_id, name, password, active) VALUES (1, 'rogers', '123', 1);
INSERT INTO tickets.site_account (site_id, name, password, active) VALUES (2, 'happy', '123', 1);