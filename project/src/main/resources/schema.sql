create table if not exists customer
  (
     email                 varchar(30) not null,
     password              varchar(30) not null,
     name                  varchar(30) not null,
     phone                 varchar(10),
     address_street_num    varchar(10),
     address_street_name   varchar(255),
     address_street_postal varchar(10),
     city                  varchar(30),
     province              varchar(2),
     card_number           varchar(13),
     admin                 boolean,
     primary key (email)
  );

create table if not exists bank_account
  (
     bank_number numeric(10, 0) not null,
     amount      numeric(38,2) not null,
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
     city                  varchar(30),
     province              varchar(2),
     primary key (order_id),
     foreign key (email) references customer
  );

create table if not exists publisher
  (
     name                  varchar(30) not null,
     phone                 numeric(10, 0),
     bank_number           numeric(10, 0) not null,
     email                 varchar(30) not null,
     address_street_num    varchar(30),
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