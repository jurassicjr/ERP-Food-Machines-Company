CREATE TABLE `rnc` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `actiontype` varchar(45) NOT NULL,
  `sequencenumber` varchar(45) NOT NULL,
  `emitter` int(11) NOT NULL,
  `date` date NOT NULL,
  `origin` varchar(45) NOT NULL,
  `description` varchar(500) DEFAULT NULL,
  `posaction` varchar(45) DEFAULT NULL,
  `actiondescription` varchar(500) DEFAULT NULL,
  `cause` varchar(500) DEFAULT NULL,
  `actionplanneed` bit(1) DEFAULT NULL,
  `actionplandescription` varchar(500) DEFAULT NULL,
  `responsible` int(11) DEFAULT NULL,
  `deadline` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=latin1;

