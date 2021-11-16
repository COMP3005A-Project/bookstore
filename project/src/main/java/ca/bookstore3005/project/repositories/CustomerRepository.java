package ca.bookstore3005.project.repositories;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ca.bookstore3005.project.models.Customer;

public interface CustomerRepository extends CrudRepository<Customer, String>{
    
  @Query("SELECT * FROM Customer where email = :email AND password = :password")
  Customer findByCreds(@Param("email") String email,
                       @Param("password") String password);

  @Query("SELECT * FROM Customer where email = :email")
  Customer findByEmail(@Param("email") String email);

  @Query("INSERT INTO Customer values(:email, :password)")
  void addCustomer(@Param("email") String email,
                   @Param("password") String password,
                   @Param("phone") String phone,
                   @Param("address_street_num") String address_street_num,
                   @Param("address_street_name") String address_street_name,
                   @Param("address_street_postal") String address_street_postal);

}
