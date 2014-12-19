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

ALTER TABLE suppliers ADD material_certificate bool AFTER fical_classification;
ALTER TABLE suppliers ADD justificative VARCHAR(350) AFTER material_certificate;
ALTER TABLE suppliers ADD phone VARCHAR(10) AFTER justificative;
ALTER TABLE suppliers ADD cep VARCHAR(8) AFTER phone;
ALTER TABLE suppliers ADD expireCertificationDate DATE AFTER cep;