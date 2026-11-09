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

CREATE TABLE IF NOT EXISTS sala (
    id INT AUTO_INCREMENT PRIMARY KEY,
    cine_id INT NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    capacidad INT NOT NULL,
    CONSTRAINT fk_sala_cine FOREIGN KEY (cine_id) REFERENCES cine (id)
        ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS funcion (
    id INT AUTO_INCREMENT PRIMARY KEY,
    pelicula_id INT NOT NULL,
    sala_id INT NOT NULL,
    horario DATETIME NOT NULL,
    precio DECIMAL(10,2) NOT NULL,
    CONSTRAINT fk_funcion_pelicula FOREIGN KEY (pelicula_id) REFERENCES pelicula (id)
        ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_funcion_sala FOREIGN KEY (sala_id) REFERENCES sala (id)
        ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS usuarios (
    idUsuario INT AUTO_INCREMENT PRIMARY KEY,
    usuario VARCHAR(100) NOT NULL,
    clave VARCHAR(100) NOT NULL
);
