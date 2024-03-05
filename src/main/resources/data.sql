
INSERT INTO category (id, name, created_At, updated_At) VALUES
    (1, 'Exemplo1', '2022-01-31T12:00:00Z', '2022-01-31T14:30:00Z'),
    (2, 'Exemplo2', '2022-01-31T13:15:00Z', '2022-01-31T15:45:00Z'),
    (3, 'Exemplo3', '2022-01-31T14:30:00Z', '2022-01-31T16:00:00Z');


INSERT INTO product (id, name, description, price, img_Url) VALUES
    (1, 'Laptop', 'Powerful laptop for work and gaming', 1299.99, 'https://example.com/laptop.jpg'),
    (2, 'T-shirt', 'Comfortable cotton T-shirt', 19.99, 'https://example.com/tshirt.jpg');

-- Inserção de dados na tabela de associação tb_product_category
INSERT INTO tb_product_category (product_id, category_id) VALUES
    (1, 1), -- Laptop belongs to Electronics category
    (2, 2); -- T-shirt belongs to Clothing category


-- Inserting data into the 'user' table
INSERT INTO users (id, name, email, password)
VALUES
(1, 'John', 'john@example.com', '$2a$10$Z6RApKzQ1Mb5CVFECacdFuiAwGazxYJufj6RU1aYrKP2My48V9Yqa'),
(2, 'Jane', 'jane@example.com', '$2a$10$Z6RApKzQ1Mb5CVFECacdFuiAwGazxYJufj6RU1aYrKP2My48V9Yqa');

-- Inserting data into the 'role' table
INSERT INTO role (id, authority)
VALUES
(1, 'ROLE_USER'),
(2, 'ROLE_ADMIN');

-- Inserting data into the 'users_role' table to establish the many-to-many relationship
INSERT INTO users_role (users_id, role_id)
VALUES
(1, 1), -- John Doe has the 'ROLE_USER' role
(2, 1), -- Jane Smith has the 'ROLE_USER' role
(2, 2); -- Jane Smith also has the 'ROLE_ADMIN' role

INSERT INTO form_payment (description) VALUES
('Credit Card'),
('PayPal'),
('Bank Transfer'),
('Cash'),
('PIX');
-- Add more payment methods as needed
;
INSERT INTO item_order (id, quantity, unity_price, total_price)
VALUES
(1, 2, 10.00, 20.00),
(2, 3, 15.50, 46.50);
-- Add more records as needed
