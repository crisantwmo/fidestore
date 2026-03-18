CREATE TABLE IF NOT EXISTS usuario (
    id_usuario   BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre       VARCHAR(100)  NOT NULL,
    correo       VARCHAR(150)  NOT NULL UNIQUE,
    contrasena   VARCHAR(255)  NOT NULL,
    rol          VARCHAR(20)   NOT NULL DEFAULT 'USUARIO',
    activo       BOOLEAN       NOT NULL DEFAULT TRUE
);

CREATE TABLE IF NOT EXISTS categoria (
    id_categoria  BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre        VARCHAR(100) NOT NULL,
    descripcion   VARCHAR(500)
);

CREATE TABLE IF NOT EXISTS producto (
    id_producto   BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre        VARCHAR(150)   NOT NULL,
    descripcion   VARCHAR(1000),
    precio        DECIMAL(10,2)  NOT NULL,
    stock         INT            NOT NULL DEFAULT 0,
    url_imagen    VARCHAR(500),
    activo        BOOLEAN        NOT NULL DEFAULT TRUE,
    id_categoria  BIGINT,
    id_vendedor   BIGINT,
    CONSTRAINT fk_prod_categoria FOREIGN KEY (id_categoria) REFERENCES categoria(id_categoria),
    CONSTRAINT fk_prod_vendedor  FOREIGN KEY (id_vendedor)  REFERENCES usuario(id_usuario)
);

INSERT IGNORE INTO categoria (nombre, descripcion) VALUES
    ('Consolas de 8 bits',  'NES, Atari, ColecoVision y similares'),
    ('Consolas de 16 bits', 'Super Nintendo, Sega Genesis y similares'),
    ('Portátiles',          'Game Boy, Game Gear y consolas portátiles vintage'),
    ('Accesorios',          'Controles, cables y periféricos retro'),
    ('Hardware PC',         'Tarjetas, procesadores y memorias vintage');
