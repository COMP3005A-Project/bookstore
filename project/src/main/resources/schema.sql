create table if not exists region
  (
     address_street_postal varchar(10) not null,
     city                  varchar(30),
     province              varchar(2),
     primary key (address_street_postal)
  );

create table if not exists customer
  (
     email                 varchar(30) not null,
     password              varchar(30) not null,
     name                  varchar(30) not null,
     phone                 varchar(10),
     address_street_num    varchar(10),
     address_street_name   varchar(255),
     address_street_postal varchar(10),
     card_number           varchar(13),
     admin                 boolean,
     primary key (email),
     foreign key (address_street_postal) references region
  );

create table if not exists bank_account
  (
     bank_number numeric(10, 0) not null,
     amount      numeric(38,2) not null,
     debt_amount numeric(38,2) not null,
     primary key (bank_number)
  );

create table if not exists book_order
  (
     order_id    serial unique,
     email       varchar(30) not null,
     shipping_id varchar(12),
     date        timestamp not null,
     address_street_num    varchar(10),
     address_street_name   varchar(255),
     address_street_postal varchar(10),
     primary key (order_id),
     foreign key (email) references customer,
     foreign key (address_street_postal) references region
  );

create table if not exists publisher
  (
     name                  varchar(30) not null,
     phone                 numeric(10),
     bank_number           numeric(10) not null,
     email                 varchar(30) not null,
     address_street_num    varchar(10),
     address_street_name   varchar(255),
     address_street_postal varchar(10),
     primary key (name),
     foreign key (bank_number) references bank_account
  );

create table if not exists book
  (
     isbn                 varchar(13) not null,
     title                varchar(30),
     publisher_name       varchar(30),
     stock                int,
     author               varchar(30),
     year                 numeric(4, 0),
     price                numeric(5, 2) not null,
     num_pages            int,
     percent_to_publisher numeric(4, 4) not null,
     genre                varchar(30),
     primary key (isbn),
     foreign key (publisher_name) references publisher
  );

create table if not exists books_in_order
  (
     order_id int not null,
     isbn     varchar(13) not null,
     amount   numeric(38,2) not null,
     primary key (order_id, isbn),
     foreign key (order_id) references book_order,
     foreign key (isbn) references book on delete cascade
  );  

/* SQL TRIGGERS FOR RESTOCK */
/* DROP TRIGGER AND LOAD FRESH ONE*/
DROP TRIGGER IF EXISTS check_stock_levels ON book;

/* TRIGGER FUNCTION DEFINITION */
CREATE OR REPLACE FUNCTION restock_book() RETURNS trigger AS
   '
   BEGIN
        IF NEW.stock < 10 THEN
            UPDATE book SET stock = stock + 15 WHERE isbn = NEW.isbn;
        END IF;
        
        RETURN NEW;
    END;'
    LANGUAGE plpgsql;

/* TRIGGER STATEMENT */
CREATE TRIGGER check_stock_levels AFTER UPDATE 
   ON book FOR EACH ROW 
   EXECUTE PROCEDURE restock_book();

/* SQL FUNCTIONS FOR REPORTS*/
/* Sales/Author */
CREATE OR REPLACE FUNCTION sales_per_author(pname varchar) 
    RETURNS table ( category varchar,
                    total numeric(8,2))
    AS
      '
        BEGIN
            return query
                select author, sum(price * percent_to_publisher)
                from (select *
                      from books_in_order join book using(isbn)
                      where publisher_name = pname) as ordered_books
                group by author;
    END;'
    LANGUAGE plpgsql;

/* Sales/Genre */
CREATE OR REPLACE FUNCTION sales_per_genre(pname varchar) 
    RETURNS table ( category varchar,
                    total numeric(8,2))
    AS
      ' 
        BEGIN
            return query
                select genre, sum(price * percent_to_publisher)
                from (select *
                      from books_in_order join book using(isbn)
                      where publisher_name = pname) as ordered_books
                group by genre;
    END;'
    LANGUAGE plpgsql;