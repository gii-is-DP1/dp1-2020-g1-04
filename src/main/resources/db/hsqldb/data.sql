
	-- One Director user, named Director with passwor director1 and authority director
INSERT INTO users(username,password,enabled) VALUES ('director1','director1',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (1,'director1','director');
-- One owner user, named owner1 with passwor 0wn3r
INSERT INTO users(username,password,enabled) VALUES ('owner1','0wn3r',TRUE);
INSERT INTO users(username,password,enabled) VALUES ('educielan','1111',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (4,'educielan','duenoadoptivo');
INSERT INTO authorities(id,username,authority) VALUES (2,'owner1','duenoadoptivo');

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
INSERT INTO cuidador (id, nombre, apellidos, telefono, email, dni, username) VALUES (1, 'JOSE ANTONIO', 'OLTRA GAYA', '754 44 51 11', 'cuiador1@mail.com', '87371991M',  'cuidador1');
INSERT INTO cuidador (id, nombre, apellidos, telefono, email, dni, username) VALUES (2, 'DAVID', 'CARRANZA ARNAU', '605 38 90 49', 'cuidador2@mail.com', '40257330Q',  'cuidador2');
INSERT INTO cuidador (id, nombre, apellidos, telefono, email, dni, username) VALUES (3, 'PILAR',  'MOYANO SIMO', '679 47 21 44', 'cuidador3@mail.com', '81494400H',  'cuidador3');
INSERT INTO cuidador (id, nombre, apellidos, telefono, email, dni, username) VALUES (4, 'MARIA NIEVES', 'NOVAS BAREA', '705 74 05 46', 'cuidador4@mail.com', '2538440E',  'cuidador4');
INSERT INTO cuidador (id, nombre, apellidos, telefono, email, dni, username) VALUES (5, 'FELIX', 'LOIS ESQUINAS', '663 11 70 08', 'cuidador5@mail.com', '37685775E',  'cuidador5');

--

INSERT INTO duenos VALUES (1, 'Franklin',  'em@mail.com','George', '6085551023','110 W. Liberty St.', '45555678W',  'owner1');
INSERT INTO duenos VALUES (2, 'Davis',   'em@mail.com','Betty','6085551749','638 Cardinal Ave.', '45555678W',  'owner1');
INSERT INTO duenos VALUES (3, 'Rodriquez',   'em@mail.com','Eduardo','6085558763','2693 Commerce St.', '45555678W',  'owner1');
INSERT INTO duenos VALUES (4, 'Davis',   'em@mail.com','Harold','6085553198','563 Friendly St.', '45555678W',  'owner1');
INSERT INTO duenos VALUES (5,  'McTavish',  'em@mail.com','Peter','6085552765','2387 S. Fair Way', '45555678W',  'owner1');
INSERT INTO duenos VALUES (6, 'Coleman',   'em@mail.com','Jean','6085552654','105 N. Lake St.', '45555678W',  'owner1');
INSERT INTO duenos VALUES (7, 'Black',   'em@mail.com','Jeff','6085555387','1450 Oak Blvd.', '45555678W',  'owner1');
INSERT INTO duenos VALUES (8, 'Escobito',   'em@mail.com','Maria','6085557683','345 Maple St.', '45555678W',  'owner1');
INSERT INTO duenos VALUES (9, 'Schroeder',   'em@mail.com','David','6085559435','2749 Blackhawk Trail', '45555678W',  'owner1');
INSERT INTO duenos VALUES (10, 'Estaban',   'em@mail.com','Carlos','6085555487','2335 Independence La.', '45555678W',  'owner1');

--Añadir josdurgar1 como owner
INSERT INTO duenos VALUES (11, 'Durán',   'em@mail.com','Jose Manuel', '680464646','123 C/Falsa', '45555678W',  'josdurgar1');
INSERT INTO duenos VALUES (12, 'Ciezar',   'em@mail.com','Eduardo','1111111111','Av.Reina Mercedes', '45555678W',  'educielan');


--Añadir un director
INSERT INTO director (id, nombre, apellidos, telefono, email, username) VALUES (13, 'LEO','FERNANDEZ', '222222222', 'Di@mail.com',  'director1');

-- Añadir centro de adopcion
INSERT INTO centros(id, nombre, direccion, cantidad_max) VALUES (1, 'CENTRO DE ADOPCIÓN 1', 'Dirección 1', 20);
INSERT INTO centros(id, nombre, direccion, cantidad_max) VALUES (2, 'CENTRO DE ADOPCIÓN 2', 'Dirección 2', 40);
INSERT INTO centros(id, nombre, direccion, cantidad_max) VALUES (3, 'CENTRO DE ADOPCIÓN 3', 'Dirección 3', 50);


--Añadir Adopcion
INSERT INTO adopciones(id, unidad_familiar, mayores_de_edad, leido_requisitos, permiso_cmunidad_vecinos, otros_animales, motivo, aceptada, motivo_decision, fecha_decision, dueno_id ) VALUES(1, 4, 3, true, true, true, 'Motivo 1', true, 'MotivoDecision 1', '2019-09-01',1);
INSERT INTO adopciones(id, unidad_familiar, mayores_de_edad, leido_requisitos, permiso_cmunidad_vecinos, otros_animales, motivo, aceptada, motivo_decision, fecha_decision,dueno_id ) VALUES(2, 3, 2, false, false, false, 'Motivo 2', false, 'MotivoDecision 2','2019-10-01',2);
INSERT INTO adopciones(id, unidad_familiar, mayores_de_edad, leido_requisitos, permiso_cmunidad_vecinos, otros_animales, motivo, aceptada, motivo_decision, fecha_decision, dueno_id ) VALUES(3, 2, 2, true, true, false, 'Motivo 3', true, 'MotivoDecision 3', '2020-09-01',3);

--Añadir Categoria
INSERT INTO categoria(id, tipo, raza) VALUES(1,1, 'husky');

--Añadir animales
INSERT INTO animales(id, adoptado, atencion, dificultad, chip, edad, fecha_nacimiento, 
	primera_incorporacion, ultima_incorporacion, nombre, numero_registro,
	grado, licencia,licenciarequerida, seguro, sexo, tamanyo, categoria_id,
	cuidador_id) VALUES(1, true, 3, 3, 'CHIP123', 5,'2019-10-01', '2020-09-01', '2020-10-01',
	'NombreAnimal', 'Nregistro', 3, true, true, true, 'Masculino', 'Mediano', 1, 1);


