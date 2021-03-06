package ca.bookstore3005.project.repositories;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ca.bookstore3005.project.models.BankAccount;

@Repository
public interface BankAccountRepository extends CrudRepository<BankAccount, String> {

    @Query("SELECT * FROM bank_account WHERE bank_number = :bank_number")
    BankAccount findBankAccount(@Param("bank_number") long bank_number);

    @Query("SELECT bank_number, amount, debt_amount FROM publisher JOIN bank_account USING(bank_number) WHERE publisher.name = :publisherName")
    BankAccount findBankAccountByName(@Param("publisherName") String publisherName);

    @Query("SELECT * FROM bank_account")
    List<BankAccount> findAllBankAccounts();

    @Modifying
    @Query("UPDATE bank_account SET amount = amount + :incr_amt FROM publisher WHERE publisher.name = :name and publisher.bank_number = bank_account.bank_number")
    void increaseSalesByName(@Param("name") String name, @Param("incr_amt") double incr_amt);

    @Modifying
    @Query("UPDATE bank_account SET amount = amount + :incr_amt WHERE bank_number = :bank_number")
    void increaseSalesByNumber(@Param("bank_number") long bank_number, @Param("incr_amt") double incr_amt);
  
}
