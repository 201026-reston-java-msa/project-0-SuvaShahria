CREATE TABLE roles(
	role_id 		SERIAL PRIMARY KEY,
	role_name	VARCHAR(25) NOT NULL UNIQUE
);
CREATE TABLE users(
	user_id 		SERIAL PRIMARY KEY,
	username		VARCHAR(25) NOT NULL UNIQUE,	
	pass_word			VARCHAR(200),
	first_name		VARCHAR(25) NOT NULL,
	last_name		VARCHAR(25) NOT NULL,
	email			VARCHAR(100) NOT NULL UNIQUE,
	role_id_users		INTEGER REFERENCES roles(role_id)
);
INSERT INTO roles (role_name)
VALUES ('admin'),('employee'),('customer');

INSERT INTO users (first_name,last_name,username,pass_word,email,role_id_users)
VALUES 	('a','a','a','a','a@gmail.com',1),
		('e','e','e','e','e@gmail.com',2),
        ('c','c','c','c','c@gmail.com',3);

select * from roles
select * from users