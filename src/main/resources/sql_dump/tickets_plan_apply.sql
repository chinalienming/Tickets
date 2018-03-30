CREATE TABLE tickets.plan_apply
(
    plan_id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    site_id int(11) NOT NULL,
    begin_time date NOT NULL,
    end_time date NOT NULL,
    plan_Type varchar(30) NOT NULL,
    description varchar(50) NOT NULL,
    price_a double NOT NULL,
    price_b double NOT NULL,
    price_c double NOT NULL,
    state int(11) DEFAULT '0' NOT NULL
);
INSERT INTO tickets.plan_apply (plan_id, site_id, begin_time, end_time, plan_Type, description, price_a, price_b, price_c, state) VALUES (1, 1, '2018-03-19', '2018-03-20', 'type', 'ooo1', 2000, 1800, 900, 0);
INSERT INTO tickets.plan_apply (plan_id, site_id, begin_time, end_time, plan_Type, description, price_a, price_b, price_c, state) VALUES (2, 1, '2018-03-20', '2018-03-30', 'notype', 'hhh1', 888, 777, 666, 0);