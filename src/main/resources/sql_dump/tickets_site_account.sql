CREATE TABLE tickets.site_account
(
    site_id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name varchar(50) NOT NULL,
    password varchar(20) NOT NULL,
    active tinyint(1) DEFAULT '0' NOT NULL
);
INSERT INTO tickets.site_account (site_id, name, password, active) VALUES (1, '仙林中心', '123', 1);
INSERT INTO tickets.site_account (site_id, name, password, active) VALUES (2, '金鹰广场', '123', 1);
INSERT INTO tickets.site_account (site_id, name, password, active) VALUES (3, '钥匙球馆', '123', 1);
INSERT INTO tickets.site_account (site_id, name, password, active) VALUES (4, '上海徐家汇', '123', 1);
INSERT INTO tickets.site_account (site_id, name, password, active) VALUES (5, '东京奥体', '123', 1);
INSERT INTO tickets.site_account (site_id, name, password, active) VALUES (92, '南京奥体', '123', 1);
INSERT INTO tickets.site_account (site_id, name, password, active) VALUES (94, '北京奥体', '123', 1);
INSERT INTO tickets.site_account (site_id, name, password, active) VALUES (96, '西京奥体', '123', 1);
INSERT INTO tickets.site_account (site_id, name, password, active) VALUES (224, '德基广场', '123', 1);
INSERT INTO tickets.site_account (site_id, name, password, active) VALUES (306, '戴尔', '123', 1);
INSERT INTO tickets.site_account (site_id, name, password, active) VALUES (513, '新街口', '123', 1);