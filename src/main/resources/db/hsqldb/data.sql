-- One admin user, named admin1 with passwor 4dm1n and authority admin
INSERT INTO users(username,password,enabled) VALUES ('admin1','4dm1n',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (1,'admin1','admin');
-- One owner user, named owner1 with passwor 0wn3r
INSERT INTO users(username,password,enabled) VALUES ('owner1','0wn3r',TRUE);
INSERT INTO users(username,password,enabled) VALUES ('educielan','1111',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (4,'educielan','duenoAdoptivo');
INSERT INTO authorities(id,username,authority) VALUES (2,'owner1','duenoAdoptivo');

--Añadir josdurgar1 como owner
-- One owner user, named owner1 with passwor 0wn3r
INSERT INTO users(username,password,enabled) VALUES ('josdurgar1','j0$durg4r1',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (5,'josdurgar1','duenoAdoptivo');




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



