package ca.bookstore3005.project.models;

import org.springframework.data.annotation.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * View data object used to contruct customer table.
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
    private String address_street_postal;
    private String city;
    private String province;
    private String card_number;
    private Boolean admin;
}
