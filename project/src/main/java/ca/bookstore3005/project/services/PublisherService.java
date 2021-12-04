package ca.bookstore3005.project.services;



import java.util.List;

import org.springframework.stereotype.Service;
import ca.bookstore3005.project.models.Publisher;
import ca.bookstore3005.project.repositories.PublisherRepository;
import ca.bookstore3005.project.models.BankAccount;
import ca.bookstore3005.project.models.Book;
import ca.bookstore3005.project.repositories.BankAccountRepository;


@Service
public class PublisherService {

    PublisherRepository publisherRepository;
    private BankAccountRepository bankAccountRepository;
    private BookService bookService;

    PublisherService(BankAccountRepository bankAccountRepository, BookService bookService, PublisherRepository publisherRepository) {
        this.bankAccountRepository = bankAccountRepository;
        this.bookService = bookService;
        this.publisherRepository = publisherRepository;
    }

    /**
     * Retrieves all Publishers from the database via the repository class
     * Formats the data into Publisher objects
     * @return the combined list of Publisher objects
     */
    public List<Publisher> getAllPublishers() {
      return publisherRepository.findAllPublishers();
    }

    /**
     * Retrieves all Publishers from the database via the repository class
     * Formats the data into Publisher objects
     * @return the combined list of Publisher objects
     */
    public BankAccount getPublisherBankAccount(String publisherName) {
        return bankAccountRepository.findBankAccountByName(publisherName);
    }

    /**
     * Function to increase the amount in the bank account of publishers based on a given list of books
     * 
     * @param booksInOrder List of ISBNs of books in the new order
     */
    public void increaseSales(List<String> booksInOrder) {
        List<Book> books = bookService.getBooksByISBN(booksInOrder);

        // For each book, calculate the cutback to the publisher and add it onto the publisher's bank account amount
        for (int i=0; i<books.size(); i++) {
            // Calculate publisher cut
            float bookPrice = books.get(i).getPrice();
            float cutbackPercent = books.get(i).getPercent_to_publisher();
            double publisherCut = Math.round(100.00 * (bookPrice * cutbackPercent)) / 100.00;

            // Get publisher bank number
            String publisherName = books.get(i).getPublisher_name();

            // Increase sales for specific publisher by name
            bankAccountRepository.increaseSalesByName(publisherName, publisherCut);
        }
    }
}
