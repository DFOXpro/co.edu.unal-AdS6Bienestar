
-- INGRESAR DATOS TABLA USUARIO
delete from USUARIO;

insert into USUARIO values('1','1','CC','Nombre 1','Apellido 1','user1@unal.edu.co','user1','A',null);
insert into USUARIO values('2','2','CC','Nombre 2','Apellido 2','user2@unal.edu.co','user2','P',null);
insert into USUARIO values('3','3','CC','Nombre 3','Apellido 3','user3@unal.edu.co','user3','P',null);
insert into USUARIO values('4','4','CC','Nombre 4','Apellido 4','user4@unal.edu.co','user4','P',null);
insert into USUARIO values('5','5','CC','Nombre 5','Apellido 5','user5@unal.edu.co','user5','P',null);
insert into USUARIO values('6','6','CC','Nombre 6','Apellido 6','user6@unal.edu.co','user6','E',null);
insert into USUARIO values('7','7','CC','Nombre 7','Apellido 7','user7@unal.edu.co','user7','E',null);
insert into USUARIO values('8','8','CC','Nombre 8','Apellido 8','user8@unal.edu.co','user8','E',null);
insert into USUARIO values('9','9','CC','Nombre 9','Apellido 9','user9@unal.edu.co','user9','E',null);
insert into USUARIO values('10','10','CC','Nombre 10','Apellido 10','user10@unal.edu.co','user10','E',null);

-- INGRESAR TALLERES Y CURSOS LIBRES
delete from TALLER;

insert into TALLER values('1' ,'T','Taller1','Taller1 Desc',CURDATE() + INTERVAL 10 DAY,CURDATE() + INTERVAL 20 DAY,CURDATE() + INTERVAL 40 DAY,'5000'	,'5');
insert into TALLER values('2' ,'T','Taller2','Taller2 Desc',CURDATE() + INTERVAL 10 DAY,CURDATE() + INTERVAL 20 DAY,CURDATE() + INTERVAL 40 DAY,'0'   	,'5');
insert into TALLER values('3' ,'T','Taller3','Taller3 Desc',CURDATE() + INTERVAL 10 DAY,CURDATE() + INTERVAL 20 DAY,CURDATE() + INTERVAL 40 DAY,'10000'	,'5');
insert into TALLER values('4' ,'T','Taller4','Taller4 Desc',CURDATE() + INTERVAL 10 DAY,CURDATE() + INTERVAL 20 DAY,CURDATE() + INTERVAL 40 DAY,'3000'	,'5');
insert into TALLER values('5' ,'T','Taller5','Taller5 Desc',CURDATE() + INTERVAL 10 DAY,CURDATE() + INTERVAL 20 DAY,CURDATE() + INTERVAL 40 DAY,'6000'	,'5');
insert into TALLER values('6' ,'C','Curso1' ,'Curso1 Desc' ,CURDATE() + INTERVAL 10 DAY,CURDATE() + INTERVAL 20 DAY,CURDATE() + INTERVAL 40 DAY,'0'	,'5');
insert into TALLER values('7' ,'C','Curso2' ,'Curso2 Desc' ,CURDATE() + INTERVAL 10 DAY,CURDATE() + INTERVAL 20 DAY,CURDATE() + INTERVAL 40 DAY,'0'	,'5');
insert into TALLER values('8' ,'C','Curso3' ,'Curso3 Desc' ,CURDATE() + INTERVAL 10 DAY,CURDATE() + INTERVAL 20 DAY,CURDATE() + INTERVAL 40 DAY,'10000'	,'5');
insert into TALLER values('9' ,'C','Curso4' ,'Curso4 Desc' ,CURDATE() + INTERVAL 10 DAY,CURDATE() + INTERVAL 20 DAY,CURDATE() + INTERVAL 40 DAY,'0'	,'5');
insert into TALLER values('10','C','Curso5' ,'Curso5 Desc' ,CURDATE() + INTERVAL 10 DAY,CURDATE() + INTERVAL 20 DAY,CURDATE() + INTERVAL 40 DAY,'0'	,'5');


