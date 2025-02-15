-- Suppression des anciennes séquences et tables
DROP SEQUENCE IF EXISTS main_course_seq;
DROP SEQUENCE IF EXISTS starter_seq;
DROP SEQUENCE IF EXISTS desserts_seq;
DROP SEQUENCE IF EXISTS drinks_seq;
DROP SEQUENCE IF EXISTS menu_seq;

DROP TABLE IF EXISTS starter;
DROP TABLE IF EXISTS main_course;
DROP TABLE IF EXISTS drinks;
DROP TABLE IF EXISTS desserts;
DROP TABLE IF EXISTS menu;

-- Création des séquences
CREATE SEQUENCE main_course_seq
    START WITH 1
    INCREMENT BY 1;

CREATE SEQUENCE starter_seq
    START WITH 1
    INCREMENT BY 1;

CREATE SEQUENCE desserts_seq
    START WITH 1
    INCREMENT BY 1;

CREATE SEQUENCE drinks_seq
    START WITH 1
    INCREMENT BY 1;

CREATE SEQUENCE menu_seq
    START WITH 1
    INCREMENT BY 1;

-- Création des tables
CREATE TABLE menu
(
    ID         INT NOT NULL DEFAULT nextval('menu_seq'),
    NAME       VARCHAR(100),
    DESCRIPTION VARCHAR(100),
    PRICE      DECIMAL(10, 2),
    STARTER_ID INT,
    MAIN_COURSE_ID INT,
    DESSERT_ID INT,
    DRINK_ID INT,
    IMAGE_URL  VARCHAR(255),  -- Ajout du champ image_url
    PRIMARY KEY (ID),
    UNIQUE (NAME)
);

CREATE TABLE starter
(
    ID    INT NOT NULL DEFAULT nextval('starter_seq'),
    NAME  VARCHAR(100),
    DESCRIPTION VARCHAR(100),
    PRICE DECIMAL(10, 2),
    MENU_ID INT,
    IMAGE_URL VARCHAR(255),  -- Ajout du champ image_url
    PRIMARY KEY (ID),
    UNIQUE (NAME)
);

CREATE TABLE main_course
(
    ID    INT NOT NULL DEFAULT nextval('main_course_seq'),
    NAME  VARCHAR(100),
    DESCRIPTION VARCHAR(100),
    PRICE DECIMAL(10, 2),
    MENU_ID INT,
    IMAGE_URL VARCHAR(255),  -- Ajout du champ image_url
    PRIMARY KEY (ID),
    UNIQUE (NAME)
);

CREATE TABLE desserts
(
    ID    INT NOT NULL DEFAULT nextval('desserts_seq'),
    NAME  VARCHAR(100),
    DESCRIPTION VARCHAR(100),
    PRICE DECIMAL(10, 2),
    MENU_ID INT,
    IMAGE_URL VARCHAR(255),  -- Ajout du champ image_url
    PRIMARY KEY (ID),
    UNIQUE (NAME)
);

CREATE TABLE drinks
(
    ID    INT NOT NULL DEFAULT nextval('drinks_seq'),
    NAME  VARCHAR(100),
    DESCRIPTION VARCHAR(100),
    PRICE DECIMAL(10, 2),
    MENU_ID INT,
    IMAGE_URL VARCHAR(255),  -- Ajout du champ image_url
    PRIMARY KEY (ID),
    UNIQUE (NAME)
);
