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
## Permitir conexiónes externas [Opcional]
Si la base de datos se encuentra en un servidor distinto a la aplicación, se debe habilirar la conexión desde servidores externos. Para ello se debe abrir el archivo ***/etc/mysql/my.cnf*** , buscar la siguiente linea

```sh
bind-address = 127.0.0.1
```

y comentarla

```sh
#bind-address = 127.0.0.1
```

Luego reiniciar el servicio de MySQL

```sh
sudo service mysql restart
```

# Configuración jdbc en Glassfish
La aplicaicón está configurada para que haga uso de un jdbc que se defina en Glassfish, está configuración está en la clase CrudDAO, en caso de que se desee cambiar el método para conectarse a la base de datos.

Si se desea acceder al panel de administración de glassfish desde un servidor externo, se debe habilitar el acceso siguiendo los pasos en el siguiente link:

[Spotlight on GlassFish 4.1: #11 Enable Remote Admin ](https://blogs.oracle.com/theaquarium/entry/spotlight_on_glassfish_4_18)

Crear en Glassfish un jdbc llamado '*jdbc/bienestar*' siguiendo las instrucciones en el siguiente link:

[How to setup a JDBC connection in Glassfish](https://computingat40s.wordpress.com/how-to-setup-a-jdbc-connection-in-glassfish/)



