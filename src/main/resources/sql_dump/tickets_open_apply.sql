CREATE TABLE tickets.open_apply
(
    oa_id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    site_id int(11) NOT NULL,
    reason varchar(200) NOT NULL,
    state int(11) DEFAULT '0' NOT NULL
);
INSERT INTO tickets.open_apply (oa_id, site_id, reason, state) VALUES (98, 1, 'no problem!', 1);