package ca.bookstore3005.project.models;

import org.springframework.data.annotation.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * View data object used to contruct book_order table.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookOrder {
    @Id
    private int order_id;
    private String email;
    private int shipping_id;
}
