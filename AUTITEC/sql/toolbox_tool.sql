CREATE TABLE `toolbox_tool` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `toolbox` int(11) NOT NULL,
  `tool` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `toolFK_idx` (`tool`),
  KEY `tooBoxFK_idx` (`toolbox`),
  CONSTRAINT `tooBoxFK` FOREIGN KEY (`toolbox`) REFERENCES `toolbox` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `toolFK` FOREIGN KEY (`tool`) REFERENCES `tool` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

