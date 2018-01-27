/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

ATTACH "[SQLITEDB-HOME]\blog.db" AS blog;

/**
 * Author:  cedric
 * Created: 20 janv. 2018
 */
create table blog.user (
	id INTEGER PRIMARY KEY,
	name TEXT NOT NULL,
	email TEXT NOT NULL,
	password TEXT NOT NULL,
        CONSTRAINT email_unique UNIQUE (email)
) ;

create table blog.post (
	id INTEGER PRIMARY KEY,
	user_id INTEGER NOT NULL,
	title TEXT NOT NULL,
	body TEXT NOT NULL,
	date TEXT NOT NULL,
	FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE RESTRICT
) ;

/*Needed for the tests*/
insert into user (id, name, email, password) values
    (1, 'u1', 'u1@domain.se', '123'),
    (2, 'test', 'test@domain.io', '123');

insert into post (user_id, title, body, date) values
    (1, 'test', 'Hello world', DATETIME('now'));