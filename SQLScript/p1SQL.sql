CREATE TABLE users (
	user_id serial PRIMARY KEY,
	user_username varchar (50) UNIQUE NOT NULL,
	user_password varchar (50) NOT NULL,
	user_first_name varchar (100),
	user_last_name varchar (100),
	user_email varchar (150) UNIQUE NOT NULL,
	user_role_id int REFERENCES roles (role_id)
);

INSERT INTO users (user_username, user_password, user_first_name, 
	user_last_name, user_email, user_role_id)
	VALUES 
	('user1', 'password', 'John', 'Doe', 'John@email.com', 2),
	('user2', 'password', 'Jane', 'Doe', 'Jane@email.com', 2),
	('admin', 'password', 'Jeff', 'Bezos', 'Jeff@email.com', 1);

CREATE TABLE roles (
	role_id serial PRIMARY KEY,
	user_role varchar(10)
);

CREATE TABLE reimb_statuses (
	reimb_status_id serial PRIMARY KEY,
	reimb_status varchar(10)
);

CREATE TABLE reimb_types (
	reimb_type_id serial PRIMARY KEY,
	reimb_type varchar(10)
);

CREATE TABLE reimbursements (
	reimb_id serial PRIMARY KEY,
	reimb_amt NUMERIC(10,2),
	reimb_submitted timestamp,
	reimb_author int REFERENCES users (user_id),
	reimb_status_id int REFERENCES reimb_statuses (reimb_status_id),
	reimb_type_id int REFERENCES reimb_types (reimb_type_id),
	reimb_description TEXT
);

INSERT INTO roles (user_role) VALUES 
	('Manager'),
	('Employee');

INSERT INTO reimb_statuses (reimb_status) VALUES 
	('Unresolved'),
	('Approved'),
	('Denied');

INSERT INTO reimb_types (reimb_type) VALUES 
	('Lodging'),
	('Travel'),
	('Food'),
	('Other');

SELECT * FROM roles;
SELECT * FROM reimb_statuses;
SELECT * FROM reimb_types;
SELECT * FROM users;
SELECT * FROM reimbursements;
	
