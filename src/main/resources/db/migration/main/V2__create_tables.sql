CREATE TABLE tbl_bank (
    account_number VARCHAR(100) NOT NULL,
	trust DECIMAL(10,2) NOT NULL ,
	transaction_fee INT NOT NULL ,

	PRIMARY KEY (account_number)
);
