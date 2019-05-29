-- data.sql
insert into ROLE(Id, Name) values (1, 'ADMIN');
insert into ROLE(Id, Name) values (2, 'USER' );
insert into Users(Id, username, password) values (1, 'testuser1', 'adminuser');
insert into Users(Id, username, password) values (2, 'testuser2', 'testuser');
insert into Users(Id, username, password) values (3, 'testuser3', 'testuser');

insert into Category(CategoryId, Category) values (1, 'PartyExpense-Category');
insert into Category(CategoryId, Category) values (2, 'TravelExpense-Category' );

insert into Member(MemberId, FullName, PhoneNumber, EmailId)
  values (1, 'Ann Taylor', '408-105-1234', 'Aann.taylor@test.com');
insert into Member(MemberId, FullName, PhoneNumber, EmailId)
  values (2, 'John Doe', '212-201-9876', 'john.doe@test.com');

insert into Expense(ExpenseId, ExpenseName, Amount, CreatedDate, Comments, MemberId, CategoryId)
  values (1, 'PizzaPartyExpense-May28', 19.69, PARSEDATETIME('2019-05-19', 'yyyy-MM-dd'), 'Testing purpose', 2, 1);
insert into Expense(ExpenseId, ExpenseName, Amount, CreatedDate, Comments, MemberId, CategoryId)
  values (2, 'Party on May27', 129.75, PARSEDATETIME('2019-05-26', 'yyyy-MM-dd'), 'Expense borne by John Doe', 2, 1);

