SET REFERENTIAL_INTEGRITY FALSE;

DROP TABLE IF EXISTS customers;

CREATE TABLE customers (
  `code` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  address varchar(45) DEFAULT NULL,
  phone_1 varchar(45) DEFAULT NULL,
  phone_2 varchar(45) DEFAULT NULL,
  credit_limit double DEFAULT NULL,
  current_credit double DEFAULT NULL,
  PRIMARY KEY (`code`)
);


DROP TABLE IF EXISTS products;

CREATE TABLE products (
  `code` int(11) NOT NULL AUTO_INCREMENT,
  description varchar(45) DEFAULT NULL,
  price double DEFAULT NULL,
  quantity int(11) DEFAULT NULL,
  PRIMARY KEY (`code`)
);


DROP TABLE IF EXISTS sales_orders;

CREATE TABLE sales_orders (
  order_number int(11) NOT NULL AUTO_INCREMENT,
  customer int(11) NOT NULL,
  total_price double DEFAULT NULL,
  PRIMARY KEY (order_number),
  KEY fk_sales_orders_customers_idx (customer),
  CONSTRAINT fk_sales_orders_customers FOREIGN KEY (customer) REFERENCES customers (`code`) ON DELETE NO ACTION ON UPDATE NO ACTION
);


DROP TABLE IF EXISTS order_lines;

CREATE TABLE order_lines (
  `code` int(11) NOT NULL AUTO_INCREMENT,
  order_number int(11) DEFAULT NULL,
  product int(11) DEFAULT NULL,
  quantity int(11) DEFAULT NULL,
  PRIMARY KEY (`code`),
  KEY fk_order_lines_sales_orders1_idx (order_number),
  KEY fk_order_lines_products1_idx (product),
  CONSTRAINT fk_order_lines_products1 FOREIGN KEY (product) REFERENCES products (`code`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT fk_order_lines_sales_orders1 FOREIGN KEY (order_number) REFERENCES sales_orders (order_number) ON DELETE NO ACTION ON UPDATE NO ACTION
);

SET REFERENTIAL_INTEGRITY TRUE;
