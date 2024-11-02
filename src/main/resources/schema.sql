DROP TABLE IF EXISTS menu;
CREATE TABLE menu(id INT auto_increment, label VARCHAR(100), primary key(id), unique(label));
CREATE TABLE item(id INT auto_increment, name VARCHAR(100), description VARCHAR(100), price DOUBLE, type VARCHAR(100), size VARCHAR(100), volume VARCHAR(100), availableForClients BOOLEAN, menu_id INT, primary key(id), foreign key(menu_id) references menu(id));