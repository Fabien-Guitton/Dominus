/* SQL for Dominus */
CREATE DATABASE Dominus;

USE Dominus;

/* Creation of all tables */
CREATE OR REPLACE TABLE Ingredients(
   IdIgredient BIGINT AUTO_INCREMENT NOT NULL,
   nameIng VARCHAR(100) NOT NULL,
   stockIng BIGINT NOT NULL,
   unityIng VARCHAR(25) NOT NULL,
   priceHTIng DOUBLE NOT NULL,
   priceTTCIng DOUBLE NOT NULL,
   userCreate VARCHAR(100) NOT NULL,
   dateCreate DATETIME NOT NULL,
   userModif VARCHAR(100) NOT NULL,
   dateModif DATETIME NOT NULL,
   PRIMARY KEY(IdIgredient)
);

CREATE OR REPLACE TABLE Products(
   IdProduct BIGINT AUTO_INCREMENT NOT NULL,
   nameProduct VARCHAR(100) NOT NULL,
   sizeProduct VARCHAR(50) NOT NULL,
   categoryProduct VARCHAR(100) NOT NULL,
   priceHTProduct DOUBLE NOT NULL,
   priceTTCProduct DOUBLE NOT NULL,
   userCreate VARCHAR(100) NOT NULL,
   dateCreate DATETIME NOT NULL,
   userModif VARCHAR(100) NOT NULL,
   dateModif DATETIME NOT NULL,
   PRIMARY KEY(IdProduct)
);

CREATE OR REPLACE TABLE Customers(
   idCustomer BIGINT AUTO_INCREMENT NOT NULL,
   nameCst VARCHAR(100) NOT NULL,
   telCst CHAR(10) NOT NULL,
   streetNumberCst CHAR(50) NOT NULL,
   streetNameCst VARCHAR(255) NOT NULL,
   postcodeCst VARCHAR(50) NOT NULL,
   instructionsCst VARCHAR(100),
   InternalComCst VARCHAR(100),
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
   userCreate VARCHAR(100) NOT NULL,
   dateCreate DATETIME NOT NULL,
   userModif VARCHAR(100) NOT NULL,
   dateModif DATETIME NOT NULL,
   idEmployee BIGINT,
   PRIMARY KEY(idClockingIn),
);

CREATE OR REPLACE TABLE Discounts(
   idDiscount BIGINT AUTO_INCREMENT NOT NULL,
   nameDist VARCHAR(50) NOT NULL,
   valueDist DOUBLE NOT NULL,
   codeDist CHAR(6) NOT NULL,
   AccredDistON BOOLEAN NOT NULL,
   userCreate VARCHAR(100) NOT NULL,
   dateCreate DATETIME NOT NULL,
   userModif VARCHAR(100) NOT NULL,
   dateModif DATETIME NOT NULL,
   PRIMARY KEY(idDiscount),
   UNIQUE(codeDist)
);

CREATE OR REPLACE TABLE Orders(
   idCommande BIGINT AUTO_INCREMENT NOT NULL,
   nameOrd VARCHAR(50) NOT NULL,
   typeOrd VARCHAR(50) NOT NULL,
   payOrdON BOOLEAN NOT NULL,
   reductionOrd DOUBLE,
   takingDateOrd DATETIME NOT NULL,
   readyDateOrd DATETIME NOT NULL,
   priceHTOrd DOUBLE NOT NULL,
   priceTTCOrd DOUBLE NOT NULL,
   userCreate VARCHAR(100) NOT NULL,
   dateCreate DATETIME NOT NULL,
   userModif VARCHAR(100) NOT NULL,
   dateModif DATETIME NOT NULL,
   idDiscount BIGINT,
   idCustomer BIGINT,
   PRIMARY KEY(idCommande),
);

CREATE OR REPLACE TABLE LineBasket(
   IdLineBasket BIGINT AUTO_INCREMENT NOT NULL,
   qtyProductLB INT NOT NULL,
   priceHTLB DOUBLE NOT NULL,
   priceTTCLB DOUBLE NOT NULL,
   userCreate VARCHAR(100) NOT NULL,
   dateCreate DATETIME NOT NULL,
   userModif VARCHAR(100) NOT NULL,
   dateModif DATETIME NOT NULL,
   IdProduct BIGINT,
   idCommande BIGINT,
   PRIMARY KEY(IdLineBasket),
);

/* Declaration of table ralations */
ALTER TABLE ClockingIn
   ADD CONSTRAINT FK_EmployeeClokingIn
   ADD FOREIGN KEY(idEmployee) REFERENCES Employees(idEmployee);

ALTER TABLE Orders
   ADD CONSTRAINT FK_DiscountOrder
   FOREIGN KEY(idDiscount) REFERENCES Discounts(idDiscount),
   ADD CONSTRAINT FK_CustomerOrder
   FOREIGN KEY(idCustomer) REFERENCES Customers(idCustomer);

ALTER TABLE LineBasket
   ADD CONSTRAINT FK_ProductLineBasket
   FOREIGN KEY(IdProduct) REFERENCES Products(IdProduct),
   ADD CONSTRAINT FK_CommandeLineBasket
   FOREIGN KEY(idCommande) REFERENCES Orders(idCommande);

/* CREATE N,N ??? */
/*
CREATE OR REPLACE TABLE DefaultIngredients(
   IdIgredient BIGINT,
   IdProduct BIGINT,
   userCreate_2 VARCHAR(100) NOT NULL,
   dateCreate_2 DATETIME NOT NULL,
   userModif_2 VARCHAR(100) NOT NULL,
   dateModif_2 DATETIME NOT NULL,
   PRIMARY KEY(IdIgredient, IdProduct),
   FOREIGN KEY(IdIgredient) REFERENCES Ingredients(IdIgredient), /* here FK ? */
   FOREIGN KEY(IdProduct) REFERENCES Products(IdProduct)
);

CREATE OR REPLACE TABLE Supplements(
   IdIgredient BIGINT,
   IdLineBasket BIGINT,
   qtySup INT NOT NULL,
   addSupON BOOLEAN NOT NULL,
   userCreate_3 VARCHAR(100) NOT NULL,
   dateCreate_3 DATETIME NOT NULL,
   userModif_3 VARCHAR(100) NOT NULL,
   dateModif_3 DATETIME NOT NULL,
   PRIMARY KEY(IdIgredient, IdLineBasket),
   FOREIGN KEY(IdIgredient) REFERENCES Ingredients(IdIgredient),
   FOREIGN KEY(IdLineBasket) REFERENCES LineBasket(IdLineBasket)
);

CREATE OR REPLACE TABLE TakeResponsibilityFor(
   idCommande BIGINT,
   idEmployee BIGINT,
   deliveryTakeON BOOLEAN NOT NULL,
   paymentTakeON BOOLEAN NOT NULL,
   userCreate_1 VARCHAR(100) NOT NULL,
   dateCreate_1 DATETIME NOT NULL,
   userModif_1 VARCHAR(100) NOT NULL,
   dateModif_1 DATETIME NOT NULL,
   PRIMARY KEY(idCommande, idEmployee),
   FOREIGN KEY(idCommande) REFERENCES Orders(idCommande),
   FOREIGN KEY(idEmployee) REFERENCES Employees(idEmployee)
);
*/
