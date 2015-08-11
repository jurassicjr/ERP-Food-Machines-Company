CREATE TABLE `procedure_version` (

  `id` int(11) NOT NULL AUTO_INCREMENT,

  `file` varchar(300) NOT NULL,

  `date_update` date NOT NULL,
 
 `procedure` int(11) NOT NULL,

  PRIMARY KEY (`id`),

  KEY `procedure` (`procedure`),

 CONSTRAINT `procedure_version_ibfk_1` FOREIGN KEY (`procedure`) REFERENCES `procedure` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;