CREATE TABLE tickets.seat
(
    seat_id int(11) PRIMARY KEY NOT NULL,
    plan_id int(11) NOT NULL,
    seat_number varchar(5) NOT NULL,
    state int(11) DEFAULT '0' NOT NULL,
    user_id int(11) NOT NULL,
    is_checked tinyint(1) DEFAULT '0' NOT NULL
);
INSERT INTO tickets.seat (seat_id, plan_id, seat_number, state, user_id, is_checked) VALUES (1, 1, 'A01', 2, 1, 0);
INSERT INTO tickets.seat (seat_id, plan_id, seat_number, state, user_id, is_checked) VALUES (2, 1, 'A02', 2, 1, 0);
INSERT INTO tickets.seat (seat_id, plan_id, seat_number, state, user_id, is_checked) VALUES (3, 1, 'A03', 0, -1, 0);
INSERT INTO tickets.seat (seat_id, plan_id, seat_number, state, user_id, is_checked) VALUES (4, 1, 'A04', 0, -1, 0);
INSERT INTO tickets.seat (seat_id, plan_id, seat_number, state, user_id, is_checked) VALUES (5, 1, 'A05', 0, -1, 0);
INSERT INTO tickets.seat (seat_id, plan_id, seat_number, state, user_id, is_checked) VALUES (6, 1, 'A06', 2, 1, 0);
INSERT INTO tickets.seat (seat_id, plan_id, seat_number, state, user_id, is_checked) VALUES (7, 1, 'A07', 2, 1, 0);
INSERT INTO tickets.seat (seat_id, plan_id, seat_number, state, user_id, is_checked) VALUES (8, 1, 'A08', 2, 1, 0);
INSERT INTO tickets.seat (seat_id, plan_id, seat_number, state, user_id, is_checked) VALUES (9, 1, 'A09', 2, 1, 0);
INSERT INTO tickets.seat (seat_id, plan_id, seat_number, state, user_id, is_checked) VALUES (10, 1, 'A10', 0, -1, 0);
INSERT INTO tickets.seat (seat_id, plan_id, seat_number, state, user_id, is_checked) VALUES (11, 1, 'B01', 2, 1, 0);
INSERT INTO tickets.seat (seat_id, plan_id, seat_number, state, user_id, is_checked) VALUES (12, 1, 'B02', 2, 1, 0);
INSERT INTO tickets.seat (seat_id, plan_id, seat_number, state, user_id, is_checked) VALUES (13, 1, 'B03', 0, -1, 0);
INSERT INTO tickets.seat (seat_id, plan_id, seat_number, state, user_id, is_checked) VALUES (14, 1, 'B04', 0, -1, 0);
INSERT INTO tickets.seat (seat_id, plan_id, seat_number, state, user_id, is_checked) VALUES (15, 1, 'B05', 2, 1, 0);
INSERT INTO tickets.seat (seat_id, plan_id, seat_number, state, user_id, is_checked) VALUES (16, 1, 'B06', 0, -1, 0);
INSERT INTO tickets.seat (seat_id, plan_id, seat_number, state, user_id, is_checked) VALUES (17, 1, 'B07', 2, 1, 0);
INSERT INTO tickets.seat (seat_id, plan_id, seat_number, state, user_id, is_checked) VALUES (18, 1, 'B08', 0, -1, 0);
INSERT INTO tickets.seat (seat_id, plan_id, seat_number, state, user_id, is_checked) VALUES (19, 1, 'B09', 0, -1, 0);
INSERT INTO tickets.seat (seat_id, plan_id, seat_number, state, user_id, is_checked) VALUES (20, 1, 'B10', 0, -1, 0);
INSERT INTO tickets.seat (seat_id, plan_id, seat_number, state, user_id, is_checked) VALUES (21, 1, 'C01', 2, 1, 0);
INSERT INTO tickets.seat (seat_id, plan_id, seat_number, state, user_id, is_checked) VALUES (22, 1, 'C02', 2, 1, 0);
INSERT INTO tickets.seat (seat_id, plan_id, seat_number, state, user_id, is_checked) VALUES (23, 1, 'C03', 2, 1, 0);
INSERT INTO tickets.seat (seat_id, plan_id, seat_number, state, user_id, is_checked) VALUES (24, 1, 'C04', 2, 1, 0);
INSERT INTO tickets.seat (seat_id, plan_id, seat_number, state, user_id, is_checked) VALUES (25, 1, 'C05', 0, -1, 0);
INSERT INTO tickets.seat (seat_id, plan_id, seat_number, state, user_id, is_checked) VALUES (26, 1, 'C06', 0, -1, 0);
INSERT INTO tickets.seat (seat_id, plan_id, seat_number, state, user_id, is_checked) VALUES (27, 1, 'C07', 2, 1, 0);
INSERT INTO tickets.seat (seat_id, plan_id, seat_number, state, user_id, is_checked) VALUES (28, 1, 'C08', 2, 1, 0);
INSERT INTO tickets.seat (seat_id, plan_id, seat_number, state, user_id, is_checked) VALUES (29, 1, 'C09', 2, 1, 0);
INSERT INTO tickets.seat (seat_id, plan_id, seat_number, state, user_id, is_checked) VALUES (30, 1, 'C10', 0, -1, 0);
INSERT INTO tickets.seat (seat_id, plan_id, seat_number, state, user_id, is_checked) VALUES (31, 1, 'A11', 0, -1, 0);
INSERT INTO tickets.seat (seat_id, plan_id, seat_number, state, user_id, is_checked) VALUES (32, 1, 'A12', 0, -1, 0);
INSERT INTO tickets.seat (seat_id, plan_id, seat_number, state, user_id, is_checked) VALUES (33, 1, 'A13', 0, -1, 0);
INSERT INTO tickets.seat (seat_id, plan_id, seat_number, state, user_id, is_checked) VALUES (34, 1, 'A14', 0, -1, 0);
INSERT INTO tickets.seat (seat_id, plan_id, seat_number, state, user_id, is_checked) VALUES (35, 1, 'A15', 0, -1, 0);
INSERT INTO tickets.seat (seat_id, plan_id, seat_number, state, user_id, is_checked) VALUES (36, 1, 'A16', 0, -1, 0);
INSERT INTO tickets.seat (seat_id, plan_id, seat_number, state, user_id, is_checked) VALUES (37, 1, 'A17', 2, 1, 0);
INSERT INTO tickets.seat (seat_id, plan_id, seat_number, state, user_id, is_checked) VALUES (38, 1, 'A18', 0, -1, 0);
INSERT INTO tickets.seat (seat_id, plan_id, seat_number, state, user_id, is_checked) VALUES (39, 1, 'A19', 0, -1, 0);
INSERT INTO tickets.seat (seat_id, plan_id, seat_number, state, user_id, is_checked) VALUES (40, 1, 'A20', 0, -1, 0);
INSERT INTO tickets.seat (seat_id, plan_id, seat_number, state, user_id, is_checked) VALUES (41, 1, 'B11', 0, -1, 0);
INSERT INTO tickets.seat (seat_id, plan_id, seat_number, state, user_id, is_checked) VALUES (42, 1, 'B12', 0, -1, 0);
INSERT INTO tickets.seat (seat_id, plan_id, seat_number, state, user_id, is_checked) VALUES (43, 1, 'B13', 0, -1, 0);
INSERT INTO tickets.seat (seat_id, plan_id, seat_number, state, user_id, is_checked) VALUES (44, 1, 'B14', 2, 1, 0);
INSERT INTO tickets.seat (seat_id, plan_id, seat_number, state, user_id, is_checked) VALUES (45, 1, 'B15', 0, -1, 0);
INSERT INTO tickets.seat (seat_id, plan_id, seat_number, state, user_id, is_checked) VALUES (46, 1, 'B16', 0, -1, 0);
INSERT INTO tickets.seat (seat_id, plan_id, seat_number, state, user_id, is_checked) VALUES (47, 1, 'B17', 2, 1, 0);
INSERT INTO tickets.seat (seat_id, plan_id, seat_number, state, user_id, is_checked) VALUES (48, 1, 'B18', 0, -1, 0);
INSERT INTO tickets.seat (seat_id, plan_id, seat_number, state, user_id, is_checked) VALUES (49, 1, 'B19', 0, -1, 0);
INSERT INTO tickets.seat (seat_id, plan_id, seat_number, state, user_id, is_checked) VALUES (50, 1, 'B20', 0, -1, 0);
INSERT INTO tickets.seat (seat_id, plan_id, seat_number, state, user_id, is_checked) VALUES (51, 1, 'C11', 0, -1, 0);
INSERT INTO tickets.seat (seat_id, plan_id, seat_number, state, user_id, is_checked) VALUES (52, 1, 'C12', 0, -1, 0);
INSERT INTO tickets.seat (seat_id, plan_id, seat_number, state, user_id, is_checked) VALUES (53, 1, 'C13', 2, 1, 0);
INSERT INTO tickets.seat (seat_id, plan_id, seat_number, state, user_id, is_checked) VALUES (54, 1, 'C14', 2, 1, 0);
INSERT INTO tickets.seat (seat_id, plan_id, seat_number, state, user_id, is_checked) VALUES (55, 1, 'C15', 2, 1, 0);
INSERT INTO tickets.seat (seat_id, plan_id, seat_number, state, user_id, is_checked) VALUES (56, 1, 'C16', 2, 1, 0);
INSERT INTO tickets.seat (seat_id, plan_id, seat_number, state, user_id, is_checked) VALUES (57, 1, 'C17', 2, 1, 0);
INSERT INTO tickets.seat (seat_id, plan_id, seat_number, state, user_id, is_checked) VALUES (58, 1, 'C18', 2, 1, 0);
INSERT INTO tickets.seat (seat_id, plan_id, seat_number, state, user_id, is_checked) VALUES (59, 1, 'C19', 2, 1, 0);
INSERT INTO tickets.seat (seat_id, plan_id, seat_number, state, user_id, is_checked) VALUES (60, 1, 'C20', 0, -1, 0);