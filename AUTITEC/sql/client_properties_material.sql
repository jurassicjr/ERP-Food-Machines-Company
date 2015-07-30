CREATE TABLE `client_properties_material` (
 
 `id` int(11) NOT NULL AUTO_INCREMENT,
 
 `ammount` int(11) NOT NULL,
  `material` int(11) NOT NULL,

  `client_properties` int(11) NOT NULL,

  `is_in_loco` tinyint(1) DEFAULT '1',
 
 `exit_fiscal_note` varchar(255) DEFAULT NULL,

  PRIMARY KEY (`id`),

  KEY `material` (`material`),
 
 KEY `client_properties` (`client_properties`),

  CONSTRAINT `client_properties_material_ibfk_1` FOREIGN KEY (`material`) REFERENCES `product` (`id`),
 
 CONSTRAINT `client_properties_material_ibfk_2` FOREIGN KEY (`client_properties`) REFERENCES `client_properties` (`id`)

) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
