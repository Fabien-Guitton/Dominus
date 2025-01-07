USE Dominus;

/* Populate Employees */
INSERT INTO Employees (nameEmp, codeEmp, roleEmp, telEmp, userCreate, dateCreate, userModif, dateModif)
VALUES
('Musa Ademi', '2012', 'Manager', '0667948123', 'admin', NOW(), 'admin', NOW()),
('Léonie Roux', '2005', 'Delivery', '0787654321', 'admin', NOW(), 'admin', NOW()),
('Aziz Cheleyfa', '2000', 'Cashier', '0721654761', 'admin', NOW(), 'admin', NOW()),
('Fabien Guitton', '2004', 'Delivery', '0678901234', 'admin', NOW(), 'admin', NOW()),
('Nicolas Labroche', '2025', 'Delivery', '0678901234', 'admin', NOW(), 'admin', NOW()),
('Nizar Messai', '2024', 'Delivery', '0678901234', 'admin', NOW(), 'admin', NOW()),
('Helene Brouard', '2023', 'Delivery', '0678901234', 'admin', NOW(), 'admin', NOW()),
('Thierry Brouard', '2022', 'Delivery', '0678901234', 'admin', NOW(), 'admin', NOW()),
('Julia Esarc', '2002', 'Cashier', '0678901234', 'admin', NOW(), 'admin', NOW()),
('Jeanne Pairault', '2003', 'Cashier', '0678901234', 'admin', NOW(), 'admin', NOW());

/* Populate ClockedIn */
INSERT INTO clockingin (startClockingIn, endClockingIn, idEmployee, userCreate, dateCreate, userModif, dateModif)
VALUES
(NOW(), NULL, 1, 'admin', NOW(), 'admin', NOW()),
(NOW(), NULL, 2, 'admin', NOW(), 'admin', NOW()),
(NOW(), NOW(), 4, 'admin', NOW(), 'admin', NOW());

/* Populate Customers */
INSERT INTO Customers (nameCst, telCst, streetNumberCst, streetNameCst, postcodeCst, instructionsCst, internalComCst, userCreate, dateCreate, userModif, dateModif)
VALUES
('John Doe', '0623456789', '14', 'RUE JULES FAVRE', '37000', 'Sonnez quand vous êtes là', 'Relou un peu lui', 'admin', NOW(), 'admin', NOW()),
('Jane Smith', '0787654321', '9', "AVENUE DE L'ALOUETTE", '37200', NULL, NULL, 'admin', NOW(), 'admin', NOW()),
('Alice Brown', '0678901234', '8', 'RUE CHANZY', '36000', "Appelez moi quand vous êtes là. N'oubliez pas les sauces piquantes.", NULL, 'admin', NOW(), 'admin', NOW()),
('Emma Wilson', '0612345678', '15', 'RUE DU PARC', '37100', 'Ne sonnez pas trop fort, je dors tôt.', NULL, 'admin', NOW(), 'admin', NOW()),
('Liam Johnson', '0754321987', '5', 'RUE DE LA PAIX', '37400', 'Pas de sonnette, frappez sur la porte', NULL, 'admin', NOW(), 'admin', NOW()),
('Sophia Taylor', '0687654321', '12', 'RUE DES LILAS', '37800', NULL, 'Aime bien discuter', 'admin', NOW(), 'admin', NOW()),
('Mason Davis', '0612347890', '10', 'AVENUE DE LA LIBERTÉ', '37300', 'Appeler avant d’arriver', 'Toujours en train de rigoler', 'admin', NOW(), 'admin', NOW()),
('Isabella Martinez', '0798765432', '7', 'RUE DES FONTAINES', '37500', 'Sonnez bien fort', 'Très bavarde', 'admin', NOW(), 'admin', NOW()),
('Ethan Harris', '0689234571', '11', 'RUE DU CENTRE', '37600', 'Pas de vis-à-vis', 'Solitaire mais attentionné', 'admin', NOW(), 'admin', NOW()),
('Ava Lee', '0776543210', '6', 'RUE SAINT-MARTIN', '37900', 'Prévenez-moi quand vous êtes là', NULL, 'admin', NOW(), 'admin', NOW());

/* Populate Discounts */
INSERT INTO Discounts (nameDist, valueDist, codeDist, accredDistON, userCreate, dateCreate, userModif, dateModif)
VALUES
('LUNDI FOU', 20.0, '267431', FALSE, 'admin', NOW(), 'admin', NOW()),
('MERCREDI FOU', 20.0, '158271', FALSE, 'admin', NOW(), 'admin', NOW()),
('REDUCTION EMPLOYE', 50.0, '647284', TRUE, 'admin', NOW(), 'admin', NOW()),
('REDUCTION ETUDIANT', 5.0, '920525', FALSE, 'admin', NOW(), 'admin', NOW()),
('REDUCTION PROF', 40.0, '87143', FALSE, 'admin', NOW(), 'admin', NOW());

