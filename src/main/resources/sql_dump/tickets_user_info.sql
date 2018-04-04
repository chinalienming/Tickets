CREATE TABLE tickets.user_info
(
    user_id int(11) PRIMARY KEY NOT NULL,
    user_name varchar(20) DEFAULT 'name' NOT NULL,
    balance double DEFAULT '0' NOT NULL,
    level int(11) DEFAULT '1' NOT NULL,
    consumption double DEFAULT '0' NOT NULL,
    credit int(11) DEFAULT '0' NOT NULL,
    benefit int(11) NOT NULL
) DEFAULT CHARACTER SET utf8;
INSERT INTO tickets.user_info (user_id, user_name, balance, level, consumption, credit, benefit) VALUES (1, '网易', 92653, 5, 34364, 20, 361);
INSERT INTO tickets.user_info (user_id, user_name, balance, level, consumption, credit, benefit) VALUES (2, '腾讯', 30000, 8, 75000, 750, 0);
INSERT INTO tickets.user_info (user_id, user_name, balance, level, consumption, credit, benefit) VALUES (3, '百度', 40000, 3, 24000, 240, 0);
INSERT INTO tickets.user_info (user_id, user_name, balance, level, consumption, credit, benefit) VALUES (4, '阿里', 50000, 1, 0, 0, 0);
INSERT INTO tickets.user_info (user_id, user_name, balance, level, consumption, credit, benefit) VALUES (5, '口碑', 60000, 1, 0, 0, 0);
INSERT INTO tickets.user_info (user_id, user_name, balance, level, consumption, credit, benefit) VALUES (38, '饿了么', 70000, 1, 0, 0, 0);
INSERT INTO tickets.user_info (user_id, user_name, balance, level, consumption, credit, benefit) VALUES (39, '美团', 80000, 1, 0, 0, 0);
INSERT INTO tickets.user_info (user_id, user_name, balance, level, consumption, credit, benefit) VALUES (40, 'name', 0, 1, 0, 0, 0);
INSERT INTO tickets.user_info (user_id, user_name, balance, level, consumption, credit, benefit) VALUES (41, 'name', 0, 1, 0, 0, 0);
INSERT INTO tickets.user_info (user_id, user_name, balance, level, consumption, credit, benefit) VALUES (42, 'name', 0, 1, 0, 0, 0);
INSERT INTO tickets.user_info (user_id, user_name, balance, level, consumption, credit, benefit) VALUES (43, 'name', 0, 1, 0, 0, 0);
INSERT INTO tickets.user_info (user_id, user_name, balance, level, consumption, credit, benefit) VALUES (44, 'name', 0, 1, 0, 0, 0);
INSERT INTO tickets.user_info (user_id, user_name, balance, level, consumption, credit, benefit) VALUES (45, 'name', 0, 1, 0, 0, 0);
INSERT INTO tickets.user_info (user_id, user_name, balance, level, consumption, credit, benefit) VALUES (46, 'name', 0, 1, 0, 0, 0);
INSERT INTO tickets.user_info (user_id, user_name, balance, level, consumption, credit, benefit) VALUES (47, 'name', 38560, 1, 1960, 16, 0);
INSERT INTO tickets.user_info (user_id, user_name, balance, level, consumption, credit, benefit) VALUES (48, 'name', 0, 1, 0, 0, 0);
INSERT INTO tickets.user_info (user_id, user_name, balance, level, consumption, credit, benefit) VALUES (49, 'name', 0, 1, 0, 0, 0);
INSERT INTO tickets.user_info (user_id, user_name, balance, level, consumption, credit, benefit) VALUES (50, 'name', 0, 1, 0, 0, 0);
INSERT INTO tickets.user_info (user_id, user_name, balance, level, consumption, credit, benefit) VALUES (51, 'name', 0, 1, 0, 0, 0);
INSERT INTO tickets.user_info (user_id, user_name, balance, level, consumption, credit, benefit) VALUES (52, 'name', 0, 1, 0, 0, 0);
INSERT INTO tickets.user_info (user_id, user_name, balance, level, consumption, credit, benefit) VALUES (305, 'name', 0, 1, 0, 0, 0);