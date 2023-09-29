# Sistema de Gestion de Reclamos de una Administración

Se debe realizar el desarrollo de una aplicación web que les permita a los inquilinos y a los dueños (vivan en alguna de las unidades administradas o no) a que ante la presencia de un problema, rotura u oportunidad de mejora se genere un reclamo para que sea procesado. 


## Documentacion API

### Autenticación
Al iniciar la aplicacion se crea un usuario con nombre : "admin" y password "admin" para poder hacer POST a /auth/login y obtener el token JWT 



#### Login
```http
  POST /auth/login
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `nombre` | `string` | **Required**. Nombre de usuario |
| `password` | `string` | **Required**. Contraseña |

Se devolvera un token JWT que luego debe utilizarse para realizar todas las peticiones.

Dentro de Postman --> Authorization --> Bearer Token