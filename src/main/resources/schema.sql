DROP SEQUENCE IF EXISTS main_course_seq;
DROP TABLE IF EXISTS starter;
DROP TABLE IF EXISTS main_course;
DROP TABLE IF EXISTS drinks;
DROP TABLE IF EXISTS dessert;
DROP TABLE IF EXISTS menu;

CREATE TABLE menu
(
    id         INT auto_increment,
    NAME      VARCHAR(100),
    DESCRIPTION VARCHAR(100),
    PRICE      DECIMAL(10, 2),
    STARTER_ID INT,
    MAIN_COURSE_ID INT,
    primary key (id),
    unique (name)
);
CREATE TABLE starter
(
    id    INT auto_increment,
    name VARCHAR(100),
    description VARCHAR(100),
    price DECIMAL(10, 2),
    menu_id INT,
    primary key (id),
    unique (name)
);
CREATE TABLE main_course
(
    id INT AUTO_INCREMENT,
    name VARCHAR(100),
    description VARCHAR(100),
    price DECIMAL(10,2),
    menu_id INT,
    PRIMARY KEY (id),
    UNIQUE (name)
);

CREATE SEQUENCE main_course_seq
    START WITH 1
    INCREMENT BY 1;
