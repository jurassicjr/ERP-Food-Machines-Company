CREATE TABLE `route_product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product` int(11) NOT NULL,
  `routedestination` int(11) NOT NULL,
  `quantity` double NOT NULL,
  PRIMARY KEY (`id`),
  KEY `route_destination_FK_product_idx` (`routedestination`),
  KEY `route_product_FK_product_idx` (`product`),
  CONSTRAINT `route_product_FK_product` FOREIGN KEY (`product`) REFERENCES `Product` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `route_product_FK_routedestination` FOREIGN KEY (`routedestination`) REFERENCES `route_destination` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;