/* Populate Ingredients */
INSERT INTO Ingredients (nameIng, stockIng, unityIng, supplementPossibleON, priceETIng, priceITIng, userCreate, dateCreate, userModif, dateModif)
VALUES
('Base Sauce Tomate', 100, 'kg', TRUE, 2.0, 2.5, 'admin', NOW(), 'admin', NOW()),
('Base Sauce Fromagère', 100, 'kg', TRUE, 2.0, 2.5, 'admin', NOW(), 'admin', NOW()),
('Base Sauce BBQ', 40, 'kg', TRUE, 2.0, 2.5, 'admin', NOW(), 'admin', NOW()),
('Base Sauce Cheddar', 40, 'kg', TRUE, 2.0, 2.5, 'admin', NOW(), 'admin', NOW()),
('Base Sauce Piquante', 40, 'kg', TRUE, 2.0, 2.5, 'admin', NOW(), 'admin', NOW()),
('Emmental', 50, 'kg', TRUE, 0.5, 1.0, 'admin', NOW(), 'admin', NOW()),
('Farine', 200, 'kg', FALSE, 1.0, 1.2, 'admin', NOW(), 'admin', NOW()),
('Olives', 30, 'kg', TRUE, 4.0, 4.8, 'admin', NOW(), 'admin', NOW()),
('Jambons', 25, 'kg', TRUE, 6.0, 7.2, 'admin', NOW(), 'admin', NOW()),
('Poulet', 25, 'kg', TRUE, 4.0, 5.2, 'admin', NOW(), 'admin', NOW()),
('Mozzarella', 60, 'kg', TRUE, 7.0, 8.0, 'admin', NOW(), 'admin', NOW()),
('Bacon', 40, 'kg', TRUE, 5.5, 6.5, 'admin', NOW(), 'admin', NOW()),
('Pesto Rouge', 40, 'kg', TRUE, 3.5, 4.0, 'admin', NOW(), 'admin', NOW()),
('Pesto Vert', 40, 'kg', TRUE, 3.5, 4.0, 'admin', NOW(), 'admin', NOW()),
('Champignon', 200, 'kg', TRUE, 1.0, 1.2, 'admin', NOW(), 'admin', NOW()),
('Ananas', 200, 'kg', TRUE, 2.5, 3.0, 'admin', NOW(), 'admin', NOW()),
('Miel', 200, 'kg', TRUE, 2.5, 3.0, 'admin', NOW(), 'admin', NOW()),
('Beurre', 200, 'kg', FALSE, 1.0, 1.2, 'admin', NOW(), 'admin', NOW()),
('Oeuf', 200, 'kg', FALSE, 1.0, 1.2, 'admin', NOW(), 'admin', NOW()),
('Sel', 200, 'kg', FALSE, 1.0, 1.2, 'admin', NOW(), 'admin', NOW()),
('Mergez', 200, 'kg', TRUE, 1.0, 1.2, 'admin', NOW(), 'admin', NOW()),
('Oignons', 200, 'kg', TRUE, 1.0, 1.2, 'admin', NOW(), 'admin', NOW()),
('Oignons Frits Croustillants', 200, 'kg', TRUE, 1.0, 1.2, 'admin', NOW(), 'admin', NOW()),
('Origan', 200, 'kg', TRUE, 1.0, 1.2, 'admin', NOW(), 'admin', NOW()),
('Pepperoni', 200, 'kg', TRUE, 1.0, 1.2, 'admin', NOW(), 'admin', NOW()),
('Kebab', 200, 'kg', TRUE, 1.0, 1.2, 'admin', NOW(), 'admin', NOW()),
('Salade', 200, 'kg', TRUE, 1.0, 1.2, 'admin', NOW(), 'admin', NOW()),
('Raclette', 200, 'kg', TRUE, 1.0, 1.2, 'admin', NOW(), 'admin', NOW()),
('Poivron', 200, 'kg', TRUE, 1.0, 1.2, 'admin', NOW(), 'admin', NOW()),
('Lardon', 200, 'kg', TRUE, 1.0, 1.2, 'admin', NOW(), 'admin', NOW()),
('Pomme de terre', 200, 'kg', TRUE, 1.0, 1.2, 'admin', NOW(), 'admin', NOW()),
('Thon', 200, 'kg', TRUE, 1.0, 1.2, 'admin', NOW(), 'admin', NOW()),
('Anchois', 200, 'kg', TRUE, 1.0, 1.2, 'admin', NOW(), 'admin', NOW()),
('Chèvre', 200, 'kg', TRUE, 2.5, 3.0, 'admin', NOW(), 'admin', NOW()),

('Sauce BBQ', 200, 'kg', TRUE, 1.0, 1.2, 'admin', NOW(), 'admin', NOW()),
('Sauce Tomate', 200, 'kg', TRUE, 1.0, 1.2, 'admin', NOW(), 'admin', NOW()),
('Sauce Mayonnaise', 200, 'kg', TRUE, 1.0, 1.2, 'admin', NOW(), 'admin', NOW()),
('Sauce Ketchup', 200, 'kg', TRUE, 1.0, 1.2, 'admin', NOW(), 'admin', NOW()),

