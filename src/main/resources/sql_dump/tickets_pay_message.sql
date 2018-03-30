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