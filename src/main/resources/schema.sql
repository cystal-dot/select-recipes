DROP TABLE IF EXISTS cooking;

CREATE TABLE cooking
(
   DishId INT NOT NULL AUTO_INCREMENT,
   Dishname VARCHAR(100),
   Genre VARCHAR(100),
   PRIMARY KEY(DishId)
);

DROP TABLE IF EXISTS ingredients;

CREATE TABLE ingredients
(
   DishId INT,
   Ingredient1 VARCHAR(100),
   Ingredient2 VARCHAR(100),
   Ingredient3 VARCHAR(100),
   Ingredient4 VARCHAR(100)
);