package ca.bookstore3005.project.models;

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
}