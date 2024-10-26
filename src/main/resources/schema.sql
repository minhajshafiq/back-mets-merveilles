DROP TABLE IF EXISTS menu;
CREATE TABLE menu(id INT auto_increment, label VARCHAR(100), primary key(id), unique(label));