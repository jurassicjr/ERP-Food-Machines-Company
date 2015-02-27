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
	FOREIGN KEY(banking_data) REFERENCES banking_data(id),
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

CREATE TABLE technical_standard (

	id INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
	technical_standard VARCHAR(200)	NOT NULL
);

CREATE TABLE technical_standard_version (

	id INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
	file VARCHAR(300) NOT NULL, 
	date_update DATE NOT NULL,
	technical_standard INT(11) NOT NULL,

	FOREIGN KEY(technical_standard) REFERENCES technical_standard(id)
);

CREATE TABLE user (

	id INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
	employee INT(11) NOT NULL,
	password VARCHAR(32) NOT NULL,

	FOREIGN KEY employee REFERENCES employee(id)
);

CREATE TABLE debt (

	id INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
	debt VARCHAR(100) NOT NULL,
	debtor VARCHAR(100)  NOT NULL,
	observation TEXT NOT NULL,
	date DATE NOT NULL,
	paid INT(11) NOT NULL,
	value REAL NOT NULL
);

CREATE TABLE compost_product (

	id INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
	product VARCHAR(200),
	description TEXT
);

CREATE TABLE material_relationship (

	id INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
	product INT(11) NOT NULL,
	material INT(11) NOT NULL,

	FOREIGN KEY (product) REFERENCES compost_product(id),
	FOREIGN KEY (material) REFERENCES Product(id)
);

CREATE TABLE production (

	id INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
	production VARCHAR(100) NOT NULL
);

CREATE TABLE stage (

	id INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
	production INT(11) NOT NULL,
	stage VARCHAR(100) NOT NULL,
	finalized INT(11) NOT NULL,
	finalized_date DATE,

	FOREIGN KEY (production) REFERENCES production(id)
);

CREATE TABLE serial (
	id INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
	serial VARCHAR(40) NOT NULL,
	cpu VARCHAR(20)
);

CREATE TABLE permission (
	id INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
	user INT(11) NOT NULL,
	permission VARCHAR(20) NOT NULL,

	FOREIGN KEY(user) REFERENCES user(id)
);

CREATE TABLE bill_group (

	id INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
	cod VARCHAR(10) NOT NULL,
	group_name VARCHAR(50) NOT NULL

);

CREATE TABLE bill_subgroup (

	id INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
	cod VARCHAR(10) NOT NULL,
	subgroup VARCHAR(50) NOT NULL,
	group INT NOT NULL,

	FOREIGN KEY (group) REFERENCES bill_group(id)

);

CREATE TABLE bill_name (

	id INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
	cod VARCHAR(10) NOT NULL,
	bill_name VARCHAR(50) NOT NULL,
	subgroup INT NOT NULL,

	FOREIGN KEY (subgroup) REFERENCES bill_subgroup(id)

);

CREATE TABLE bill (

	id INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
	value double NOT NULL,
	pay_date DATE NOT NULL,
	observation TEXT,
	creditor VARCHAR(100) NOT NULL,

);