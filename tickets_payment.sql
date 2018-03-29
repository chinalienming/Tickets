CREATE TABLE tickets.payment
(
    account_id int(11) PRIMARY KEY NOT NULL,
    password varchar(20) NOT NULL,
    balance double DEFAULT '0' NOT NULL,
    user_id int(11),
    is_site tinyint(1) DEFAULT '0' NOT NULL
);
INSERT INTO tickets.payment (account_id, password, balance, user_id, is_site) VALUES (1, '1234', 730, 1, 0);
INSERT INTO tickets.payment (account_id, password, balance, user_id, is_site) VALUES (2, '1234', 0, 2, 0);