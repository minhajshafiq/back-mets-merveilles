-- Vider les tables
DELETE FROM MAIN_COURSE;
DELETE FROM STARTER;
DELETE FROM DESSERTS;
DELETE FROM DRINKS;
DELETE FROM MENU;

-- Insérer un menu
INSERT INTO MENU (ID, NAME, DESCRIPTION, PRICE, STARTER_ID, MAIN_COURSE_ID, DESSERT_ID, DRINK_ID)
VALUES (nextval('menu_seq'), 'my first menuId', 'my first menuId description', 22.0, NULL, NULL, NULL, NULL);

-- Insérer un starter
INSERT INTO STARTER (NAME, DESCRIPTION, PRICE, MENU_ID)
VALUES ('my first starter', 'my first starter description', 5.0, (SELECT ID FROM MENU WHERE NAME = 'my first menuId'));

-- Insérer un plat principal
INSERT INTO MAIN_COURSE (NAME, DESCRIPTION, PRICE, MENU_ID)
VALUES ('my first main course', 'my first main course description', 10.0, (SELECT ID FROM MENU WHERE NAME = 'my first menuId'));

-- Insérer un dessert
INSERT INTO DESSERTS (NAME, DESCRIPTION, PRICE, MENU_ID)
VALUES ('my first dessert', 'my first dessert description', 7.0, (SELECT ID FROM MENU WHERE NAME = 'my first menuId'));

-- Insérer une boisson
INSERT INTO DRINKS (NAME, DESCRIPTION, PRICE, MENU_ID)
VALUES ('my first drink', 'my first drink description', 3.0, (SELECT ID FROM MENU WHERE NAME = 'my first menuId'));
