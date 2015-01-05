-- checking database and drop when exist
drop database if exists bawl;

create database bawl; 

-- use bawl by default
use bawl;                  

-- create tables
create table roles (
  role_id	TINYINT AUTO_INCREMENT NOT NULL, 
  role_name VARCHAR(20) NOT NULL,
  PRIMARY KEY (role_id)
);

create table statuses (
  status_id	TINYINT AUTO_INCREMENT NOT NULL, 
  status_name VARCHAR(30) NOT NULL,
  PRIMARY KEY (status_id)  
);

create table users (
  id	TINYINT AUTO_INCREMENT NOT NULL, 
  name VARCHAR(30) NOT NULL, 
  email VARCHAR(60) NOT NULL, 
  login VARCHAR(60) NOT NULL, 
  password VARCHAR(60) NOT NULL, 
  avatar_url VARCHAR(60), 
  role_id TINYINT NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (role_id) REFERENCES roles(role_id)
);

create table categories (
  category_id TINYINT AUTO_INCREMENT NOT NULL, 
  category_name VARCHAR(30) NOT NULL,
  PRIMARY KEY (category_id)  
);

create table priorities (
  priority_id TINYINT AUTO_INCREMENT NOT NULL, 
  priority_name VARCHAR(30) NOT NULL,
  PRIMARY KEY (priority_id)  
);

create table problems (
  id  TINYINT AUTO_INCREMENT NOT NULL, 
  name VARCHAR(30) NOT NULL, 
  category_id TINYINT NOT NULL, 
  description text NOT NULL, 
  map_pointer VARCHAR(20) NOT NULL, 
  attachments VARCHAR(256), 
  priority_id TINYINT NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (category_id) REFERENCES categories(category_id),
  FOREIGN KEY (priority_id) REFERENCES priorities(priority_id)
);  

create table history (
  history_id	TINYINT AUTO_INCREMENT NOT NULL, 
  problem_id TINYINT NOT NULL, 
  history_date DATETIME NOT NULL, 
  status_id TINYINT NOT NULL, 
  id TINYINT NOT NULL,
  PRIMARY KEY (history_id),
  FOREIGN KEY (problem_id) REFERENCES problems(problem_id),
  FOREIGN KEY (status_id) REFERENCES statuses(status_id),
  FOREIGN KEY (id) REFERENCES users(id)
);


  

-- insert any data to tables
insert into roles(role_name) 
values ('admin'),('manager'),('user');

insert into statuses(status_name)
values ('new'),('viewed'),('finished');

insert into users(name, email, login, password, role_id)
values ('Mary', 'mary@email.com', 'mary', 'mary2015', '3'), ('John', 'john@email.com', 'john', 'john2015', '3'), ('Stacey', 'stacey@email.com', 'stacey', 'stacey2015', '3');

insert into categories(category_name) 
values ('Thefts'),('Fires'),('Murders');

insert into priorities(priority_name) 
values ('high'),('medium'),('low');

insert into problems(name, category_id, description, map_pointer, priority_id)
values ('Car theft', '1', 'Car theft by 3 oclock beside with', '3030', '3'), ('Fire', '2', 'Fire near with', '4080', '2'), ('Murder', '3', 'Murder in the region beside with', '9020', '1');  

insert into history(problem_id, history_date, status_id, id)
values ('1', '2014-12-25', '1', '1'), ('2', '2014-12-26', '2', '2'), ('3', '2014-12-27', '3', '3');


commit;