('Coca Cola', 100, '33cl', TRUE, 1.0, 1.5, 'admin', NOW(), 'admin', NOW()),
('Coca Cola', 1, '1,5L', TRUE, 3.0, 3.5, 'admin', NOW(), 'admin', NOW()),
('Coca Cola', 1, '1,25L', TRUE, 3.0, 3.5, 'admin', NOW(), 'admin', NOW()),
('Coca Cola Cherry', 100, '33cl', TRUE, 1.0, 1.5, 'admin', NOW(), 'admin', NOW()),
('Coca Cola Cherry', 1, '1,5L', TRUE, 3.0, 3.5, 'admin', NOW(), 'admin', NOW()),
('Coca Cola Cherry', 1, '1,25L', TRUE, 3.0, 3.5, 'admin', NOW(), 'admin', NOW()),
('Orangina', 100, '33cl', TRUE, 1.0, 1.5, 'admin', NOW(), 'admin', NOW()),
('Orangina', 1, '1,5L', TRUE, 3.0, 3.5, 'admin', NOW(), 'admin', NOW()),
('Orangina', 1, '1,25L', TRUE, 3.0, 3.5, 'admin', NOW(), 'admin', NOW()),
('7up', 100, '33cl', TRUE, 1.0, 1.5, 'admin', NOW(), 'admin', NOW()),
('7up', 1, '1,5L', TRUE, 3.0, 3.5, 'admin', NOW(), 'admin', NOW()),
('7up', 1, '1,25L', TRUE, 3.0, 3.5, 'admin', NOW(), 'admin', NOW()),
('Fuze Tea', 100, '33cl', TRUE, 1.0, 1.5, 'admin', NOW(), 'admin', NOW());

/* Populate Products */
INSERT INTO Products (nameProduct, sizeProduct, categoryProduct, priceETProduct, priceITProduct, userCreate, dateCreate, userModif, dateModif)
VALUES
('Margherita Pizza', 'Medium', 'Pizzas', 8.0, 9.6, 'admin', NOW(), 'admin', NOW()),
('Margherita Pizza', 'Large', 'Pizzas', 10.0, 11.6, 'admin', NOW(), 'admin', NOW()),
('Margherita Pizza', 'Extra Large', 'Pizzas', 12.0, 13.6, 'admin', NOW(), 'admin', NOW()),
('Jambon Pizza', 'Medium', 'Pizzas', 8.0, 9.6, 'admin', NOW(), 'admin', NOW()),
('Jambon Pizza', 'Large', 'Pizzas', 10.0, 11.6, 'admin', NOW(), 'admin', NOW()),
('Jambon Pizza', 'Extra Large', 'Pizzas', 12.0, 13.6, 'admin', NOW(), 'admin', NOW()),
('Cannibale Pizza', 'Medium', 'Pizzas', 8.0, 9.6, 'admin', NOW(), 'admin', NOW()),
('Cannibale Pizza', 'Large', 'Pizzas', 10.0, 11.6, 'admin', NOW(), 'admin', NOW()),
('Cannibale Pizza', 'Extra Large', 'Pizzas', 12.0, 13.6, 'admin', NOW(), 'admin', NOW()),
("Chick'n Raclette", 'Medium', 'Pizzas', 8.0, 9.6, 'admin', NOW(), 'admin', NOW()),
("Chick'n Raclette", 'Large', 'Pizzas', 10.0, 11.6, 'admin', NOW(), 'admin', NOW()),
("Chick'n Raclette", 'Extra Large', 'Pizzas', 12.0, 13.6, 'admin', NOW(), 'admin', NOW()),
('Authentique Raclette', 'Medium', 'Pizzas', 8.0, 9.6, 'admin', NOW(), 'admin', NOW()),
('Authentique Raclette', 'Large', 'Pizzas', 10.0, 11.6, 'admin', NOW(), 'admin', NOW()),
('Authentique Raclette', 'Extra Large', 'Pizzas', 12.0, 13.6, 'admin', NOW(), 'admin', NOW()),
('Savoyarde', 'Medium', 'Pizzas', 8.0, 9.6, 'admin', NOW(), 'admin', NOW()),
('Savoyarde', 'Large', 'Pizzas', 10.0, 11.6, 'admin', NOW(), 'admin', NOW()),
('Savoyarde', 'Extra Large', 'Pizzas', 12.0, 13.6, 'admin', NOW(), 'admin', NOW()),
('Chèvre Miel', 'Medium', 'Pizzas', 8.0, 9.6, 'admin', NOW(), 'admin', NOW()),
('Chèvre Miel', 'Large', 'Pizzas', 10.0, 11.6, 'admin', NOW(), 'admin', NOW()),
('Chèvre Miel', 'Extra Large', 'Pizzas', 12.0, 13.6, 'admin', NOW(), 'admin', NOW()),
('Hawaïenne', 'Medium', 'Pizzas', 8.0, 9.6, 'admin', NOW(), 'admin', NOW()),
('Hawaïenne', 'Large', 'Pizzas', 10.0, 11.6, 'admin', NOW(), 'admin', NOW()),
('Hawaïenne', 'Extra Large', 'Pizzas', 12.0, 13.6, 'admin', NOW(), 'admin', NOW()),
('Orientale', 'Medium', 'Pizzas', 8.0, 9.6, 'admin', NOW(), 'admin', NOW()),
('Orientale', 'Large', 'Pizzas', 10.0, 11.6, 'admin', NOW(), 'admin', NOW()),
('Orientale', 'Extra Large', 'Pizzas', 12.0, 13.6, 'admin', NOW(), 'admin', NOW()),
('Reine', 'Medium', 'Pizzas', 8.0, 9.6, 'admin', NOW(), 'admin', NOW()),
('Reine', 'Large', 'Pizzas', 10.0, 11.6, 'admin', NOW(), 'admin', NOW()),
('Reine', 'Extra Large', 'Pizzas', 12.0, 13.6, 'admin', NOW(), 'admin', NOW()),
('Campagnarde', 'Medium', 'Pizzas', 8.0, 9.6, 'admin', NOW(), 'admin', NOW()),
('Campagnarde', 'Large', 'Pizzas', 10.0, 11.6, 'admin', NOW(), 'admin', NOW()),
('Campagnarde', 'Extra Large', 'Pizzas', 12.0, 13.6, 'admin', NOW(), 'admin', NOW()),
('Bacon Groovy', 'Medium', 'Pizzas', 8.0, 9.6, 'admin', NOW(), 'admin', NOW()),
('Bacon Groovy', 'Large', 'Pizzas', 10.0, 11.6, 'admin', NOW(), 'admin', NOW()),
('Bacon Groovy', 'Extra Large', 'Pizzas', 12.0, 13.6, 'admin', NOW(), 'admin', NOW()),
('Tasty Kebab', 'Medium', 'Pizzas', 8.0, 9.6, 'admin', NOW(), 'admin', NOW()),
('Tasty Kebab', 'Large', 'Pizzas', 10.0, 11.6, 'admin', NOW(), 'admin', NOW()),
('Tasty Kebab', 'Extra Large', 'Pizzas', 12.0, 13.6, 'admin', NOW(), 'admin', NOW()),
('Oceana', 'Medium', 'Pizzas', 8.0, 9.6, 'admin', NOW(), 'admin', NOW()),
('Oceana', 'Large', 'Pizzas', 10.0, 11.6, 'admin', NOW(), 'admin', NOW()),
('Oceana', 'Extra Large', 'Pizzas', 12.0, 13.6, 'admin', NOW(), 'admin', NOW()),

