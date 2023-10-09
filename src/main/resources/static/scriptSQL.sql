CREATE DATABASE enemigosbdd;

USE enemigosbdd;

CREATE TABLE enemigos (
    id_enemigo INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    descripcion TEXT,
    nivel INT,
    recompensa INT,
    fuerza INT,
    defensa INT,
    vida_original INT,
    vida_actual INT,
    vencido BOOLEAN,
    activo BOOLEAN
);

INSERT INTO enemigos (nombre, descripcion, nivel, recompensa, fuerza, defensa, vida_original, vida_actual, vencido, activo)
VALUES
  ('Dragón de Fuego', 'Un dragón escupe fuego y vuela alto.', 10, 1000, 90, 70, 1000, 1000, 0, 1),
  ('Orco Guerrero', 'Un orco fuerte con un hacha gigante.', 5, 300, 60, 40, 500, 500, 0, 1),
  ('Esqueleto Mago', 'Un esqueleto con poderes mágicos.', 8, 200, 70, 50, 800, 800, 0, 1),
  ('Goblin Ladrón', 'Un goblin astuto que roba tesoros.', 3, 100, 40, 30, 300, 300, 0, 1),
  ('Hidra Venenosa', 'Una criatura con múltiples cabezas venenosas.', 12, 1000, 110, 80, 1500, 1500, 0, 1),
  ('Bandido Jefe', 'El líder de una banda de bandidos.', 6, 600, 70, 50, 600, 600, 0, 1),
  ('Lobo Gigante', 'Un lobo enorme con colmillos afilados.', 4, 500, 50, 35, 400, 400, 0, 1),
  ('Demonio de las Sombras', 'Un demonio que acecha en la oscuridad.', 9, 1000, 80, 60, 900, 900, 0, 1),
  ('Gólem de Piedra', 'Una criatura hecha de roca sólida.', 7, 800, 65, 55, 700, 700, 0, 1),
  ('Bruja Malvada', 'Una bruja que lanza hechizos maleficiosos.', 5, 700, 55, 45, 550, 550, 0, 1);

