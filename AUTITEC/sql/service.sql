CREATE TABLE IF NOT EXISTS service(

id INT(11) NOT NULL AUTO_INCREMENT,
name VARCHAR(255) NOT NULL,
meal double NOT NULL,
stay_price VARCHAR(255) NOT NULL,
price_I_I_I DOUBLE NOT NULL,
price_I_I_II DOUBLE NOT NULL,
price_I_II_I DOUBLE NOT NULL,
price_I_II_II DOUBLE NOT NULL,
price_I_III_I DOUBLE NOT NULL,
price_I_III_II DOUBLE NOT NULL,
price_II_I_I  DOUBLE NOT NULL,
price_II_I_II DOUBLE NOT NULL,
price_II_II_I DOUBLE NOT NULL,
price_II_II_II DOUBLE NOT NULL,
price_II_III_I DOUBLE NOT NULL,
price_II_III_II DOUBLE NOT NULL,
price_per_km DOUBLE NOT NULL,

PRIMARY KEY(id)
);