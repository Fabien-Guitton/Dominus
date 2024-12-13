Sub Create_Tables()

DoCmd.RunSQL "CREATE TABLE Ingredients(" & _
   "IdIgredient COUNTER," & _
   "nomIng VARCHAR(100) NOT NULL," & _
   "stockIng BIGINT NOT NULL," & _
   "uniteIng VARCHAR(25) NOT NULL," & _
   "prixHTIng REAL NOT NULL," & _
   "prixTTCIng REAL NOT NULL," & _
   "PRIMARY KEY(IdIgredient)" & _
");"   

DoCmd.RunSQL "CREATE TABLE Produits(" & _
   "IdProduit COUNTER," & _
   "nomProduit VARCHAR(100) NOT NULL," & _
   "tailleProduit VARCHAR(50) NOT NULL," & _
   "categorieProduit VARCHAR(100) NOT NULL," & _
   "prixHTProduit REAL NOT NULL," & _
   "prixTTCProduit REAL NOT NULL," & _
   "PRIMARY KEY(IdProduit)" & _
");"   

DoCmd.RunSQL "CREATE TABLE Clients(" & _
   "idClient COUNTER," & _
   "nomCli VARCHAR(100) NOT NULL," & _
   "telCli CHAR(10) NOT NULL," & _
   "numRueCli CHAR(50) NOT NULL," & _
   "nomRueCli VARCHAR(255) NOT NULL," & _
   "codePostalCli VARCHAR(50) NOT NULL," & _
   "instructionCli VARCHAR(100)," & _
   "comInterneCli VARCHAR(100)," & _
   "PRIMARY KEY(idClient)" & _
");"   

DoCmd.RunSQL "CREATE TABLE Employes(" & _
   "idEmploye COUNTER," & _
   "nomEmp VARCHAR(100) NOT NULL," & _
   "codeEmp CHAR(4) NOT NULL," & _
   "roleEmp VARCHAR(50) NOT NULL," & _
   "telEmp CHAR(10) NOT NULL," & _
   "PRIMARY KEY(idEmploye)," & _
   "UNIQUE(codeEmp)" & _
");"   

DoCmd.RunSQL "CREATE TABLE Pointages(" & _
   "idPointage COUNTER," & _
   "debutPointage DATETIME NOT NULL," & _
   "finPointage DATETIME," & _
   "idEmploye INT," & _
   "PRIMARY KEY(idPointage)," & _
   "FOREIGN KEY(idEmploye) REFERENCES Employes(idEmploye)" & _
");"   

DoCmd.RunSQL "CREATE TABLE Commandes(" & _
   "idCommande COUNTER," & _
   "nomCmd VARCHAR(50) NOT NULL," & _
   "typeCmd VARCHAR(50) NOT NULL," & _
   "payerCmdON LOGICAL NOT NULL," & _
   "reductionCmd REAL," & _
   "priseDateCmd DATETIME NOT NULL," & _
   "pretDateCmd DATETIME NOT NULL," & _
   "prixHTCmd REAL NOT NULL," & _
   "prixTTCCmd REAL NOT NULL," & _
   "idEmploye INT," & _
   "idClient INT," & _
   "PRIMARY KEY(idCommande)," & _
   "FOREIGN KEY(idEmploye) REFERENCES Employes(idEmploye)," & _
   "FOREIGN KEY(idClient) REFERENCES Clients(idClient)" & _
");"   

DoCmd.RunSQL "CREATE TABLE LignePanier(" & _
   "IdLignePanier COUNTER," & _
   "qteProduitLP INT NOT NULL," & _
   "prixHTLP REAL NOT NULL," & _
   "prixTTCLP REAL NOT NULL," & _
   "IdProduit INT," & _
   "idCommande INT," & _
   "PRIMARY KEY(IdLignePanier)," & _
   "FOREIGN KEY(IdProduit) REFERENCES Produits(IdProduit)," & _
   "FOREIGN KEY(idCommande) REFERENCES Commandes(idCommande)" & _
");"   

DoCmd.RunSQL "CREATE TABLE ingredientsParDefault(" & _
   "IdIgredient INT," & _
   "IdProduit INT," & _
   "PRIMARY KEY(IdIgredient, IdProduit)," & _
   "FOREIGN KEY(IdIgredient) REFERENCES Ingredients(IdIgredient)," & _
   "FOREIGN KEY(IdProduit) REFERENCES Produits(IdProduit)" & _
");"   

DoCmd.RunSQL "CREATE TABLE supplements(" & _
   "IdIgredient INT," & _
   "IdLignePanier INT," & _
   "qteSup INT NOT NULL," & _
   "ajouterSupON LOGICAL NOT NULL," & _
   "PRIMARY KEY(IdIgredient, IdLignePanier)," & _
   "FOREIGN KEY(IdIgredient) REFERENCES Ingredients(IdIgredient)," & _
   "FOREIGN KEY(IdLignePanier) REFERENCES LignePanier(IdLignePanier)" & _
");"   

End Sub