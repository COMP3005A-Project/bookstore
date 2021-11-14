package ca.bookstore3005.project.models;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * View data object used to contruct books_in_order table.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BooksInOrder {
    @Id
    private int order_id;
    private int ISBN;
    private BigDecimal shipping_id;    
}
