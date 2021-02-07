-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1:3306
-- Tiempo de generación: 07-02-2021 a las 01:56:37
-- Versión del servidor: 5.7.31
-- Versión de PHP: 7.3.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `schsdb`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `anuncio`
--

DROP TABLE IF EXISTS `anuncio`;
CREATE TABLE IF NOT EXISTS `anuncio` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `anuncio_text` varchar(255) DEFAULT NULL,
  `imagen` longtext,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `archivo`
--

DROP TABLE IF EXISTS `archivo`;
CREATE TABLE IF NOT EXISTS `archivo` (
  `id_file` int(11) NOT NULL AUTO_INCREMENT,
  `url` varchar(4000) COLLATE latin1_general_ci NOT NULL,
  `descripcion` varchar(4000) COLLATE latin1_general_ci NOT NULL,
  `carnet` varchar(250) COLLATE latin1_general_ci NOT NULL,
  `fecha` date DEFAULT NULL,
  PRIMARY KEY (`id_file`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

--
-- Disparadores `archivo`
--
DROP TRIGGER IF EXISTS `ins_first_comment`;
DELIMITER $$
CREATE TRIGGER `ins_first_comment` AFTER INSERT ON `archivo` FOR EACH ROW BEGIN
	INSERT INTO comentario(usuario,idArchivo, comentario) values(New.carnet, New.id_file, 'Bienvenido al área de comentarios');
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `carrera`
--

DROP TABLE IF EXISTS `carrera`;
CREATE TABLE IF NOT EXISTS `carrera` (
  `idcarrera` int(11) NOT NULL AUTO_INCREMENT,
  `codigocarrera` varchar(20) COLLATE latin1_general_ci DEFAULT NULL,
  `nombrecarrera` varchar(100) COLLATE latin1_general_ci DEFAULT NULL,
  `codigofacultad` varchar(20) COLLATE latin1_general_ci DEFAULT NULL,
  `nombrefacultad` varchar(100) COLLATE latin1_general_ci DEFAULT NULL,
  PRIMARY KEY (`idcarrera`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

--
-- Volcado de datos para la tabla `carrera`
--

INSERT INTO `carrera` (`idcarrera`, `codigocarrera`, `nombrecarrera`, `codigofacultad`, `nombrefacultad`) VALUES
(1, 'SI', 'INGENIERIA EN SISTEMAS', 'IyAR', 'Ingeniería y Arquitectura'),
(3, 'EL', 'INGENIERIA ELECTRICA', 'IYAR', 'Ingeniería y Arquitectura'),
(6, 'CCSS', 'Ciencias Sociales', 'JRSP', 'Jurisprudencia');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `comentario`
--

DROP TABLE IF EXISTS `comentario`;
CREATE TABLE IF NOT EXISTS `comentario` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `usuario` varchar(25) NOT NULL,
  `idArchivo` int(11) NOT NULL,
  `comentario` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=68 DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `comentario`
--

INSERT INTO `comentario` (`id`, `usuario`, `idArchivo`, `comentario`) VALUES
(67, 'segudu', 1, 'Bienvenido al área de comentarios');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `datoscertificacion`
--

DROP TABLE IF EXISTS `datoscertificacion`;
CREATE TABLE IF NOT EXISTS `datoscertificacion` (
  `id` int(11) NOT NULL,
  `Cargo` varchar(20) COLLATE latin1_general_ci DEFAULT NULL,
  `nombre` varchar(100) COLLATE latin1_general_ci DEFAULT NULL,
  `titulo` varchar(100) COLLATE latin1_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

--
-- Volcado de datos para la tabla `datoscertificacion`
--

INSERT INTO `datoscertificacion` (`id`, `Cargo`, `nombre`, `titulo`) VALUES
(2, 'DR.', 'EDGARDO HERRERA MEDRANO PACHECO', 'VICEDECANO'),
(1, 'MDF.', ' REINALDO CHÁVEZ MARTÍNEZ', 'JEFE DE PROYECCIÓN SOCIAL');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `escuela`
--

DROP TABLE IF EXISTS `escuela`;
CREATE TABLE IF NOT EXISTS `escuela` (
  `id_escuela` varchar(10) COLLATE latin1_general_ci NOT NULL,
  `escuela` varchar(100) COLLATE latin1_general_ci NOT NULL,
  PRIMARY KEY (`id_escuela`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

--
-- Volcado de datos para la tabla `escuela`
--

INSERT INTO `escuela` (`id_escuela`, `escuela`) VALUES
('1', 'JURISPRUDENCIA'),
('2', 'LEYES');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estudiante`
--

DROP TABLE IF EXISTS `estudiante`;
CREATE TABLE IF NOT EXISTS `estudiante` (
  `due` varchar(10) COLLATE latin1_general_ci NOT NULL,
  `nombres` varchar(100) COLLATE latin1_general_ci NOT NULL,
  `apellidos` varchar(100) COLLATE latin1_general_ci NOT NULL,
  `ciclo_inicio` int(11) NOT NULL,
  `clave` varchar(255) COLLATE latin1_general_ci NOT NULL,
  `correo` varchar(255) COLLATE latin1_general_ci NOT NULL,
  `idcarrera` int(11) NOT NULL,
  `idInstructor` varchar(10) COLLATE latin1_general_ci DEFAULT NULL,
  `idinstitucion` int(11) DEFAULT NULL,
  PRIMARY KEY (`due`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

--
-- Volcado de datos para la tabla `estudiante`
--

INSERT INTO `estudiante` (`due`, `nombres`, `apellidos`, `ciclo_inicio`, `clave`, `correo`, `idcarrera`, `idInstructor`, `idinstitucion`) VALUES
('MA09121', 'DENISSE YASMARA', 'MEJÍA', 8, '$2a$12$fyztxVoApetHEW4HO0A9uuXY01OBkRMAUgG.H6US/9CJuib0KVIDW', 'BM022020@ues.edu.sv', 1, NULL, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `finalizado`
--

DROP TABLE IF EXISTS `finalizado`;
CREATE TABLE IF NOT EXISTS `finalizado` (
  `due` varchar(20) NOT NULL,
  `nombres` varchar(100) NOT NULL,
  `apellidos` varchar(100) NOT NULL,
  `carrera` int(11) NOT NULL,
  PRIMARY KEY (`due`),
  KEY `carrera` (`carrera`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `finalizado`
--

INSERT INTO `finalizado` (`due`, `nombres`, `apellidos`, `carrera`) VALUES
('ZZ00001', 'Estudiante de prueba', 'Con sus apellidos', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `institucion`
--

DROP TABLE IF EXISTS `institucion`;
CREATE TABLE IF NOT EXISTS `institucion` (
  `id_institucion` int(11) NOT NULL AUTO_INCREMENT,
  `nom_institucion` varchar(70) NOT NULL,
  `enc_institucion` varchar(60) NOT NULL,
  `tel_institucion` varchar(30) NOT NULL,
  `correo_institucion` varchar(60) NOT NULL,
  `rs_institucion` varchar(255) NOT NULL,
  `res_institucion` varchar(100) NOT NULL,
  `status_institucion` varchar(10) NOT NULL,
  PRIMARY KEY (`id_institucion`),
  UNIQUE KEY `nom_institucion` (`nom_institucion`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `institucion`
--

INSERT INTO `institucion` (`id_institucion`, `nom_institucion`, `enc_institucion`, `tel_institucion`, `correo_institucion`, `rs_institucion`, `res_institucion`, `status_institucion`) VALUES
(10, 'PROCURADURIA DE DERECHOS HUMANOS', 'RENÉ FIGUEROA', '74152545', 'CORREO@ALGO.COM', 'DEFENSA DE DERECHOS HUMANOS', 'DIRECCION, SAN SALVADOR CALLE #5', 'A'),
(11, 'Juzgado Tercero de lo Civil y Mercantil', 'Encargado del Juzgado Tercero de lo Civil y Mercantil', '+(503) 1212-1212', 'juzgadotercerodelocivilymercantil@gmail.com', 'Juzgado', 'Tres estrellas', 'A');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `instructor`
--

DROP TABLE IF EXISTS `instructor`;
CREATE TABLE IF NOT EXISTS `instructor` (
  `id` varchar(20) COLLATE latin1_general_ci NOT NULL,
  `first_name` varchar(20) COLLATE latin1_general_ci NOT NULL,
  `second_name` varchar(25) COLLATE latin1_general_ci DEFAULT NULL,
  `last_name` varchar(25) COLLATE latin1_general_ci NOT NULL,
  `second_last_name` varchar(25) COLLATE latin1_general_ci DEFAULT NULL,
  `status` varchar(1) COLLATE latin1_general_ci NOT NULL DEFAULT 'I',
  `id_escuela` varchar(10) COLLATE latin1_general_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

--
-- Volcado de datos para la tabla `instructor`
--

INSERT INTO `instructor` (`id`, `first_name`, `second_name`, `last_name`, `second_last_name`, `status`, `id_escuela`) VALUES
('RR01920', 'NOMBRET', 'TUTOR', 'RIVAS', 'PEREZ', 'A', '1');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `opcion`
--

DROP TABLE IF EXISTS `opcion`;
CREATE TABLE IF NOT EXISTS `opcion` (
  `id` int(11) NOT NULL,
  `desc` varchar(100) COLLATE latin1_general_ci DEFAULT NULL,
  `status` varchar(1) COLLATE latin1_general_ci DEFAULT 'Y',
  `menu_icon` varchar(100) COLLATE latin1_general_ci NOT NULL,
  `url` varchar(1000) COLLATE latin1_general_ci NOT NULL,
  `id_opc_ppal` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_opcion_ppal` (`id_opc_ppal`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

--
-- Volcado de datos para la tabla `opcion`
--

INSERT INTO `opcion` (`id`, `desc`, `status`, `menu_icon`, `url`, `id_opc_ppal`) VALUES
(7, 'Nota de autorización', 'A', 'ui-icon ui-icon-assessment', '/GestionAdmin/NotaAutorizacion', 2),
(1, 'Solicitudes', 'A', 'ui-icon ui-icon-school', '/GestionAdmin/AdminSolicitud', 1),
(2, 'Consultar Estudiantes', 'A', 'ui-icon ui-icon-collections-bookmark', '/GestionAdmin/AdminEstudiante', 1),
(3, 'Instructores', 'A', 'ui-icon ui-icon-account-circle', '/GestionAdmin/Instructores', 1),
(4, 'Asignar tutor', 'A', 'ui-icon ui-icon-local-library', '/GestionAdmin/AsignarTutor', 2),
(5, 'Carreras', 'A', 'ui-icon ui-icon-computer', '/GestionAdmin/AdminCarreras', 2),
(6, 'Instituciones', 'A', 'domain', '/GestionAdmin/AdminInstitucion', 2),
(8, 'Reportes', 'A', 'ui-icon ui-icon-poll', '/GestionAdmin/reportes', 3),
(9, 'Cargar archivo', 'A', 'ui-icon ui-icon-attach-file', '/default_page/AdjuntarDocEstudiante', 4),
(10, 'Revisiones', 'A', 'ui-icon ui-icon-poll', '/default_page/dashboard', 5),
(11, 'Mantenimiento anuncios', 'A', 'ui-icon ui-icon-live-tv', '/GestionAdmin/MntoAnuncios', 1),
(12, 'Proyectos', 'A', 'ui-icon ui-icon-folder-collapsed', '/GestionAdmin/AdminProyecto', 2),
(13, 'Seleccionar Proyecto', 'A', 'ui-icon ui-icon-check', '/default_page/AsignacionProyectos', 4),
(14, 'Estudiantes', 'A', 'ui-icon ui-icon-group', '/GestionTutor/estudiantes', 2),
(15, 'Finalizados', 'A', 'ui-icon ui-icon-flag', '/GestionAdmin/Finalizados', 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `opcion_principal`
--

DROP TABLE IF EXISTS `opcion_principal`;
CREATE TABLE IF NOT EXISTS `opcion_principal` (
  `id` int(11) NOT NULL,
  `descripcion` varchar(255) COLLATE latin1_general_ci DEFAULT NULL,
  `menu_icon` varchar(255) COLLATE latin1_general_ci DEFAULT NULL,
  `status` varchar(255) COLLATE latin1_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

--
-- Volcado de datos para la tabla `opcion_principal`
--

INSERT INTO `opcion_principal` (`id`, `descripcion`, `menu_icon`, `status`) VALUES
(1, 'Gestión Usuarios', 'ui-icon ui-icon-people', 'A'),
(2, 'Gestión Administrativa', 'ui-icon ui-icon-content-paste', 'A'),
(3, 'Reportes', 'ui-icon ui-icon-library-books', 'A'),
(4, 'Actividades', 'ui-icon ui-icon-folder-shared', 'A'),
(5, 'Orientación', 'ui-icon ui-icon-folder-shared', 'A');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `opcion_rol`
--

DROP TABLE IF EXISTS `opcion_rol`;
CREATE TABLE IF NOT EXISTS `opcion_rol` (
  `id_opcion` int(11) NOT NULL,
  `id_rol` int(11) NOT NULL,
  PRIMARY KEY (`id_opcion`,`id_rol`),
  KEY `FK_opcion_rol_id_rol` (`id_rol`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

--
-- Volcado de datos para la tabla `opcion_rol`
--

INSERT INTO `opcion_rol` (`id_opcion`, `id_rol`) VALUES
(1, 1),
(2, 1),
(3, 1),
(4, 1),
(5, 1),
(6, 1),
(7, 1),
(8, 1),
(9, 1),
(9, 2),
(10, 1),
(10, 2),
(10, 3),
(11, 1),
(11, 2),
(12, 1),
(12, 3),
(13, 1),
(13, 2),
(14, 1),
(15, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `parametro`
--

DROP TABLE IF EXISTS `parametro`;
CREATE TABLE IF NOT EXISTS `parametro` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parametro` varchar(250) COLLATE latin1_general_ci NOT NULL,
  `valor` varchar(250) COLLATE latin1_general_ci NOT NULL,
  `tipo` int(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

--
-- Volcado de datos para la tabla `parametro`
--

INSERT INTO `parametro` (`id`, `parametro`, `valor`, `tipo`) VALUES
(1, 'username', 'proyeccionsocial.ues.sv@gmail.com', 1),
(2, 'password', 'piaqzdvfdwtezrml', 1),
(3, 'mailServer', 'smtp.gmail.com', 1),
(4, 'puerto', '587', 1),
(5, 'asunto', 'Notificación proyección social UES', 1),
(9, 'msjEstudiante', 'Bienvenido al siste estudiate ', 2),
(6, 'msjInstructor', 'Bienvenido al sistema de horas sociales, facultad de Ciencias y Jurisprudencia', 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `proyecto`
--

DROP TABLE IF EXISTS `proyecto`;
CREATE TABLE IF NOT EXISTS `proyecto` (
  `id_proyecto` int(11) NOT NULL AUTO_INCREMENT,
  `nom_proyecto` varchar(255) NOT NULL,
  `id_institucion` int(11) NOT NULL,
  `cupos_proyecto` int(4) NOT NULL,
  PRIMARY KEY (`id_proyecto`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `proyecto`
--

INSERT INTO `proyecto` (`id_proyecto`, `nom_proyecto`, `id_institucion`, `cupos_proyecto`) VALUES
(1, 'Colaboracion con la Aplicacion del Derecho Civil y Mercantil en la actualidad salvadoreña', 11, 150),
(2, 'Proyecto 1', 10, 48),
(3, 'Proyecto 2', 10, 111),
(17, 'Proyecto 3', 10, 0),
(18, 'Proyecto de Prueba', 11, 4);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `relacion_estudiante_proyecto`
--

DROP TABLE IF EXISTS `relacion_estudiante_proyecto`;
CREATE TABLE IF NOT EXISTS `relacion_estudiante_proyecto` (
  `id_relacionEP` int(11) NOT NULL AUTO_INCREMENT,
  `carnet_estudiante` varchar(7) NOT NULL,
  `id_proyecto` int(11) NOT NULL,
  `fecha_inicio` varchar(50) NOT NULL,
  `fecha_final` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id_relacionEP`),
  UNIQUE KEY `carnet_estudiante` (`carnet_estudiante`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `rol`
--

DROP TABLE IF EXISTS `rol`;
CREATE TABLE IF NOT EXISTS `rol` (
  `id` int(11) NOT NULL,
  `desc` varchar(100) DEFAULT NULL,
  `status` varchar(1) DEFAULT 'Y',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `rol`
--

INSERT INTO `rol` (`id`, `desc`, `status`) VALUES
(1, 'ROLE_ADMIN', 'Y'),
(2, 'ROLE_STUDENT', 'Y'),
(3, 'ROLE_TEACHER', 'Y');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `rol_usuario`
--

DROP TABLE IF EXISTS `rol_usuario`;
CREATE TABLE IF NOT EXISTS `rol_usuario` (
  `usr` varchar(255) COLLATE latin1_general_ci NOT NULL,
  `id_rol` int(11) NOT NULL,
  PRIMARY KEY (`usr`,`id_rol`),
  KEY `FK_rol_usuario_id_rol` (`id_rol`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

--
-- Volcado de datos para la tabla `rol_usuario`
--

INSERT INTO `rol_usuario` (`usr`, `id_rol`) VALUES
('ADMIN', 1),
('ESTUDIANTE', 2),
('INSTRUCTOR', 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `solicitud`
--

DROP TABLE IF EXISTS `solicitud`;
CREATE TABLE IF NOT EXISTS `solicitud` (
  `due` varchar(255) COLLATE latin1_general_ci NOT NULL,
  `apellido` varchar(255) COLLATE latin1_general_ci DEFAULT NULL,
  `ciclo` smallint(6) DEFAULT NULL,
  `idcarrera` int(11) DEFAULT NULL,
  `nombre` varchar(255) COLLATE latin1_general_ci DEFAULT NULL,
  PRIMARY KEY (`due`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

DROP TABLE IF EXISTS `usuario`;
CREATE TABLE IF NOT EXISTS `usuario` (
  `usr` varchar(255) COLLATE latin1_general_ci NOT NULL,
  `clave` varchar(255) COLLATE latin1_general_ci DEFAULT NULL,
  `email` varchar(255) COLLATE latin1_general_ci DEFAULT NULL,
  `nombre` varchar(255) COLLATE latin1_general_ci DEFAULT NULL,
  `status` varchar(255) COLLATE latin1_general_ci DEFAULT NULL,
  PRIMARY KEY (`usr`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`usr`, `clave`, `email`, `nombre`, `status`) VALUES
('ADMIN', '$2a$12$UC.TFojkO3BQbLSFnVaCHuxoDy4ILdnpXOlwocAZH4wFGWSaz2c42', 'adm@ues.com', 'ADMIN', 'A'),
('ESTUDIANTE', '$2a$12$UC.TFojkO3BQbLSFnVaCHuxoDy4ILdnpXOlwocAZH4wFGWSaz2c42', 'ESTUDIANTE@CORREO.COM', 'ESTUDIANTE', 'A'),
('INSTRUCTOR', '$2a$12$UC.TFojkO3BQbLSFnVaCHuxoDy4ILdnpXOlwocAZH4wFGWSaz2c42', 'INSTRUCTOR@UES.COM', 'INSTRUCTOR', 'A');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
