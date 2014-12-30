-- checking database and drop when exist
drop database if exists bawl_db;

create database bawl_db; 

-- use bawl_db by default
use bawl_db;                  

-- create tables
create table roles_tbl (
  role_id	TINYINT AUTO_INCREMENT NOT NULL, 
  role_name VARCHAR(20) NOT NULL,
  PRIMARY KEY (role_id)
);

create table statuses_tbl (
  status_id	TINYINT AUTO_INCREMENT NOT NULL, 
  status_name VARCHAR(30) NOT NULL,
  PRIMARY KEY (status_id)  
);

create table users_tbl (
  user_id	TINYINT AUTO_INCREMENT NOT NULL, 
  user_name VARCHAR(30) NOT NULL, 
  user_email VARCHAR(60) NOT NULL, 
  user_login VARCHAR(60) NOT NULL, 
  user_password VARCHAR(60) NOT NULL, 
  user_avatar VARCHAR(60), 
  role_id TINYINT NOT NULL,
  PRIMARY KEY (user_id),
  FOREIGN KEY (role_id) REFERENCES roles_tbl(role_id)
);

create table categories_tbl (
  category_id TINYINT AUTO_INCREMENT NOT NULL, 
  category_name VARCHAR(30) NOT NULL,
  PRIMARY KEY (category_id)  
);

create table priorities_tbl (
  priority_id TINYINT AUTO_INCREMENT NOT NULL, 
  priority_name VARCHAR(30) NOT NULL,
  PRIMARY KEY (priority_id)  
);

create table problems_tbl (
  problem_id  TINYINT AUTO_INCREMENT NOT NULL, 
  problem_name VARCHAR(30) NOT NULL, 
  category_id TINYINT NOT NULL, 
  problem_description text NOT NULL, 
  problem_map_pointer VARCHAR(20) NOT NULL, 
  problem_attachments VARCHAR(256), 
  priority_id TINYINT NOT NULL,
  PRIMARY KEY (problem_id),
  FOREIGN KEY (category_id) REFERENCES categories_tbl(category_id),
  FOREIGN KEY (priority_id) REFERENCES priorities_tbl(priority_id)
);  

create table history_tbl (
  history_id	TINYINT AUTO_INCREMENT NOT NULL, 
  problem_id TINYINT NOT NULL, 
  history_date DATETIME NOT NULL, 
  status_id TINYINT NOT NULL, 
  user_id TINYINT NOT NULL,
  PRIMARY KEY (history_id),
  FOREIGN KEY (problem_id) REFERENCES problems_tbl(problem_id),
  FOREIGN KEY (status_id) REFERENCES statuses_tbl(status_id),
  FOREIGN KEY (user_id) REFERENCES users_tbl(user_id)
);


  

-- insert any data to tables
insert into roles_tbl(role_name) 
values ('admin'),('manager'),('user');

insert into statuses_tbl(status_name)
values ('new'),('viewed'),('finished');

insert into users_tbl(user_name, user_email, user_login, user_password, role_id)
values ('Mary', 'mary@email.com', 'mary', 'mary2015', '3'), ('John', 'john@email.com', 'john', 'john2015', '3'), ('Stacey', 'stacey@email.com', 'stacey', 'stacey2015', '3');

insert into categories_tbl(category_name) 
values ('Thefts'),('Fires'),('Murders');

insert into priorities_tbl(priority_name) 
values ('high'),('medium'),('low');

insert into problems_tbl(problem_name, category_id, problem_description, problem_map_pointer, priority_id)
values ('Car theft', '1', 'Car theft by 3 oclock beside with', '3030', '3'), ('Fire', '2', 'Fire near with', '4080', '2'), ('Murder', '3', 'Murder in the region beside with', '9020', '1');  

insert into history_tbl(problem_id, history_date, status_id, user_id)
values ('1', '2014-12-25', '1', '1'), ('2', '2014-12-26', '2', '2'), ('3', '2014-12-27', '3', '3');





commit;