CREATE TABLE IF NOT EXISTS client_properties(


id INT(11) NOT NULL AUTO_INCREMENT,
entry_date DATE NOT NULL,

exit_date DATE,

fiscal_note VARCHAR(255) NOT NULL,


PRIMARY KEY(id)

);