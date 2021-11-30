package ca.bookstore3005.project.models;

import org.springframework.data.annotation.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * View data object used to contruct region table.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Region {
  
  @Id
  private String address_street_postal;
  private String province;
  private String city;

}