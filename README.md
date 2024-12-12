# Library-Management-System
## Overview
The library management system is a simple application for managing library books and users.  
The library has a GUI to make things easy.  
The library offers some privilages for admin users such as see all users, delete a user and show all transactions.  
The library has a database to store users and books and ensure data security.  
## Features
- Graphical-User-Interface (GUI)
- Book management
	- show avilable books, borrow a book and return a book.
- user management
	- sign up a new user.
- admin privilages
	- add a new book, show all users show all transactions and delete a user.
## Database
### Scope
- The purpose of this database is to manage and speed up the operations on the library.
### Functional Requirements
- A user can see the books, borrow a book and return a book.
#### Entities
For this database, I've chosen the following entities and their attributes:
##### 1- user
- id (primary key, auto_increment, integer)
- username (not null, string)
- password (not null, string)
- type (not null enum('admin', 'normal'))
##### 2-book
- id (primary key, auto_increment, integer)
- ISBN (not null, string)
- book_name (not null, string)
- author_name (string)
- publication_year (year)
- copies (not null, integer)
##### 3-borrowed_books
- user_id (primary key, auto_increment, integer)
- username (not null, string)
- book_ISBN (not null, string)
- status (not null, enum('borrowed', 'returned'))
##### For this database, I used the types:
- integer: to represent the whole numbers such as id and copies.
- string: to represent names such as username, book_name, and password (as it may include characters and digits).
##### And also used constraints:
- primary key: for the id to be unique for each row in the table.
- auto_increment: to automaticly give the new row a unique number specifically it increments the number by one for every new row.
- not null: for username, password and type to avoid wrong login and for ISBN, book_name and copies to identify the book, etc.
#### Relationships
![ER Diagram](images/ER%20Diagram.png)  
- The relationship between user and book is that user can borrow zero or more books and user can return zero or more books.
- The relationship between book and user is that a book can be borrowed by one user and a book can be returned by one user. 
#### Optimizations
- indexing: for username, password, type, ISBN, etc. to speed up these queries.
## Technologies Used
- Java as a programming language.
- Java swing for the GUI.
- MySQL for the database.