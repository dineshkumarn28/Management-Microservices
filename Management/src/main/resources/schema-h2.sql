CREATE TABLE Order_Service(cust_id INT PRIMARY KEY AUTO_INCREMENT,
    cust_Name VARCHAR(255), cust_total INT,cust_Shipping VARCHAR(255), cust_date DATE);
CREATE TABLE Order_Items(product_id INT PRIMARY KEY AUTO_INCREMENT,product_code VARCHAR(25),product_name VARCHAR(25),
    quantity INT, FOREIGN KEY (cust_id) REFERENCES Order_Service(cust_id));
