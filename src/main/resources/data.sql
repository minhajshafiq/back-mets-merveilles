-- Vider les tables
DELETE FROM MAIN_COURSE;
DELETE FROM STARTER;
DELETE FROM DESSERTS;
DELETE FROM DRINKS;
DELETE FROM MENU;

-- Insérer un menu avec l'URL de l'image
INSERT INTO MENU (ID, NAME, DESCRIPTION, PRICE, STARTER_ID, MAIN_COURSE_ID, DESSERT_ID, DRINK_ID, IMAGE_URL)
VALUES (nextval('menu_seq'), 'my first menuId', 'my first menuId description', 22.0, NULL, NULL, NULL, NULL, 'http://example.com/menu-image.jpg');

-- Insérer un starter avec l'URL de l'image
INSERT INTO STARTER (NAME, DESCRIPTION, PRICE, MENU_ID, IMAGE_URL)
VALUES ('my first starter', 'my first starter description', 5.0, (SELECT ID FROM MENU WHERE NAME = 'my first menuId'), 'http://example.com/starter-image.jpg');

-- Insérer un plat principal avec l'URL de l'image
INSERT INTO MAIN_COURSE (NAME, DESCRIPTION, PRICE, MENU_ID, IMAGE_URL)
VALUES ('my first main course', 'my first main course description', 10.0, (SELECT ID FROM MENU WHERE NAME = 'my first menuId'), 'http://example.com/main-course-image.jpg');

-- Insérer un dessert avec l'URL de l'image
INSERT INTO DESSERTS (NAME, DESCRIPTION, PRICE, MENU_ID, IMAGE_URL)
VALUES ('my first dessert', 'my first dessert description', 7.0, (SELECT ID FROM MENU WHERE NAME = 'my first menuId'), 'http://example.com/dessert-image.jpg');

-- Insérer une boisson avec l'URL de l'image
INSERT INTO DRINKS (NAME, DESCRIPTION, PRICE, MENU_ID, IMAGE_URL)
VALUES ('my first drink', 'my first drink description', 3.0, (SELECT ID FROM MENU WHERE NAME = 'my first menuId'), 'http://example.com/drink-image.jpg');

