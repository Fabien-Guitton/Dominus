USE Dominus;

/* Populate Employees */
INSERT INTO Employees (nameEmp, codeEmp, roleEmp, telEmp, userCreate, dateCreate, userModif, dateModif)
VALUES
('Musa Ademi', '2012', 'Manager', '0667948123', 'admin', NOW(), 'admin', NOW()),
('Léonie Roux', '2005', 'Delivery', '0787654321', 'admin', NOW(), 'admin', NOW()),
('Aziz Cheleyfa', '2000', 'Cashier', '0721654761', 'admin', NOW(), 'admin', NOW()),
('Fabien Guitton', '2004', 'Delivery', '0678901234', 'admin', NOW(), 'admin', NOW());

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
('Alice Brown', '0678901234', '8', 'RUE CHANZY', '36000', "Appelez moi quand vous êtes là. N'oubliez pas les sauces piquantes.", NULL, 'admin', NOW(), 'admin', NOW());

/* Populate Discounts */
INSERT INTO Discounts (nameDist, valueDist, codeDist, accredDistON, userCreate, dateCreate, userModif, dateModif)
VALUES
('LUNDI FOU', 20.0, '267431', FALSE, 'admin', NOW(), 'admin', NOW()),
('MERCREDI FOU', 20.0, '158271', FALSE, 'admin', NOW(), 'admin', NOW()),
('REDUCTION EMPLOYE', 50.0, '647284', TRUE, 'admin', NOW(), 'admin', NOW());

/* Populate Ingredients */
INSERT INTO Ingredients (nameIng, stockIng, unityIng, supplementPossibleON, priceETIng, priceITIng, userCreate, dateCreate, userModif, dateModif)
VALUES
('Base Sauce Tomate', 100, 'kg', TRUE, 2.5, 3.0, 'admin', NOW(), 'admin', NOW()),
('Emmental', 50, 'kg', TRUE, 5.0, 6.0, 'admin', NOW(), 'admin', NOW()),
('Farine', 200, 'kg', FALSE, 1.0, 1.2, 'admin', NOW(), 'admin', NOW()),
('Olives', 30, 'kg', TRUE, 4.0, 4.8, 'admin', NOW(), 'admin', NOW()),
('Jambons', 25, 'kg', TRUE, 6.0, 7.2, 'admin', NOW(), 'admin', NOW()),
('Poulet', 25, 'kg', TRUE, 4.0, 5.2, 'admin', NOW(), 'admin', NOW()),
('Coca Cola', 100, '33cl', TRUE, 1.0, 1.5, 'admin', NOW(), 'admin', NOW()),
('Coca Cola', 1, '1,5L', TRUE, 3.0, 3.5, 'admin', NOW(), 'admin', NOW()),
('Coca Cola', 1, '1,25L', TRUE, 3.0, 3.5, 'admin', NOW(), 'admin', NOW()),
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
('Coca Cola', '1,5L', 'Boissons', 3.0, 3.5, 'admin', NOW(), 'admin', NOW()),
('Coca Cola', '1,25L', 'Boissons', 3.0, 3.5, 'admin', NOW(), 'admin', NOW()),
('Coca Cola', '33cl', 'Boissons', 1.0, 1.5, 'admin', NOW(), 'admin', NOW()),
('Fuze Tea', '33cl', 'Boissons', 1.0, 1.5, 'admin', NOW(), 'admin', NOW());

/* Populate DefaultIngredients */
INSERT INTO DefaultIngredients (idIngredient, idProduct, userCreate, dateCreate, userModif, dateModif)
VALUES
(1, 1, 'admin', NOW(), 'admin', NOW()), /* Pizza margharita : Base sauce tomate (1), Emmental (2), Farine (3) */
(2, 1, 'admin', NOW(), 'admin', NOW()),
(3, 1, 'admin', NOW(), 'admin', NOW()),
(1, 2, 'admin', NOW(), 'admin', NOW()),
(2, 2, 'admin', NOW(), 'admin', NOW()),
(3, 2, 'admin', NOW(), 'admin', NOW()),
(1, 3, 'admin', NOW(), 'admin', NOW()),
(2, 3, 'admin', NOW(), 'admin', NOW()),
(3, 3, 'admin', NOW(), 'admin', NOW()),

(1, 4, 'admin', NOW(), 'admin', NOW()), /* Pizza jambon : Base sauce tomate (1), Emmental (2), Jambon (5), Farine (3) */
(2, 4, 'admin', NOW(), 'admin', NOW()),
(3, 4, 'admin', NOW(), 'admin', NOW()),
(5, 4, 'admin', NOW(), 'admin', NOW()),
(1, 5, 'admin', NOW(), 'admin', NOW()), 
(2, 5, 'admin', NOW(), 'admin', NOW()),
(3, 5, 'admin', NOW(), 'admin', NOW()),
(5, 5, 'admin', NOW(), 'admin', NOW()),
(1, 6, 'admin', NOW(), 'admin', NOW()), 
(2, 6, 'admin', NOW(), 'admin', NOW()),
(3, 6, 'admin', NOW(), 'admin', NOW()),
(5, 6, 'admin', NOW(), 'admin', NOW()),

(1, 7, 'admin', NOW(), 'admin', NOW()), /* Pizza Canibale : Base sauce tomate (1), Emmental (2), Poulet (6), Farine (3) */
(2, 7, 'admin', NOW(), 'admin', NOW()),
(3, 7, 'admin', NOW(), 'admin', NOW()),
(6, 7, 'admin', NOW(), 'admin', NOW()),
(1, 8, 'admin', NOW(), 'admin', NOW()),
(2, 8, 'admin', NOW(), 'admin', NOW()),
(3, 8, 'admin', NOW(), 'admin', NOW()),
(6, 8, 'admin', NOW(), 'admin', NOW()),
(1, 9, 'admin', NOW(), 'admin', NOW()),
(2, 9, 'admin', NOW(), 'admin', NOW()),
(3, 9, 'admin', NOW(), 'admin', NOW()),
(6, 9, 'admin', NOW(), 'admin', NOW()),

(8, 10, 'admin', NOW(), 'admin', NOW()), /* Coca cola 33 cl */
(9, 11, 'admin', NOW(), 'admin', NOW()), /* Coca cola 1,5L */
(7, 12, 'admin', NOW(), 'admin', NOW()), /* Coca cola 1,25L */
(10, 13, 'admin', NOW(), 'admin', NOW()); /* Fuze Tea 33 cl */

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