('Coca Cola', '1,5L', 'Boissons', 3.0, 3.5, 'admin', NOW(), 'admin', NOW()),
('Coca Cola', '1,25L', 'Boissons', 3.0, 3.5, 'admin', NOW(), 'admin', NOW()),
('Coca Cola', '33cl', 'Boissons', 1.0, 1.5, 'admin', NOW(), 'admin', NOW()),
('Coca Cola Cherry', '1,5L', 'Boissons', 3.0, 3.5, 'admin', NOW(), 'admin', NOW()),
('Coca Cola Cherry', '1,25L', 'Boissons', 3.0, 3.5, 'admin', NOW(), 'admin', NOW()),
('Coca Cola Cherry', '33cl', 'Boissons', 1.0, 1.5, 'admin', NOW(), 'admin', NOW()),
('Orangina', '1,5L', 'Boissons', 3.0, 3.5, 'admin', NOW(), 'admin', NOW()),
('Orangina', '1,25L', 'Boissons', 3.0, 3.5, 'admin', NOW(), 'admin', NOW()),
('Orangina', '33cl', 'Boissons', 1.0, 1.5, 'admin', NOW(), 'admin', NOW()),
('7up', '1,5L', 'Boissons', 3.0, 3.5, 'admin', NOW(), 'admin', NOW()),
('7up', '1,25L', 'Boissons', 3.0, 3.5, 'admin', NOW(), 'admin', NOW()),
('7up', '33cl', 'Boissons', 1.0, 1.5, 'admin', NOW(), 'admin', NOW()),
('Fuze Tea', '33cl', 'Boissons', 1.0, 1.5, 'admin', NOW(), 'admin', NOW());

/* Populate DefaultIngredients */
INSERT INTO DefaultIngredients (idIngredient, idProduct, userCreate, dateCreate, userModif, dateModif)
VALUES
(1, 1, 'admin', NOW(), 'admin', NOW()), /* Pizza margharita : Base sauce tomate (1), Emmental (6), Farine (7) */
(6, 1, 'admin', NOW(), 'admin', NOW()),
(7, 1, 'admin', NOW(), 'admin', NOW()),
(1, 2, 'admin', NOW(), 'admin', NOW()),
(6, 2, 'admin', NOW(), 'admin', NOW()),
(7, 2, 'admin', NOW(), 'admin', NOW()),
(1, 3, 'admin', NOW(), 'admin', NOW()),
(6, 3, 'admin', NOW(), 'admin', NOW()),
(7, 3, 'admin', NOW(), 'admin', NOW()),

