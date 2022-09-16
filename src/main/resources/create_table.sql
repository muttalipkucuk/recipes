DROP TABLE IF EXISTS dish;

-- Create new table named `dish`
CREATE TABLE dish(
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    vegetarian BOOLEAN,
    servings INT,
    ingredients TEXT ARRAY,
    instructions TEXT);
