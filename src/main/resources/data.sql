-- data.sql
insert into Role(Id, Name) values (1, 'ADMIN');
insert into Role(Id, Name) values (2, 'USER' );
insert into Users(Id, username, password, passwordConfirm) values (1, 'testuser1', 'adminuser', 'adminuser');
insert into Users(Id, username, password, passwordConfirm) values (2, 'testuser2', 'testuser', 'testuser');
insert into Users(Id, username, password, passwordConfirm) values (3, 'testuser3', 'testuser', 'testuser');
