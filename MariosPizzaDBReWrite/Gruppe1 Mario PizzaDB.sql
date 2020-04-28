	    CREATE DATABASE MariosPizza;
        USE MariosPizza;
        SET time_zone = "+02:00";
        SET FOREIGN_KEY_CHECKS=0;
        DROP TABLE IF EXISTS pizzasMenu;
        DROP TABLE IF EXISTS Ordre;
        DROP TABLE IF EXISTS Customer_Infomation;
        DROP TABLE IF EXISTS OrderLine;
        DROP TABLE IF EXISTS OrderLines;
	    SET FOREIGN_KEY_CHECKS=1;

        CREATE TABLE pizzasMenu(
        pizza_id INT NOT NULL AUTO_INCREMENT,
        pizza_name VARCHAR(255) NOT NULL,
        pizza_price DOUBLE NOT NULL,
        pizza_status TINYINT DEFAULT 0,
        PRIMARY KEY (pizza_id)
        )ENGINE innoDB;
        
		CREATE TABLE Customer_Infomation(
         CustomerID int not null auto_increment,
	     Customer_Name varchar(255) NOT NULL,
         Customer_PhoneNr int DEFAULT 0,
         Customer_Adresse varchar(255) DEFAULT "none",
         primary key (CustomerID)
         )ENGINE innoDB;
         
        CREATE TABLE Ordre(
        Ordre_Line INT NOT NULL AUTO_INCREMENT, 
        pizza_OrdreID INT NOT NULL,
        Order_Customer_Name varchar(255) NOT NULL,
        pizza_name VARCHAR(255) NOT NULL,
        pizza_ordretid DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL,
        ordre_Total_Price DOUBLE NOT NULL,
        pizza_price DOUBLE NOT NULL,
        pizza_ordre_Status TINYINT DEFAULT 0, 
        home_delivery boolean NOT NULL,
        PRIMARY KEY (Ordre_Line)
         )ENGINE innoDB;
         
		INSERT INTO Customer_Infomation(Customer_Name,Customer_PhoneNr,Customer_Adresse) VALUES ("Test",13453424,"Adresse 1, 2");
		INSERT INTO Ordre(pizza_OrdreID, Order_Customer_Name, pizza_name,ordre_Total_Price, pizza_price, pizza_ordre_Status, home_delivery) VALUES ('1',"Test","MARGHERITA",'20.0','69',1,false);
        INSERT INTO pizzasMenu (pizza_name, pizza_price) VALUES ("MARGHERITA" , 69.00);
        INSERT INTO pizzasMenu (pizza_name, pizza_price) VALUES ("VESUVIO" , 75.20);
        INSERT INTO pizzasMenu (pizza_name, pizza_price) VALUES ("CAPRICCIOSA" , 80.00);
        INSERT INTO pizzasMenu (pizza_name, pizza_price) VALUES ("CALZONE" , 74.50);
        INSERT INTO pizzasMenu (pizza_name, pizza_price) VALUES ("QUATTRO STAGIONI" , 85.00);
        INSERT INTO pizzasMenu (pizza_name, pizza_price) VALUES ("MARINARA" , 85.00);
        INSERT INTO pizzasMenu (pizza_name, pizza_price) VALUES ("VEGETARIANA" , 80.00);
        INSERT INTO pizzasMenu (pizza_name, pizza_price) VALUES ("ITALIANA" , 75.00);
        INSERT INTO pizzasMenu (pizza_name, pizza_price) VALUES ("GORGONZOLA" , 85.00);
        INSERT INTO pizzasMenu (pizza_name, pizza_price) VALUES ("CONTADINA" , 75.00);
        INSERT INTO pizzasMenu (pizza_name, pizza_price) VALUES ("NAPOLI" , 79.00);
        INSERT INTO pizzasMenu (pizza_name, pizza_price) VALUES ("VICHINGA" , 79.00);
        INSERT INTO pizzasMenu (pizza_name, pizza_price) VALUES ("CALZONE SPECIALE" , 80.00);
        INSERT INTO pizzasMenu (pizza_name, pizza_price) VALUES ("ESOTICA" , 100.00);
        INSERT INTO pizzasMenu (pizza_name, pizza_price) VALUES ("TONNO" , 95.00);
        INSERT INTO pizzasMenu (pizza_name, pizza_price) VALUES ("SARDEGNA" , 80.00);
        INSERT INTO pizzasMenu (pizza_name, pizza_price) VALUES ("ROMANA" , 78.00);
        INSERT INTO pizzasMenu (pizza_name, pizza_price) VALUES ("SOLE" , 78.00);
        INSERT INTO pizzasMenu (pizza_name, pizza_price) VALUES ("BIG MAMMA" , 90.00);
        INSERT INTO pizzasMenu (pizza_name, pizza_price) VALUES ("LA SALAMI" , 80.00);
        INSERT INTO pizzasMenu (pizza_name, pizza_price) VALUES ("ROCCO" , 75.00);
        INSERT INTO pizzasMenu (pizza_name, pizza_price) VALUES ("MARCO" ,95.00);
        INSERT INTO pizzasMenu (pizza_name, pizza_price) VALUES ("KOKKODE" , 80.00);
        INSERT INTO pizzasMenu (pizza_name, pizza_price) VALUES ("ANTONELLO" , 95.00);
        INSERT INTO pizzasMenu (pizza_name, pizza_price) VALUES ("PASQUALINO" , 80.00);
        INSERT INTO pizzasMenu (pizza_name, pizza_price) VALUES ("FELIX" , 75.00);
        INSERT INTO pizzasMenu (pizza_name, pizza_price) VALUES ("BAMBINO" , 55.00);
        INSERT INTO pizzasMenu (pizza_name, pizza_price) VALUES ("ALI COOL" , 250.00);
        INSERT INTO pizzasMenu (pizza_name, pizza_price) VALUES ("BRESAOLA SPECIAL" , 110.00);
        INSERT INTO pizzasMenu (pizza_name, pizza_price) VALUES ("PARMA SPECIAL" , 90.00);
        INSERT INTO pizzasMenu (pizza_name, pizza_price) VALUES ("PEPPERONI" , 89.00);