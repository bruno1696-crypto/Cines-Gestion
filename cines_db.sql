-- Bruno Mercado


CREATE DATABASE IF NOT EXISTS cines_db;
USE cines_db;

CREATE TABLE IF NOT EXISTS cine (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    salas INT NOT NULL,
    abierto BOOLEAN NOT NULL,
    fechaInauguracion DATE NOT NULL
);

CREATE TABLE IF NOT EXISTS pelicula (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(150) NOT NULL,
    duracion INT NOT NULL,
    disponible BOOLEAN NOT NULL,
    fechaEstreno DATE NOT NULL
);
