create database if not exists superdatashow_db;
use superdatashow_db;

CREATE TABLE states (
	id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    uf CHAR(2) NOT NULL
);

CREATE TABLE cities (
	id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    state_id INT NOT NULL,
    CONSTRAINT fk_city_state FOREIGN KEY (state_id) REFERENCES states (id)
);

CREATE TABLE projectors (
	id INT AUTO_INCREMENT PRIMARY KEY,
    brand VARCHAR(255) NOT NULL,
    model VARCHAR(255) NOT NULL,
    serial_number VARCHAR(100) NOT NULL UNIQUE,
    purchase_date DATE,
    ansi_lumens INT,
    date_last_lamp_change DATE,
    projector_state VARCHAR(100) DEFAULT 'Disponível'
);

CREATE TABLE clients (
	id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255),
    telephone VARCHAR(20),
    street VARCHAR(100),
    city_id INT NOT NULL,
    CONSTRAINT fk_client_city FOREIGN KEY (city_id) REFERENCES cities (id)
);

CREATE TABLE physical_persons (
	id INT NOT NULL PRIMARY KEY,
    cpf CHAR(11) NOT NULL UNIQUE,
    rg CHAR(10) NOT NULL,
    birthday DATE,
    CONSTRAINT fk_physical_person_client FOREIGN KEY (id) REFERENCES clients (id)
);

CREATE TABLE juridical_persons (
	id INT NOT NULL PRIMARY KEY,
    cnpj CHAR(14) NOT NULL UNIQUE,
    CONSTRAINT fk_juridical_person_client FOREIGN KEY (id) REFERENCES clients (id)
);

CREATE TABLE locations (
	id INT AUTO_INCREMENT PRIMARY KEY,
    location_date DATE NOT NULL,
    devolution_date DATE NOT NULL,
    price DECIMAL (7, 2),
    returned BOOLEAN DEFAULT 0,
    client_id INT NOT NULL,
    projector_id INT NOT NULL,
    CONSTRAINT fk_location_client FOREIGN KEY (client_id) REFERENCES clients (id),
    CONSTRAINT fk_location_projector FOREIGN KEY (projector_id) REFERENCES projectors (id)
);

