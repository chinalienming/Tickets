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
INSERT INTO tickets.ticket_record (record_id, user_id, site_id, plan_id, seat_type, seat_number, price, create_time, is_valid, credit_add) VALUES (1, 1, '1', 1, 'A', 'A03', 1000, '2018-01-21 03:31:51', 2, 0);
INSERT INTO tickets.ticket_record (record_id, user_id, site_id, plan_id, seat_type, seat_number, price, create_time, is_valid, credit_add) VALUES (2, 1, '1', 1, 'A', 'A04', 1000, '2018-01-21 03:31:51', 2, 0);
INSERT INTO tickets.ticket_record (record_id, user_id, site_id, plan_id, seat_type, seat_number, price, create_time, is_valid, credit_add) VALUES (3, 1, '1', 1, 'B', 'B08', 500, '2018-01-21 03:31:51', 2, 0);
INSERT INTO tickets.ticket_record (record_id, user_id, site_id, plan_id, seat_type, seat_number, price, create_time, is_valid, credit_add) VALUES (4, 1, '1', 1, 'B', 'B09', 500, '2018-01-21 03:31:51', 2, 0);
INSERT INTO tickets.ticket_record (record_id, user_id, site_id, plan_id, seat_type, seat_number, price, create_time, is_valid, credit_add) VALUES (5, 1, '1', 1, 'C', 'C05', 250, '2018-01-21 03:31:51', 2, 0);
INSERT INTO tickets.ticket_record (record_id, user_id, site_id, plan_id, seat_type, seat_number, price, create_time, is_valid, credit_add) VALUES (6, 1, '1', 1, 'C', 'C06', 250, '2018-01-21 03:31:51', 2, 0);
INSERT INTO tickets.ticket_record (record_id, user_id, site_id, plan_id, seat_type, seat_number, price, create_time, is_valid, credit_add) VALUES (22, 1, '1', 1, 'A', 'A10', 880, '2018-03-26 14:26:41', 2, 0);
INSERT INTO tickets.ticket_record (record_id, user_id, site_id, plan_id, seat_type, seat_number, price, create_time, is_valid, credit_add) VALUES (23, 1, '1', 1, 'B', 'B10', 555, '2018-03-26 14:26:41', 2, 0);
INSERT INTO tickets.ticket_record (record_id, user_id, site_id, plan_id, seat_type, seat_number, price, create_time, is_valid, credit_add) VALUES (24, 1, '1', 1, 'C', 'C10', 250, '2018-03-26 14:26:41', 2, 0);
INSERT INTO tickets.ticket_record (record_id, user_id, site_id, plan_id, seat_type, seat_number, price, create_time, is_valid, credit_add) VALUES (25, 1, '1', 1, 'A', 'A11', 880, '2018-03-26 14:26:41', 2, 0);
INSERT INTO tickets.ticket_record (record_id, user_id, site_id, plan_id, seat_type, seat_number, price, create_time, is_valid, credit_add) VALUES (26, 1, '1', 1, 'B', 'B11', 555, '2018-03-26 14:26:41', 2, 0);
INSERT INTO tickets.ticket_record (record_id, user_id, site_id, plan_id, seat_type, seat_number, price, create_time, is_valid, credit_add) VALUES (27, 1, '1', 1, 'C', 'C11', 250, '2018-03-26 14:26:41', 2, 0);
INSERT INTO tickets.ticket_record (record_id, user_id, site_id, plan_id, seat_type, seat_number, price, create_time, is_valid, credit_add) VALUES (28, 1, '1', 1, 'C', 'C12', 250, '2018-03-26 14:26:41', 2, 0);
INSERT INTO tickets.ticket_record (record_id, user_id, site_id, plan_id, seat_type, seat_number, price, create_time, is_valid, credit_add) VALUES (29, 1, '1', 1, 'A', 'A05', 880, '2018-03-27 12:33:53', 2, 0);
INSERT INTO tickets.ticket_record (record_id, user_id, site_id, plan_id, seat_type, seat_number, price, create_time, is_valid, credit_add) VALUES (30, 1, '1', 1, 'B', 'B05', 555, '2018-03-27 12:33:53', 1, 168);
INSERT INTO tickets.ticket_record (record_id, user_id, site_id, plan_id, seat_type, seat_number, price, create_time, is_valid, credit_add) VALUES (31, 1, '1', 1, 'C', 'C15', 250, '2018-03-27 12:33:53', 1, 168);
INSERT INTO tickets.ticket_record (record_id, user_id, site_id, plan_id, seat_type, seat_number, price, create_time, is_valid, credit_add) VALUES (32, 1, '1', 1, 'A', 'A07', 880, '2018-03-27 12:38:49', 1, 337);
INSERT INTO tickets.ticket_record (record_id, user_id, site_id, plan_id, seat_type, seat_number, price, create_time, is_valid, credit_add) VALUES (33, 1, '1', 1, 'A', 'A17', 880, '2018-03-27 12:38:49', 1, 337);
INSERT INTO tickets.ticket_record (record_id, user_id, site_id, plan_id, seat_type, seat_number, price, create_time, is_valid, credit_add) VALUES (34, 1, '1', 1, 'B', 'B07', 555, '2018-03-27 12:38:49', 1, 337);
INSERT INTO tickets.ticket_record (record_id, user_id, site_id, plan_id, seat_type, seat_number, price, create_time, is_valid, credit_add) VALUES (35, 1, '1', 1, 'B', 'B17', 555, '2018-03-27 12:38:49', 1, 337);
INSERT INTO tickets.ticket_record (record_id, user_id, site_id, plan_id, seat_type, seat_number, price, create_time, is_valid, credit_add) VALUES (36, 1, '1', 1, 'C', 'C07', 250, '2018-03-27 12:38:49', 1, 337);
INSERT INTO tickets.ticket_record (record_id, user_id, site_id, plan_id, seat_type, seat_number, price, create_time, is_valid, credit_add) VALUES (37, 1, '1', 1, 'C', 'C17', 250, '2018-03-27 12:38:49', 1, 337);
INSERT INTO tickets.ticket_record (record_id, user_id, site_id, plan_id, seat_type, seat_number, price, create_time, is_valid, credit_add) VALUES (53, 1, '1', 1, 'B', 'B14', 555, '2018-03-28 10:56:26', 1, 130);
INSERT INTO tickets.ticket_record (record_id, user_id, site_id, plan_id, seat_type, seat_number, price, create_time, is_valid, credit_add) VALUES (54, 1, '1', 1, 'C', 'C04', 250, '2018-03-28 10:56:26', 1, 130);
INSERT INTO tickets.ticket_record (record_id, user_id, site_id, plan_id, seat_type, seat_number, price, create_time, is_valid, credit_add) VALUES (55, 1, '1', 1, 'C', 'C14', 250, '2018-03-28 10:56:26', 1, 130);
INSERT INTO tickets.ticket_record (record_id, user_id, site_id, plan_id, seat_type, seat_number, price, create_time, is_valid, credit_add) VALUES (56, 1, '1', 1, 'C', 'C03', 250, '2018-03-28 10:56:26', 1, 130);
INSERT INTO tickets.ticket_record (record_id, user_id, site_id, plan_id, seat_type, seat_number, price, create_time, is_valid, credit_add) VALUES (57, 1, '1', 1, 'A', 'A01', 880, '2018-03-28 12:19:06', 1, 751);
INSERT INTO tickets.ticket_record (record_id, user_id, site_id, plan_id, seat_type, seat_number, price, create_time, is_valid, credit_add) VALUES (58, 1, '1', 1, 'A', 'A02', 880, '2018-03-28 12:19:06', 1, 751);
INSERT INTO tickets.ticket_record (record_id, user_id, site_id, plan_id, seat_type, seat_number, price, create_time, is_valid, credit_add) VALUES (59, 1, '1', 1, 'A', 'A06', 880, '2018-03-28 12:19:06', 1, 751);
INSERT INTO tickets.ticket_record (record_id, user_id, site_id, plan_id, seat_type, seat_number, price, create_time, is_valid, credit_add) VALUES (60, 1, '1', 1, 'A', 'A08', 880, '2018-03-28 12:19:06', 1, 751);
INSERT INTO tickets.ticket_record (record_id, user_id, site_id, plan_id, seat_type, seat_number, price, create_time, is_valid, credit_add) VALUES (61, 1, '1', 1, 'A', 'A09', 880, '2018-03-28 12:19:06', 1, 751);
INSERT INTO tickets.ticket_record (record_id, user_id, site_id, plan_id, seat_type, seat_number, price, create_time, is_valid, credit_add) VALUES (62, 1, '1', 1, 'B', 'B01', 555, '2018-03-28 12:19:06', 1, 751);
INSERT INTO tickets.ticket_record (record_id, user_id, site_id, plan_id, seat_type, seat_number, price, create_time, is_valid, credit_add) VALUES (63, 1, '1', 1, 'B', 'B02', 555, '2018-03-28 12:19:06', 1, 751);
INSERT INTO tickets.ticket_record (record_id, user_id, site_id, plan_id, seat_type, seat_number, price, create_time, is_valid, credit_add) VALUES (64, 1, '1', 1, 'C', 'C01', 250, '2018-03-28 12:19:06', 1, 751);
INSERT INTO tickets.ticket_record (record_id, user_id, site_id, plan_id, seat_type, seat_number, price, create_time, is_valid, credit_add) VALUES (65, 1, '1', 1, 'C', 'C02', 250, '2018-03-28 12:19:06', 1, 751);
INSERT INTO tickets.ticket_record (record_id, user_id, site_id, plan_id, seat_type, seat_number, price, create_time, is_valid, credit_add) VALUES (66, 1, '1', 1, 'C', 'C08', 250, '2018-03-28 12:19:06', 1, 751);
INSERT INTO tickets.ticket_record (record_id, user_id, site_id, plan_id, seat_type, seat_number, price, create_time, is_valid, credit_add) VALUES (67, 1, '1', 1, 'C', 'C09', 250, '2018-03-28 12:19:06', 1, 751);
INSERT INTO tickets.ticket_record (record_id, user_id, site_id, plan_id, seat_type, seat_number, price, create_time, is_valid, credit_add) VALUES (68, 1, '1', 1, 'C', 'C13', 250, '2018-03-28 12:19:06', 1, 751);
INSERT INTO tickets.ticket_record (record_id, user_id, site_id, plan_id, seat_type, seat_number, price, create_time, is_valid, credit_add) VALUES (69, 1, '1', 1, 'C', 'C16', 250, '2018-03-28 12:19:06', 1, 751);
INSERT INTO tickets.ticket_record (record_id, user_id, site_id, plan_id, seat_type, seat_number, price, create_time, is_valid, credit_add) VALUES (70, 1, '1', 1, 'C', 'C18', 250, '2018-03-28 12:19:06', 1, 751);
INSERT INTO tickets.ticket_record (record_id, user_id, site_id, plan_id, seat_type, seat_number, price, create_time, is_valid, credit_add) VALUES (71, 1, '1', 1, 'C', 'C19', 250, '2018-03-28 12:19:06', 1, 751);