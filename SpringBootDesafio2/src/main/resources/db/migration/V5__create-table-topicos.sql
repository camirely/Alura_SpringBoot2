CREATE TABLE topicos (
                         id BIGINT NOT NULL AUTO_INCREMENT,
                         titulo VARCHAR(100) NOT NULL,
                         mensaje VARCHAR(500) NOT NULL ,
                         fecha_creacion DATETIME NOT NULL,
                         status TINYINT NOT NULL,
                         usuario_id BIGINT NOT NULL,    -- Asumo que la clave foránea de 'usuario' es 'usuarios_id'
                         curso_id BIGINT NOT NULL,      -- Asumo que la clave foránea de 'curso' es 'cursos_id'
                         PRIMARY KEY(id),
                         CONSTRAINT fk_usuario_id FOREIGN KEY (usuario_id) REFERENCES usuarios(id),
                         CONSTRAINT fk_curso_id FOREIGN KEY (curso_id) REFERENCES cursos(id)
);