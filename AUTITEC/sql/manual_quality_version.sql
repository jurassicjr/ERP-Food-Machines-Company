CREATE TABLE `manual_quality_version` (

  `id` int(11) NOT NULL AUTO_INCREMENT,

  `file` varchar(300) NOT NULL,

  `date_update` date NOT NULL,

  `manual_quality` int(11) NOT NULL,

  PRIMARY KEY (`id`),

  KEY `manual_quality` (`manual_quality`),

  CONSTRAINT `manual_quality_ibfk_1` FOREIGN KEY (`manual_quality`) REFERENCES `manual_quality` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;