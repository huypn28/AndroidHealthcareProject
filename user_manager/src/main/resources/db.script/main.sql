CREATE DATABASE user_manager;

USE user_manager;

GRANT ALL PRIVILEGES ON user_manager.* TO 'root'@'localhost' ;

CREATE TABLE users(
    id INT NOT NULL AUTO_INCREMENT,
    username VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE cart (
    id INTEGER PRIMARY KEY,
    user_id INTEGER,
    product VARCHAR(255) NOT NULL,
    price FLOAT,
    otype VARCHAR(255) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE orderplace (
    id INTEGER PRIMARY KEY,
    user_id INTEGER,
    fullname VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL,
    contact VARCHAR(255) NOT NULL,
    age INT,
    date VARCHAR(255) NOT NULL,
    time VARCHAR(255) NOT NULL,
    amount FLOAT,
    otype VARCHAR(255) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id)
);


