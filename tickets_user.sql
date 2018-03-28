CREATE TABLE tickets.user
(
    user_id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    email varchar(50) NOT NULL,
    password varchar(20) NOT NULL,
    qualified tinyint(1) DEFAULT '1' NOT NULL,
    activated tinyint(1) DEFAULT '1' NOT NULL
);
INSERT INTO tickets.user (user_id, email, password, qualified, activated) VALUES (1, 'gakki@qq.com', '123', 1, 1);
INSERT INTO tickets.user (user_id, email, password, qualified, activated) VALUES (2, 'lem@qq.com', '123', 1, 1);
INSERT INTO tickets.user (user_id, email, password, qualified, activated) VALUES (3, 'xjp@qq.com', '123', 1, 1);
INSERT INTO tickets.user (user_id, email, password, qualified, activated) VALUES (4, 'mygakki@qq.com', '123', 1, 1);
INSERT INTO tickets.user (user_id, email, password, qualified, activated) VALUES (5, 'ok@qq.com', '123', 1, 1);
INSERT INTO tickets.user (user_id, email, password, qualified, activated) VALUES (38, 'wowowo@qq.com', '123', 1, 1);
INSERT INTO tickets.user (user_id, email, password, qualified, activated) VALUES (39, 'ewqwqwq@qq.com', '123', 1, 1);
INSERT INTO tickets.user (user_id, email, password, qualified, activated) VALUES (40, 'ttt@qq.com', '123', 1, 1);
INSERT INTO tickets.user (user_id, email, password, qualified, activated) VALUES (41, 'ferwfqewf@qq.com', '123', 1, 1);
INSERT INTO tickets.user (user_id, email, password, qualified, activated) VALUES (42, 'ergerqgewgr@qq.com', '123', 1, 1);
INSERT INTO tickets.user (user_id, email, password, qualified, activated) VALUES (43, '363463452345@qq.com', '123', 1, 1);
INSERT INTO tickets.user (user_id, email, password, qualified, activated) VALUES (44, '12421342314@qq.com', '123', 1, 1);
INSERT INTO tickets.user (user_id, email, password, qualified, activated) VALUES (45, '151345ki@qq.com', '123', 1, 1);
INSERT INTO tickets.user (user_id, email, password, qualified, activated) VALUES (46, '24525235235@qq.com', '123', 1, 1);
INSERT INTO tickets.user (user_id, email, password, qualified, activated) VALUES (47, 'gakk345345i@qq.com', '123', 1, 1);
INSERT INTO tickets.user (user_id, email, password, qualified, activated) VALUES (48, 'gak12312312ki@qq.com', '123', 1, 1);
INSERT INTO tickets.user (user_id, email, password, qualified, activated) VALUES (49, 'gak124124124ki@qq.com', '123', 1, 1);
INSERT INTO tickets.user (user_id, email, password, qualified, activated) VALUES (50, '123gakki@qq.com', '123', 1, 1);
INSERT INTO tickets.user (user_id, email, password, qualified, activated) VALUES (51, '222gakki@qq.com', '123', 1, 1);
INSERT INTO tickets.user (user_id, email, password, qualified, activated) VALUES (52, 'gakk22221111i@qq.com', '123', 1, 1);