CREATE TABLE tickets.ticket_record
(
    record_id int(11) PRIMARY KEY NOT NULL,
    user_id int(11) NOT NULL,
    site_id char(7) NOT NULL,
    plan_id int(11) NOT NULL,
    seat_type char(1) NOT NULL,
    seat_number varchar(5),
    price double NOT NULL,
    create_time timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
    is_valid tinyint(1) DEFAULT '1' NOT NULL,
    credit_add int(11) NOT NULL,
    pay_type int(11) DEFAULT '-1' NOT NULL
);
INSERT INTO tickets.ticket_record (record_id, user_id, site_id, plan_id, seat_type, seat_number, price, create_time, is_valid, credit_add, pay_type) VALUES (79, 1, '1', 1, 'A', 'A10', 880, '2018-03-29 18:12:19', 2, 0, 0);
INSERT INTO tickets.ticket_record (record_id, user_id, site_id, plan_id, seat_type, seat_number, price, create_time, is_valid, credit_add, pay_type) VALUES (80, 1, '1', 1, 'B', 'B10', 555, '2018-03-29 18:12:19', 1, 5, 1);
INSERT INTO tickets.ticket_record (record_id, user_id, site_id, plan_id, seat_type, seat_number, price, create_time, is_valid, credit_add, pay_type) VALUES (81, 1, '1', 1, 'C', 'C10', 250, '2018-03-29 18:12:19', 1, 2, 1);
INSERT INTO tickets.ticket_record (record_id, user_id, site_id, plan_id, seat_type, seat_number, price, create_time, is_valid, credit_add, pay_type) VALUES (85, 1, '1', 1, 'A', 'A01', 880, '2018-03-29 18:21:06', 1, 8, 1);
INSERT INTO tickets.ticket_record (record_id, user_id, site_id, plan_id, seat_type, seat_number, price, create_time, is_valid, credit_add, pay_type) VALUES (86, 1, '1', 1, 'B', 'B01', 555, '2018-03-29 18:21:06', 1, 5, 1);
INSERT INTO tickets.ticket_record (record_id, user_id, site_id, plan_id, seat_type, seat_number, price, create_time, is_valid, credit_add, pay_type) VALUES (87, 1, '1', 1, 'C', 'C01', 250, '2018-03-29 18:21:06', 2, 0, 0);
INSERT INTO tickets.ticket_record (record_id, user_id, site_id, plan_id, seat_type, seat_number, price, create_time, is_valid, credit_add, pay_type) VALUES (90, 1, '1', 1, 'A', 'A02', 880, '2018-03-29 18:35:39', 2, 0, 0);
INSERT INTO tickets.ticket_record (record_id, user_id, site_id, plan_id, seat_type, seat_number, price, create_time, is_valid, credit_add, pay_type) VALUES (91, 1, '1', 1, 'B', 'B02', 555, '2018-03-29 18:37:26', 2, 0, 0);
INSERT INTO tickets.ticket_record (record_id, user_id, site_id, plan_id, seat_type, seat_number, price, create_time, is_valid, credit_add, pay_type) VALUES (101, 1, '1', 1, 'A', 'A03', 880, '2018-03-30 12:19:22', 4, 8, 0);
INSERT INTO tickets.ticket_record (record_id, user_id, site_id, plan_id, seat_type, seat_number, price, create_time, is_valid, credit_add, pay_type) VALUES (102, 1, '1', 1, 'B', 'B03', 555, '2018-03-30 12:19:22', 4, 5, 0);
INSERT INTO tickets.ticket_record (record_id, user_id, site_id, plan_id, seat_type, seat_number, price, create_time, is_valid, credit_add, pay_type) VALUES (103, -1, '1', 1, 'A', 'A04', 880, '2018-03-30 12:20:17', 1, 0, 0);
INSERT INTO tickets.ticket_record (record_id, user_id, site_id, plan_id, seat_type, seat_number, price, create_time, is_valid, credit_add, pay_type) VALUES (104, -1, '1', 1, 'C', 'C01', 250, '2018-03-30 12:20:17', 1, 0, 0);
INSERT INTO tickets.ticket_record (record_id, user_id, site_id, plan_id, seat_type, seat_number, price, create_time, is_valid, credit_add, pay_type) VALUES (112, 1, '1', 1, 'A', 'A02', 880, '2018-03-31 13:07:20', 2, 0, 0);
INSERT INTO tickets.ticket_record (record_id, user_id, site_id, plan_id, seat_type, seat_number, price, create_time, is_valid, credit_add, pay_type) VALUES (175, 1, '1', 114, 'A', 'A17', 9999, '2018-03-31 14:09:42', 2, 0, 2);
INSERT INTO tickets.ticket_record (record_id, user_id, site_id, plan_id, seat_type, seat_number, price, create_time, is_valid, credit_add, pay_type) VALUES (176, 1, '1', 114, 'B', 'B05', 8888, '2018-03-31 14:09:42', 1, 88, 1);
INSERT INTO tickets.ticket_record (record_id, user_id, site_id, plan_id, seat_type, seat_number, price, create_time, is_valid, credit_add, pay_type) VALUES (177, 1, '1', 114, 'B', 'B14', 8888, '2018-03-31 14:09:42', 1, 88, 1);
INSERT INTO tickets.ticket_record (record_id, user_id, site_id, plan_id, seat_type, seat_number, price, create_time, is_valid, credit_add, pay_type) VALUES (178, 1, '1', 114, 'B', 'B15', 8888, '2018-03-31 14:09:42', 1, 88, 2);
INSERT INTO tickets.ticket_record (record_id, user_id, site_id, plan_id, seat_type, seat_number, price, create_time, is_valid, credit_add, pay_type) VALUES (179, 1, '1', 114, 'B', 'B16', 8888, '2018-03-31 14:09:42', 1, 88, 1);
INSERT INTO tickets.ticket_record (record_id, user_id, site_id, plan_id, seat_type, seat_number, price, create_time, is_valid, credit_add, pay_type) VALUES (180, 1, '1', 114, 'B', 'B17', 8888, '2018-03-31 14:09:42', 2, 0, 1);
INSERT INTO tickets.ticket_record (record_id, user_id, site_id, plan_id, seat_type, seat_number, price, create_time, is_valid, credit_add, pay_type) VALUES (193, 1, '1', 1, 'A', 'A02', 880, '2018-03-31 22:50:54', 1, 8, 1);
INSERT INTO tickets.ticket_record (record_id, user_id, site_id, plan_id, seat_type, seat_number, price, create_time, is_valid, credit_add, pay_type) VALUES (194, 1, '1', 1, 'B', 'B02', 555, '2018-03-31 22:50:54', 1, 5, 1);
INSERT INTO tickets.ticket_record (record_id, user_id, site_id, plan_id, seat_type, seat_number, price, create_time, is_valid, credit_add, pay_type) VALUES (195, 1, '1', 1, 'C', 'C02', 250, '2018-03-31 22:50:54', 1, 2, 1);
INSERT INTO tickets.ticket_record (record_id, user_id, site_id, plan_id, seat_type, seat_number, price, create_time, is_valid, credit_add, pay_type) VALUES (199, 1, '1', 1, 'A', 'A07', 880, '2018-03-31 23:30:21', 3, 0, -1);
INSERT INTO tickets.ticket_record (record_id, user_id, site_id, plan_id, seat_type, seat_number, price, create_time, is_valid, credit_add, pay_type) VALUES (200, 1, '1', 1, 'A', 'A17', 880, '2018-03-31 23:30:21', 3, 0, -1);
INSERT INTO tickets.ticket_record (record_id, user_id, site_id, plan_id, seat_type, seat_number, price, create_time, is_valid, credit_add, pay_type) VALUES (201, 1, '1', 1, 'C', 'C07', 250, '2018-03-31 23:30:21', 3, 0, -1);
INSERT INTO tickets.ticket_record (record_id, user_id, site_id, plan_id, seat_type, seat_number, price, create_time, is_valid, credit_add, pay_type) VALUES (202, 1, '1', 1, 'C', 'C17', 250, '2018-03-31 23:30:21', 3, 0, -1);
INSERT INTO tickets.ticket_record (record_id, user_id, site_id, plan_id, seat_type, seat_number, price, create_time, is_valid, credit_add, pay_type) VALUES (203, 1, '1', 1, 'C', 'C09', 250, '2018-03-31 23:30:21', 1, 2, 1);