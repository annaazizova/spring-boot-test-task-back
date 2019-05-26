INSERT INTO products (id, name, brand, price, quantity) VALUES (0, 'ProductName0', 'ProductBrand0', 0, 0);
INSERT INTO products (id, name, brand, price, quantity) VALUES (1, 'ProductName1', 'ProductBrand1', 1, 1);
INSERT INTO products (id, name, brand, price, quantity) VALUES (2, 'ProductName2', 'ProductBrand2', 2, 2);
INSERT INTO products (id, name, brand, price, quantity) VALUES (3, 'ProductName3', 'ProductBrand3', 3, 3);
INSERT INTO products (id, name, brand, price, quantity) VALUES (4, 'ProductName4', 'ProductBrand4', 4, 4);

INSERT INTO USERS (ID, USERNAME, PASSWORD) VALUES (
  1, 'email1@gmail.com','$2a$10$9hdvt4tXBIpnuo.gNnBYF.vLzyzAfOhLZSjj7xlkc3y0x/u1xj0wu');

INSERT INTO USERS (ID, USERNAME, PASSWORD) VALUES (
  2, 'email2@gmail.com','$2a$10$9hdvt4tXBIpnuo.gNnBYF.vLzyzAfOhLZSjj7xlkc3y0x/u1xj0wu');

INSERT INTO ROLES (ID, NAME) VALUES (1, 'ROLE_USER');
INSERT INTO ROLES (ID, NAME) VALUES (2, 'ROLE_ADMIN');

INSERT INTO PRIVILEGES (ID, NAME) VALUES (1, 'READ_PRIVILEGE');
INSERT INTO PRIVILEGES (ID, NAME) VALUES (2, 'WRITE_PRIVILEGE');