CREATE TABLE tickets.settlement
(
    settle_id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    plan_id int(11) NOT NULL,
    site_id int(11) NOT NULL,
    create_time timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
    price double NOT NULL
) DEFAULT CHARACTER SET utf8 ;
INSERT INTO tickets.settlement (settle_id, plan_id, site_id, create_time, price) VALUES (186, 1, 1, '2018-03-31 15:23:43', 1000000);
INSERT INTO tickets.settlement (settle_id, plan_id, site_id, create_time, price) VALUES (187, 2, 1, '2018-03-31 15:23:43', 50000);
INSERT INTO tickets.settlement (settle_id, plan_id, site_id, create_time, price) VALUES (188, 3, 1, '2018-03-31 15:23:43', 30000);
INSERT INTO tickets.settlement (settle_id, plan_id, site_id, create_time, price) VALUES (304, 114, 1, '2018-04-01 13:59:38', 2666.4);