El Diagrama de base de datos se abre con MySQL Workbench, usé la versión 6.


El script se debe ejecutar como root, en él se crea el esquema bienestar con todos los datos.

> cd {folder donde tengan el .sql}
mysql -u root -p
{ingresar clave de root}
source {Nombre de Archivo}

Si quieren, pueden crear otro usuario y asignarle permisos sobre el esquema.
ingresan como root y ejecutan.


CREATE USER 'bienestar'@localhost IDENTIFIED BY 'bienestar';
GRANT ALL ON bienestar.* to bienestar@'%' IDENTIFIED BY 'bienestar';
FLUSH PRIVILEGES;


Después de crear la base de datos se puede correr el script de datos para llenar las tablas.

Ingresar con el usuario bienestar 
mysql -u bienestar -p
{ingresar clave de bienestar}
use bienestar
source Datos.sql
