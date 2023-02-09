# vigilancia-app
Proyecto de Sistema de Vigilancia para Laboratorio I de la UCLA.

Integrantes:

    Dorieliz Guerrero	C.I. 27.085.237
	Yaisraylith Gimenez	C.I. 27.198.512
	Jose Sayago		C.I. 27.146.763

El diagrama de clases y el MER se encuentran en la carpeta "UML".

Para compilar sin necesidad de tener Maven Instalado

##### Para Linux:
```bash
./mvnw clean package
```

##### Para Windows:
```bash
mvnw.cmd clean package
```

##### Para Ejecutar:
```bash
java -jar target/vigilancia-app-jar-with-dependencies.jar
```

##### Archivo de configuracion de conexion a base de datos:
```
src/main/resources/config.properties
```

##### SQL para la base de datos:
```
sql/db-vigilancia_1.0.sql
```
sql/db-vigilancia_1.1.sql
```
