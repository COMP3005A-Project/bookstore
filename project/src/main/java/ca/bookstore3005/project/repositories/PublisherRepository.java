package ca.bookstore3005.project.repositories;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ca.bookstore3005.project.models.Publisher;

@Repository
public interface PublisherRepository extends CrudRepository<Publisher, String> {

    @Query("SELECT * FROM publisher")
    List<Publisher> findAllPublishers();

    @Query("SELECT * FROM bank_account WHERE name = :name")
    Publisher findPublisher(@Param("name") String name);

    @Query("SELECT bank_number FROM publisher WHERE name = :name")
    Long findPublisherBankNum(@Param("name") String name);

    @Query("SELECT bank_number FROM publisher")
    List<String> findAllBankNumbers();

    @Query("SELECT bank_number FROM publisher WHERE name = :name")
    long findBankNumber(@Param("name") String name);
}
