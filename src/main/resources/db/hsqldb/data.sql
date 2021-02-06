

	-- One Director user, named Director with passwor director1 and authority director
INSERT INTO users(username,password,enabled) VALUES ('director1','director1',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (1,'director1','director');
-- One owner user, named owner1 with passwor 0wn3r
INSERT INTO users(username,password,enabled) VALUES ('dueno1','dueno1',TRUE);
INSERT INTO users(username,password,enabled) VALUES ('educielan','1111',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (4,'educielan','duenoadoptivo');
INSERT INTO authorities(id,username,authority) VALUES (2,'dueno1','duenoadoptivo');

--Users
INSERT INTO users(username,password,enabled) VALUES ('dueno2','dueno2',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (12,'dueno2','duenoadoptivo');
INSERT INTO users(username,password,enabled) VALUES ('dueno3','dueno3',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (13,'dueno3','duenoadoptivo');
INSERT INTO users(username,password,enabled) VALUES ('dueno4','dueno4',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (14,'dueno4','duenoadoptivo');
INSERT INTO users(username,password,enabled) VALUES ('dueno5','dueno5',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (15,'dueno5','duenoadoptivo');
INSERT INTO users(username,password,enabled) VALUES ('dueno6','dueno6',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (16,'dueno6','duenoadoptivo');
INSERT INTO users(username,password,enabled) VALUES ('dueno7','dueno7',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (17,'dueno7','duenoadoptivo');
INSERT INTO users(username,password,enabled) VALUES ('dueno8','dueno8',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (18,'dueno8','duenoadoptivo');
INSERT INTO users(username,password,enabled) VALUES ('dueno9','dueno9',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (19,'dueno9','duenoadoptivo');
INSERT INTO users(username,password,enabled) VALUES ('dueno10','dueno10',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (20,'dueno10','duenoadoptivo');





--Añadir josdurgar1 como owner
-- One owner user, named owner1 with passwor 0wn3r
INSERT INTO users(username,password,enabled) VALUES ('josdurgar1','j0$durg4r1',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (5,'josdurgar1','duenoadoptivo');

--Cuidadores
INSERT INTO users(username,password,enabled) VALUES ('cuidador1','cuidador1',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (6,'cuidador1','cuidador');
INSERT INTO users(username,password,enabled) VALUES ('cuidador2','cuidador2',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (7,'cuidador2','cuidador');
INSERT INTO users(username,password,enabled) VALUES ('cuidador3','cuidador3',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (8,'cuidador3','cuidador');
INSERT INTO users(username,password,enabled) VALUES ('cuidador4','cuidador4',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (9,'cuidador4','cuidador');
INSERT INTO users(username,password,enabled) VALUES ('cuidador5','cuidador5',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (10,'cuidador5','cuidador');

--Añadir un director
INSERT INTO director (id, nombre, apellidos, telefono, email, username) VALUES (13, 'LEO','FERNANDEZ', '222222222', 'Di@mail.com',  'director1');

-- Añadir centro de adopcion
INSERT INTO centros(id, nombre, direccion, cantidad_max,director_id) VALUES (1, 'CENTRO DE ADOPCIÓN 1', 'Dirección 1', 20,13);
INSERT INTO centros(id, nombre, direccion, cantidad_max,director_id) VALUES (2, 'CENTRO DE ADOPCIÓN 2', 'Dirección 2', 2,13);
INSERT INTO centros(id, nombre, direccion, cantidad_max,director_id) VALUES (3, 'CENTRO DE ADOPCIÓN 3', 'Dirección 3', 50,13);

--Añadir Cuidadores
INSERT INTO cuidador (id, nombre, apellidos, telefono, email, dni, username, centro_de_adopcion_id) VALUES (1, 'JOSE ANTONIO', 'OLTRA GAYA', '754 44 51 11', 'cuiador1@mail.com', '87371991M',  'cuidador1','1');
INSERT INTO cuidador (id, nombre, apellidos, telefono, email, dni, username, centro_de_adopcion_id) VALUES (2, 'DAVID', 'CARRANZA ARNAU', '605 38 90 49', 'cuidador2@mail.com', '40257330Q',  'cuidador2','2');
INSERT INTO cuidador (id, nombre, apellidos, telefono, email, dni, username, centro_de_adopcion_id) VALUES (3, 'PILAR',  'MOYANO SIMO', '679 47 21 44', 'cuidador3@mail.com', '81494400H',  'cuidador3','3');
INSERT INTO cuidador (id, nombre, apellidos, telefono, email, dni, username, centro_de_adopcion_id) VALUES (4, 'MARIA NIEVES', 'NOVAS BAREA', '705 74 05 46', 'cuidador4@mail.com', '2538440E',  'cuidador4','1');
INSERT INTO cuidador (id, nombre, apellidos, telefono, email, dni, username, centro_de_adopcion_id) VALUES (5, 'FELIX', 'LOIS ESQUINAS', '663 11 70 08', 'cuidador5@mail.com', '37685775E',  'cuidador5','2');

--

INSERT INTO duenos VALUES (1, 'Franklin',  'em@mail.com','George', '6085551023','110 W. Liberty St.', '45555678W',  'dueno1');
INSERT INTO duenos VALUES (2, 'Davis',   'em@mail.com','Betty','6085551749','638 Cardinal Ave.', '45555678W',  'dueno2');
INSERT INTO duenos VALUES (3, 'Rodriquez',   'em@mail.com','Eduardo','6085558763','2693 Commerce St.', '45555678W',  'dueno3');
INSERT INTO duenos VALUES (4, 'Davis',   'em@mail.com','Harold','6085553198','563 Friendly St.', '45555678W',  'dueno4');
INSERT INTO duenos VALUES (5,  'McTavish',  'em@mail.com','Peter','6085552765','2387 S. Fair Way', '45555678W',  'dueno5');
INSERT INTO duenos VALUES (6, 'Coleman',   'em@mail.com','Jean','6085552654','105 N. Lake St.', '45555678W',  'dueno6');
INSERT INTO duenos VALUES (7, 'Black',   'em@mail.com','Jeff','6085555387','1450 Oak Blvd.', '45555678W',  'dueno7');
INSERT INTO duenos VALUES (8, 'Escobito',   'em@mail.com','Maria','6085557683','345 Maple St.', '45555678W',  'dueno8');
INSERT INTO duenos VALUES (9, 'Schroeder',   'em@mail.com','David','6085559435','2749 Blackhawk Trail', '45555678W',  'dueno9');
INSERT INTO duenos VALUES (10, 'Estaban',   'em@mail.com','Carlos','6085555487','2335 Independence La.', '45555678W',  'dueno10');

--Añadir josdurgar1 como owner
INSERT INTO duenos VALUES (11, 'Durán',   'em@mail.com','Jose Manuel', '680464646','123 C/Falsa', '45555678W',  'josdurgar1');
INSERT INTO duenos VALUES (12, 'Ciezar',   'em@mail.com','Eduardo','1111111111','Av.Reina Mercedes', '45555678W',  'educielan');




--Añadir Categoria
INSERT INTO categoria(id, tipo, raza) VALUES(1,1, 'husky');
INSERT INTO categoria(id, tipo, raza) VALUES(2,1, 'Agua');
INSERT INTO categoria(id, tipo, raza) VALUES(3,1, 'pequines');

--Añadir animales
INSERT INTO animales(id, adoptado, atencion, dificultad, chip, fecha_nacimiento, 
	primera_incorporacion, ultima_incorporacion, nombre, numero_registro,
	grado, licencia,licenciarequerida, seguro, sexo, tamanyo, categoria_id,
	cuidador_id, centro_id, foto) VALUES(1, true, 3, 3, 'CHIP123','2019-10-01', '2020-09-01', '2020-10-01',
	'Bruno', 'Nregistro1', 3, true, true, true, 'Masculino', 'Mediano', 1, 1,1, 'https://cutt.ly/HhSsxwu');


INSERT INTO animales(id, adoptado, atencion, dificultad, chip, fecha_nacimiento, 
	primera_incorporacion, ultima_incorporacion, nombre, numero_registro,
	grado, licencia,licenciarequerida, seguro, sexo, tamanyo, categoria_id,
	cuidador_id, centro_id, foto) VALUES(2, true, 3, 3, 'CHIP123','2015-01-04', '2020-09-01', '2020-10-01',
	'Pepito', 'Nregistro2', 3, true, true, true, 'Masculino', 'Pequeño', 1, 1,1, 'https://cutt.ly/ShSfSBE');

INSERT INTO animales(id, adoptado, atencion, dificultad, chip, fecha_nacimiento, 
	primera_incorporacion, ultima_incorporacion, nombre, numero_registro,
	grado, licencia,licenciarequerida, seguro, sexo, tamanyo, categoria_id,
	cuidador_id, centro_id, foto) VALUES(3, false, 3, 3, 'CHIP123','2015-01-31', '2020-09-01', '2020-10-01',
	'Loki', 'Nregistro3', 3, true, true, true, 'Masculino', 'Grande', 1, 1,1, 'https://cutt.ly/DhSfKbv');

	INSERT INTO animales(id, adoptado, atencion, dificultad, chip, fecha_nacimiento, 
	primera_incorporacion, ultima_incorporacion, nombre, numero_registro,
	grado, licencia,licenciarequerida, seguro, sexo, tamanyo, categoria_id,
	cuidador_id, centro_id, foto) VALUES(4, false, 3, 3, 'CHIP123','2015-01-31', '2020-09-01', '2020-10-01',
	'Toby', 'Nregistro3', 3, true, true, true, 'Masculino', 'Grande', 1, 1,3, 'https://cutt.ly/DhSfKbv');
	INSERT INTO animales(id, adoptado, atencion, dificultad, chip, fecha_nacimiento, 
	primera_incorporacion, ultima_incorporacion, nombre, numero_registro,
	grado, licencia,licenciarequerida, seguro, sexo, tamanyo, categoria_id,
	cuidador_id, centro_id, foto) VALUES(5, false, 3, 3, 'CHIP123','2015-01-31', '2020-09-01', '2020-10-01',
	'Thor', 'Nregistro3', 3, true, true, true, 'Masculino', 'Grande', 1, 1,3, 'https://cutt.ly/DhSfKbv');
	INSERT INTO animales(id, adoptado, atencion, dificultad, chip, fecha_nacimiento, 
	primera_incorporacion, ultima_incorporacion, nombre, numero_registro,
	grado, licencia,licenciarequerida, seguro, sexo, tamanyo, categoria_id,
	cuidador_id, centro_id, foto) VALUES(6, false, 3, 3, 'CHIP123','2015-01-31', '2020-09-01', '2020-10-01',
	'Chipirón', 'Nregistro3', 3, true, true, true, 'Masculino', 'Grande', 1, 1,3, 'https://cutt.ly/DhSfKbv');
	INSERT INTO animales(id, adoptado, atencion, dificultad, chip, fecha_nacimiento, 
	primera_incorporacion, ultima_incorporacion, nombre, numero_registro,
	grado, licencia,licenciarequerida, seguro, sexo, tamanyo, categoria_id,
	cuidador_id, centro_id, foto) VALUES(7, false, 3, 3, 'CHIP123','2015-01-31', '2020-09-01', '2020-10-01',
	'Lucky', 'Nregistro3', 3, true, true, true, 'Masculino', 'Grande', 1, 1,3, 'https://cutt.ly/DhSfKbv');
	INSERT INTO animales(id, adoptado, atencion, dificultad, chip, fecha_nacimiento, 
	primera_incorporacion, ultima_incorporacion, nombre, numero_registro,
	grado, licencia,licenciarequerida, seguro, sexo, tamanyo, categoria_id,
	cuidador_id, centro_id, foto) VALUES(8, false, 3, 3, 'CHIP123','2015-01-31', '2020-09-01', '2020-10-01',
	'Coco', 'Nregistro3', 3, true, true, true, 'Masculino', 'Grande', 1, 1,3, 'https://cutt.ly/DhSfKbv');
	INSERT INTO animales(id, adoptado, atencion, dificultad, chip, fecha_nacimiento, 
	primera_incorporacion, ultima_incorporacion, nombre, numero_registro,
	grado, licencia,licenciarequerida, seguro, sexo, tamanyo, categoria_id,
	cuidador_id, centro_id, foto) VALUES(9, false, 3, 3, 'CHIP123','2015-01-31', '2020-09-01', '2020-10-01',
	'Nala', 'Nregistro3', 3, true, true, true, 'Hembra', 'Grande', 1, 1,3, 'https://cutt.ly/DhSfKbv');
	INSERT INTO animales(id, adoptado, atencion, dificultad, chip, fecha_nacimiento, 
	primera_incorporacion, ultima_incorporacion, nombre, numero_registro,
	grado, licencia,licenciarequerida, seguro, sexo, tamanyo, categoria_id,
	cuidador_id, centro_id, foto) VALUES(10, false, 3, 3, 'CHIP123','2015-01-31', '2020-09-01', '2020-10-01',
	'Lola', 'Nregistro3', 3, true, true, true, 'Hembra', 'Grande', 1, 1,3, 'https://cutt.ly/DhSfKbv');
	INSERT INTO animales(id, adoptado, atencion, dificultad, chip, fecha_nacimiento, 
	primera_incorporacion, ultima_incorporacion, nombre, numero_registro,
	grado, licencia,licenciarequerida, seguro, sexo, tamanyo, categoria_id,
	cuidador_id, centro_id, foto) VALUES(11, false, 3, 3, 'CHIP123','2015-01-31', '2020-09-01', '2020-10-01',
	'Monroe', 'Nregistro3', 3, true, true, true, 'Hembra', 'Grande', 1, 1,3, 'https://cutt.ly/DhSfKbv');
	INSERT INTO animales(id, adoptado, atencion, dificultad, chip, fecha_nacimiento, 
	primera_incorporacion, ultima_incorporacion, nombre, numero_registro,
	grado, licencia,licenciarequerida, seguro, sexo, tamanyo, categoria_id,
	cuidador_id, centro_id, foto) VALUES(12, false, 3, 3, 'CHIP123','2015-01-31', '2020-09-01', '2020-10-01',
	'Lana', 'Nregistro3', 3, true, true, true, 'Hembra', 'Grande', 1, 1,3, 'https://cutt.ly/DhSfKbv');
	INSERT INTO animales(id, adoptado, atencion, dificultad, chip, fecha_nacimiento, 
	primera_incorporacion, ultima_incorporacion, nombre, numero_registro,
	grado, licencia,licenciarequerida, seguro, sexo, tamanyo, categoria_id,
	cuidador_id, centro_id, foto) VALUES(13, false, 3, 3, 'CHIP123','2015-01-31', '2020-09-01', '2020-10-01',
	'Trufa', 'Nregistro3', 3, true, true, true, 'Hembra', 'Grande', 1, 1,3, 'https://cutt.ly/DhSfKbv');
	INSERT INTO animales(id, adoptado, atencion, dificultad, chip, fecha_nacimiento, 
	primera_incorporacion, ultima_incorporacion, nombre, numero_registro,
	grado, licencia,licenciarequerida, seguro, sexo, tamanyo, categoria_id,
	cuidador_id, centro_id, foto) VALUES(14, false, 3, 3, 'CHIP123','2015-01-31', '2020-09-01', '2020-10-01',
	'Peluchina', 'Nregistro3', 3, true, true, true, 'Hembra', 'Grande', 1, 1,3, 'https://cutt.ly/DhSfKbv');
	INSERT INTO animales(id, adoptado, atencion, dificultad, chip, fecha_nacimiento, 
	primera_incorporacion, ultima_incorporacion, nombre, numero_registro,
	grado, licencia,licenciarequerida, seguro, sexo, tamanyo, categoria_id,
	cuidador_id, centro_id, foto) VALUES(15, false, 3, 3, 'CHIP123','2015-01-31', '2020-09-01', '2020-10-01',
	'Napoleón', 'Nregistro3', 3, true, true, true, 'Masculino', 'Grande', 1, 1,3, 'https://cutt.ly/DhSfKbv');
	INSERT INTO animales(id, adoptado, atencion, dificultad, chip, fecha_nacimiento, 
	primera_incorporacion, ultima_incorporacion, nombre, numero_registro,
	grado, licencia,licenciarequerida, seguro, sexo, tamanyo, categoria_id,
	cuidador_id, centro_id, foto) VALUES(16, false, 3, 3, 'CHIP123','2015-01-31', '2020-09-01', '2020-10-01',
	'Bruno', 'Nregistro3', 3, true, true, true, 'Masculino', 'Grande', 1, 1,3, 'https://cutt.ly/DhSfKbv');
	INSERT INTO animales(id, adoptado, atencion, dificultad, chip, fecha_nacimiento, 
	primera_incorporacion, ultima_incorporacion, nombre, numero_registro,
	grado, licencia,licenciarequerida, seguro, sexo, tamanyo, categoria_id,
	cuidador_id, centro_id, foto) VALUES(17, false, 3, 3, 'CHIP123','2015-01-31', '2020-09-01', '2020-10-01',
	'Penny', 'Nregistro3', 3, true, true, true, 'Hembra', 'Grande', 1, 1,3, 'https://cutt.ly/DhSfKbv');
	INSERT INTO animales(id, adoptado, atencion, dificultad, chip, fecha_nacimiento, 
	primera_incorporacion, ultima_incorporacion, nombre, numero_registro,
	grado, licencia,licenciarequerida, seguro, sexo, tamanyo, categoria_id,
	cuidador_id, centro_id, foto) VALUES(18, false, 3, 3, 'CHIP123','2015-01-31', '2020-09-01', '2020-10-01',
	'Bella', 'Nregistro3', 3, true, true, true, 'Hembra', 'Grande', 2, 1,3, 'https://cutt.ly/DhSfKbv');
	
	
--Añadir Adopcion
INSERT INTO adopciones(id, unidad_familiar, mayores_de_edad, leido_requisitos, permiso_comunidad_vecinos, otros_animales, motivo, estado, motivo_decision, fecha_decision, dueno_id, animal_id ) 
	VALUES(1, 4, 3, true, true, true, 'Motivo 1', 1, 'MotivoDecision 1', '2019-09-01',1,2);
INSERT INTO adopciones(id, unidad_familiar, mayores_de_edad, leido_requisitos, permiso_comunidad_vecinos, otros_animales, motivo, estado, motivo_decision, fecha_decision,dueno_id, animal_id) 
	VALUES(2, 3, 2, false, false, false, 'Motivo 2', 2, 'MotivoDecision 2','2019-10-01',2,2);
INSERT INTO adopciones(id, unidad_familiar, mayores_de_edad, leido_requisitos, permiso_comunidad_vecinos, otros_animales, motivo, estado, motivo_decision, fecha_decision, dueno_id, animal_id ) 
	VALUES(3, 2, 2, true, true, false, 'Motivo 3', 0, 'MotivoDecision 3', '2020-09-01',11,3);

--Añadir Visitas
INSERT INTO visitas(id, lugar, momento, animal_id, cuidador_id, dueno_id)
	VALUES (1, 'centroNuevo', '2010-09-07', 3, 1, 11);
	INSERT INTO visitas(id, lugar, momento, animal_id, cuidador_id, dueno_id)
	VALUES (2, 'centroNuevo', '2019-09-07', 2, 1, 11);
	INSERT INTO visitas(id, lugar, momento, animal_id, cuidador_id, dueno_id)
	VALUES (3, 'centroNuevo', '2110-09-07', 1, 2, 11);
	
--Añadir Eventos	
INSERT INTO eventos(id, titulo, direccion, fecha, aforo, descripcion, DIRECTOR_ID)
	VALUES (1, 'Evento nuevo', 'C/falsa 4D', '2021-12-11', 25, 'Evento Nuevo predefinido', 13);
INSERT INTO eventos(id, titulo, direccion, fecha, aforo, descripcion, DIRECTOR_ID)
	VALUES (2, 'Evento nuevo2', 'C/falsa 2x4D', '2020-12-15', 23, 'Evento Nuevo predefinido2', 13);
INSERT INTO eventos(id, titulo, direccion, fecha, aforo, descripcion, DIRECTOR_ID)
	VALUES (3, 'Evento nuevo', 'C/falsa 4D', '2021-12-11', 0, 'Evento Nuevo predefinido', 13);
	
INSERT INTO rel_eventos_cuidadores(evento_id, cuidador_id) VALUES (1,1); 	
INSERT INTO rel_eventos_cuidadores(evento_id, cuidador_id) VALUES (1,2); 	
INSERT INTO rel_eventos_cuidadores(evento_id, cuidador_id) VALUES (3,1); 
INSERT INTO rel_eventos_duenos(evento_id, dueno_id) VALUES (3,1); 

--Comentarios
INSERT INTO comentarios(id, comentario, momento, CUIDADOR_ID, DIRECTOR_ID ,DUENO_ID, VISITA_ID)VALUES(1,'Comentario 1','2020-12-11 10:10:10',null,null,11,1);
INSERT INTO comentarios(id, comentario, momento, CUIDADOR_ID, DIRECTOR_ID ,DUENO_ID, VISITA_ID)VALUES(2,'Comentario 2','2020-12-11',1,null,null,1);
INSERT INTO comentarios(id, comentario, momento, CUIDADOR_ID, DIRECTOR_ID ,DUENO_ID, VISITA_ID)VALUES(3,'Comentario 3','2020-12-11',null,null,11,1);
INSERT INTO comentarios(id, comentario, momento, CUIDADOR_ID, DIRECTOR_ID ,DUENO_ID, VISITA_ID)VALUES(4,'Comentario 4','2020-12-11',1,null,null,1);
INSERT INTO comentarios(id, comentario, momento, CUIDADOR_ID, DIRECTOR_ID ,DUENO_ID, VISITA_ID)VALUES(5,'Comentario 5','2020-12-11',null,null,11,1);