(1, 4, 'admin', NOW(), 'admin', NOW()), /* Pizza jambon : Base sauce tomate (1), Emmental (6), Jambon (9), Farine (7) */
(6, 4, 'admin', NOW(), 'admin', NOW()),
(9, 4, 'admin', NOW(), 'admin', NOW()),
(7, 4, 'admin', NOW(), 'admin', NOW()),
(1, 5, 'admin', NOW(), 'admin', NOW()), 
(6, 5, 'admin', NOW(), 'admin', NOW()),
(9, 5, 'admin', NOW(), 'admin', NOW()),
(7, 5, 'admin', NOW(), 'admin', NOW()),
(1, 6, 'admin', NOW(), 'admin', NOW()), 
(6, 6, 'admin', NOW(), 'admin', NOW()),
(9, 6, 'admin', NOW(), 'admin', NOW()),
(7, 6, 'admin', NOW(), 'admin', NOW()),

(1, 7, 'admin', NOW(), 'admin', NOW()), /* Pizza Canibale : Base sauce tomate (1), Emmental (6), Poulet (10), Sauce BBQ (35), Kebab(26), Farine (7) */
(6, 7, 'admin', NOW(), 'admin', NOW()),
(10, 7, 'admin', NOW(), 'admin', NOW()),
(35, 7, 'admin', NOW(), 'admin', NOW()),
(26, 7, 'admin', NOW(), 'admin', NOW()),
(7, 7, 'admin', NOW(), 'admin', NOW()),
(1, 8, 'admin', NOW(), 'admin', NOW()),
(6, 8, 'admin', NOW(), 'admin', NOW()),
(10, 8, 'admin', NOW(), 'admin', NOW()),
(35, 8, 'admin', NOW(), 'admin', NOW()),
(26, 8, 'admin', NOW(), 'admin', NOW()),
(7, 8, 'admin', NOW(), 'admin', NOW()),
(1, 9, 'admin', NOW(), 'admin', NOW()),
(6, 9, 'admin', NOW(), 'admin', NOW()),
(10, 9, 'admin', NOW(), 'admin', NOW()),
(35, 9, 'admin', NOW(), 'admin', NOW()),
(26, 9, 'admin', NOW(), 'admin', NOW()),
(7, 9, 'admin', NOW(), 'admin', NOW()),

(2, 10, 'admin', NOW(), 'admin', NOW()), /* Chick'n Raclette : Base Sauce Fromagère(2), Mozzarella(11), Poulet(10), Raclette(28), Champignon(15), Oignons Frits Croustillants(23), Farine (7) */
(11, 10, 'admin', NOW(), 'admin', NOW()),
(10, 10, 'admin', NOW(), 'admin', NOW()),
(28, 10, 'admin', NOW(), 'admin', NOW()),
(15, 10, 'admin', NOW(), 'admin', NOW()),
(23, 10, 'admin', NOW(), 'admin', NOW()),
(7, 10, 'admin', NOW(), 'admin', NOW()),
(2, 11, 'admin', NOW(), 'admin', NOW()),
(11, 11, 'admin', NOW(), 'admin', NOW()),
(10, 11, 'admin', NOW(), 'admin', NOW()),
(28, 10, 'admin', NOW(), 'admin', NOW()),
(15, 10, 'admin', NOW(), 'admin', NOW()),
(23, 11, 'admin', NOW(), 'admin', NOW()),
(7, 11, 'admin', NOW(), 'admin', NOW()),
(2, 12, 'admin', NOW(), 'admin', NOW()),
(11, 12, 'admin', NOW(), 'admin', NOW()),
(10, 12, 'admin', NOW(), 'admin', NOW()),
(28, 12, 'admin', NOW(), 'admin', NOW()),
(15, 12, 'admin', NOW(), 'admin', NOW()),
(23, 12, 'admin', NOW(), 'admin', NOW()),
(7, 12, 'admin', NOW(), 'admin', NOW()),

(2, 13, 'admin', NOW(), 'admin', NOW()), /* Authentique Raclette  :  Base Sauce Fromagère(2), Mozzarella(11), Raclette(28), Oignons(22), Pomme de terre(31), Bacon(12), Farine (7) */
(11, 13, 'admin', NOW(), 'admin', NOW()),
(28, 13, 'admin', NOW(), 'admin', NOW()),
(22, 13, 'admin', NOW(), 'admin', NOW()),
(31, 13, 'admin', NOW(), 'admin', NOW()),
(12, 13, 'admin', NOW(), 'admin', NOW()),
(7, 13, 'admin', NOW(), 'admin', NOW()),
(2, 14, 'admin', NOW(), 'admin', NOW()),
(11, 14, 'admin', NOW(), 'admin', NOW()),
(28, 14, 'admin', NOW(), 'admin', NOW()),
(22, 14, 'admin', NOW(), 'admin', NOW()),
(31, 14, 'admin', NOW(), 'admin', NOW()),
(12, 14, 'admin', NOW(), 'admin', NOW()),
(7, 14, 'admin', NOW(), 'admin', NOW()),
(2, 15, 'admin', NOW(), 'admin', NOW()),
(11, 15, 'admin', NOW(), 'admin', NOW()),
(28, 15, 'admin', NOW(), 'admin', NOW()),
(22, 15, 'admin', NOW(), 'admin', NOW()),
(31, 15, 'admin', NOW(), 'admin', NOW()),
(12, 15, 'admin', NOW(), 'admin', NOW()),
(7, 15, 'admin', NOW(), 'admin', NOW()),

