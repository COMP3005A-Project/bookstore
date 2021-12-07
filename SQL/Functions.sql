/* SQL FUNCTIONS FOR REPORTS*/

/* Sales vs Author */
CREATE OR REPLACE FUNCTION sales_per_author(pname varchar) 
    RETURNS table ( category varchar,
                    total numeric(8,2))
    AS
      $
        BEGIN
            return query
                select author, sum(price * percent_to_publisher)
                from (select *
                      from books_in_order join book using(isbn)
                      where publisher_name = pname) as ordered_books
                group by author;
    END;$
    LANGUAGE plpgsql;

/* Sales vs Genre */
CREATE OR REPLACE FUNCTION sales_per_genre(pname varchar) 
    RETURNS table ( category varchar,
                    total numeric(8,2))
    AS
      $ 
        BEGIN
            return query
                select genre, sum(price * percent_to_publisher)
                from (select *
                      from books_in_order join book using(isbn)
                      where publisher_name = pname) as ordered_books
                group by genre;
    END;$
    LANGUAGE plpgsql;