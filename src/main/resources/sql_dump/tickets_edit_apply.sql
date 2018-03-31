CREATE TABLE tickets.edit_apply
(
    ea_id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    site_id int(11) NOT NULL,
    new_name varchar(30) NOT NULL,
    new_add varchar(200) NOT NULL,
    state int(11) DEFAULT '0' NOT NULL,
    num_a int(11) NOT NULL,
    num_b int(11) NOT NULL,
    num_c int(11)
);
INSERT INTO tickets.edit_apply (ea_id, site_id, new_name, new_add, state, num_a, num_b, num_c) VALUES (97, 1, 'ooooo', 'Shanghai', 1, 20, 20, 20);