(2, 16, 'admin', NOW(), 'admin', NOW()), /* Savoyarde : Base Sauce Fromagère(2), Lardon(30), Origan(24), Emmental(6), Mozzarella(11), Farine (7) */
(30, 16, 'admin', NOW(), 'admin', NOW()),
(24, 16, 'admin', NOW(), 'admin', NOW()),
(6, 16, 'admin', NOW(), 'admin', NOW()),
(11, 16, 'admin', NOW(), 'admin', NOW()),
(7, 16, 'admin', NOW(), 'admin', NOW()),
(2, 17, 'admin', NOW(), 'admin', NOW()),
(30, 17, 'admin', NOW(), 'admin', NOW()),
(24, 17, 'admin', NOW(), 'admin', NOW()),
(6, 17, 'admin', NOW(), 'admin', NOW()),
(11, 17, 'admin', NOW(), 'admin', NOW()),
(7, 17, 'admin', NOW(), 'admin', NOW()),
(2, 18, 'admin', NOW(), 'admin', NOW()),
(30, 18, 'admin', NOW(), 'admin', NOW()),
(24, 18, 'admin', NOW(), 'admin', NOW()),
(6, 18, 'admin', NOW(), 'admin', NOW()),
(11, 18, 'admin', NOW(), 'admin', NOW()),
(7, 18, 'admin', NOW(), 'admin', NOW()),

(2, 19, 'admin', NOW(), 'admin', NOW()), /* Chèvre Miel : Base Sauce Fromagère(2), Chèvre(34), Miel(17), Mozzarella(11), Farine (7) */
(34, 19, 'admin', NOW(), 'admin', NOW()),
(17, 19, 'admin', NOW(), 'admin', NOW()),
(11, 19, 'admin', NOW(), 'admin', NOW()),
(7, 19, 'admin', NOW(), 'admin', NOW()),
(2, 20, 'admin', NOW(), 'admin', NOW()),
(34, 20, 'admin', NOW(), 'admin', NOW()),
(17, 20, 'admin', NOW(), 'admin', NOW()),
(11, 20, 'admin', NOW(), 'admin', NOW()),
(7, 20, 'admin', NOW(), 'admin', NOW()),
(2, 21, 'admin', NOW(), 'admin', NOW()),
(34, 21, 'admin', NOW(), 'admin', NOW()),
(17, 21, 'admin', NOW(), 'admin', NOW()),
(11, 21, 'admin', NOW(), 'admin', NOW()),
(7, 21, 'admin', NOW(), 'admin', NOW()),

(1, 22, 'admin', NOW(), 'admin', NOW()), /* Hawaïenne : Base Sauce Tomate(1), Ananas(16), Poulet(10), Mozzarella(11), Farine (7) */
(16, 22, 'admin', NOW(), 'admin', NOW()),
(10, 22, 'admin', NOW(), 'admin', NOW()),
(11, 22, 'admin', NOW(), 'admin', NOW()),
(7, 22, 'admin', NOW(), 'admin', NOW()),
(1, 23, 'admin', NOW(), 'admin', NOW()),
(16, 23, 'admin', NOW(), 'admin', NOW()),
(10, 23, 'admin', NOW(), 'admin', NOW()),
(11, 23, 'admin', NOW(), 'admin', NOW()),
(7, 23, 'admin', NOW(), 'admin', NOW()),
(1, 24, 'admin', NOW(), 'admin', NOW()),
(16, 24, 'admin', NOW(), 'admin', NOW()),
(10, 24, 'admin', NOW(), 'admin', NOW()),
(11, 24, 'admin', NOW(), 'admin', NOW()),
(7, 24, 'admin', NOW(), 'admin', NOW()),

(5, 25, 'admin', NOW(), 'admin', NOW()), /* Orientale : Base Sauce Piquante(5), Pepperoni(25), Poivron(29), Oignon(22), Farine (7) */
(25, 25, 'admin', NOW(), 'admin', NOW()),
(29, 25, 'admin', NOW(), 'admin', NOW()),
(22, 25, 'admin', NOW(), 'admin', NOW()),
(7, 25, 'admin', NOW(), 'admin', NOW()),
(5, 26, 'admin', NOW(), 'admin', NOW()),
(25, 26, 'admin', NOW(), 'admin', NOW()),
(29, 26, 'admin', NOW(), 'admin', NOW()),
(22, 26, 'admin', NOW(), 'admin', NOW()),
(7, 26, 'admin', NOW(), 'admin', NOW()),
(5, 27, 'admin', NOW(), 'admin', NOW()),
(25, 27, 'admin', NOW(), 'admin', NOW()),
(29, 27, 'admin', NOW(), 'admin', NOW()),
(22, 27, 'admin', NOW(), 'admin', NOW()),
(7, 27, 'admin', NOW(), 'admin', NOW()),

