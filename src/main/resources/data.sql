DELETE
FROM MAIN_COURSE;
DELETE
FROM STARTER;
DELETE
FROM MENU;

INSERT INTO MENU (NAME, DESCRIPTION, PRICE, STARTER_ID, MAIN_COURSE_ID)
VALUES ('my first menuId', 'my first menuId description', 22.0, 1, 1);
INSERT INTO STARTER (NAME, DESCRIPTION, PRICE, MENU_ID)
VALUES ('my first starter', 'my first starter description', 5.0, 1);
INSERT INTO MAIN_COURSE (NAME, DESCRIPTION, PRICE, MENU_ID)
VALUES ('my first main course', 'my first main course description', 10.0, 1);