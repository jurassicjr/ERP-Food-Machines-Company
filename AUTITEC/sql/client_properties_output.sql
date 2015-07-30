CREATE TABLE IF NOT EXISTS client_properties_output(

id INT(11) NOT NULL AUTO_INCREMENT,

motive VARCHAR(255) NOT NULL,

exit_date DATE NOT NULL,

exit_fiscal_note VARCHAR(255),

client_property INT(11) NOT NULL,



PRIMARY KEY(id),
FOREIGN KEY(client_property) REFERENCES client_properties(id)

);