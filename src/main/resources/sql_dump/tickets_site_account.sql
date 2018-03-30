CREATE TABLE `site_account` (
  `site_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `password` varchar(20) NOT NULL,
  `active` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`site_id`)
) DEFAULT CHARACTER SET utf8;

INSERT INTO tickets.site_account (name, password, active) VALUES ('rogers', '123', 1);
INSERT INTO tickets.site_account (name, password, active) VALUES ('happy', '123', 1);