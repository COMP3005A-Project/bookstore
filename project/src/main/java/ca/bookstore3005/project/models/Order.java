package ca.bookstore3005.project.models;

import java.sql.Timestamp;

import org.springframework.data.annotation.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * View data object used to contruct order table.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
  
  @Id
  private String orderId;
  private String shippingId;
  private String email;
  private Timestamp date;
  private String address_street_num;
  private String address_street_name;
  private String address_street_postal;
  private String city;
  private String province;
}