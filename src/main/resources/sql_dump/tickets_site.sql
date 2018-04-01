CREATE TABLE tickets.site
(
    site_id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    site_name varchar(30) DEFAULT 'name' NOT NULL,
    address varchar(500) DEFAULT 'Nanjing' NOT NULL,
    num_a int(11) DEFAULT '10' NOT NULL,
    num_b int(11) DEFAULT '10' NOT NULL,
    num_c int(11) DEFAULT '10' NOT NULL,
    income double DEFAULT '0' NOT NULL,
    picUrl varchar(50)
);
INSERT INTO tickets.site (site_id, site_name, address, num_a, num_b, num_c, income, picUrl) VALUES (1, '仙林中心', 'Shanghai', 20, 20, 20, 1105357.6, '/img/site/site4.jpg');
INSERT INTO tickets.site (site_id, site_name, address, num_a, num_b, num_c, income, picUrl) VALUES (2, '金鹰广场', '1-3277-7 Iojimamachi ,Nagasaki,Japan', 20, 20, 20, 0, '/img/site/site8.jpg');
INSERT INTO tickets.site (site_id, site_name, address, num_a, num_b, num_c, income, picUrl) VALUES (3, '钥匙球馆', '10-30 Takanawa 4-chome, Minato-ku ,Shinagawa,Tokyo,Japan', 20, 20, 20, 0, '/img/site/site9.jpg');
INSERT INTO tickets.site (site_id, site_name, address, num_a, num_b, num_c, income, picUrl) VALUES (4, '上海徐家汇', 'Avenida Da Nave Desportiva,Cotai ,Macau ,Taipa,Macau,China', 20, 20, 20, 0, '/img/site/site3.jpg');
INSERT INTO tickets.site (site_id, site_name, address, num_a, num_b, num_c, income, picUrl) VALUES (5, '东京奥体', '东京市建邺区江东中路222号', 20, 20, 20, 0, '/img/site/site6.jpg');
INSERT INTO tickets.site (site_id, site_name, address, num_a, num_b, num_c, income, picUrl) VALUES (92, '南京奥体', '南京市建邺区江东中路222号', 0, 0, 0, 0, '/img/site/big3.jpg');
INSERT INTO tickets.site (site_id, site_name, address, num_a, num_b, num_c, income, picUrl) VALUES (94, '北京奥体', '北京市建邺区江东中路222号', 0, 0, 0, 0, '/img/site/big3.jpg');
INSERT INTO tickets.site (site_id, site_name, address, num_a, num_b, num_c, income, picUrl) VALUES (96, '西京奥体', '西京市建邺区江东中路222号', 0, 0, 0, 0, '/img/site/big3.jpg');
INSERT INTO tickets.site (site_id, site_name, address, num_a, num_b, num_c, income, picUrl) VALUES (224, '德吉广场', '厦门', 20, 20, 20, 0, '/img/site/big3.jpg');
INSERT INTO tickets.site (site_id, site_name, address, num_a, num_b, num_c, income, picUrl) VALUES (306, '戴尔', '南京市玄武区中山路18号', 20, 20, 20, 0, '/img/site/big3.jpg');
INSERT INTO tickets.site (site_id, site_name, address, num_a, num_b, num_c, income, picUrl) VALUES (513, '新街口', '南京市中心区域', 20, 20, 20, 0, '/img/site/big3.jpg');