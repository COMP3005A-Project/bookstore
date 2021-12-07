delete from books_in_order;
delete from book;
delete from publisher;
delete from book_order;
delete from bank_account;
delete from customer;
delete from region;


/* Inserting bank_accounts */
insert into bank_account (bank_number, amount, debt_amount)
	values (1234567891, 890000, 43000);

insert into bank_account (bank_number, amount, debt_amount)
	values (1234567892, 1000000, 68000);

insert into bank_account (bank_number, amount, debt_amount)
	values (1234567893, 25500, 36000);


/* Inserting publishers */
insert into publisher (name, phone, bank_number, email, address_street_num, address_street_name, address_street_postal)
	values ('Pearson', 1300473277, 1234567891, 'pearson@gmail.com', 330, 'Hudson', 'L1P9V5');

insert into publisher (name, phone, bank_number, email, address_street_num, address_street_name, address_street_postal)
	values ('Bloomsbury Publishing', 6131234568, 1234567892, 'onlinesales@bloomsburyprofessional.com', 64, 'Hammer Cres.', 'J5G9T1');

insert into publisher (name, phone, bank_number, email, address_street_num, address_street_name, address_street_postal)
	values ('Scholastic Corp.', 18002683860, 1234567893, 'scholastic@gmail.com', 175, 'Hillmount Rd.', 'L6C1Z7');


/* Inserting books */
insert into book (ISBN, publisher_name, stock, title, author, year, price, num_pages, percent_to_publisher, genre)
	values ('9780439784542', 'Bloomsbury Publishing', 32,   'Half-Blood Prince', 'J.K. Rowling', 2005, 18.99, 607, 0.1050, 'Fantasy');

insert into book (ISBN, publisher_name, stock, title, author, year, price, num_pages, percent_to_publisher, genre)
	values ('9780545010221', 'Bloomsbury Publishing', 10,   'Deathly Hallows', 'J.K. Rowling', 2007, 18.99, 607, 0.1550, 'Fantasy');

insert into book (ISBN, publisher_name, stock, title, author, year, price, num_pages, percent_to_publisher, genre)
	values ('9780137464401', 'Pearson', 16,   'Code That Fits in Your Head', 'Mark Seemann', 2021, 34.99, 189, 0.0570, 'Education');

insert into book (ISBN, publisher_name, stock, title, author, year, price, num_pages, percent_to_publisher, genre)
	values ('9781459814325', 'Pearson', 11,   'The Magic Boat', 'Katherine Farris & Kit Pearson', 2019, 8.99, 12, 0.0570, 'Fiction');

insert into book (ISBN, publisher_name, stock, title, author, year, price, num_pages, percent_to_publisher, genre)
	values ('9780439023498', 'Scholastic Corp.', 40,   'Catching Fire', 'Suzzane Collins', 2009, 15.99, 391, 0.2123, 'Adventure');

insert into book (ISBN, publisher_name, stock, title, author, year, price, num_pages, percent_to_publisher, genre)
	values ('9780545917148', 'Scholastic Corp.', 25,   'The Bad Guys', 'Aaron Blabey', 2015, 11.99, 144, 0.2123, 'Fiction');


/* Inserting into region */
insert into region (address_street_postal, city, province)
	values ('K1J6S5', 'Ottawa', 'ON');
	
insert into region (address_street_postal, city, province)
	values ('K1J6Y4', 'Toronto', 'ON');


/* Inserting into user */
insert into customer (email, password, name, address_street_num, address_street_name, address_street_postal, phone, card_number, admin)
	values ('test.pilot@gmail.com', 'gaben12', 'Gaben', '123', 'Sesame Street', 'K1J6S5', '6137371111', '123456789012', false);

insert into customer (email, password, name, address_street_num, address_street_name, address_street_postal, phone, admin)
	values ('admin@gmail.com', 'gaben12', 'Owner', '123', 'Sesame Street', 'K1J6Y4', '6138981111', true);






