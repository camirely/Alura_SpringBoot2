CREATE TABLE respuestas (
                            id BIGINT NOT NULL AUTO_INCREMENT,
                            mensaje VARCHAR(500) NOT NULL,
                            fecha_creacion DATETIME NOT NULL,
                            solucion VARCHAR(500) NOT NULL,
                            topico_id BIGINT NOT NULL,  -- Clave foránea para 'topicos'
                            usuario_id BIGINT NOT NULL, -- Clave foránea para 'usuario'
                            PRIMARY KEY(id),
                            CONSTRAINT fk_topico_id FOREIGN KEY (topico_id) REFERENCES topicos(id),
                            CONSTRAINT fk_respuestas_usuario_id FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
);