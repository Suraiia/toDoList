//creates the database toDoList
create database toDoList;

// adds the table 'status'
create table status(
     id int primary key auto_increment,
     name varchar(50));

// adds the table 'category'
create table category
     (id int primary key auto_increment,
     name varchar(50),
     hexValue char(7));

// adds the table 'task'
Create table task(
    id int auto_increment,
     name varchar(100),
     createdAt date,
     planned boolean,
     plannedOn date,
     statusId int,
     categoryId int,
     primary key(id),
     foreign key (statusId) references status(id),
     foreign key (categoryId) references category(id));