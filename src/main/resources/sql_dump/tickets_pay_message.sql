CREATE TABLE tickets.pay_message
(
    pay_id int(11) PRIMARY KEY NOT NULL,
    user_id int(11) NOT NULL,
    msg varchar(50) NOT NULL,
    money double DEFAULT '0' NOT NULL,
    time timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL
);
INSERT INTO tickets.pay_message (pay_id, user_id, msg, money, time) VALUES (84, 1, '余额支付', -880, '2018-03-29 18:20:46');
INSERT INTO tickets.pay_message (pay_id, user_id, msg, money, time) VALUES (88, 1, '支付宝支付', -250, '2018-03-29 18:21:23');
INSERT INTO tickets.pay_message (pay_id, user_id, msg, money, time) VALUES (89, 1, '退款', 250, '2018-03-29 18:22:22');
INSERT INTO tickets.pay_message (pay_id, user_id, msg, money, time) VALUES (99, 1, '余额支付', -555, '2018-03-30 10:41:00');
INSERT INTO tickets.pay_message (pay_id, user_id, msg, money, time) VALUES (100, 1, '支付宝支付', -880, '2018-03-30 10:41:10');
INSERT INTO tickets.pay_message (pay_id, user_id, msg, money, time) VALUES (105, 1, '退款', 880, '2018-03-30 13:21:38');
INSERT INTO tickets.pay_message (pay_id, user_id, msg, money, time) VALUES (106, 1, '退款', 555, '2018-03-30 13:23:23');
INSERT INTO tickets.pay_message (pay_id, user_id, msg, money, time) VALUES (107, 1, '退款', 880, '2018-03-30 13:23:53');
INSERT INTO tickets.pay_message (pay_id, user_id, msg, money, time) VALUES (108, 1, '余额支付', -555, '2018-03-31 13:06:28');
INSERT INTO tickets.pay_message (pay_id, user_id, msg, money, time) VALUES (109, 1, '余额支付', -880, '2018-03-31 13:06:30');
INSERT INTO tickets.pay_message (pay_id, user_id, msg, money, time) VALUES (110, 1, '余额支付', -250, '2018-03-31 13:06:33');
INSERT INTO tickets.pay_message (pay_id, user_id, msg, money, time) VALUES (111, 1, '余额支付', -555, '2018-03-31 13:06:36');
INSERT INTO tickets.pay_message (pay_id, user_id, msg, money, time) VALUES (181, 1, '余额支付', -8888, '2018-03-31 14:09:55');
INSERT INTO tickets.pay_message (pay_id, user_id, msg, money, time) VALUES (182, 1, '支付宝支付', -9999, '2018-03-31 14:10:01');
INSERT INTO tickets.pay_message (pay_id, user_id, msg, money, time) VALUES (183, 1, '退款', 8888, '2018-03-31 14:10:10');
INSERT INTO tickets.pay_message (pay_id, user_id, msg, money, time) VALUES (184, 1, '退款', 9999, '2018-03-31 14:10:21');
INSERT INTO tickets.pay_message (pay_id, user_id, msg, money, time) VALUES (185, 1, '退款', 880, '2018-03-31 14:10:36');
INSERT INTO tickets.pay_message (pay_id, user_id, msg, money, time) VALUES (189, 1, '余额支付', -8888, '2018-03-31 19:28:46');
INSERT INTO tickets.pay_message (pay_id, user_id, msg, money, time) VALUES (190, 1, '支付宝支付', -8888, '2018-03-31 19:28:52');
INSERT INTO tickets.pay_message (pay_id, user_id, msg, money, time) VALUES (191, 1, '余额支付', -8888, '2018-03-31 19:50:45');
INSERT INTO tickets.pay_message (pay_id, user_id, msg, money, time) VALUES (192, 1, '余额支付', -8888, '2018-03-31 20:05:22');
INSERT INTO tickets.pay_message (pay_id, user_id, msg, money, time) VALUES (196, 1, '余额支付', -250, '2018-03-31 22:54:05');
INSERT INTO tickets.pay_message (pay_id, user_id, msg, money, time) VALUES (197, 1, '余额支付', -555, '2018-03-31 23:20:33');
INSERT INTO tickets.pay_message (pay_id, user_id, msg, money, time) VALUES (198, 1, '余额支付', -880, '2018-03-31 23:22:15');
INSERT INTO tickets.pay_message (pay_id, user_id, msg, money, time) VALUES (204, 1, '余额支付', -225, '2018-03-31 23:30:55');