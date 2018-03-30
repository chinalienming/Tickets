CREATE TABLE tickets.manager
(
    manager_id int(11) PRIMARY KEY NOT NULL,
    manager_name varchar(20) NOT NULL
);
INSERT INTO tickets.manager (manager_id, manager_name) VALUES (1, 'admin');