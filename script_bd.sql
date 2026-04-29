-- Borrar si existe para poder probar desde cero
DROP SCHEMA IF EXISTS catalogo_juegos;
CREATE SCHEMA catalogo_juegos;
USE catalogo_juegos;

CREATE TABLE videojuego (
    id_videojuego INT NOT NULL AUTO_INCREMENT,
    titulo VARCHAR(100) NOT NULL,
    desarrollador VARCHAR(100),
    genero VARCHAR(50),
    precio DECIMAL(10,2),
    ruta_imagen VARCHAR(1024), -- Tu tabla actual tiene 1024 según la foto
    PRIMARY KEY (id_videojuego)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Insertar datos de prueba iniciales (opcional pero recomendado)
INSERT INTO videojuego (titulo, desarrollador, genero, precio, ruta_imagen) 
VALUES ('God of War', 'Santa Monica Studio', 'Action', 50.00, 'https://picsum.photos/200');
