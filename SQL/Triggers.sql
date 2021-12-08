/* TRIGGER for restocking books after going under a certain threshold */
/* TRIGGER FUNCTION DEFINITION */
CREATE OR REPLACE FUNCTION restock_book() RETURNS trigger AS
   $
   BEGIN
        IF NEW.stock < 10 THEN
            UPDATE book SET stock = stock + 15 WHERE isbn = NEW.isbn;
        END IF;
        
        RETURN NEW;
    END;$
    LANGUAGE plpgsql;

/* TRIGGER STATEMENT */
CREATE TRIGGER check_stock_levels AFTER UPDATE 
   ON book FOR EACH ROW 
   EXECUTE PROCEDURE restock_book();