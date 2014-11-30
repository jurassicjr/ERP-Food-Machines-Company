create table suppliers (
id INT (11) NOT NULL AUTO_INCREMENT,
corporate_name VARCHAR(255) NOT NULL,
CNPJ VARCHAR(14) NOT NULL,
city int(11) NOT NULL,
state int(11) NOT NULL,
street VARCHAR(255) NOT NULL,
neighborhood VARCHAR(255) NOT NULL,
certificate bool NOT NULL,
email VARCHAR(255),
state_registration VARCHAR(9) NOT NULL,
 
 PRIMARY KEY(id),
 
 FOREIGN KEY (city) REFERENCES city(id),
 FOREIGN KEY(state) REFERENCES state(id)
)