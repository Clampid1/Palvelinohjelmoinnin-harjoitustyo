If you start the database from scratch;

The program needs 4 different statuses to be typed from the h2-console;
using the username: sa and password "";

The statuses are:
  -id 1, 'Waiting' : insert into status (id, status) values (1, 'Waiting')
  -id 2, 'Doing' : insert into status (id, status) values (2, 'Doing')
  -id 3, 'Reviewing' : insert into status (id, status) values (3, 'Reviewing')
  -id 4, 'Complete' : insert into status (id, status) values (4, 'Complete')

The program also needs a user account. You can register using postman or other REST based program to send a message to
/register/user with a JSON message.
{
  username: "{your chosen username}"
  password: "{your chosen password}"
}

for example:
{
  username: "user"
  password: "password"
}

Otherwise everything else works on the program.
