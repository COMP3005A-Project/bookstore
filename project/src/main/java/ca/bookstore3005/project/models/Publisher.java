package ca.bookstore3005.project.models;

import org.springframework.data.annotation.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * View data object used to contruct publisher table.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Publisher {

  @Id
  private String name; 
  private String phone;
  private int bank_number;
  private String email;    
  private String address_street_num;
  private String address_street_name;
  private String address_street_postal;
}

