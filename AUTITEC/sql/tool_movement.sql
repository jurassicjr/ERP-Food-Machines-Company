CREATE TABLE `tool_movement` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tool` int(11) NOT NULL,
  `toolbox` int(11) NOT NULL,
  `employee` int(11) NOT NULL,
  `user` int(11) NOT NULL,
  `date` datetime NOT NULL,
  `movementtype` char(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_tool_movement_tool_idx` (`tool`),
  KEY `fk_tool_movement_employee_idx` (`employee`),
  KEY `fk_tool_movement_user_idx` (`user`),
  KEY `fk_tool_movement_toolbox_idx` (`toolbox`),
  CONSTRAINT `fk_tool_movement_employee` FOREIGN KEY (`employee`) REFERENCES `employee` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_tool_movement_tool` FOREIGN KEY (`tool`) REFERENCES `tool` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_tool_movement_toolbox` FOREIGN KEY (`toolbox`) REFERENCES `toolbox` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_tool_movement_user` FOREIGN KEY (`user`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
