/* SQL install script for Dominus */
CREATE DATABASE Dominus;

USE Dominus;

/* Creation of all tables */
CREATE OR REPLACE TABLE Ingredients(
   idIgredient BIGINT AUTO_INCREMENT NOT NULL,
   nameIng VARCHAR(100) NOT NULL,
   stockIng BIGINT NOT NULL,
   unityIng VARCHAR(25) NOT NULL,
   priceHTIng DOUBLE NOT NULL,
   priceTTCIng DOUBLE NOT NULL,
   userCreate VARCHAR(100) NOT NULL,
   dateCreate DATETIME NOT NULL,
   userModif VARCHAR(100) NOT NULL,
   dateModif DATETIME NOT NULL,
   PRIMARY KEY(idIgredient)
);

CREATE OR REPLACE TABLE Products(
   idProduct BIGINT AUTO_INCREMENT NOT NULL,
   nameProduct VARCHAR(100) NOT NULL,
   sizeProduct VARCHAR(50) NOT NULL,
   categoryProduct VARCHAR(100) NOT NULL,
   priceHTProduct DOUBLE NOT NULL,
   priceTTCProduct DOUBLE NOT NULL,
   userCreate VARCHAR(100) NOT NULL,
   dateCreate DATETIME NOT NULL,
   userModif VARCHAR(100) NOT NULL,
   dateModif DATETIME NOT NULL,
   PRIMARY KEY(idProduct)
);

CREATE OR REPLACE TABLE Customers(
   idCustomer BIGINT AUTO_INCREMENT NOT NULL,
   nameCst VARCHAR(100) NOT NULL,
   telCst CHAR(10) NOT NULL,
   streetNumberCst CHAR(50) NOT NULL,
   streetNameCst VARCHAR(255) NOT NULL,
   postcodeCst VARCHAR(50) NOT NULL,
   instructionsCst VARCHAR(100),
   internalComCst VARCHAR(100),
   userCreate VARCHAR(100) NOT NULL,
   dateCreate DATETIME NOT NULL,
   userModif VARCHAR(100) NOT NULL,
   dateModif DATETIME NOT NULL,
   PRIMARY KEY(idCustomer)
);

CREATE OR REPLACE TABLE Employees(
   idEmployee BIGINT AUTO_INCREMENT NOT NULL,
   nameEmp VARCHAR(100) NOT NULL,
   codeEmp CHAR(4) NOT NULL,
   roleEmp VARCHAR(50) NOT NULL,
   telEmp CHAR(10) NOT NULL,
   userCreate VARCHAR(100) NOT NULL,
   dateCreate DATETIME NOT NULL,
   userModif VARCHAR(100) NOT NULL,
   dateModif DATETIME NOT NULL,
   PRIMARY KEY(idEmployee),
   UNIQUE(codeEmp)
);

CREATE OR REPLACE TABLE ClockingIn(
   idClockingIn BIGINT AUTO_INCREMENT NOT NULL,
   startClockingIn DATETIME NOT NULL,
   endClockingIn DATETIME,
   idEmployee BIGINT,
   userCreate VARCHAR(100) NOT NULL,
   dateCreate DATETIME NOT NULL,
   userModif VARCHAR(100) NOT NULL,
   dateModif DATETIME NOT NULL,
   PRIMARY KEY(idClockingIn)
);

CREATE OR REPLACE TABLE Discounts(
   idDiscount BIGINT AUTO_INCREMENT NOT NULL,
   nameDist VARCHAR(50) NOT NULL,
   valueDist DOUBLE NOT NULL,
   codeDist CHAR(6) NOT NULL,
   accredDistON BOOLEAN NOT NULL,
   userCreate VARCHAR(100) NOT NULL,
   dateCreate DATETIME NOT NULL,
   userModif VARCHAR(100) NOT NULL,
   dateModif DATETIME NOT NULL,
   PRIMARY KEY(idDiscount),
   UNIQUE(codeDist)
);

