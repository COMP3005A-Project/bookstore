/* ALL bank_account RELATION RELATED QUERIES */
/* Note: All vars with preceeding ':' char are input variables from the backend of the implemenation (ex. ':bank_number' is a value passed in by the backend) */

/* return bank account info via bank_number */
SELECT * FROM bank_account WHERE bank_number = :bank_number;

/* return bank account info via publisher_name tied to bank_number */
SELECT bank_number, amount, debt_amount FROM publisher JOIN bank_account USING(bank_number) WHERE publisher.name = :publisherName;

/* return all bank_accounts */
SELECT * FROM bank_account;

/* increase amount in bank_account via the publisher_name tied to bank_number */
UPDATE bank_account SET amount = amount + :incr_amt FROM publisher WHERE publisher.name = :name and publisher.bank_number = bank_account.bank_number;

/* increase amount in bank_account via the bank_number */
UPDATE bank_account SET amount = amount + :incr_amt WHERE bank_number = :bank_number;



/* ALL book RELATION RELATED QUERIES */
/* Note: All vars with preceeding ':' char are input variables from the backend of the implemenation (ex. ':bank_number' is a value passed in by the backend) */

/* return book info via isbn */
SELECT * FROM book WHERE isbn = :isbn;

/* return all books */
SELECT * FROM book;

/* decrease book stock by one for book with given isbn*/
UPDATE book SET stock = stock - 1 WHERE isbn = :isbn;

/* remove book via isbn */
DELETE FROM book WHERE isbn = :isbn;

/* insert new book into table with all values specified */
INSERT INTO Book values(:isbn, :title, :publisher_name, :stock, :author, :year, :price, :num_pages, :percent_to_publisher, :genre);





/* ALL customer RELATION RELATED QUERIES */
/* Note: All vars with preceeding ':' char are input variables from the backend of the implemenation (ex. ':bank_number' is a value passed in by the backend) */

/* return customer info via email and password */
SELECT * FROM Customer where email = :email AND password = :password;

/* return customer info via email */
SELECT * FROM Customer where email = :email;

/* insert new customer into table with all values specified */
INSERT INTO Customer values(:email, :password, :name, :phone, :address_street_num, :address_street_name, :address_street_postal, :card_number, :admin);





/* ALL order RELATION RELATED QUERIES */
/* Note: All vars with preceeding ':' char are input variables from the backend of the implemenation (ex. ':bank_number' is a value passed in by the backend) */

/* return order info via order_id */
SELECT * FROM book_order join region using(address_street_postal) WHERE order_id = :order_id;

/* return order info via shipping_id */
SELECT * FROM book_order join region using(address_street_postal) WHERE shipping_id = :shipping_id;

/* return all orders */
SELECT * FROM book_order join region using(address_street_postal);

/* return all order info for specific user with given email */
SELECT * FROM book_order join region using(address_street_postal) WHERE email = :email;

/* return all books associated to order via the order_id*/
SELECT isbn, title, author, year, price, stock FROM books_in_order JOIN book using(isbn) WHERE order_id = :order_id;

/* add new order to table, with all values specified and return the newly created order_id after */
INSERT INTO book_order(email, date, shipping_id) values(:email, :date, :shipping_id) returning order_id;

/* add new book to order via the order_id provided */
INSERT INTO books_in_order(order_id, isbn, amount) values(:order_id, :isbn, 1);





/* ALL publisher RELATION RELATED QUERIES */
/* Note: All vars with preceeding ':' char are input variables from the backend of the implemenation (ex. ':bank_number' is a value passed in by the backend) */

/* return all publishers */
SELECT * FROM publisher;

/* return bank_number based on publisher name */
SELECT bank_number FROM publisher WHERE name = :name;

/* return all publishers bank numbers*/
SELECT bank_number FROM publisher;

/* return bank_number of publisher with given name */
SELECT bank_number FROM publisher WHERE name = :name;




/* ALL region RELATION RELATED QUERIES */
/* Note: All vars with preceeding ':' char are input variables from the backend of the implemenation (ex. ':bank_number' is a value passed in by the backend) */

/* add new region entry with all values specified */
INSERT INTO region values(:address_street_postal, :city, :province) ON CONFLICT DO NOTHING;





/* ALL report RELATED QUERIES */
/* Note: All vars with preceeding ':' char are input variables from the backend of the implemenation (ex. ':bank_number' is a value passed in by the backend) */
/* Makes use of functions for retrieving report data */

/* return report information for the authors vs sales report using the sales_per_author SQL function */
SELECT * FROM sales_per_author(:publisherName);

/* return report information for the genres vs sales report using the sales_per_genre SQL function */
SELECT * FROM sales_per_genre(:publisherName);