-- INGRESAR CONVOCATORIAS
delete from CONVOCATORIA;

insert into CONVOCATORIA values('1' ,'Convocatoria1' ,'Convocatoria1 Desc' ,CURDATE() + INTERVAL 10 DAY,'5');
insert into CONVOCATORIA values('2' ,'Convocatoria2' ,'Convocatoria2 Desc' ,CURDATE() + INTERVAL 10 DAY,'5');
insert into CONVOCATORIA values('3' ,'Convocatoria3' ,'Convocatoria3 Desc' ,CURDATE() + INTERVAL 10 DAY,'5');
insert into CONVOCATORIA values('4' ,'Convocatoria4' ,'Convocatoria4 Desc' ,CURDATE() + INTERVAL 10 DAY,'5');
insert into CONVOCATORIA values('5' ,'Convocatoria5' ,'Convocatoria5  Desc',CURDATE() + INTERVAL 10 DAY,'5');
insert into CONVOCATORIA values('6' ,'Convocatoria6' ,'Convocatoria6 Desc' ,CURDATE() + INTERVAL 10 DAY,'5');
insert into CONVOCATORIA values('7' ,'Convocatoria7' ,'Convocatoria7 Desc' ,CURDATE() + INTERVAL 10 DAY,'5');
insert into CONVOCATORIA values('8' ,'Convocatoria8' ,'Convocatoria8 Desc' ,CURDATE() + INTERVAL 10 DAY,'5');
insert into CONVOCATORIA values('9' ,'Convocatoria9' ,'Convocatoria9 Desc' ,CURDATE() + INTERVAL 10 DAY,'5');
insert into CONVOCATORIA values('10','Convocatoria10','Convocatoria10 Desc',CURDATE() + INTERVAL 10 DAY,'5');


-- INGRESAR PROFESOR_TALLER
delete from PROFESOR_TALLER;

insert into PROFESOR_TALLER values(2, 6);
insert into PROFESOR_TALLER values(2, 7);
insert into PROFESOR_TALLER values(3, 8);
insert into PROFESOR_TALLER values(4, 9);
insert into PROFESOR_TALLER values(5, 10);

-- INGRESAR USUARIO_TALLER
delete from USUARIO_TALLER;

insert into USUARIO_TALLER values( 6, 1, CURDATE());
insert into USUARIO_TALLER values( 7, 1, CURDATE());
insert into USUARIO_TALLER values( 8, 2, CURDATE());
insert into USUARIO_TALLER values( 6, 3, CURDATE());
insert into USUARIO_TALLER values( 7, 4, CURDATE());
insert into USUARIO_TALLER values( 8, 4, CURDATE());
insert into USUARIO_TALLER values( 9, 4, CURDATE());
insert into USUARIO_TALLER values( 10, 5, CURDATE());
insert into USUARIO_TALLER values( 10, 6, CURDATE());
insert into USUARIO_TALLER values( 6, 6, CURDATE());

-- INGRESAR USUARIO_CONVOCATORIA
delete from USUARIO_CONVOCATORIA;

insert into USUARIO_CONVOCATORIA values( 6, 1);
insert into USUARIO_CONVOCATORIA values( 7, 1);
insert into USUARIO_CONVOCATORIA values( 8, 2);
insert into USUARIO_CONVOCATORIA values( 6, 3);
insert into USUARIO_CONVOCATORIA values( 7, 4);
insert into USUARIO_CONVOCATORIA values( 8, 4);
insert into USUARIO_CONVOCATORIA values( 9, 4);
insert into USUARIO_CONVOCATORIA values( 10, 5);
insert into USUARIO_CONVOCATORIA values( 10, 6);
insert into USUARIO_CONVOCATORIA values( 6, 6);










