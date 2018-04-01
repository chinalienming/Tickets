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
INSERT INTO tickets.edit_apply (ea_id, site_id, new_name, new_add, state, num_a, num_b, num_c) VALUES (226, 224, '新的场馆', '厦门', 1, 20, 20, 20);
INSERT INTO tickets.edit_apply (ea_id, site_id, new_name, new_add, state, num_a, num_b, num_c) VALUES (227, 224, '新的场馆', '厦门', 1, 20, 20, 20);
INSERT INTO tickets.edit_apply (ea_id, site_id, new_name, new_add, state, num_a, num_b, num_c) VALUES (307, 306, '德基广场', '南京市玄武区中山路18号', 1, 20, 20, 20);
INSERT INTO tickets.edit_apply (ea_id, site_id, new_name, new_add, state, num_a, num_b, num_c) VALUES (514, 513, '新街口', '南京市中心区域', 1, 20, 20, 20);