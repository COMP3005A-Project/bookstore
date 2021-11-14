package ca.bookstore3005.project.models;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * View data object used to contruct bank_account table.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankAccount {

    @Id
    private int bank_number;
    private BigDecimal amount;
}