CREATE OR REPLACE TABLE Orders(
   idOrder BIGINT AUTO_INCREMENT NOT NULL,
   nameOrd VARCHAR(50) NOT NULL,
   typeOrd VARCHAR(50) NOT NULL,
   payOrdON BOOLEAN NOT NULL,
   reductionOrd DOUBLE,
   takingDateOrd DATETIME NOT NULL,
   readyDateOrd DATETIME NOT NULL,
   priceHTOrd DOUBLE NOT NULL,
   priceTTCOrd DOUBLE NOT NULL,
   idDiscount BIGINT,
   idCustomer BIGINT,
   userCreate VARCHAR(100) NOT NULL,
   dateCreate DATETIME NOT NULL,
   userModif VARCHAR(100) NOT NULL,
   dateModif DATETIME NOT NULL,
   PRIMARY KEY(idOrder)
);

CREATE OR REPLACE TABLE LineBasket(
   idLineBasket BIGINT AUTO_INCREMENT NOT NULL,
   qtyProductLB INT NOT NULL,
   priceHTLB DOUBLE NOT NULL,
   priceTTCLB DOUBLE NOT NULL,
   idProduct BIGINT,
   idOrder BIGINT,
   userCreate VARCHAR(100) NOT NULL,
   dateCreate DATETIME NOT NULL,
   userModif VARCHAR(100) NOT NULL,
   dateModif DATETIME NOT NULL,
   PRIMARY KEY(idLineBasket)
);

/* Creation of N,N link tables */
CREATE OR REPLACE TABLE DefaultIngredients(
   idIgredient BIGINT,
   idProduct BIGINT,
   userCreate VARCHAR(100) NOT NULL,
   dateCreate DATETIME NOT NULL,
   userModif VARCHAR(100) NOT NULL,
   dateModif DATETIME NOT NULL,
   PRIMARY KEY(idIgredient, idProduct)
);

CREATE OR REPLACE TABLE Supplements(
   idIgredient BIGINT,
   idLineBasket BIGINT,
   qtySup INT NOT NULL,
   addSupON BOOLEAN NOT NULL,
   userCreate VARCHAR(100) NOT NULL,
   dateCreate DATETIME NOT NULL,
   userModif VARCHAR(100) NOT NULL,
   dateModif DATETIME NOT NULL,
   PRIMARY KEY(idIgredient, idLineBasket)
);

CREATE OR REPLACE TABLE TakeResponsibilityFor(
   idOrder BIGINT,
   idEmployee BIGINT,
   deliveryTakeON BOOLEAN NOT NULL,
   paymentTakeON BOOLEAN NOT NULL,
   userCreate VARCHAR(100) NOT NULL,
   dateCreate DATETIME NOT NULL,
   userModif VARCHAR(100) NOT NULL,
   dateModif DATETIME NOT NULL,
   PRIMARY KEY(idOrder, idEmployee)
);

/* Creating links between tables */
ALTER TABLE ClockingIn
   ADD CONSTRAINT FK_EmployeeClokingIn
   FOREIGN KEY(idEmployee) REFERENCES Employees(idEmployee);

ALTER TABLE Orders
   ADD CONSTRAINT FK_DiscountOrder
   FOREIGN KEY(idDiscount) REFERENCES Discounts(idDiscount),
   ADD CONSTRAINT FK_CustomerOrder
   FOREIGN KEY(idCustomer) REFERENCES Customers(idCustomer);

ALTER TABLE LineBasket
   ADD CONSTRAINT FK_ProductLineBasket
   FOREIGN KEY(idProduct) REFERENCES Products(idProduct),
   ADD CONSTRAINT FK_OrderLineBasket
   FOREIGN KEY(idOrder) REFERENCES Orders(idOrder);

ALTER TABLE DefaultIngredients
   ADD CONSTRAINT FK_IngredientDefaultIngredient
   FOREIGN KEY(idIgredient) REFERENCES Ingredients(idIgredient),
   ADD CONSTRAINT FK_ProductDefaultIngredient
   FOREIGN KEY(idProduct) REFERENCES Products(idProduct);

ALTER TABLE Supplements
   ADD CONSTRAINT FK_IngredientSupplement
   FOREIGN KEY(idIgredient) REFERENCES Ingredients(idIgredient),
   ADD CONSTRAINT FK_LineBasketSupplement
   FOREIGN KEY(idLineBasket) REFERENCES LineBasket(idLineBasket);

ALTER TABLE TakeResponsibilityFor
   ADD CONSTRAINT FK_OrderTakeResponsibilityFor
   FOREIGN KEY(idOrder) REFERENCES Orders(idOrder),
   ADD CONSTRAINT FK_EmployeeTakeResponsibilityFor
   FOREIGN KEY(idEmployee) REFERENCES Employees(idEmployee);
