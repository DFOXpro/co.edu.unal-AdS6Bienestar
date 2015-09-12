# Diagrama 
El Diagrama de base de datos se abre con MySQL Workbench, se usó la ***versión 6***.

# Base de Datos
## Crear Esquema
El script se debe ejecutar como root, en él se crea el esquema bienestar con todos los datos.
```sh
$ cd {folder donde tengan los .sql}
>mysql -u root -p
>{ingresar clave de root}
>source 01_Esquema.sql
>source 02_Vistas.sql
```
## Crear Usuario
se debe crear otro usuario y asignarle permisos sobre el esquema.

Ingresar como root y ejecutar.
```sql
CREATE USER 'bienestar'@localhost IDENTIFIED BY 'bienestar';
GRANT ALL ON bienestar.* to bienestar@'%' IDENTIFIED BY 'bienestar';
FLUSH PRIVILEGES;
```
## Datos [Opcional]
Después de crear la base de datos se puede correr el script de datos para llenar las tablas.

Ingresar con el usuario bienestar

```sh
$ mysql -u bienestar -p
```
```sql
{ingresar clave de bienestar}
use bienestar
source 03_Datos.sql
```
