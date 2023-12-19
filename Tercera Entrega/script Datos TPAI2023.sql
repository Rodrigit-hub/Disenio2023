INSERT INTO `ppai`.`validacion` (`id_validacion`, `audio_mensaje_validacion`, `nombre`) VALUES ('1', 'Informe su Fecha de nacimiento ddmmaaaa', 'Fecha de Nacimiento (dd/mm/aaaa)');
INSERT INTO `ppai`.`validacion` (`id_validacion`, `audio_mensaje_validacion`, `nombre`) VALUES ('2', 'Informe su codigo postal', 'Codigo Postal');

INSERT INTO `ppai`.`cliente` (`id_cliente`, `dni`, `nombre_completo`, `nro_celular`) VALUES ('1', '43476266', 'Tomas Gimenez', '3572572259');

INSERT INTO `ppai`.`informacion_cliente` (`id_info_cliente`, `dato_a_validar`,`id_cliente`, `validacion_id`) VALUES ('1', '12/09/2001', '1', '1');
INSERT INTO `ppai`.`informacion_cliente` (`id_info_cliente`, `dato_a_validar`,`id_cliente`, `validacion_id`) VALUES ('2', '5986', '1', '2');


INSERT INTO `ppai`.`sub_opcion_llamada` (`id_sub_opcion`, `audio_mensaje_subopciones`, `nombre`) VALUES ('1', 'Si cuenta con los datos de la tarjeta marque 1', 'Cuenta con los datos de la tarjeta');
INSERT INTO `ppai`.`sub_opcion_llamada` (`id_sub_opcion`, `audio_mensaje_subopciones`, `nombre`) VALUES ('2', 'Si no cuenta con los datos de la tarjeta 2', 'No cuenta con los datos de tarjeta');
INSERT INTO `ppai`.`sub_opcion_llamada` (`id_sub_opcion`, `audio_mensaje_subopciones`, `nombre`) VALUES ('3', 'Si desea comunicarse con un responsable de atencion al cliente marque 3', 'Comunicarse atencion al cliente');
INSERT INTO `ppai`.`sub_opcion_llamada` (`id_sub_opcion`, `audio_mensaje_subopciones`, `nombre`) VALUES ('4', 'Si desea finalizar la llamada marque 4', 'Finalizar llamada');

INSERT INTO `ppai`.`opcion_llamada` (`id_opcion_llamada`, `audio_mensaje_opciones`, `nombre`, `sub_opcion_llamada_id`) VALUES ('1', 'Si desea informar un robo y solicitar una nueva tarjeta marque 1', 'Informar robo y solicitar nueva tarjeta', '1');
INSERT INTO `ppai`.`opcion_llamada` (`id_opcion_llamada`, `audio_mensaje_opciones`, `nombre`, `sub_opcion_llamada_id`) VALUES ('2', 'si desea informar un robo y anular su tarjeta marque 2', 'Informar robo y anular tarjeta', '2');
INSERT INTO `ppai`.`opcion_llamada` (`id_opcion_llamada`, `audio_mensaje_opciones`, `nombre`, `sub_opcion_llamada_id`) VALUES ('3', 'Si desea finalizar la llamada marque 3', 'Finalizar llamada', '4');

INSERT INTO `ppai`.`categoria_llamada` (`id_categoria_llamada`, `audio_mensaje_categoria`, `nombre`, `opcion_llamada_id`) VALUES ('1', 'Si quiere informar un robo marque 1', 'Informar robo tarjeta', '1');
INSERT INTO `ppai`.`categoria_llamada` (`id_categoria_llamada`, `audio_mensaje_categoria`, `nombre`, `opcion_llamada_id`) VALUES ('2', 'Si su tarjeta está bloqueada marque 2', 'Tarjeta bloqueada', '2');
INSERT INTO `ppai`.`categoria_llamada` (`id_categoria_llamada`, `audio_mensaje_categoria`, `nombre`, `opcion_llamada_id`) VALUES ('3', 'Si desea finalizar la llamada marque 8', 'Finalizar llamada', '3');

INSERT INTO `ppai`.`estado` (`id_estado`, `nombre`) VALUES ('1', 'En Curso');
INSERT INTO `ppai`.`estado` (`id_estado`, `nombre`) VALUES ('2', 'Iniciada');
INSERT INTO `ppai`.`estado` (`id_estado`, `nombre`) VALUES ('3', 'Finalizada');
INSERT INTO `ppai`.`estado` (`id_estado`, `nombre`) VALUES ('4', 'Cancelada');
