CREATE DATABASE IF NOT EXISTS `autitec`;
USE `autitec`;

CREATE TABLE employee (

	id INT(11) NOT NULL AUTO_INCREMENT, 
	name VARCHAR(150) NOT NULL,
	birth DATE NOT NULL,
	gender CHAR NOT NULL,
	marital_status VARCHAR(20) NOT NULL,
	nacionality VARCHAR(100) NOT NULL,
	birth_place VARCHAR(100) NOT NULL,
	rg VARCHAR(9) NOT NULL, 
	cpf VARCHAR(11) NOT NULL,
	cpts VARCHAR(10) NOT NULL,
	cpts_category VARCHAR(10) NOT NULL,
	voter VARCHAR(16) NOT NULL,
	driver_license VARCHAR(11),
	driver_license_category VARCHAR(2),
	schooling VARCHAR(50) NOT NULL,
	reservist VARCHAR(20),
	reservist_category VARCHAR(50),
	address INT(11) NOT NULL,
	phone VARCHAR(10) NOT NULL,
	cellphone VARCHAR(11),
	job INT(11) NOT NULL,
	banking_data INT(11) NOT NULL,
	social_integration INT(11),
	guarantee_fund INT(11) NOT NULL,
	picture VARCHAR(250),

	FOREIGN KEY(address) REFERENCES address(id),
	FOREIGN KEY(job) REFERENCES job(id),
	FOREIGN KEY(baking_data) REFERENCES baking_data(id),
	FOREIGN KEY(social_integration) REFERENCES social_integration(id),
	FOREIGN KEY(guarantee_fund) REFERENCES guarantee_fund(id),

	PRIMARY KEY(id, cpf)
	
);


CREATE TABLE address (

	id INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
	address VARCHAR(150) NOT NULL,
	neighborhood VARCHAR(100) NOT NULL,
	cep VARCHAR(8) NOT NULL,
	city INT(11) NOT NULL,

	FOREIGN KEY(city) REFERENCES city(id)
);

CREATE TABLE job (

	ID INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
	admission_date date NOT NULL,
	cbo INT(11) NOT NULL,
	initial_salary REAL NOT NULL,
	payment VARCHAR(20) NOT NULL,

	FOREIGN KEY(cbo) REFERENCES cbo(id)
);

CREATE TABLE baking_data (

	ID INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
	bank INT(11) NOT NULL,
	agency VARCHAR(10) NOT NULL,
	account VARCHAR(10) NOT NULL, 

	FOREIGN KEY(bank) REFERENCES bank(id)
);

CREATE TABLE guarantee_fund (

	ID INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
	option_date date NOT NULL,
	retraction_date date,
	depositary_bank int (11) NOT NULL,

	FOREIGN KEY(depositary_bank) REFERENCES bank(id)
);

CREATE TABLE social_integration (

	ID INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,

	dadastre_date DATE NOT NULL,
	cadastre_number VARCHAR(10) NOT NULL,
	baking_data INT(11) NOT NULL,

	FOREIGN KEY(baking_data) REFERENCES baking_data(id)
);

CREATE TABLE dependents (

	ID INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
	employee INT(11) NOT NULL,
	name VARCHAR(150),
	relationship VARCHAR(50),
	birth_wedding_date DATE,

	FOREIGN KEY(employee) REFERENCES employee(id)
);