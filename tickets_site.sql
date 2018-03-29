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
INSERT INTO tickets.site (site_id, site_name, address, num_a, num_b, num_c, income, picUrl) VALUES (1, 'Rogers', '2-2-1 Nishi-Shinjuku, Shinjuku-ku ,Shinjuku/Nakano,Tokyo,Japan', 20, 20, 20, 0, '/img/site/site4.jpg');
INSERT INTO tickets.site (site_id, site_name, address, num_a, num_b, num_c, income, picUrl) VALUES (2, 'Bird Nest', '1-3277-7 Iojimamachi ,Nagasaki,Japan', 20, 20, 20, 0, '/img/site/site8.jpg');
INSERT INTO tickets.site (site_id, site_name, address, num_a, num_b, num_c, income, picUrl) VALUES (3, 'CN Tower', '10-30 Takanawa 4-chome, Minato-ku ,Shinagawa,Tokyo,Japan', 20, 20, 20, 0, '/img/site/site9.jpg');
INSERT INTO tickets.site (site_id, site_name, address, num_a, num_b, num_c, income, picUrl) VALUES (4, 'Water', 'Avenida Da Nave Desportiva,Cotai ,Macau ,Taipa,Macau,China', 20, 20, 20, 0, '/img/site/site3.jpg');
INSERT INTO tickets.site (site_id, site_name, address, num_a, num_b, num_c, income, picUrl) VALUES (5, 'Sun', 'Estrada do Istmo, Cotai Macau ,Cotai,Macau,853,China', 20, 20, 20, 0, '/img/site/site6.jpg');