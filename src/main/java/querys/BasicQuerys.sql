/** TIPO_USUARIO **/
INSERT INTO `arguments`.`tipo_usuario` (`ID_TIPO_USUARIO`, `NOME`) VALUES ('1', 'Aluno');
INSERT INTO `arguments`.`tipo_usuario` (`ID_TIPO_USUARIO`, `NOME`) VALUES ('2', 'Professor');
INSERT INTO `arguments`.`tipo_usuario` (`ID_TIPO_USUARIO`, `NOME`) VALUES ('3', 'Especialista');

/** INSTITUICAO **/
INSERT INTO `arguments`.`instituicao` (`ID_INSTITUICAO`, `NOME`) VALUES ('1', 'UniBrasil');
INSERT INTO `arguments`.`instituicao` (`ID_INSTITUICAO`, `NOME`) VALUES ('2', 'PUC');
INSERT INTO `arguments`.`instituicao` (`ID_INSTITUICAO`, `NOME`) VALUES ('3', 'Positivo');
INSERT INTO `arguments`.`instituicao` (`ID_INSTITUICAO`, `NOME`) VALUES ('4', 'UTFPR');
INSERT INTO `arguments`.`instituicao` (`ID_INSTITUICAO`, `NOME`) VALUES ('5', 'Opet');

/** CURSOS **/
INSERT INTO `arguments`.`cursos` (`ID_CURSOS`, `NOME`) VALUES ('1', 'Sistemas de Informação');
INSERT INTO `arguments`.`cursos` (`ID_CURSOS`, `NOME`) VALUES ('2', 'Ed. Física');
INSERT INTO `arguments`.`cursos` (`ID_CURSOS`, `NOME`) VALUES ('3', 'Direito');
INSERT INTO `arguments`.`cursos` (`ID_CURSOS`, `NOME`) VALUES ('4', 'Medicina');
INSERT INTO `arguments`.`cursos` (`ID_CURSOS`, `NOME`) VALUES ('5', 'Administração');
INSERT INTO `arguments`.`cursos` (`ID_CURSOS`, `NOME`) VALUES ('6', 'Contabilidade');

/** INSTITUICAO_CURSO **/
INSERT INTO `arguments`.`instituicao_cursos` (`ID_INSTITUICAO_CURSOS`, `ID_CURSOS`, `ID_INSTITUICAO`) VALUES ('1', '1', '1');
INSERT INTO `arguments`.`instituicao_cursos` (`ID_INSTITUICAO_CURSOS`, `ID_CURSOS`, `ID_INSTITUICAO`) VALUES ('2', '2', '1');
INSERT INTO `arguments`.`instituicao_cursos` (`ID_INSTITUICAO_CURSOS`, `ID_CURSOS`, `ID_INSTITUICAO`) VALUES ('3', '3', '1');
INSERT INTO `arguments`.`instituicao_cursos` (`ID_INSTITUICAO_CURSOS`, `ID_CURSOS`, `ID_INSTITUICAO`) VALUES ('4', '1', '2');
INSERT INTO `arguments`.`instituicao_cursos` (`ID_INSTITUICAO_CURSOS`, `ID_CURSOS`, `ID_INSTITUICAO`) VALUES ('5', '4', '2');
INSERT INTO `arguments`.`instituicao_cursos` (`ID_INSTITUICAO_CURSOS`, `ID_CURSOS`, `ID_INSTITUICAO`) VALUES ('6', '5', '3');
INSERT INTO `arguments`.`instituicao_cursos` (`ID_INSTITUICAO_CURSOS`, `ID_CURSOS`, `ID_INSTITUICAO`) VALUES ('7', '6', '3');
INSERT INTO `arguments`.`instituicao_cursos` (`ID_INSTITUICAO_CURSOS`, `ID_CURSOS`, `ID_INSTITUICAO`) VALUES ('8', '2', '3');
INSERT INTO `arguments`.`instituicao_cursos` (`ID_INSTITUICAO_CURSOS`, `ID_CURSOS`, `ID_INSTITUICAO`) VALUES ('9', '1', '3');
INSERT INTO `arguments`.`instituicao_cursos` (`ID_INSTITUICAO_CURSOS`, `ID_CURSOS`, `ID_INSTITUICAO`) VALUES ('10', '4', '4');
INSERT INTO `arguments`.`instituicao_cursos` (`ID_INSTITUICAO_CURSOS`, `ID_CURSOS`, `ID_INSTITUICAO`) VALUES ('11', '3', '4');
INSERT INTO `arguments`.`instituicao_cursos` (`ID_INSTITUICAO_CURSOS`, `ID_CURSOS`, `ID_INSTITUICAO`) VALUES ('12', '5', '5');