TRUNCATE TABLE dish;

-- Load test data into the `dish`-table
INSERT INTO dish(vegetarian, servings, ingredients, instructions)
VALUES
    (TRUE, 1, ARRAY ['tomato', 'pepper', 'eggplant'], 'Put all ingredients in the oven.'),
    (FALSE, 2, ARRAY ['chicken breast'], 'Bake the chicken breasts.'),
    (FALSE, 3, ARRAY ['salmon', 'carrot'], 'Put all ingredients in the oven.'),
    (TRUE, 4, ARRAY ['potatoes'], 'Put the potatoes in the air fryer.');
