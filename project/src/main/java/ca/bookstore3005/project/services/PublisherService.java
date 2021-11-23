package ca.bookstore3005.project.services;

import java.util.List;

import org.springframework.stereotype.Service;
import ca.bookstore3005.project.models.Book;
import ca.bookstore3005.project.repositories.BankAccountRepository;
import ca.bookstore3005.project.repositories.BookRepository;
import ca.bookstore3005.project.repositories.PublisherRepository;

@Service
public class PublisherService {

    private BankAccountRepository bankAccountRepository;
    private BookService bookService;

    PublisherService(BankAccountRepository bankAccountRepository, BookService bookService) {
        this.bankAccountRepository = bankAccountRepository;
        this.bookService = bookService;
    }

    /**
     * Function to increase the amount in the bank account of publishers based on a given list of books
     * 
     * @param booksInOrder List of ISBNs of books in the new order
     */
    public void increaseSales(List<String> booksInOrder) {
        List<Book> books = bookService.getBooksByISBN(booksInOrder);

        for (int i=0; i<books.size(); i++) {
            // Calculate publisher cut
            double publisherCut = Math.round(100.00 * books.get(i).getPrice() * books.get(i).getPercent_to_publisher()) / 100.00;

            // Increase sales for specific publisher
            bankAccountRepository.increaseSales(books.get(i).getPublisher(), publisherCut);

        }
    }
}
