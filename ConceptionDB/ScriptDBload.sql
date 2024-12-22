USE Dominus;

/* Populate Ingredients */
INSERT INTO Ingredients (nameIng, stockIng, unityIng, priceETIng, priceITIng, userCreate, dateCreate, userModif, dateModif)
VALUES
('Tomatoes', 100, 'kg', 2.5, 3.0, 'admin', NOW(), 'admin', NOW()),
('Cheese', 50, 'kg', 5.0, 6.0, 'admin', NOW(), 'admin', NOW()),
('Flour', 200, 'kg', 1.0, 1.2, 'admin', NOW(), 'admin', NOW()),
('Olives', 30, 'kg', 4.0, 4.8, 'admin', NOW(), 'admin', NOW()),
('Ham', 25, 'kg', 6.0, 7.2, 'admin', NOW(), 'admin', NOW());

/* Populate Products */
INSERT INTO Products (nameProduct, sizeProduct, categoryProduct, priceETProduct, priceITProduct, userCreate, dateCreate, userModif, dateModif)
VALUES
('Margherita Pizza', 'Medium', 'Pizza', 8.0, 9.6, 'admin', NOW(), 'admin', NOW()),
('Pepperoni Pizza', 'Large', 'Pizza', 12.0, 14.4, 'admin', NOW(), 'admin', NOW()),
('Veggie Pizza', 'Small', 'Pizza', 6.5, 7.8, 'admin', NOW(), 'admin', NOW()),
('Caesar Salad', 'Regular', 'Salad', 5.0, 6.0, 'admin', NOW(), 'admin', NOW()),
('Spaghetti Carbonara', 'Single', 'Pasta', 7.0, 8.4, 'admin', NOW(), 'admin', NOW());

/* Populate Customers */
INSERT INTO Customers (nameCst, telCst, streetNumberCst, streetNameCst, postcodeCst, instructionsCst, internalComCst, userCreate, dateCreate, userModif, dateModif)
VALUES
('John Doe', '0123456789', '123', 'Main Street', '75001', 'Leave at door', 'VIP Customer', 'admin', NOW(), 'admin', NOW()),
('Jane Smith', '0987654321', '456', 'Elm Street', '75002', NULL, NULL, 'admin', NOW(), 'admin', NOW()),
('Alice Brown', '0678901234', '789', 'Oak Avenue', '75003', 'Call on arrival', 'Regular', 'admin', NOW(), 'admin', NOW());

/* Populate Employees */
INSERT INTO Employees (nameEmp, codeEmp, roleEmp, telEmp, userCreate, dateCreate, userModif, dateModif)
VALUES
('Michael Scott', 'EMP1', 'Manager', '0123456789', 'admin', NOW(), 'admin', NOW()),
('Dwight Schrute', 'EMP2', 'Delivery', '0987654321', 'admin', NOW(), 'admin', NOW()),
('Pam Beesly', 'EMP3', 'Cashier', '0678901234', 'admin', NOW(), 'admin', NOW());

/* Populate Discounts */
INSERT INTO Discounts (nameDist, valueDist, codeDist, accredDistON, userCreate, dateCreate, userModif, dateModif)
VALUES
('New Year Discount', 10.0, 'NY2024', TRUE, 'admin', NOW(), 'admin', NOW()),
('Loyalty Discount', 15.0, 'LOYAL', TRUE, 'admin', NOW(), 'admin', NOW()),
('Black Friday', 20.0, 'BF2024', TRUE, 'admin', NOW(), 'admin', NOW());

/* Populate Orders */
INSERT INTO Orders (nameOrd, typeOrd, payOrdON, reductionOrd, takingDateOrd, readyDateOrd, priceETOrd, priceITOrd, instructionsOrd, idDiscount, idCustomer, userCreate, dateCreate, userModif, dateModif)
VALUES
('Order001', 'Delivery', TRUE, 10.0, NOW(), NOW() + INTERVAL 1 HOUR, 20.0, 24.0, 'No onions', 1, 1, 'admin', NOW(), 'admin', NOW()),
('Order002', 'Takeaway', TRUE, NULL, NOW(), NOW() + INTERVAL 30 MINUTE, 15.0, 18.0, NULL, NULL, 2, 'admin', NOW(), 'admin', NOW());

/* Populate LineBasket */
INSERT INTO LineBasket (qtyProductLB, priceETLB, priceITLB, idProduct, idOrder, userCreate, dateCreate, userModif, dateModif)
VALUES
(2, 16.0, 19.2, 1, 1, 'admin', NOW(), 'admin', NOW()),
(1, 12.0, 14.4, 2, 1, 'admin', NOW(), 'admin', NOW()),
(1, 6.5, 7.8, 3, 2, 'admin', NOW(), 'admin', NOW());

/* Populate DefaultIngredients */
INSERT INTO DefaultIngredients (idIngredient, idProduct, userCreate, dateCreate, userModif, dateModif)
VALUES
(1, 1, 'admin', NOW(), 'admin', NOW()),
(2, 1, 'admin', NOW(), 'admin', NOW()),
(3, 1, 'admin', NOW(), 'admin', NOW()),
(1, 2, 'admin', NOW(), 'admin', NOW()),
(5, 2, 'admin', NOW(), 'admin', NOW());

/* Populate Supplements */
INSERT INTO Supplements (idIngredient, idLineBasket, qtySup, addSupON, userCreate, dateCreate, userModif, dateModif)
VALUES
(4, 1, 1, TRUE, 'admin', NOW(), 'admin', NOW()),
(5, 2, 1, TRUE, 'admin', NOW(), 'admin', NOW());

/* Populate TakeResponsibilityFor */
INSERT INTO TakeResponsibilityFor (idOrder, idEmployee, deliveryTakeON, paymentTakeON, startDateTake, endDateTake, userCreate, dateCreate, userModif, dateModif)
VALUES
(1, 2, TRUE, TRUE, NOW(), NULL, 'admin', NOW(), 'admin', NOW()),
(2, 3, FALSE, TRUE, NOW(), NOW(), 'admin', NOW(), 'admin', NOW());
