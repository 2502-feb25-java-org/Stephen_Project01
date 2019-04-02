create database Expense_Reimbursement_Db
go

create table Reimbursement_status(
reimb_status_id int identity(1,1) not null primary key,
reimb_status varchar(10) not null 
);

create table Reimbursement_type(
reimb_type_id int identity(1,1) not null primary key,
reimb_type varchar(20) not null 
);
--alter table Reimbursement_type
--alter column reimb_type varchar(20)

create table User_roles(
user_role_id int identity(1,1) not null primary key,
user_role varchar(20) not null 
);

create table Users(
ERS_users_id int identity(1,1) not null primary key,
ERS_username varchar(50) not null unique,
ERS_password varchar(50) not null,
user_firstname varchar(100) not null,
user_lastname varchar(100) not null,
user_email  varchar(150) not null unique,
user_role_id int not null,
constraint FK_User_Roles foreign key(user_role_id)
references User_roles(user_role_id)
);



create table reimbursement(
reimb_id int identity(1,1) not null primary key,
reimb_amount int not null,
reimb_submitted date not null,
reimb_resolved date,
reimb_description varchar(250),
reimb_recipt nvarchar(MAX),
ERS_users_id int not null,
reimb_resolver int not null,
reimb_status int not null,
reimb_type int not null,
constraint FK_author foreign key (ERS_users_id)
references Users(ERS_users_id),

constraint FK_resolver foreign key (reimb_resolver)
references Users(ERS_users_id),

constraint FK_reimbursement_status foreign key (reimb_status)
references Reimbursement_status(reimb_status_id),

constraint FK_reimbursemet_type foreign key (reimb_type)
references Reimbursement_type(reimb_type_id)
);


--dbcc checkident ('User_roles', reseed,0); -- checks the identity value, then reseeds it
dbcc checkident('reimbursement', reseed, 0);

insert into User_roles(user_role)
values('Finance_Manager'),
('Employee');

insert into Reimbursement_status(reimb_status)
values('PENDING'),
('APPROVED'),
('DENIED');

insert into Reimbursement_type(reimb_type)
values('Lodging'),
('Food'),
('Travel'),
('Certification'),
('Medical');

insert into Users(ERS_username, ERS_password, user_firstname, user_lastname, user_email, user_role_id)
values('apple', '123', 'Bob', 'Rogan', 'bobr@gmail.com', 2),
('pear', '123', 'Jack', 'Daniels', 'JackDaniels@gmail.com',1);

insert into reimbursement(reimb_amount, reimb_submitted, reimb_description, reimb_type, reimb_status, ERS_users_id)
values('100', CURRENT_TIMESTAMP, 'I need food.', '2', '1','2'),
('100', CURRENT_TIMESTAMP, 'I need Health.', '5', '1','2');

select * from user_roles
select * from Reimbursement_status
select * from Users
select * from Reimbursement_type
select * from reimbursement


