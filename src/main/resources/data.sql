-- select * from users;
-- --
-- INSERT INTO users (id, address, city, postcode, email, enabled, first_name, last_name, password, telephone, status, created_date)
-- VALUES (1, 'address 1', 'Rajvosa', '71000', 'ernezcatovic@gmail.com', true, 'Ernez', 'Catovic', '', '062226831', 'active', CURRENT_TIMESTAMP);
-- --
INSERT INTO roles (id, name, created_date) VALUES (1, 'ROLE_ADMIN', CURRENT_TIMESTAMP);
--
INSERT INTO roles (id, name, created_date) VALUES (2, 'ROLE_CUSTOMER', CURRENT_TIMESTAMP);
--
INSERT INTO roles (id, name, created_date) VALUES (3, 'ROLE_REPAIRER', CURRENT_TIMESTAMP)
-- --
-- --
