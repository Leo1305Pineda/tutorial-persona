create table asistencias(
	id  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
	asistencia varchar(10)  NOT NULL DEFAULT 'asistio',
	comentario varchar(300),
	fecha datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
	persona_id INTEGER REFERENCES personas(id) 
);