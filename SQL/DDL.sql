CREATE TABLE customer
  (
     email                 varchar(30) not null,
     password              varchar(30) not null,
     name                  varchar(30) not null,
     phone                 numeric(10, 0),
     address_street_num    varchar(30),
     address_street_name   varchar(30),
     address_street_postal varchar(30),
     primary key (email)
  );

CREATE TABLE bank_account
  (
     bank_number numeric(10, 0) not null,
     amount      int not null,
     primary key (bank_number)
  );

CREATE TABLE book_order
  (
     order_id    numeric(10, 0) not null,
     email       varchar(30) not null,
     shipping_id numeric(10, 0) not null,
     primary key (order_id),
     foreign key (email) references customer
  );

CREATE TABLE publisher
  (
     NAME                  varchar(30) not null,
     phone                 numeric(10, 0),
     bank_number           numeric(10, 0) not null,
     email                 varchar(30) not null,
     address_street_num    varchar(30),
     address_street_name   varchar(30),
     address_street_postal varchar(30),
     primary key (NAME),
     foreign key (bank_number) references bank_account
  );

CREATE TABLE book
  (
     isbn                 numeric(13, 0) not null,
     title                varchar(30),
     publisher_name       varchar(30),
     stock                int,
     author               varchar(30),
     year                 numeric(4, 0),
     price                numeric(5, 2) not null,
     num_pages            int,
     percent_to_publisher numeric(4, 2) not null,
     primary key (isbn),
     foreign key (publisher_name) references publisher
  );

CREATE TABLE books_in_order
  (
     order_id numeric(10, 0) not null,
     isbn     numeric(13, 0) not null,
     amount   int not null,
     primary key (order_id, isbn),
     foreign key (order_id) references book_order,
     foreign key (isbn) references book
  ); 