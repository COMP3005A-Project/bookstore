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
	values ('Publisher 1', 6131234567, 1234567891, 'publisher1@gmail.com', 89, 'Topoly Dr.', 'L1P9V5');

insert into publisher (name, phone, bank_number, email, address_street_num, address_street_name, address_street_postal)
	values ('Publisher 2', 6131234568, 1234567892, 'publisher2@gmail.com', 64, 'Hammer Cres.', 'J5G9T1');

insert into publisher (name, phone, bank_number, email, address_street_num, address_street_name, address_street_postal)
	values ('Publisher 3', 6131234569, 1234567893, 'publisher3@gmail.com', 12, 'Ogilive', 'V9W7D9');


/* Inserting books */
insert into book (ISBN, publisher_name, stock, title, author, year, price, num_pages, percent_to_publisher, genre)
	values ('1234567891234', 'Publisher 1', 3,   'Title 1', 'Author 1', 2000, 12.99, 123, 0.1050, 'Horror');

insert into book (ISBN, publisher_name, stock, title, author, year, price, num_pages, percent_to_publisher, genre)
	values ('1234567891235', 'Publisher 1', 1,   'Title 2', 'Author 2', 2012, 16.99, 250, 0.1550, 'Fiction');

insert into book (ISBN, publisher_name, stock, title, author, year, price, num_pages, percent_to_publisher, genre)
	values ('1234567891236', 'Publisher 2', 6,   'Title 3', 'Author 3', 1990, 34.99, 189, 0.0570, 'Sci-Fi');

insert into book (ISBN, publisher_name, stock, title, author, year, price, num_pages, percent_to_publisher, genre)
	values ('1234567891237', 'Publisher 3', 13,   'Title 4', 'Author 4', 2020, 125.99, 1123, 0.2123, 'Non-Fiction');


/* Inserting into region */
insert into region (address_street_postal, city, province)
	values ('K1J6S5', 'Ottawa', "ON");
	
insert into region (address_street_postal, city, province)
	values ('K1J6Y4', 'Toronto', "ON");


/* Inserting into user */
insert into customer (email, password, name, address_street_num, address_street_name, address_street_postal, phone, card_number, admin)
	values ('test.pilot@gmail.com', 'gaben12', 'Gaben', '123', 'Sesame Street', 'K1J6S5', '6137371111', '123456789012', false);

insert into customer (email, password, name, address_street_num, address_street_name, address_street_postal, phone, admin)
	values ('admin@gmail.com', 'gaben12', 'Owner', '123', 'Sesame Street', 'K1J6Y4', '6138981111', true);






