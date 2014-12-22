CREATE TABLE  supplier_product_association(
	id int(11) NOT NULL AUTO_INCREMENT,
    product int(11) NOT NULL, 
    supplier int(11) NOT NULL,
    
    PRIMARY KEY(id),
    FOREIGN KEY(supplier) REFERENCES suppliers(id),
    FOREIGN KEY(product) REFERENCES Product(id)
);