(1, 28, 'admin', NOW(), 'admin', NOW()), /* Reine : Base Sauce Tomate(1), Mozzarella(11), Jambon(9), Champignon(15), Farine (7) */
(11, 28, 'admin', NOW(), 'admin', NOW()),
(9, 28, 'admin', NOW(), 'admin', NOW()),
(15, 28, 'admin', NOW(), 'admin', NOW()),
(7, 28, 'admin', NOW(), 'admin', NOW()),
(1, 29, 'admin', NOW(), 'admin', NOW()),
(11, 29, 'admin', NOW(), 'admin', NOW()),
(9, 29, 'admin', NOW(), 'admin', NOW()),
(15, 29, 'admin', NOW(), 'admin', NOW()),
(7, 29, 'admin', NOW(), 'admin', NOW()),
(1, 30, 'admin', NOW(), 'admin', NOW()),
(11, 30, 'admin', NOW(), 'admin', NOW()),
(9, 30, 'admin', NOW(), 'admin', NOW()),
(15, 30, 'admin', NOW(), 'admin', NOW()),
(7, 30, 'admin', NOW(), 'admin', NOW()),

(2, 31, 'admin', NOW(), 'admin', NOW()), /* Campagnarde : Base Sauce Fromgère(2), Mozzarella(11), Poulet(10), Jambon(9), Emmental(6), Farine (7) */
(11, 31, 'admin', NOW(), 'admin', NOW()),
(10, 31, 'admin', NOW(), 'admin', NOW()),
(9, 31, 'admin', NOW(), 'admin', NOW()),
(6, 31, 'admin', NOW(), 'admin', NOW()),
(7, 31, 'admin', NOW(), 'admin', NOW()),
(2, 32, 'admin', NOW(), 'admin', NOW()),
(11, 32, 'admin', NOW(), 'admin', NOW()),
(10, 32, 'admin', NOW(), 'admin', NOW()),
(9, 32, 'admin', NOW(), 'admin', NOW()),
(6, 32, 'admin', NOW(), 'admin', NOW()),
(7, 32, 'admin', NOW(), 'admin', NOW()),
(2, 33, 'admin', NOW(), 'admin', NOW()),
(11, 33, 'admin', NOW(), 'admin', NOW()),
(10, 33, 'admin', NOW(), 'admin', NOW()),
(9, 33, 'admin', NOW(), 'admin', NOW()),
(6, 33, 'admin', NOW(), 'admin', NOW()),
(7, 33, 'admin', NOW(), 'admin', NOW()),

(3, 34, 'admin', NOW(), 'admin', NOW()), /* Bacon Groovy : Base Sauce BBQ(3), Mozzarella(11), Poulet(10), Sauce BBQ(35), Oignon(22), Bacon(12), Farine (7) */
(11, 34, 'admin', NOW(), 'admin', NOW()),
(10, 34, 'admin', NOW(), 'admin', NOW()),
(35, 34, 'admin', NOW(), 'admin', NOW()),
(22, 34, 'admin', NOW(), 'admin', NOW()),
(12, 34, 'admin', NOW(), 'admin', NOW()),
(7, 34, 'admin', NOW(), 'admin', NOW()),
(3, 35, 'admin', NOW(), 'admin', NOW()),
(11, 35, 'admin', NOW(), 'admin', NOW()),
(10, 35, 'admin', NOW(), 'admin', NOW()),
(35, 35, 'admin', NOW(), 'admin', NOW()),
(22, 35, 'admin', NOW(), 'admin', NOW()),
(12, 35, 'admin', NOW(), 'admin', NOW()),
(7, 35, 'admin', NOW(), 'admin', NOW()),
(3, 36, 'admin', NOW(), 'admin', NOW()),
(11, 36, 'admin', NOW(), 'admin', NOW()),
(10, 36, 'admin', NOW(), 'admin', NOW()),
(35, 36, 'admin', NOW(), 'admin', NOW()),
(22, 36, 'admin', NOW(), 'admin', NOW()),
(12, 36, 'admin', NOW(), 'admin', NOW()),
(7, 36, 'admin', NOW(), 'admin', NOW()),

(3, 37, 'admin', NOW(), 'admin', NOW()), /* Tasty Kebab : Base Sauce BBQ(3), Kebab(26), Oignon(22), Sauce Mayonnaise(37), Farine (7) */
(26, 37, 'admin', NOW(), 'admin', NOW()),
(22, 37, 'admin', NOW(), 'admin', NOW()),
(37, 37, 'admin', NOW(), 'admin', NOW()),
(7, 37, 'admin', NOW(), 'admin', NOW()),
(3, 38, 'admin', NOW(), 'admin', NOW()),
(26, 38, 'admin', NOW(), 'admin', NOW()),
(22, 38, 'admin', NOW(), 'admin', NOW()),
(37, 38, 'admin', NOW(), 'admin', NOW()),
(22, 38, 'admin', NOW(), 'admin', NOW()),
(3, 39, 'admin', NOW(), 'admin', NOW()),
(26, 39, 'admin', NOW(), 'admin', NOW()),
(22, 39, 'admin', NOW(), 'admin', NOW()),
(37, 39, 'admin', NOW(), 'admin', NOW()),
(7, 39, 'admin', NOW(), 'admin', NOW()),

