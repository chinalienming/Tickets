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
    credit_add int(11) NOT NULL
);
INSERT INTO tickets.ticket_record (record_id, user_id, site_id, plan_id, seat_type, seat_number, price, create_time, is_valid, credit_add) VALUES (79, 1, '1', 1, 'A', 'A10', 880, '2018-03-29 18:12:19', 1, 8);
INSERT INTO tickets.ticket_record (record_id, user_id, site_id, plan_id, seat_type, seat_number, price, create_time, is_valid, credit_add) VALUES (80, 1, '1', 1, 'B', 'B10', 555, '2018-03-29 18:12:19', 0, 0);
INSERT INTO tickets.ticket_record (record_id, user_id, site_id, plan_id, seat_type, seat_number, price, create_time, is_valid, credit_add) VALUES (81, 1, '1', 1, 'C', 'C10', 250, '2018-03-29 18:12:19', 0, 0);
INSERT INTO tickets.ticket_record (record_id, user_id, site_id, plan_id, seat_type, seat_number, price, create_time, is_valid, credit_add) VALUES (85, 1, '1', 1, 'A', 'A01', 880, '2018-03-29 18:21:06', 0, 0);
INSERT INTO tickets.ticket_record (record_id, user_id, site_id, plan_id, seat_type, seat_number, price, create_time, is_valid, credit_add) VALUES (86, 1, '1', 1, 'B', 'B01', 555, '2018-03-29 18:21:06', 0, 0);
INSERT INTO tickets.ticket_record (record_id, user_id, site_id, plan_id, seat_type, seat_number, price, create_time, is_valid, credit_add) VALUES (87, 1, '1', 1, 'C', 'C01', 250, '2018-03-29 18:21:06', 2, 0);
INSERT INTO tickets.ticket_record (record_id, user_id, site_id, plan_id, seat_type, seat_number, price, create_time, is_valid, credit_add) VALUES (90, 1, '1', 1, 'A', 'A02', 880, '2018-03-29 18:35:39', 0, 0);
INSERT INTO tickets.ticket_record (record_id, user_id, site_id, plan_id, seat_type, seat_number, price, create_time, is_valid, credit_add) VALUES (91, 1, '1', 1, 'B', 'B02', 555, '2018-03-29 18:37:26', 0, 0);