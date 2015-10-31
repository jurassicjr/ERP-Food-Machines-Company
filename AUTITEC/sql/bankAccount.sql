CREATE TABLE IF NOT EXISTS bank_account(
    
    id INT(11) NOT NULL AUTO_INCREMENT,
    agency VARCHAR(255) NOT NULL,
    account VARCHAR(255) NOT NULL,
    safe_account VARCHAR(255),
    inicial_value  DOUBLE NOT NULL,
    bank INT(11) NOT NULL,
    
    PRIMARY KEY(id),
    FOREIGN KEY(bank) REFERENCES bank(id)
    )