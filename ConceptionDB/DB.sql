/* No Finish ! */

CREATE TABLE Ingredients(
   IdIgredient COUNTER,
   nomIng VARCHAR(100) NOT NULL,
   stockIng BIGINT NOT NULL,
   uniteIng VARCHAR(25) NOT NULL,
   prixHTIng REAL NOT NULL,
   prixTTCIng REAL NOT NULL,
   userCreate VARCHAR(100) NOT NULL,
   dateCreate DATETIME NOT NULL,
   userModif VARCHAR(100) NOT NULL,
   dateModif DATETIME NOT NULL,
   PRIMARY KEY(IdIgredient)
);

CREATE TABLE Produits(
   IdProduit COUNTER,
   nomProduit VARCHAR(100) NOT NULL,
   tailleProduit VARCHAR(50) NOT NULL,
   categorieProduit VARCHAR(100) NOT NULL,
   prixHTProduit REAL NOT NULL,
   prixTTCProduit REAL NOT NULL,
   userCreate VARCHAR(100) NOT NULL,
   dateCreate DATETIME NOT NULL,
   userModif VARCHAR(100) NOT NULL,
   dateModif DATETIME NOT NULL,
   PRIMARY KEY(IdProduit)
);

CREATE TABLE Clients(
   idClient COUNTER,
   nomCli VARCHAR(100) NOT NULL,
   telCli CHAR(10) NOT NULL,
   numRueCli CHAR(50) NOT NULL,
   nomRueCli VARCHAR(255) NOT NULL,
   codePostalCli VARCHAR(50) NOT NULL,
   instructionCli VARCHAR(100),
   comInterneCli VARCHAR(100),
   userCreate VARCHAR(100) NOT NULL,
   dateCreate DATETIME NOT NULL,
   userModif VARCHAR(100) NOT NULL,
   dateModif DATETIME NOT NULL,
   PRIMARY KEY(idClient)
);

CREATE TABLE Employes(
   idEmploye COUNTER,
   nomEmp VARCHAR(100) NOT NULL,
   codeEmp CHAR(4) NOT NULL,
   roleEmp VARCHAR(50) NOT NULL,
   telEmp CHAR(10) NOT NULL,
   userCreate VARCHAR(100) NOT NULL,
   dateCreate DATETIME NOT NULL,
   userModif VARCHAR(100) NOT NULL,
   dateModif DATETIME NOT NULL,
   PRIMARY KEY(idEmploye),
   UNIQUE(codeEmp)
);

CREATE TABLE Pointages(
   idPointage COUNTER,
   debutPointage DATETIME NOT NULL,
   finPointage DATETIME,
   userCreate VARCHAR(100) NOT NULL,
   dateCreate DATETIME NOT NULL,
   userModif VARCHAR(100) NOT NULL,
   dateModif DATETIME NOT NULL,
   idEmploye INT,
   PRIMARY KEY(idPointage),
   FOREIGN KEY(idEmploye) REFERENCES Employes(idEmploye)
);

CREATE TABLE Commandes(
   idCommande COUNTER,
   nomCmd VARCHAR(50) NOT NULL,
   typeCmd VARCHAR(50) NOT NULL,
   payerCmdON LOGICAL NOT NULL,
   reductionCmd REAL,
   priseDateCmd DATETIME NOT NULL,
   pretDateCmd DATETIME NOT NULL,
   prixHTCmd REAL NOT NULL,
   prixTTCCmd REAL NOT NULL,
   userCreate VARCHAR(100) NOT NULL,
   dateCreate DATETIME NOT NULL,
   userModif VARCHAR(100) NOT NULL,
   dateModif DATETIME NOT NULL,
   idClient INT,
   PRIMARY KEY(idCommande),
   FOREIGN KEY(idClient) REFERENCES Clients(idClient)
);

CREATE TABLE LignePanier(
   IdLignePanier COUNTER,
   qteProduitLP INT NOT NULL,
   prixHTLP REAL NOT NULL,
   prixTTCLP REAL NOT NULL,
   userCreate VARCHAR(100) NOT NULL,
   dateCreate DATETIME NOT NULL,
   userModif VARCHAR(100) NOT NULL,
   dateModif DATETIME NOT NULL,
   IdProduit INT,
   idCommande INT,
   PRIMARY KEY(IdLignePanier),
   FOREIGN KEY(IdProduit) REFERENCES Produits(IdProduit),
   FOREIGN KEY(idCommande) REFERENCES Commandes(idCommande)
);

CREATE TABLE ingredientsParDefault(
   IdIgredient INT,
   IdProduit INT,
   userCreate_2 VARCHAR(100) NOT NULL,
   dateCreate_2 DATETIME NOT NULL,
   userModif_2 VARCHAR(100) NOT NULL,
   dateModif_2 DATETIME NOT NULL,
   PRIMARY KEY(IdIgredient, IdProduit),
   FOREIGN KEY(IdIgredient) REFERENCES Ingredients(IdIgredient),
   FOREIGN KEY(IdProduit) REFERENCES Produits(IdProduit)
);

CREATE TABLE supplements(
   IdIgredient INT,
   IdLignePanier INT,
   qteSup INT NOT NULL,
   ajouterSupON LOGICAL NOT NULL,
   userCreate_3 VARCHAR(100) NOT NULL,
   dateCreate_3 DATETIME NOT NULL,
   userModif_3 VARCHAR(100) NOT NULL,
   dateModif_3 DATETIME NOT NULL,
   PRIMARY KEY(IdIgredient, IdLignePanier),
   FOREIGN KEY(IdIgredient) REFERENCES Ingredients(IdIgredient),
   FOREIGN KEY(IdLignePanier) REFERENCES LignePanier(IdLignePanier)
);

CREATE TABLE Occupe(
   idCommande INT,
   idEmploye INT,
   livraisonOccupeON LOGICAL NOT NULL,
   paiementOccupeON LOGICAL NOT NULL,
   userCreate_1 VARCHAR(100) NOT NULL,
   dateCreate_1 DATETIME NOT NULL,
   userModif_1 VARCHAR(100) NOT NULL,
   dateModif_1 DATETIME NOT NULL,
   PRIMARY KEY(idCommande, idEmploye),
   FOREIGN KEY(idCommande) REFERENCES Commandes(idCommande),
   FOREIGN KEY(idEmploye) REFERENCES Employes(idEmploye)
);
