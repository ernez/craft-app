select * from users;
--
-- INSERT INTO users (id, address, city, postcode, email, enabled, first_name, last_name, password, telephone, status, created_date)
-- VALUES (1, 'address 1', 'Rajvosa', '71000', 'ernezcatovic@gmail.com', true, 'Ernez', 'Catovic', '', '062226831', 'active', CURRENT_TIMESTAMP);
--
INSERT INTO roles (name) VALUES ('ROLE_ADMIN');

INSERT INTO roles (name) VALUES ('ROLE_CUSTOMER');

INSERT INTO roles (name) VALUES ('ROLE_REPAIRER')
--
--
