CREATE TABLE tickets.site_tr
(
    record_id int(11) PRIMARY KEY NOT NULL,
    create_time datetime NOT NULL,
    credit_add int(11) NOT NULL,
    is_valid int(11) NOT NULL,
    pay_type int(11) NOT NULL,
    plan_id int(11) NOT NULL,
    price double NOT NULL,
    seat_number varchar(255),
    seat_type char(1) NOT NULL,
    site_id int(11) NOT NULL,
    user_id int(11) NOT NULL
) DEFAULT CHARACTER SET utf8 ;