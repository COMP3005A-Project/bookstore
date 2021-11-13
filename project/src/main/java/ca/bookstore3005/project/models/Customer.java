package ca.bookstore3005.project.models;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * View data object used to contruct book table.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
    private String email;
    private String password;
    private String name;      
    private String phone;
    private String address_street_num;
    private String address_street_name;
    private String address_street_postalString;
}
