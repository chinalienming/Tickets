CREATE TABLE tickets.site_plan
(
    plan_id int(11) PRIMARY KEY NOT NULL,
    site_id char(7) NOT NULL,
    begin_time timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
    end_time timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
    plan_type varchar(20) DEFAULT 'normal' NOT NULL,
    description varchar(100) DEFAULT 'no description' NOT NULL,
    price_a double NOT NULL,
    price_b double NOT NULL,
    price_c double NOT NULL,
    plan_income double DEFAULT '0' NOT NULL,
    seat_a int(11) NOT NULL,
    seat_b int(11) NOT NULL,
    seat_c int(11) NOT NULL
);
INSERT INTO tickets.site_plan (plan_id, site_id, begin_time, end_time, plan_type, description, price_a, price_b, price_c, plan_income, seat_a, seat_b, seat_c) VALUES (1, '1', '2018-01-03 15:05:06', '2018-02-20 15:05:12', '音乐剧', '沂蒙情', 880, 555, 250, 1910, 0, 0, 0);
INSERT INTO tickets.site_plan (plan_id, site_id, begin_time, end_time, plan_type, description, price_a, price_b, price_c, plan_income, seat_a, seat_b, seat_c) VALUES (228, '224', '2018-04-04 00:00:00', '2018-04-29 00:00:00', '娱乐', 'foosday', 80, 60, 40, 240, 20, 20, 20);
INSERT INTO tickets.site_plan (plan_id, site_id, begin_time, end_time, plan_type, description, price_a, price_b, price_c, plan_income, seat_a, seat_b, seat_c) VALUES (309, '306', '2018-01-06 00:00:00', '2018-05-26 00:00:00', '话剧', '头号玩家', 1500, 750, 500, 0, 20, 20, 20);
INSERT INTO tickets.site_plan (plan_id, site_id, begin_time, end_time, plan_type, description, price_a, price_b, price_c, plan_income, seat_a, seat_b, seat_c) VALUES (370, '306', '2018-02-10 00:00:00', '2018-03-30 00:00:00', '影视', '花滑女王', 900, 800, 700, -800, 20, 20, 20);
INSERT INTO tickets.site_plan (plan_id, site_id, begin_time, end_time, plan_type, description, price_a, price_b, price_c, plan_income, seat_a, seat_b, seat_c) VALUES (431, '306', '2018-04-01 00:00:00', '2018-05-25 00:00:00', '影视', '环太平洋', 800, 500, 250, -250, 20, 20, 20);
INSERT INTO tickets.site_plan (plan_id, site_id, begin_time, end_time, plan_type, description, price_a, price_b, price_c, plan_income, seat_a, seat_b, seat_c) VALUES (515, '513', '2018-01-20 00:00:00', '2018-04-01 00:00:00', '音乐剧', '遇见你真好', 800, 500, 300, 3680, 20, 20, 20);
INSERT INTO tickets.site_plan (plan_id, site_id, begin_time, end_time, plan_type, description, price_a, price_b, price_c, plan_income, seat_a, seat_b, seat_c) VALUES (576, '513', '2018-02-17 00:00:00', '2018-05-18 00:00:00', '体育竞技', '通勤营救', 600, 300, 200, 0, 20, 20, 20);
INSERT INTO tickets.site_plan (plan_id, site_id, begin_time, end_time, plan_type, description, price_a, price_b, price_c, plan_income, seat_a, seat_b, seat_c) VALUES (646, '1', '2018-02-02 00:00:00', '2018-04-01 00:00:00', '音乐剧', 'City of Star', 1000, 800, 600, 0, 20, 20, 20);
INSERT INTO tickets.site_plan (plan_id, site_id, begin_time, end_time, plan_type, description, price_a, price_b, price_c, plan_income, seat_a, seat_b, seat_c) VALUES (707, '1', '2017-12-07 00:00:00', '2018-04-01 00:00:00', '话剧', 'City of Sun', 2000, 1800, 1500, 0, 20, 20, 20);
INSERT INTO tickets.site_plan (plan_id, site_id, begin_time, end_time, plan_type, description, price_a, price_b, price_c, plan_income, seat_a, seat_b, seat_c) VALUES (768, '1', '2018-03-02 00:00:00', '2018-05-23 00:00:00', '体育', 'City of Moon', 5000, 2500, 1000, 0, 20, 20, 20);
INSERT INTO tickets.site_plan (plan_id, site_id, begin_time, end_time, plan_type, description, price_a, price_b, price_c, plan_income, seat_a, seat_b, seat_c) VALUES (829, '2', '2018-02-10 00:00:00', '2018-05-11 00:00:00', '体育', '奥运会', 5000, 3000, 2000, 0, 20, 20, 20);
INSERT INTO tickets.site_plan (plan_id, site_id, begin_time, end_time, plan_type, description, price_a, price_b, price_c, plan_income, seat_a, seat_b, seat_c) VALUES (890, '2', '2018-03-16 00:00:00', '2018-04-26 00:00:00', '体育竞技', '亚运会', 3000, 2000, 1000, 0, 20, 20, 20);
INSERT INTO tickets.site_plan (plan_id, site_id, begin_time, end_time, plan_type, description, price_a, price_b, price_c, plan_income, seat_a, seat_b, seat_c) VALUES (951, '2', '2018-03-16 00:00:00', '2018-05-18 00:00:00', '科技展览', '世博会', 1000, 500, 300, 0, 20, 20, 20);
INSERT INTO tickets.site_plan (plan_id, site_id, begin_time, end_time, plan_type, description, price_a, price_b, price_c, plan_income, seat_a, seat_b, seat_c) VALUES (1032, '3', '2018-03-16 00:00:00', '2018-07-20 00:00:00', '电子竞技', 'LOL-S9联赛', 300, 200, 100, 0, 20, 20, 20);
INSERT INTO tickets.site_plan (plan_id, site_id, begin_time, end_time, plan_type, description, price_a, price_b, price_c, plan_income, seat_a, seat_b, seat_c) VALUES (1093, '3', '2018-03-22 00:00:00', '2018-06-01 00:00:00', '电子竞技', '守望先锋', 300, 200, 100, 0, 20, 20, 20);
INSERT INTO tickets.site_plan (plan_id, site_id, begin_time, end_time, plan_type, description, price_a, price_b, price_c, plan_income, seat_a, seat_b, seat_c) VALUES (1154, '3', '2018-02-16 00:00:00', '2018-05-18 00:00:00', '电子竞技', 'CS GO比赛', 3000, 2000, 1000, 0, 20, 20, 20);