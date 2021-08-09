DROP TABLE IF EXISTS usuario;
DROP TABLE IF EXISTS tipo_usuario;
  
CREATE TABLE tipo_usuario (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  nombre VARCHAR(250) NOT NULL
);



  
CREATE TABLE usuario (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  cedula VARCHAR(10) NOT NULL,
  nombre VARCHAR(100) NOT NULL,
  apellido VARCHAR(100) NOT NULL,
  direccion VARCHAR(100) NOT NULL,
  celular VARCHAR(15) NOT NULL,
  tipo_usuario_id INT NOT NULL
  --foreign key (tipo_usuario_id) references tipo_usuario(id)
);