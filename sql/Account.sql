CREATE TABLE account_status(
	status_id 	SERIAL PRIMARY KEY,
	acc_status		VARCHAR(25) NOT NULL UNIQUE
);

CREATE TABLE account_types(
	type_id 	SERIAL PRIMARY KEY,
	acc_type	VARCHAR(25) NOT NULL UNIQUE
);

CREATE TABLE accounts (
	account_id			SERIAL PRIMARY KEY,
	user_id_fk			INTEGER REFERENCES users(user_id),
	balance				NUMERIC(10,2) DEFAULT 0,
	status_id_fk	INTEGER REFERENCES account_status(status_id),	
	type_id_fk		INTEGER REFERENCES account_types(type_id)
);
INSERT INTO account_status(acc_status)
VALUES		('PENDING'),
			('OPEN'),
			('CLOSED'),
			('DENIED');
INSERT INTO account_types(acc_type)
VALUES		('CHECKING'),
			('SAVINGS');
			
select * from account_status 
select * from account_types 
select * from accounts 