CREATE TABLE `route_destination` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `route` int(11) NOT NULL,
  `client` int(11) DEFAULT NULL,
  `otherdestination` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `route_FK_destination_idx` (`route`),
  CONSTRAINT `route_FK_destination` FOREIGN KEY (`route`) REFERENCES `route` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
