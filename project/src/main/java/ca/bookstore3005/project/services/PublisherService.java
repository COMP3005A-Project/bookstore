package ca.bookstore3005.project.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;
import ca.bookstore3005.project.models.Publisher;
import ca.bookstore3005.project.repositories.PublisherRepository;

@Service
public class PublisherService {

    PublisherRepository publisherRepository;

    PublisherService(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }


    /**
   * Retrieves all Publishers from the database via the repository class
   * Formats the data into Publisher objects
   * @return the combined list of Publisher objects
   */
  public List<Publisher> getAllPublishers() {
    return publisherRepository.findAllPublishers();
    //List<Publisher> arr = StreamSupport.stream(publisherRepository.findAll().spliterator(), false).collect(Collectors.toList());
    
    //return arr;
  }
}
