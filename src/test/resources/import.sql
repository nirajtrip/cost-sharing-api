insert into Member(MemberId, FullName, EmailId, phoneNumber) values (1, 'Member-1', 'Member1@test.com', '212-101-1234');
insert into Member(MemberId, FullName, EmailId, phoneNumber) values (2, 'Member-2', 'Member2@test.com', '408-201-9876');
insert into Category(CategoryId, Category) values (1, 'TripExpenses');
insert into Category(CategoryId, Category) values (2, 'PizzaParty');

insert into Role(Id, Name) values (1, 'ADMIN', '1');
insert into Role(Id, Name) values (1, 'USER' , '2,3');
insert into users(Id, username, password, passwordConfirm) values (1, 'testuser1', 'adminuser', 'adminuser');
insert into users(Id, username, password, passwordConfirm) values (2, 'testuser2', 'testuser', 'testuser');
insert into users(Id, username, password, passwordConfirm) values (3, 'testuser3', 'testuser', 'testuser');
