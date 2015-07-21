CREATE TABLE `route` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(400) NOT NULL,
  `date` date NOT NULL,
  `initialhodometer` double NOT NULL,
  `vehicle` int(11) NOT NULL,
  `conductor` int(11) NOT NULL,
  `user` int(11) NOT NULL,
  `returnhodometer` double DEFAULT NULL,
  `returndate` date DEFAULT NULL,
  `returnobs` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `vehicle_FK_idx` (`vehicle`),
  KEY `conductor_FK_idx` (`conductor`),
  CONSTRAINT `conductor_FK` FOREIGN KEY (`conductor`) REFERENCES `employee` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `vehicle_FK` FOREIGN KEY (`vehicle`) REFERENCES `vehicle` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;