(2, 40, 'admin', NOW(), 'admin', NOW()), /* Oceana : Base Sauce Fromgère(2), Olive(8), Anchois(33), Thon(32), Farine (7) */
(8, 40, 'admin', NOW(), 'admin', NOW()),
(33, 40, 'admin', NOW(), 'admin', NOW()),
(32, 40, 'admin', NOW(), 'admin', NOW()),
(7, 40, 'admin', NOW(), 'admin', NOW()),
(2, 41, 'admin', NOW(), 'admin', NOW()),
(8, 41, 'admin', NOW(), 'admin', NOW()),
(33, 41, 'admin', NOW(), 'admin', NOW()),
(32, 41, 'admin', NOW(), 'admin', NOW()),
(7, 41, 'admin', NOW(), 'admin', NOW()),
(2, 42, 'admin', NOW(), 'admin', NOW()),
(8, 42, 'admin', NOW(), 'admin', NOW()),
(33, 42, 'admin', NOW(), 'admin', NOW()),
(32, 42, 'admin', NOW(), 'admin', NOW()),
(7, 42, 'admin', NOW(), 'admin', NOW()),

(39, 43, 'admin', NOW(), 'admin', NOW()), /* Coca cola 33 cl */
(40, 44, 'admin', NOW(), 'admin', NOW()), /* Coca cola 1,5L */
(41, 45, 'admin', NOW(), 'admin', NOW()), /* Coca cola 1,25L */
(42, 46, 'admin', NOW(), 'admin', NOW()), /* Coca cola Cherry 33 cl */
(43, 47, 'admin', NOW(), 'admin', NOW()), /* Coca cola Cherry 1,5L */
(44, 48, 'admin', NOW(), 'admin', NOW()), /* Coca cola Cherry 1,25L */
(45, 49, 'admin', NOW(), 'admin', NOW()), /* Orangina 33 cl */
(46, 50, 'admin', NOW(), 'admin', NOW()), /* Orangina 1,5L */
(47, 51, 'admin', NOW(), 'admin', NOW()), /* Orangina 1,25L */
(48, 52, 'admin', NOW(), 'admin', NOW()), /* 7up 33 cl */
(49, 53, 'admin', NOW(), 'admin', NOW()), /* 7up 1,5L */
(50, 54, 'admin', NOW(), 'admin', NOW()), /* 7up 1,25L */
(51, 55, 'admin', NOW(), 'admin', NOW()); /* Fuze Tea 33 cl */

/* Populate Orders */
INSERT INTO Orders (nameOrd, typeOrd, payOrdON, reductionOrd, takingDateOrd, readyDateOrd, priceETOrd, priceITOrd, idDiscount, idCustomer, userCreate, dateCreate, userModif, dateModif)
VALUES
('Musa Ademi', 'Takeaway', FALSE, 20.0, NOW(), NOW() + INTERVAL 1 HOUR, 20.0, 24.0, 1, NULL, 'admin', NOW(), 'admin', NOW()),
('John Doe', 'Delivery', FALSE, NULL, NOW(), NOW() + INTERVAL 30 MINUTE, 15.0, 18.0, NULL, 1, 'admin', NOW(), 'admin', NOW());

/* Populate LineBasket */
INSERT INTO LineBasket (qtyProductLB, priceETLB, priceITLB, idProduct, idOrder, userCreate, dateCreate, userModif, dateModif)
VALUES
(2, 20.0, 21.2, 1, 1, 'admin', NOW(), 'admin', NOW()),
(1, 12.0, 14.4, 3, 1, 'admin', NOW(), 'admin', NOW()),
(1, 6.5, 7.8, 1, 2, 'admin', NOW(), 'admin', NOW()),
(1, 6.5, 7.8, 11, 2, 'admin', NOW(), 'admin', NOW());

/* Populate Supplements */
INSERT INTO Supplements (idIngredient, idLineBasket, qtySup, addSupON, userCreate, dateCreate, userModif, dateModif)
VALUES
(4, 1, 1, TRUE, 'admin', NOW(), 'admin', NOW()),
(6, 2, 2, TRUE, 'admin', NOW(), 'admin', NOW());

/* Populate TakeResponsibilityFor */
INSERT INTO TakeResponsibilityFor (idOrder, idEmployee, deliveryTakeON, paymentTakeON, startDateTake, endDateTake, userCreate, dateCreate, userModif, dateModif)
VALUES
(1, 2, FALSE, TRUE, NOW(), NOW(), 'admin', NOW(), 'admin', NOW()),
(2, 1, FALSE, FALSE, NOW(), NOW(), 'admin', NOW(), 'admin', NOW());
