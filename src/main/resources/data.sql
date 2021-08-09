INSERT INTO tipo_usuario (id, nombre) VALUES
  (1, 'Afiliado'),
   (2, 'Empleado'),
   (3, 'Invitado'),
   (5, 'Desconocido');


INSERT INTO usuario (id,cedula ,nombre,apellido,direccion,celular,tipo_usuario_id) VALUES
  (1,'154515485', 'Carlos','Perez','Car 9', '3217685423',1),
  (2,'165514484', 'Mario','Mejia','Car 88', '3117485523',2),
  (3,'74851254', 'Carlos','Perez','Car 6', '3227685428',3),
  (4,'1111111111', 'Carlos','Perez','Car 3', '3237685427',3),
  (5,'7481545', 'Oecar','Suarez','Car 10', '3227685421',2),
  (6,'974148', 'Miguel','Garcia','Car 10', '3227685420',1),
  (7,'1111111111', 'Jorge','Hernandez','Car 11', '3227685411',1);

  commit;