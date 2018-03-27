CREATE TABLE tickets.user_info
(
    user_id int(11) PRIMARY KEY NOT NULL,
    user_name varchar(20) DEFAULT 'name' NOT NULL,
    balance double DEFAULT '0' NOT NULL,
    level int(11) DEFAULT '1' NOT NULL,
    consumption double DEFAULT '0' NOT NULL,
    credit int(11) DEFAULT '0' NOT NULL
);
INSERT INTO tickets.user_info (user_id, user_name, balance, level, consumption, credit) VALUES (1, 'gakki', 979530, 1, 119830, 2577);
INSERT INTO tickets.user_info (user_id, user_name, balance, level, consumption, credit) VALUES (2, 'name', 0, 1, 0, 0);
INSERT INTO tickets.user_info (user_id, user_name, balance, level, consumption, credit) VALUES (3, 'name', 0, 1, 0, 0);
INSERT INTO tickets.user_info (user_id, user_name, balance, level, consumption, credit) VALUES (4, 'name', 0, 1, 0, 0);
INSERT INTO tickets.user_info (user_id, user_name, balance, level, consumption, credit) VALUES (5, 'name', 0, 1, 0, 0);