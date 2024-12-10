# Library-Management-System
## Overview

The library management system is a simple application designed to manage

the library books and Staff. 

The library has a GUI to make things easy.

The library has a database to store the users, books, and staff and ensure

the security of the data.
## Features
- Graphical-User-Interface (GUI)
- Book management
	- add a book and remove a book
- Staff management
	- add a Staff and remove a staff
## Database
### Scope
- The purpose of this database is to
### Functional Requirements
- A user can query for a book/staff and add or remove a book/staff.
#### Entities
For this database, I've chosen the following entities and their attributes:
##### 1- users
- id (primary key, integer)
- username (not null, string)
- password (not null, string)
##### 2- staff
- id (primary key, integer)
- staff_name (string)
##### 3-books
- id (primary key, integer)
- book_name (string)
- author_name (string)
- copies (integer)

For this database, I used the types:
- integer: to represent numbers such as id and copies.
- string: to represent names such as user name, staff name, book name, author name, and password (as it may include characters and digits).

And also used constraints:
- primary key: for the id to be unique for each row in the table.
- not null: for name and password to avoid wrong login.
#### Optimizations
- indexing: for username, password, staff name, book name, and author name to speed up these queries.
## Technologies Used
- Java as a programming language.
- Java swing for the GUI.
- MySQL for the database.
